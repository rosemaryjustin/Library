<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="headerscripts">
    	<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				
				$('#borrowerstable').dataTable({
					"aaSorting": [[0, 'asc']]
					
				});
			} );
		</script>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

	<form action="/library/addBorrower/search/submit" method="post">
		<span style="float:left;color:#333" align="left">
			<h2>Search for a Borrower</h2> 
			
		</span>
		<span style="float:right;color:#333" align="right"><h3><a href="/library/addBorrower">Add a Borrower</a></h3></span> 
		<table style="padding-bottom:10px;margin-bottom:35px;" bgcolor="#eeffee">
			<tr style="border-bottom: 1px solid #98FB98;">
				<td class="rightpaddingbig">Card No :<br/><input id='cardNo' name="cardNo" type="text"
					 value="${cardNo}" /></td>
				<td class="rightpaddingbig">Last Name :<br/><input id='borrowerLastName' 
				name='borrowerLastName' type="text" value="${borrowerLastName}" /></td>
				<td class="rightpaddingbig">First Name:<br/><input id='borrowerFirstName' name='borrowerFirstName' type="text"
					value="${borrowerFirstName}" /></td>
					<td class="rightpaddingbig">Phone:<br/><input id='phoneNumber' name='phoneNumber' type="text"
					value="${phoneNumber}" /></td>
			
				<td style="padding-top:10px"><input id='submit'
					value='Search' type="submit" class="orangebutton" /></td>
			</tr>
		</table>
		<table id="borrowerstable">
			<c:choose>
				<c:when test="${borrowers.size() > 0}">
				<thead>
					<tr>
						<th width="20%">Card No&nbsp;</th>
						<th width="20%">First Name&nbsp;</th>
						<th width="20%">Last Name&nbsp;</th>
						<th width="20%">Address&nbsp;</th>
						<th width="20%">Phone&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${borrowers}" var="borrower">
						<tr>
							<td width="20%">&nbsp;<c:out value="${borrower.cardNo}" />&nbsp;</td>
							<td width="10%"><c:out value="${borrower.firstName}" />&nbsp;&nbsp;</td>
							<td width="10%"><c:out value="${borrower.lastName}" />&nbsp;</td>
							<td width="25%"><c:out value="${borrower.address}" />&nbsp;</td>
							<td width="20%"><c:out
									value="${borrower.phone}" />&nbsp;</td>
							<td width="10%">
									
									<a href="/library/addBorrower/edit?cardNo=${borrower.cardNo}
									&borrowerFirstName=${borrower.firstName}
									&borrowerLastName=${borrower.lastName}
									&address=${borrower.address}
									&phoneNumber=${borrower.phone}" 
									>Edit</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${borrowers.size() == 0}">
					<tr>
						<td colspan="7">There are no borrowers matching that description
							! Please enter valid values.</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
	</form>
    </jsp:body>
</t:genericpage>
