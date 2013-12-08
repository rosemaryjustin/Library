
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="headerscripts">
    	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				
				$('#bookstable').dataTable({
					"aaSorting": [[0, 'asc']]
					
				});
			} );
		</script>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

	<form action="/library/checkAvailablity/submit" method="post">
		<h1>Check Availability of BOOKS !!!</h1>
		<table >
		
			<tr >
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
		<table id="bookstable">
			<c:choose>
				<c:when test="${availableBooks.size() > 0}">
				<thead>
					<tr class="gradeA">
						<th width="20%">Book ID&nbsp;</th>
						<th width="20%">Title&nbsp;</th>
						<th width="20%">Branch ID&nbsp;</th>
						<th width="20%">Total Number Of Copies&nbsp;</th>
						<th width="20%">Available Copies&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${availableBooks}" var="availableBook">
						<tr class="odd gradeX">
							<td width="20%">&nbsp;<c:out value="${availableBook.bookId}" />&nbsp;</td>
							<td width="20%"><c:out value="${availableBook.title}" />&nbsp;&nbsp;</td>
							<td width="20%"><c:out value="${availableBook.branchId}" />&nbsp;</td>
							<td width="20%"><c:out value="${availableBook.totalCopies}" />&nbsp;</td>
							<td width="20%"><c:out
									value="${availableBook.availableCopies}" />&nbsp;</td>
						</tr>
					</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${availableBooks.size() == 0}">
					<tr>
						<td colspan="7">There are no books matching that description
							! Please enter valid values.</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
	</form>
    </jsp:body>
</t:genericpage>
