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
	

	
	<form:form commandName="insertClientFormBean" action="${pageContext.request.contextPath}/insertClient.do" method="post">
			<fieldset>
				<legend>Inserisci Utente</legend>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">Nome:</label></div>
					<div class="myCol"><form:input path="name"/></div>
					<div class="myCol">	 <label class="title" for="email">Cognome: </label></div>
					<div class="myCol">	 <form:input path="surname"/></div>	
				</div>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">user:</label></div>
					<div class="myCol"><form:input path="username"/></div>
					<div class="myCol">	 <label class="title" for="email">password: </label></div>
					<div class="myCol">	 <form:input path="password"/></div>	
				</div>
				
				<div class="myRow">
					<div class="myCol">	 <label class="title" for="email">email: </label></div>
					<div class="myCol">	 <form:input path="mail"/></div>	
				
				
					<div class="myCol"><label for="location" class="title">Ruolo:</label></div>
					
					<div class="myCol">
						<ul>
						<form:select path="roleSelezionato" items="${insertClientFormBean.listaRuoli}"  ></form:select>
						</ul>
				    </div>
					
					
					
				</div>
			</fieldset>
 	    <div class="submit"><input type="submit" value="Inserisci" /></div>
		</form:form>
		<br/>
		<br/>
		<br/>
		
		
		