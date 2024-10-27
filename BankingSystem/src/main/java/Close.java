import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Close
 */
@WebServlet("/Close")
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Close() {
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/7amdb","root","root");
			PreparedStatement ps1=con.prepareStatement("select * from accountInfo where acc_num=? and name=? and password=?");
			ps1.setInt(1, acc_num);
			ps1.setString(2, name);
			ps1.setString(3, password);
			String status;
			ResultSet rs=ps1.executeQuery();
			double amount=0.0;
			if(rs.next()) {
				amount=rs.getDouble(4);
				status=rs.getString(7);
				if(amount>0) {
					out.print("<center>"+"Withdraw your money from your account before deactivate your account......... "+"</center>");
				}
				else {
					PreparedStatement ps2=con.prepareStatement("update accountInfo set status='deactivate' where acc_num=?");
					ps2.setInt(1, acc_num);
				    int i=ps2.executeUpdate();
					if(i>0) {
						out.print("<center>"+"Successfully deactivated your account......... "+"</center>");
					}
					else {
						out.print("Again check your account balance.If your account have money,please withdraw total money!");
					}
					response.sendRedirect("success.html");
				}
			}
			else {
				out.print("Check your details clearly.Please try again");
			}
			
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
