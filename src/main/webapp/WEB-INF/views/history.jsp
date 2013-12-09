
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

	<form action="/library/admin/history/submit" method="post">
		<span style="color:#333" align="left"><h2>Search checkouts history from archive</h2></span> 
		<table style="padding-bottom:10px;margin-bottom:35px;" bgcolor="#eeffee">
			<tr style="border-bottom: 1px solid #98FB98;">
				<td class="rightpaddingbig">Book Id :<br/><input id='bookId' name="bookId" type="text"
					value="${bookId}" /></td>
				<td class="rightpaddingbig">Branch Id :<br/><input id='branchId' 
				name='branchId' type="text" value="${branchId}" /></td>
				<td class="rightpaddingbig">Card No:<br/><input id='cardNo' name='cardNo' type="text"
					value="${cardNo}" /></td>
			
				<td style="padding-top:10px"><input id='submit'
					value='Search' type="submit" class="orangebutton" /></td>
			</tr>
		</table>
		<table id="bookstable">
			<c:choose>
				<c:when test="${checkinHistoryList.size() > 0}">
				<thead>
					<tr>
						<th width="20%">Book ID&nbsp;</th>
						<th width="20%">Branch ID&nbsp;</th>
						<th width="20%">Card No&nbsp;</th>
						<th width="20%">Checkout Date&nbsp;</th>
						<th width="20%">Checkin Date&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${checkinHistoryList}" var="history">
						<tr>
							<td width="20%">&nbsp;<c:out value="${history.bookId}" />&nbsp;</td>
							<td width="20%"><c:out value="${history.branchId}" />&nbsp;&nbsp;</td>
							<td width="20%"><c:out value="${history.cardNo}" />&nbsp;</td>
							<td width="20%"><c:out value="${history.checkOut}" />&nbsp;</td>
							<td width="20%"><c:out
									value="${history.checkIn}" />&nbsp;</td>
						</tr>
					</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${checkinHistoryList.size() == 0}">
					<tr>
						<td colspan="7">There are no books loans matching that description
							! Please enter valid values.</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
	</form>
    </jsp:body>
</t:genericpage>
