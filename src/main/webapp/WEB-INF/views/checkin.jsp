<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>CheckIn</title>
</head>
<body>
	<form action="/library/checkin/search" method="post">
		<h1>Check in BOOKS !!!</h1>
		<table>
			<tr>
				<td colspan="2">${statusOfCheckinSearch}</td>
			</tr>
			<tr>
				<td>Card No :</td>
				<td><input id='cardNo' name='cardNo' type="text"
					value="${cardNo}" /></td>
				<td>Borrower Last Name :</td>
				<td><input id='borrowerLastName' name='borrowerLastName'
					type="text" value="${borrowerLastName}" /></td>
			</tr>
			<tr>
				<td>Book Id :</td>
				<td><input id='bookId' name="bookId" type="text"
					value="${bookId}" /></td>
				<td>Borrower First Name :</td>
				<td><input id='borrowerFirstName' name='borrowerFirstName'
					type="text" value="${borrowerFirstName}" /></td>
			</tr>
			<tr>
				<td colspan="4"><input id='submit' value='Check for Books'
					type="submit" /></td>
			</tr>
		</table>
	</form>
	<form action="/library/checkin/submit" method="post">
		<table>
			<c:choose>
				<c:when test="${checkInBooks.size() > 0}">
					<tr>
						<th></th>
						<th>Card No&nbsp;</th>
						<th>Book Id&nbsp;</th>
						<th>Title &nbsp;</th>
						<th>Branch Id&nbsp;</th>
						<th>Borrower Last Name&nbsp;</th>
						<th>Borrower First Name&nbsp;</th>
						<th>Borrower Address&nbsp;</th>
					</tr>
					<c:forEach items="${checkInBooks}" var="checkInBook">
						<tr>
							<td><input type="checkbox"
								value="${checkInBook.cardNo}#${checkInBook.bookId}#${checkInBook.branchId}"
								name="checked"></td>
							<td><c:out value="${checkInBook.cardNo}" />&nbsp;</td>
							<td><c:out value="${checkInBook.bookId}" />&nbsp;</td>
							<td><c:out value="${checkInBook.title}" />&nbsp;</td>
							<td><c:out value="${checkInBook.branchId}" />&nbsp;</td>
							<td><c:out value="${checkInBook.lastName}" />&nbsp;</td>
							<td><c:out value="${checkInBook.firstName}" />&nbsp;</td>
							<td><c:out value="${checkInBook.address}" />&nbsp;</td>

						</tr>
					</c:forEach>
					<tr>
						<td colspan="4"><input id='submit' value='Check In'
							type="submit" /></td>
					</tr>

				</c:when>
				<c:when test = "${checkInBooks.size() == 0}">
					<tr>
						<td colspan="7">There are no book loans matching that description
							! Please enter valid values.</td>
					</tr>
				</c:when>
			</c:choose>
			<tr>
				<td colspan="2">${checkinStatus}</td>
			</tr>
		</table>
	</form>

</body>
</html>
