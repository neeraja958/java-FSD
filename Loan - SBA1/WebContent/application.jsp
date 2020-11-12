<html>
<head>
<title>Application Form</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>

</head>
<body>

<form action="placeloan" method="POST"  >
	<table border="0" cellspacing="0" cellpadding="0" width="40%" >
			<tr><td><strong>User Name</strong></td> 
			<td><em>${username}</em></td>
			</tr>
			<tr><td><strong>Loan Purpose</strong></td> 
			<td><em><input type="text" name="purpose" required /></em></td>
			</tr>
			<tr><td><strong>Loan Name</strong></td> 
			<td><em><input type="text" name="loanName" required /></em></td>
			</tr>
			<tr><td><strong>Loan Amount</strong></td> 
			<td><em><input type="number" name="amtRequested" required /></em></td>
			</tr>
			<tr><td><strong>Business Structure</strong></td> 
			<td><select name="busstructure">
					<option></option>
					<option>Individual</option>
					<option>Organization</option>
					</select></td>
			</tr>
			<tr><td><strong>Billing Indicator</strong></td> 
			<td><em><input type="text" name="billindicator" required /></em></td>
			</tr>
			<tr><td><strong>Address</strong></td> 
			<td><em><input type="text" name="address" required /></em></td>
			</tr>
			<tr><td>
				<button>Submit</button>
			<td><em></em></td>
			</tr>
	</table>
</form>

<jsp:include page="footer.jsp"/>
</body>
</html>