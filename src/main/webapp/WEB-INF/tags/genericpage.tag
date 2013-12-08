<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="headerscripts" fragment="true" %>
<html>
<head>
	<title>Library</title>
	<LINK href="/library/resources/css/master.css" rel="stylesheet" type="text/css">
	<LINK href="/library/resources/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/library/resources/js/jquery.js"></script>
	<script type="text/javascript"  src="/library/resources/js/jquery.dataTables.js"></script>

		<jsp:invoke fragment="headerscripts"/>
</head>
  <body>
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
      <a href="/library/checkAvailablity">Search</a>
      <a href="/library/checkout">Check Out</a>
      <a href="/library/checkin">Check In</a>
      <a href="/library/addBorrower">Manage Borrower</a>
    </div>
    <div id="body">
    <table width="100%"><tr><td align="center"><jsp:doBody/></td></tr></table>
      
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>