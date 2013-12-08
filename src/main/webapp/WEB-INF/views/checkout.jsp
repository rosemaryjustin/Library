<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

	<form action="/library/checkout/submit" method="post">
		<span style="color:#333"><h2>Check out BOOKS</h2></span> 
		<table style="padding-bottom:10px;margin-bottom:15px;" cellpadding="20px" bgcolor="#eeffee">
			<tr>
				<td colspan="2">
					<i>${statusOfCheckout}</i>
				</td>
			</tr>
			<tr>
				<td>Book Id :</td>
				<td><input id='bookId' name="bookId" type="text" value="${bookId}"/></td>
			</tr>
			<tr>
				<td>Branch Id :</td>
				<td><input id='branchId' name='branchId' type="text" value="${branchId}"/></td>
			</tr>
			<tr>
				<td>Card No :</td>
				<td><input id='cardNo' name='cardNo' type="text" value="${cardNo}"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id='submit' value='Check Out'
					type="submit" class="orangebutton" /></td>
			</tr>
		</table>
	</form>
    </jsp:body>
</t:genericpage>