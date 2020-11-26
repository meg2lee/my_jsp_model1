<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*" %>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<jsp:useBean id="u" class="com.tj.UserVO"></jsp:useBean>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	u = dao.getUser("C:/test/users.csv", num);
	pageContext.setAttribute("u", u);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 상세정보</title>
<style>
	a {text-decoration: none;}
	div,p{width:300px; margin:0 auto;}
	h1,h2{text-align: center;}
</style>
<script>
	function deleteIt(num) {
		if(confirm(num+'번 사용자를 삭제하시겠어요?')){
			location.href='delete.jsp?num='+num;
		}
	}
</script>
</head>
<body>
<h1>이용자 상세정보</h1>
<div>
<%=u.getNum()%>
<%=u.getName()%>
<%=u.getPhone()%>
<%=u.getEmail()%>
</div>
<h2>EL(Expression Language) 연습</h2>
<div>
번호 : ${u.num}<br>
이름 : ${u.name}<br>
전화 : ${u.phone}<br>
메일 : ${u.email}<br>
</div>
<hr>
<p>
	[<a href="list.jsp">목록보기</a>]
	[<a href="edit.jsp?num=${u.num}">수정</a>]
	[<a href="javascript:deleteIt('${u.num}');">삭제</a>]
</p>
</body>
</html>