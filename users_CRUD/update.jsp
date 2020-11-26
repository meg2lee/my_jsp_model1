<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<jsp:useBean id="u" class="com.tj.UserVO"></jsp:useBean>
<jsp:setProperty name="u" property="*"></jsp:setProperty>
<%
	boolean updated = dao.update("C:/test/users.csv", u);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 정보수정 결과</title>
<script>
	if(<%=updated%>) alert('정상적으로 수정되었습니다');
	else alert('수정작업에 실패했습니다');
	location.href='list.jsp';
</script>
</head>
<body>
</body>
</html>