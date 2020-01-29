package com.util.happylife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Date;
/**
 * Servlet implementation class bookappointment
 */
 
@WebServlet(urlPatterns = "/bookappointment",initParams= {
		@WebInitParam(name="driver",value = "com.mysql.jdbc.Driver"),
		@WebInitParam(name="url",value = "jdbc:mysql://localhost:3306/happylife"),
		@WebInitParam(name="uname",value = "root"),
		@WebInitParam(name="pwd",value = "sudhamsh")}
)
public class bookappointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookappointment() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		int dno = (int)session.getAttribute("dno");
		String time = request.getParameter("time");
		String d = request.getParameter("date"); 
		 
		//Calendar c = Calendar.getInstance();
		 
		// DateFormat format2=new SimpleDateFormat(); 
	//	 String day=format2.format(date);
		// String cdate = (String)date;
	//	 d = d+day; 
     	SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy");
     	  Date dt1=null;
		try {
			dt1 = format1.parse(d);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	  DateFormat format2=new SimpleDateFormat("EEEE"); 
     	  String Day=format2.format(dt1);
		 response.setContentType("text/html");
		 if(time.equals("fn"))
		 {
			 d+="-"+Day+"- 9:00 AM";
		 }
		 else
		 {
			 d+="-"+Day+" 4:00 PM";
		 }
		 PrintWriter out = response.getWriter();
		 try
		 {
			 Statement st = con.createStatement();
			// System.out.println("insert into patients(dno,ptest,preport,dadvicestatus,techreportstatus)values('"+dno+"','"+"null','null','false','false','"+d+"'");
			 st.executeUpdate("insert into patients(dno,appointmentdate)values('"+dno+"','"+d+"')");
			 
			 out.println("<html><body>");
			 out.println("<a href=\"doctorsearch.html\">back</a>");
			 out.println("<br><br><br>");
			 out.println("<h2 align=\"center\">Your Appointment booked  !!!! </h2>");
			 out.println("<br>"+"<h3>Booked on : <h3>"+d);
			 out.println("</body></html>");
			 
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		
	}

}
