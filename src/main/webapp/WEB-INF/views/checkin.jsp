<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>CheckIn</title>
</head>
<body>
	<form action="/library/checkin/submit" method="post">
		<h1>Check in BOOKS !!!</h1>
		<table>
			<tr>
				<td colspan="2">
					${statusOfCheckin}
				</td>
			</tr>
			<tr>
				<td>Card No :</td>
				<td><input id='cardNo' name='cardNo' type="text" value="${cardNo}"/></td>
				<td>Borrower Last Name :</td>
				<td><input id='borrowerLastName' name='borrowerLastName' type="text" value="${borrowerLastName}"/></td>
			</tr>
			<tr>
				<td>Book Id :</td>
				<td><input id='bookId' name="bookId" type="text" value="${bookId}"/></td>
				<td>Borrower First Name :</td>
				<td><input id='borrowerFirstName' name='borrowerFirstName' type="text" value="${borrowerFirstName}"/></td>
			</tr>
			<tr>
				<td colspan="4"><input id='submit' value='Check In'
					type="submit" /></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${checkInBooks.size() > 0}">
				<table>
					<tr>
						<th>Card No</th>
						<th>Book Id </th>
						<th>Last Name</th>
						<th>First Name</th>
						<th>Address</th>
					</tr>
					<c:forEach items="${checkInBooks}" var="checkInBook">
						<tr>
							<td><c:out value="${checkInBook.cardNo}" /></td>
							<td><c:out value="${checkInBook.bookId}" /></td>
							<td><c:out value="${checkInBook.lastName}" /></td>
							<td><c:out value="${checkInBook.firstName}" /></td>
							<td><c:out value="${checkInBook.address}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</form>
</body>
</html>
