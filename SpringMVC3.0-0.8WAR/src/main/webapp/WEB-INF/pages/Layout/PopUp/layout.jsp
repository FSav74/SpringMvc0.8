<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<html>
	<head>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link href="<%=request.getContextPath() %>/resources/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/global.css" rel="stylesheet" type="text/css" />
	
	
	</head>
	<body>
	  
	<table border="1" style="border-collapse: collapse;" cellpadding="2" cellspacing="2" align="center" width="100%">   
	 <tbody><tr>
	        <td  colspan="2"><tiles:insertAttribute name="header" /></td>
	    </tr>
	    <tr>
	        <td valign="top"class="centraltdlayout"  width="100%">
	  
	         <tiles:insertAttribute name="body" />
	  
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2">
	  
	         <tiles:insertAttribute name="footer" />
	  
	        </td>
	    </tr>
	</tbody></table></body>
	</html>