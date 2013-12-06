<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Add Borrower</title>
</head>
<body>
	<form action="/library/addBorrower/submit" method="post">
		<h1>Adding a Borrower !!!</h1>
		<table>
			<tr>
				<td colspan="2">
					${statusOfAddBorrower}
				</td>
			</tr>
			<tr>
				<td>Borrower Last Name:</td>
				<td><input id='borrowerLastName' name="borrowerLastName" type="text" value="${borrowerLastName}"/></td>
			</tr>
			<tr>
				<td>Borrower First Name:</td>
				<td><input id='borrowerFirstName' name='borrowerFirstName' type="text" value="${borrowerFirstName}"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input id='address' name='address' type="text" value="${address}"/></td>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td><input id='phoneNumber' name='phoneNumber' type="text" value="${phoneNumber}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input id='submit' value='Add Borrower'
					type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
