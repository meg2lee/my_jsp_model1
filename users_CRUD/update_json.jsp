<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<jsp:useBean id="u" class="com.tj.UserVO"></jsp:useBean>
<jsp:setProperty name="u" property="*"></jsp:setProperty>
<%=dao.update("C:/test/users.csv", u)%>