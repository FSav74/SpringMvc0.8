<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="back">
	
	<c:if test="${not empty mytitle}">
	<div id="headerbg"><p class="text1">&#8220;..${mytitle} &#8221;</p>
	</div>
	</c:if>
	
	<c:if test="${empty mytitle}">
	<div id="headerbg"><p class="text1">&#8220;..Lorem ipsum dolor sit amet,<br />
		consectetur adipisci elit, sed eiusmod tempor..&#8221;</p>
	</div>
</c:if>
</div>

