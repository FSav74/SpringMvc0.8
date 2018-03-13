<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<link href="<%=request.getContextPath() %>/resources/css/searchAndDetails.css" rel="stylesheet" type="text/css" />
	
	

	
	<style type="text/css">
		
			.myRow {
				
				width: 100%;
				min-height: 20px;
				margin-top: 4px;
				}
			.myCol {
				width: 25%;
				text-align: left;
				float: left;
				vertical-align: middle;
				}
			.myButton{
				border: 1px solid #006633;
				background-color: #159EE5;
				color: #ffffff;
				border-radius: 5px;
				/* padding: 5px; */
				
			}
			.myButton:hover {
				border: 1px solid #006633;
				background-color: #F5A91D;
				color: #ffffff;
				cursor: pointer;}
			
				
			
		</style>

	

	
	<form:form commandName="insertFormBean" action="${pageContext.request.contextPath}/insertDB2.do" method="post">
			<fieldset>
				<legend>Riassunto</legend>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">Titolo:</label></div>
					<div class="myCol"><label class="title" for="name">${bean.titolo}</label></div>
					<div class="myCol">	 <label class="title" for="email">Tipologia: </label></div>
					<div class="myCol"><label class="title" for="name">${bean.titolo}</label></div>

				    </div>
				</div>
				
				<div class="myRow">
					<div class="myCol"><label for="location" class="title">Categoria:</label></div>
					<div class="myCol">
						
				    </div>
					
					
					<div class="myCol"><label for="location" class="title">Genere:</label></div>
					<div class="myCol">
						
					</div>	
				</div>
				
				
				<div class="myRow">
				 <div class="myCol"><label for="location" class="title">Autori</label></div>
				
				
				</div>
				
				
				
			</fieldset>
 	    <div class="submit"><input type="submit" value="Inserisci" /></div>
		</form:form>
		<br/>
		<br/>
		<br/>
		
		
		