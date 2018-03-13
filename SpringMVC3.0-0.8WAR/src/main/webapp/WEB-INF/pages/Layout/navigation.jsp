<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<div id="page2">
<p><a href="<%=request.getContextPath()%>/index.do">Welcome</a></p>
<p><a href="<%=request.getContextPath()%>/preparesearch.do">Ricerca</a></p>
<p><a href="<%=request.getContextPath()%>/prepareinsert.do">Inserisci</a></p>
<p><a href="<%=request.getContextPath()%>/prepareinsert3.do">Inserisci2</a></p>
<p><a href="<%=request.getContextPath()%>/administration.do">Admin</a></p>

	<!-- SOLO PER il ruolo di AMDIN -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="<%=request.getContextPath()%>/secondAdmin.do">AdminHidden</a></p>
	</sec:authorize>
	
</div>
