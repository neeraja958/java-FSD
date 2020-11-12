<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>Welcome  !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4> Dash Board</h4>
<a href="listall">List All</a><br>
<a href="process.jsp">Process Loan</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>