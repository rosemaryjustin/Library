<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>CheckOut</title>
</head>
<body>
	<form action="/library/checkout/submit" method="post">
		<h1>Check out BOOKS !!!</h1>
		<table>
			<tr>
				<td colspan="2">
					${statusOfCheckout}
				</td>
			</tr>
			<tr>
				<td>Book Id :</td>
				<td><input id='bookId' name="bookId" type="text" /></td>
			</tr>
			<tr>
				<td>Branch Id :</td>
				<td><input id='branchId' name='branchId' type="text" /></td>
			</tr>
			<tr>
				<td>Card No :</td>
				<td><input id='cardNo' name='cardNo' type="text" /></td>
			</tr>
			<tr>
				<td colspan="2"><input id='submit' value='Check Out'
					type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
