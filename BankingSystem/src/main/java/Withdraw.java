import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		double withdraw_amount=Double.parseDouble(request.getParameter("withamount"));
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/7amdb","root","root");
			PreparedStatement ps1=con.prepareStatement("select * from accountInfo where acc_num=? and name=? and password=?");
			ps1.setInt(1,acc_num);
			ps1.setString(2, name);
			ps1.setString(3, password);
			ResultSet rs=ps1.executeQuery();
			double amount=0.0;
			if(rs.next()) {
				amount=rs.getDouble(4);
			}
			out.print("Before Withdrawal my account balance is "+amount+"<br>");
			out.print("MY withdrawal amount is "+withdraw_amount+"<br>");
			if(amount>=withdraw_amount) {
				amount=amount-withdraw_amount;
				PreparedStatement ps2=con.prepareStatement("update accountInfo set amount=? where acc_num=?");
				ps2.setDouble(1, amount);
				ps2.setInt(2, acc_num);
				int i=ps2.executeUpdate();
				out.print("After withdraw my account balance is "+amount);
			}
			else {
				out.print("Don't have sufficent amount to the withdraw");
			}
			con.close();
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
