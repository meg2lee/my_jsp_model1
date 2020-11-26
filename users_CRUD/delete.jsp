<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="dao" class="com.tj.UserDAO" scope="page"></jsp:useBean>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	boolean deleted = dao.delete("C:/test/users.csv", num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 정보삭제 결과</title>
<script>
	if(<%=deleted%>) { alert('정상적으로 삭제되었습니다');}
	else { alert('삭제에 실패했습니다'); }
	location.href='list.jsp';
</script>
</head>
<body>
</body>
</html>