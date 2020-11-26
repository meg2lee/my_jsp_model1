<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<jsp:useBean id="user" class="com.tj.UserVO"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>
<% boolean inserted = dao.save("C:/test/users.csv", user); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 추가 결과</title>
<script>
	if(<%=inserted%>) alert('정상적으로 추가했습니다');
	else alert('추가하는 작업에 실패했습니다');
	location.href='list.jsp';
</script>
</head>
<body>
</body>
</html>