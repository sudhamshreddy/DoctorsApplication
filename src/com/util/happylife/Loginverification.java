package com.util.happylife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginverification
 */
@WebServlet(urlPatterns="/Loginverification", initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")
		
}
		)
public class Loginverification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
	 
    public Loginverification() {
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
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String formname =request.getParameter("form");
	//	System.out.println(formname);
		try
		{
			
			 Statement st = con.createStatement();
		  ResultSet rs =null;
		//  System.out.println("try started");
		  if( formname.equals("patient"))
		  {
			//  System.out.println("222");
			  
			   rs = st.executeQuery("select upass from userdetails where uemail='"+email+"'");
		//   System.out.println(rs.getString(1));
		  }
		  else if(formname.equals("doctor"))
		  {  // Statement st = con.createStatement();
			   rs = st.executeQuery("select dpassword from doctordetails where demail='"+email+"'");
		  }
		  else if(formname.equals("labtech"))
		  {   // Statement st = con.createStatement();
			   rs = st.executeQuery("select labpassword from labtechdetails where labemail='"+email+"'");
		  }
		  String pwd=null;
		  if(formname.equals("patient"))
		  {    while (rs.next())
			  {
			  pwd = rs.getString(1);
			  }
		  }
		  else  if(  formname.equals("doctor"))
		  {   while (rs.next())
		     {
			  pwd = rs.getString(1);
		     }
		  }
		  else  if(formname.equals("labtech"))
		  {   while (rs.next())
		     {
			  pwd = rs.getString("upass");
		     }
		  }
			 
	//	System.out.println(pwd);
			 
			 
			if(password.equals(pwd))
			{  HttpSession session = request.getSession();
		        session.setAttribute("umail",email);
				response.sendRedirect("doctorsearch.html");
			}
			else
			{    PrintWriter out = response.getWriter();
			     response.setContentType("text/html");
			     out.println("<html><body>");
			     
			     RequestDispatcher rd=null;
			     if(formname.equals("patient"))
				  {    
			    	  rd = request.getRequestDispatcher("patientLogin.html");
			    	  rd.include(request,response);
			    	  out.println("Invalid email address or password !!!");
			    	   
				  }
				  else if(formname.equals("doctor"))
				  {
					   rd = request.getRequestDispatcher("doctorloginPage.html");
					   rd.include(request,response);
					   out.println("Invalid email address or password !!!");
					   System.out.println("included");
				  }
				  else if(formname == "labtech")
				  {
					   rd = request.getRequestDispatcher("labtechloginPage.html");
					   rd.forward(request,response);
					   out.println("Invalid email address or password !!!");
				  }
				  
				  
				 out.println("</html></body>");
			}
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	 
		}
		 
		
	}


