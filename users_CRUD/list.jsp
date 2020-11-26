<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*" %>
<%@ page import="java.util.ArrayList"%>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<jsp:useBean id="u" class="com.tj.UserVO"></jsp:useBean>
<%
	ArrayList<UserVO> list = dao.getUserList("C:/test/users.csv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 리스트</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<style>
	body{text-align: center;}
	table { border:1px solid black; border-spacing: 0px; margin:0 auto;}
	th,td{border:1px solid black; padding:5px;}
	th{background-color:#fcc;}
	a { text-decoration: none; color:blue;}
</style>
<body>
<h1>사용자 리스트</h1>
<table>
<tr><th>번호</th><th>이름</th><th>전화</th><th>Email</th></tr>
<%
	for(int i=0;i<list.size();i++){
		int num = list.get(i).getNum();
		String name = list.get(i).getName();
%>
	<tr>
		<td><%=list.get(i).getNum()%></td>
		<td><a href="user.jsp?num=<%=num%>"><%=name%></a></td>
		<td><%=list.get(i).getPhone()%></td>
		<td><%=list.get(i).getEmail()%></td>
	</tr>	
<%	}
%>
</table>
<hr>
<p>[<a href="insert_form.jsp">추가</a>]</p>
</body>
</html>