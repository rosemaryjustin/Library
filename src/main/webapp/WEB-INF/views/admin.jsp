<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

		<table style="padding-bottom:10px;margin-bottom:15px;" cellpadding="20px" bgcolor="#eeffee">
			<tr>
				<td colspan="2">
					<a href="/library/admin/history"><h2>Search Checkout History</h2></a>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<a href="/library/admin/branch"><h2>View Branch Details</h2></a>
				</td>
			</tr>
		</table>
    </jsp:body>
</t:genericpage>