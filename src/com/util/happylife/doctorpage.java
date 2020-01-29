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
 * Servlet implementation class doctorpage
 */
 
@WebServlet(urlPatterns="/doctorpage", initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")
		
})
public class doctorpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
       		 System.out.println(e);
       	 }
   	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select count(*) from patients where dno=");
	}

}
