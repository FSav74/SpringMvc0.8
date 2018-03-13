<%
String context = request.getContextPath();
String redirectURL =context+"/index.do";
response.sendRedirect(redirectURL);
 %>
