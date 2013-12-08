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
  	<div id="logo" style="float:left">
  		<img height = "70px" src="/library/resources/images/green_logo.png"/>
  		<span class="logoheader">Library</span>
  	    
  	</div>
    <div id="nav" style="float:right;margin-top:45px;">
      <a href="/library/checkAvailablity" class="item">Search</a>
      <a href="/library/checkout" class="item">Check Out</a>
      <a href="/library/checkin" class="item">Check In</a>
      <a href="/library/addBorrower" class="item">Manage Borrower</a>
    </div>
    </div>
    <div id="body">
    <table width="100%"><tr><td align="center"><jsp:doBody/></td></tr></table>
      
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>