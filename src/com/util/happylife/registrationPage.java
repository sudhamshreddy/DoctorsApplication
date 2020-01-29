package com.util.happylife;


import javax.servlet.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class registrationPageservlet
 */
@WebServlet(urlPatterns="/registrationPageservlet", initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")
		
}
		)
public class registrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException
	{
		String driver = config.getInitParameter("driver");
    	String url = config.getInitParameter("url");
    	String name = config.getInitParameter("uname");
    	String pwd = config.getInitParameter("pwd");
    	 try
    	 {
    		 Class.forName(driver);
    		 con = DriverManager.getConnection(url,name,pwd);
    		 System.out.println("Connection Established");
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println("Connection not Established");
    	 }
	}
    public registrationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
	//	String loc = request.getParameter("location");
	//	System.out.println(name+","+mail+","+phone+","+password);
		try
		{
		  Statement st = con.createStatement();
		  st.executeUpdate("insert into userdetails(ufullname,uemail,umobile,upass)values('"+name+"','"+mail+"','"+phone+"','"+password+"')");
		  System.out.println("inserted");
		  RequestDispatcher rd = request.getRequestDispatcher("patientLogin.html");
		  rd.forward(request,response);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	    
	}

}
