<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Fetch Inbox</title>
</head>
<body>
	<form action="showinbox.jsp" method="post">
		<table border="0" width="35%" align="center">
			<caption>
				<h2>Fetch Emails from an INBOX ....</h2>
			</caption>
			<tr>
				<td width="50%">Email</td>
				<td><input type="text" name="email"
					 /></td>
			</tr>
			<tr>
				<td>Subject</td>
				<td><input type="password" name="password"  /></td>
			</tr>
			<tr>
				<td>Store</td>
				<td><input type="radio" name="storeType" value="pop3s" />POP3S <br><input type="radio" name="storeType" value="imaps" />IMAPS
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Fetch" /></td>
			</tr>
		</table>
	</form>
</body>
</html>