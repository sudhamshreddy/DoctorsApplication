package com.util.happylife;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import java.sql.*;
/**
 * Servlet implementation class doctorsearch
 */
@WebServlet(urlPatterns = "/doctorsearch",initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")}
)
public class doctorsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorsearch() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config)
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		 
	}
/*	protected String getDay(String s)
	{   int temp = Integer.parseInt(s);
		String p=null;
		switch(temp)
		{
		case 1: p="Sunday";
		case 2: p="Monday";
		case 3: p="Tuesday";
		case 4: p="Wednesday";
		case 5: p="Thursday";
		case 6: p="Friday";
		case 7: p="Saturday";

		}
		return p;
	}*/
	 
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		String[] days = request.getParameterValues("day");
		String spl = request.getParameter("speciality");
		String area = request.getParameter("area");
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    out.println("<html><body>");
		try
		{
			Statement st = con.createStatement();
			for(int i=0;i<days.length;i++)
			{ //System.out.println("select * from doctor where dspec='"+dept+"'and dworkday like '%"+days[i]+"%'");
			  ResultSet re= st.executeQuery("select * from doctordetails where dspeciality='"+spl+"'and dworkdays like '%"+days[i]+"%'  and darea='"+area+"'");
			  
			  while(re.next())
			  {   int dno =re.getInt("dno");
				  String name=re.getString("dname");
				  String dep =re.getString("dspeciality");
				  String day = days[i];
				  session.setAttribute("dno",dno);
				  System.out.println("displaying"+name);
				  out.println("<h3>'"+name+"'   '"+dep+"'  '"+day+"        "+"'<a href='appointment.html'>book an appointment</a>");
			  }
			  re.close();
			}
			out.println("</body></html>");
		}
			
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
