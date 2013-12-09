<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="headerscripts">
    	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				
				$('#checkinlist').dataTable({
					"aaSorting": [[0, 'asc']]
					
				});
			} );
		</script>
    </jsp:attribute>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        
    
	<form action="/library/checkin/search" method="post">
		<span style="color:#333" align="left"><h2>Check in Books </h2></span>
		<table style="padding-bottom:10px;margin-bottom:35px;" bgcolor="#eeffee">
			<tr>
				<td class="rightpadding">Card No:<br />
					<input id='cardNo' name='cardNo' type="text" value="${cardNo}" />
				</td>
				<td class="rightpadding">
					Book Id :<br />
					<input id='bookId' name="bookId" type="text" value="${bookId}" />
				</td>
				<td class="rightpadding">Borrower Last Name:<br />
				<input id='borrowerLastName' name='borrowerLastName' type="text"
						value="${borrowerLastName}" /></td>
			
				
				<td class="rightpadding">Borrower First Name:<br />
				<input id='borrowerFirstName' name='borrowerFirstName' type="text"
						value="${borrowerFirstName}" /></td>
			
				<td style="padding-top:10px"><input id='submit' value='Check for Books'
						type="submit" class="orangebutton" /></td>
			</tr>
		</table>
	</form>
	<form action="/library/checkin/submit" method="post">
		<table id="checkinlist">
			<c:choose>
				<c:when test="${checkInBooks.size() > 0}">
				<thead>
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
					</thead>
					<tbody>
					<c:forEach items="${checkInBooks}" var="checkInBook">
						<tr>
							<td><input type="checkbox"
										value="${checkInBook.cardNo}#${checkInBook.bookId}#${checkInBook.branchId}#${checkInBook.title}"
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
					</tbody>
				</c:when>
				<c:when test="${checkInBooks.size() == 0}">
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


</jsp:body>
</t:genericpage>

