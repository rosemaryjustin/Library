<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>

	<form action="/library/addBorrower/submit" method="post">
		<span style="color:#333"><h2>Adding a Borrower</h2> </span>
		<table style="padding-bottom:10px;margin-bottom:15px;" cellpadding="15px" bgcolor="#eeffee">
			<tr>
				<td colspan="2">
					<i>${statusOfAddBorrower}</i>
				</td>
			</tr>
			<tr>
				<td>Borrower Last Name:</td>
				<td><input id='borrowerLastName' name="borrowerLastName"
						type="text" value="${borrowerLastName}" /></td>
			</tr>
			<tr>
				<td>Borrower First Name:</td>
				<td><input id='borrowerFirstName' name='borrowerFirstName'
						type="text" value="${borrowerFirstName}" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td ><input id='address' name='address' type="text"
						value="${address}" /></td>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td><input id='phoneNumber' name='phoneNumber' type="text"
						value="${phoneNumber}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id='submit' value='Add Borrower'
						type="submit" class="orangebutton" /></td>
			</tr>
		</table>
	</form>
    </jsp:body>
</t:genericpage>
