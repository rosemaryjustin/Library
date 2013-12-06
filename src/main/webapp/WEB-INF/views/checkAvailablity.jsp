<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>CheckAvailablity</title>
</head>
<body>
	<form action="/library/checkAvailablity/submit" method="post">
		<h1>Check Availability of BOOKS !!!</h1>
		<table>
			<tr>
				<td>Book Id :</td>
				<td><input id='bookId' name="bookId" type="text"
					value="${bookId}" /></td>
				<td>Title :</td>
				<td><input id='title' name='title' type="text" value="${title}" /></td>
				<td>Author Name:</td>
				<td><input id='authorName' name='authorName' type="text"
					value="${authorName}" /></td>
			</tr>
			<tr>
				<td colspan="6" style="align: center"><input id='submit'
					value='Check Availablity' type="submit" /></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${availableBooks.size() > 0}">
				<table>
					<tr>
						<th width="20%">Book ID</th>
						<th width="20%">Title</th>
						<th width="20%">Branch ID</th>
						<th width="20%">Total Number Of Copies</th>
						<th width="20%">Available Copies</th>
					</tr>
					<c:forEach items="${availableBooks}" var="availableBook">
						<tr>
							<td width="20%"><c:out value="${availableBook.bookId}" /></td>
							<td width="20%"><c:out value="${availableBook.title}" /></td>
							<td width="20%"><c:out value="${availableBook.branchId}" /></td>
							<td width="20%"><c:out value="${availableBook.totalCopies}" /></td>
							<td width="20%"><c:out
									value="${availableBook.availableCopies}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</form>
</body>
</html>
