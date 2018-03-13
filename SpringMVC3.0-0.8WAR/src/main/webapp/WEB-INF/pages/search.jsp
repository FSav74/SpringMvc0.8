<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<link href="<%=request.getContextPath() %>/resources/css/searchAndDetails.css" rel="stylesheet" type="text/css" />
	
	

	
	<style type="text/css">
		
			.myRow {
				
				width: 100%;
				min-height: 20px;}
			.myCol {
				width: 25%;
				text-align: left;
				float: left;
				}
				
			
		</style>
	
	
		<script>
	function othersubmit(){
		var frm = document.getElementById('searchFormBean');
		frm.action='${pageContext.request.contextPath}/preparesearch2.do';
		frm.submit();
	}
	</script>
	
	<form:form commandName="searchFormBean" action="${pageContext.request.contextPath}/search.do" method="post">
			<fieldset>
				<legend>Ricerca libro/Rivista/fumetto</legend>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">Titolo:</label></div>
					<div class="myCol"><form:input path="titolo"/></div>
					<div class="myCol">	 <label class="title" for="email">Autore: </label></div>
					<div class="myCol">	 <form:input path="autore"/></div>
					
					
				</div>
				
				<div class="myRow">
					<div class="myCol"><label for="location" class="title">Categoria:</label></div>
					
					<div class="myCol">
						<ul>
						<form:select path="genereSel" items="${searchFormBean.listaGeneri}" onchange="othersubmit()" ></form:select>
						</ul>
				    </div>
					
					<c:if test="${searchFormBean.genereSel != '0'}">
					<div class="myCol"><label for="location" class="title">Genere:</label></div>
					<div class="myCol">
						<ul>
						<form:select path="genereSel2" items="${searchFormBean.listaGeneri2}"  ></form:select>
						</ul>
					</div>
					</c:if>
					<c:if test="${searchFormBean.genereSel == '0'}">
					<div class="myCol"><label for="location" class="title"></label></div>
					</c:if>
					
				</div>
			</fieldset>
 	    <div class="submit"><input type="submit" value="Ricerca" /></div>
		</form:form>
		<br/>
		<br/>
		<br/>
		
		
		