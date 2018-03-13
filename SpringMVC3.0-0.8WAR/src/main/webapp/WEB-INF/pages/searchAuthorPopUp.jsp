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

	    <script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
	    <script src="<c:url value="/resources/jquery-ui-1.11.2.custom/jquery-ui.min.js" />"></script>
	    <link href="<%=request.getContextPath() %>/resources/jquery-ui-1.11.2.custom/jquery-ui.css" rel="stylesheet" type="text/css" />
	    
		<script>
	
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
        
        
   
     
     $("#myButtonId2").click(function(){
     
     	var nome = $('input[name="nomeAutore"]').val();
     	var cognome = $('input[name="cognomeAutore"]').val();
     	
     	$("#loaderPage").load("searchAuthor.do?nome="+nome+"&cognome="+cognome);
     
     return false;
     });  
        
        
 //        $("#myButtonSelAut").click(function(){
 //    alert('jquery');
  //   	var nome = 'saverio';
   //  	var cognome = 'letterese'
     	
   //  	$("#authorDiv").append("<label for=\"location\" class=\"title\">Autori</label>");
     
   //  return false;
    // });  
        
        
        
        
        
	});
	
	function seleziona(nome,cognome,id){
	alert('selez:');
	$("#authorDiv").append("<label for=\"location\" class=\"title\">"+nome+" "+cognome+"</label><input name=\"hidden"+id+"\" type=\"hidden\" value=\""+id+"\" \>");

//la var javascript myDialog è definito in insert.jsp
   myDialog.dialog('close');
	}
	
	</script>
	
	<form:form commandName="searchAuthorFormBean" action="${pageContext.request.contextPath}/searchAuthor.do" method="post">
			<fieldset>
				<legend>Ricerca libro/Rivista/fumetto</legend>
				<div class="myRow">
					<div class="myCol"><label class="title" for="name">Nome:</label></div>
					<div class="myCol"><form:input path="nomeAutore"/></div>
					<div class="myCol">	 <label class="title" for="email">Cognome: </label></div>
					<div class="myCol">	 <form:input path="cognomeAutore"/></div>	
				</div>
				
			
				
				
			</fieldset>
 	    	<div class="submit"><input id="myButtonId2" type="button" value="Ricerca" /></div>
 	    	<br>
 	    	<br>
 	    	
 	    	<c:if test="${searchAuthorFormBean.numeroRisultati != '0'}">
 	    	<table class="resultTable">
			<tr class="head">
				<th>NOME</th>
				<th>COGNOME</th>
				<th>RUOLO</th>
				<th> </th>
				<th>-</th>
			</tr>
			
			<c:forEach var="elem" items="${resultList}" varStatus="loop">
			
				<!-- differenzio il class se è pari o dispari -->
				<tr class="${loop.index % 2 ==0 ? 'even' : 'odd'  }">
					<th>${elem.nomeAutore}</th>
					<td>${elem.cognomeAutore}</td>
					<td></td>
					<td></td>
					<td><div class="submit"><input id="myButtonSelAut"  type="button" value="Seleziona" onclick="seleziona('${elem.nomeAutore}','${elem.cognomeAutore}','${elem.codice}')" /></div></td>
				</tr>
			</c:forEach>
			</table>
			</c:if>
			
			
			
		</form:form>
		<br/>
		<br/>
		<br/>
		
		
		