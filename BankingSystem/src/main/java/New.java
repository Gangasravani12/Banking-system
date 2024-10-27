import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class New
 */
@WebServlet("/New")
public class New extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New() {
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
		float amount=Float.parseFloat(request.getParameter("amt"));
		String address=request.getParameter("addr");
		String mobile_num=request.getParameter("mno");
		String status="active";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/7amdb","root","root");
			PreparedStatement ps=con.prepareStatement("insert into accountInfo values(?,?,?,?,?,?,?)");
			ps.setInt(1,acc_num);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setFloat(4, amount);
			ps.setString(5, address);
			ps.setString(6, mobile_num);
			ps.setString(7, status);
			int i=ps.executeUpdate();
			if(i>0) {
				out.print("Account Created Successfully");
			}
			else {
				out.print("Recheck the details you provided! Try again........");
			}
			con.close();
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
