<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<%=request.getContextPath() %>/resources/css/searchAndDetails.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

function seleziona(selezionato){
	alert("codice:"+selezionato);
}
</script>
	<style type="text/css">
			
		/*				
			tr.head th {
				color: #fff;
				background-color: #90b4d6;
				border-bottom: 2px solid #547ca0;
				border-right: 1px solid #749abe;
				border-top: 1px solid #90b4d6;
				text-align: center;
				text-shadow: -1px -1px 1px #666666;
				letter-spacing: 0.15em;}
		
			tr.even td, tr.even th {
				background-color: #e8eff5;}
			tr.head th:first-child {
				-webkit-border-top-left-radius: 5px;
				-moz-border-radius-topleft: 5px;
				border-top-left-radius: 5px;}
			tr.head th:last-child {
				-webkit-border-top-right-radius: 5px;
				-moz-border-radius-topright: 5px;
				border-top-right-radius: 5px;}
				*/
		</style>
		
		
	
	<c:if test="${not empty resultList}">
	
	<form:form commandName="searchFormBean" action="${pageContext.request.contextPath}/search.do" method="post">
				<table class="resultTable">
			<tr class="head">
				<th>codice</th>
				<th>AUTORE</th>
				<th>TITOLO</th>
				<th>EDITORE</th>
				<th>-</th>
			</tr>
			
			<c:forEach var="elem" items="${resultList}" varStatus="loop">
			
				<!-- differenzio il class se è pari o dispari -->
				<tr class="${loop.index % 2 ==0 ? 'even' : 'odd'  }">
					<th>${elem.codice}</th>
					<td>${elem.autore}</td>
					<td>${elem.titolo}</td>
					<td>${elem.editore}</td>
					<td><div class="submit"><input type="submit" value="Dettaglio" onclick="seleziona('${elem.codice}')" /></div></td>
				</tr>
			</c:forEach>
			
		
			
		</table>
 	    
		</form:form>
		
	</c:if>
	
		<br/>
		<br/>
		<br/>