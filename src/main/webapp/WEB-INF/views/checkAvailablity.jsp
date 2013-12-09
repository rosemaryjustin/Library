
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
		<span style="color:#333" align="left"><h2>Check Availability of Books</h2></span> 
		<table style="padding-bottom:10px;margin-bottom:35px;" bgcolor="#eeffee">
			<tr style="border-bottom: 1px solid #98FB98;">
				<td class="rightpaddingbig">Book Id :<br/><input id='bookId' name="bookId" type="text"
					value="${bookId}" /></td>
				<td class="rightpaddingbig">Title :<br/><input id='title' name='title' type="text" value="${title}" /></td>
				<td class="rightpaddingbig">Author Name:<br/><input id='authorName' name='authorName' type="text"
					value="${authorName}" /></td>
			
				<td style="padding-top:10px"><input id='submit'
					value='Check Availablity' type="submit" class="orangebutton" /></td>
			</tr>
		</table>
		<table id="bookstable">
			<c:choose>
				<c:when test="${availableBooks.size() > 0}">
				<thead>
					<tr>
						<th width="20%">Book ID&nbsp;</th>
						<th width="20%">Title&nbsp;</th>
						<th width="20%">Branch ID&nbsp;</th>
						<th width="20%">Total Number Of Copies&nbsp;</th>
						<th width="20%">Available Copies&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${availableBooks}" var="availableBook">
						<tr>
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
