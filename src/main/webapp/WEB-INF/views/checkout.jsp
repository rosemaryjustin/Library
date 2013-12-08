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
		<h1>Check out BOOKS !!!</h1>
		<table>
			<tr>
				<td colspan="2">
					${statusOfCheckout}
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
				<td colspan="2"><input id='submit' value='Check Out'
					type="submit" /></td>
			</tr>
		</table>
	</form>
    </jsp:body>
</t:genericpage>