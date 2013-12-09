
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

	
		<table id="bookstable">
			<c:choose>
				<c:when test="${branches.size() > 0}">
				<thead>
					<tr>
						<th >Branch ID&nbsp;</th>
						<th >Branch Name&nbsp;</th>
						<th >Address&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${branches}" var="branch">
						<tr>
							<td >&nbsp;<c:out value="${branch.branchId}" />&nbsp;</td>
							<td ><c:out value="${branch.branchName}" />&nbsp;&nbsp;</td>
							<td ><c:out value="${branch.address}" />&nbsp;</td>
						</tr>
					</c:forEach>
					</tbody>
				</c:when>
				<c:when test="${branches.size() == 0}">
					<tr>
						<td colspan="7">There are no branches.</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
    </jsp:body>
</t:genericpage>
