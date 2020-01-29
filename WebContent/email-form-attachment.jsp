<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send an e-mail with attachment</title>
</head>
<body>
	<form action="SendEmailWithAttachmentServlet" method="post"
		enctype="multipart/form-data">
		<table border="0" width="60%" align="center">
			<caption>
				<h2>Send New E-mail</h2>
			</caption>
			<tr>
				<td width="50%">Recipient address</td>
				<td><input type="text" name="recipient" size="50" value="elitefs.demos@gmail.com"/></td>
			</tr>
			<tr>
				<td>Subject</td>
				<td><input type="text" name="subject" size="50" value="attachment testing"/></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><textarea rows="10" cols="39" name="content">This is a test message testing sending emails with attachments....!</textarea></td>
			</tr>
			<tr>
				<td>Attach file</td>
				<td><input type="file" name="file" size="50" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Send" /></td>
			</tr>
		</table>
	</form>
</body>
</html>