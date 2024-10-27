import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int acc_num=Integer.parseInt(request.getParameter("acc_num"));
		String name=request.getParameter("acc_name");
		String password=request.getParameter("psw");
		int trans_acc=Integer.parseInt(request.getParameter("tacc"));
		double transfer_amount=Double.parseDouble(request.getParameter("tamount"));
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/7amdb","root","root");
			PreparedStatement ps1=con.prepareStatement("select * from accountInfo where acc_num=? and name=? and password=?");
			ps1.setInt(1,acc_num);
			ps1.setString(2, name);
			ps1.setString(3, password);
			ResultSet rs1=ps1.executeQuery();
			double amount=0.0;
			PreparedStatement ps2=con.prepareStatement("select * from accountInfo where acc_num=?");
			ps2.setInt(1, trans_acc);
			ResultSet rs2=ps2.executeQuery();
			if(rs2.next()) {
				double transfergadiamount=rs2.getDouble(4);
				if(rs1.next()) {
					amount=rs1.getDouble(4);
					out.print("Before sending the money the amount is "+amount+"<br>");
					if(amount>transfer_amount) {
						amount=amount-transfer_amount;
						out.print("sending the money is "+transfer_amount+"<br>");
						out.print("After sending the money the amount is "+amount+"<br>");
						PreparedStatement ps3=con.prepareStatement("update accountInfo set amount=? where acc_num=?");
						ps3.setDouble(1, amount);
						ps3.setInt(2, acc_num);
						int i=ps3.executeUpdate();
						out.print("Before receiving the money the amount is "+transfergadiamount+"<br>");
						transfergadiamount=transfergadiamount+transfer_amount;
						PreparedStatement ps4=con.prepareStatement("update accountInfo set amount=? where acc_num=?");
						ps4.setDouble(1, transfergadiamount);
						ps4.setInt(2, trans_acc);
						ps4.executeUpdate();
						out.print("After recieving the money the amount is "+transfergadiamount+"<br>");
					}
					else {
						out.print("no sufficient money in your account");
					}
				}
				else {
					out.print("ENter correct details of the user...........");
				}
			}
			else {
				if(rs1.next()) {
					amount=rs1.getDouble(4);
					out.print("Before sending the money the amount is "+amount+"<br>");
					if(amount>transfer_amount) {
						amount=amount-transfer_amount;
						out.print("sending the money is "+transfer_amount+"<br>");
						out.print("After sending the money the amount is "+amount+"<br>");
						PreparedStatement ps3=con.prepareStatement("update accountInfo set amount=? where acc_num=?");
						ps3.setDouble(1, amount);
						ps3.setInt(2, acc_num);
						ps3.executeUpdate();
					}
					else {
						out.print("no sufficent money in your account");
					}
				}
				else {
					out.print("Enter correct details of the user.........");
				}
			}
			con.close();
			}
		catch(Exception ex) {
			out.print(ex);
		}
	}
}
