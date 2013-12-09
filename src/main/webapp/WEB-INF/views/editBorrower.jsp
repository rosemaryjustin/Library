<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>

	<form action="/library/addBorrower/edit/submit" method="post">
		<span style="color:#333"><h2>Update a Borrower</h2> </span>
		<table style="padding-bottom:10px;margin-bottom:15px;" cellpadding="15px" bgcolor="#eeffee">
			<tr>
				<td colspan="2">
					<i>${editBorrowerMessage}</i>
				</td>
			</tr>
			<tr>
				<td>Card No:</td>
				<td>${cardNo}<input type="hidden" value="${cardNo}" name="cardNo"/></td>
			</tr>
			<tr>
				<td>Borrower Last Name:</td>
				<td>${borrowerLastName}<input type="hidden" value="${borrowerLastName}" name="borrowerLastName"/></td>
			</tr>
			<tr>
				<td>Borrower First Name:</td>
				<td>${borrowerFirstName}<input type="hidden" value="${borrowerFirstName}" name="borrowerFirstName"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td ><textarea id='address' name='address'>${address}</textarea>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td><input id='phoneNumber' name='phoneNumber' type="text"
						value="${phoneNumber}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id='submit' value='Update Borrower'
						type="submit" class="orangebutton" /></td>
			</tr>
		</table>
	</form>
    </jsp:body>
</t:genericpage>
