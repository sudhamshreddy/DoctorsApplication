<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException"%>;
<%@ page import="java.io.PrintWriter"%>;
<%@ page import="java.util.Properties"%>;
<%@ page import="javax.mail.Folder"%>;
<%@ page import="javax.mail.Message"%>;
<%@ page import="javax.mail.MessagingException"%>;
<%@ page import="javax.mail.NoSuchProviderException"%>;
<%@ page import="javax.mail.PasswordAuthentication"%>;
<%@ page import="javax.mail.Session"%>;
<%@ page import="javax.mail.Store"%>;
<html>
<head>
<title>Inbox using JavaMail & JSP</title>
</head>
<body>

	<%!String email;
	String password;
	String storetype;
	String host;
	String port;

	public void jspInit() {
		host = "smtp.gmail.com";
		port = "993";
	}%>
	<%
		email = request.getParameter("email");
		password = request.getParameter("password");
		storetype = request.getParameter("storetype");
		try {
			// 1) get the session object
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "imaps");
			//properties.put("mail.store.protocol", "pop3s");

			Session emailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, password);
				}
			});
			// 2) create the POP3 store object and connect with the pop server
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", email, password);

			/* Store emailStore = emailSession.getStore("pop3s");
			emailStore.connect("pop.gmail.com", email, password); */

			// 3) create the folder object and open it
			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
	%>
	<TABLE WIDTH="100%">
		<CAPTION>
			<B>Showing Inbox of <%=email%>
		</CAPTION>
		<TR>
			<TH>Email#</TH>
			<TH>Subject</TH>
			<TH>From</TH>
			<TH>Text</TH>
		</TR>
		<%
			// 4) retrieve the messages from the folder in an array and print it
				Message[] messages = emailFolder.getMessages();
				for (int i = 0; i < messages.length; i++) {
					Message message = messages[i];
		%>
		<TR>
			<TD><%=(i + 1)%></TD>
			<TD><%=message.getSubject()%></TD>
			<TD><%=message.getFrom()[0]%></TD>
			<TD><%=message.getContent().toString()%></TD>
			<TD><A HREF="">Reply</A></TD>
			<TD><A HREF="">Forward</A></TD>
		</TR>
		<%
			}
		%>
	</TABLE>

	<%
		// 5) close the store and folder objects
			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	%>