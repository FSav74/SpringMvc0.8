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
			.myCol2 {
				width: 50%;
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
				
			.myButtonRicerca{
				border: 1px solid #006633;
				background-color: #009966;
				color: #ffffff;
				border-radius: 5px;
				padding: 5px;
				margin-top: 10px;
			}
			.myButtonRicerca:hover {
				border: 1px solid #006633;
				background-color: #00cc33;
				color: #ffffff;
				cursor: pointer;}
				
			.errorblock{
			color: #ff0000;
			}
			
				
			
		</style>

	    <script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
	    <script src="<c:url value="/resources/jquery-ui-1.11.2.custom/jquery-ui.min.js" />"></script>
	    <link href="<%=request.getContextPath() %>/resources/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet" type="text/css" />
	    
		<script>
	var myDialog;
	$(document).ready(function(){
	 $("select#categoria").change(function(){
	 	
	 	 var selezione = $(this).val();
	 	 var trimmato = $.trim(selezione);
	 	 if(trimmato=='0'){
	 	 	var options = '';
	 	 	$("select#genere").html(options);
	 	 	return;
	 	 }
	 
         $.getJSON("prepareinsert2.do",{categoriaSelezionata: $(this).val()}, function(j){
              var options = '';
              //for (var i = 0; i < j.length; i++) {
              //  options += '<option value="' + j[i].id + '">' + j[i].name + '</option>';
              //}
              $.each(j,function (key, val){
              options += '<option value="' + key + '">' + val + '</option>';
              });
              alert('campo:'+options);
              $("select#genere").html(options);
            }).fail(function(jqXHR, textStatus, errorThrown) { alert('getJSON request failed! ' + textStatus + ' errorThrown '+errorThrown); })
        });
        

      $("#myButtonId").click(function(){
     
     	$("#loaderPage").load("preparesearchauthor.do");
     	myDialog=$("#loaderPage").dialog({
     	height:600,
     	width:600,
     	modal:true,
     	
     	isOpen:function(){
     	     $("#myButtonId2").click(function(){
     	     	$("#loaderPage").load("searchAuthor.do");
     	     });
     	}
     	
     	});
     return false;
     });  
        
        
        
        
	});
	
	
	
	</script>
	
	<form:form commandName="insertFormBean" action="${pageContext.request.contextPath}/insert.do" method="post">
	
	<!-- Errori validatzione -->
	<form:errors path="*" cssClass="errorblock" element="div" />
	
			<fieldset>
				<legend>Inserisci libro/Rivista/fumetto</legend>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">Titolo:</label></div>
					<div class="myCol"><form:input path="titolo"/></div>
					<div class="myCol">	 <label class="title" for="email">Tipologia: </label></div>
					<div class="myCol">
						<ul>
						<form:select path="tipologiaSel" items="${insertFormBean.listaTipologiaGeneri}" id="tipologia" ></form:select>
						</ul>
				    </div>
				</div>
				
				<div class="myRow">
					<div class="myCol"><label for="location" class="title">Categoria:</label></div>
					<div class="myCol">
						<ul>
						<form:select path="genereSel" items="${insertFormBean.listaGeneri}" id="categoria" ></form:select>
						</ul>
				    </div>
					
					
					<div class="myCol"><label for="location" class="title">Genere:</label></div>
					<div class="myCol">
						<ul>
						<form:select path="genereSel2" items="${insertFormBean.listaGeneri2}" id="genere" ></form:select>
						</ul>
					</div>	
				</div>
				
				
				<div class="myRow">
				 <div class="myCol"><label for="location" class="title">Autori</label></div>
				<div class="myCol"><input id="myButtonId" class="myButton" type="button" value="Cerca" /></div>
				<div id="authorDiv" class="myCol">
				
				<c:forEach var="autore" items="${insertFormBean.listaMyAutori}">
				<label for="location" class="title">${autore.value} </label><input name=hidden${autore.key} type="hidden" value=${autore.key} \>
				</c:forEach>
				
				</div>
				</div>
				
				<div id="loaderPage"></div>
				
			</fieldset>
			
			<div class="myRow">
			<div class="myCol2">
		 	    <div class="submit"><input type="submit" value="Salva" /></div>
		 	</div>
		 	<div class="myCol">    
		 	    <div class="submit"><input type="button" class="myButtonRicerca" value="Inserisci Dettagli" /></div>
		 	 </div>   
		 	</div> 
		</form:form>
		<br/>
		<br/>
		<br/>
		
		
		