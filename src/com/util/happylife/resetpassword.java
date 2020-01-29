package com.util.happylife;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class resetpassword
 */
 
@WebServlet(urlPatterns="/resetpassword", initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")
		
}
		)
public class resetpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private Connection con; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetpassword() {
        super();
        // TODO Auto-generated constructor stub
    }
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
       		 System.out.println(e);
       	 }
   	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String s=request.getParameter("otp");
		if(s.equals("100"))
		{
			
		}
	}

}
