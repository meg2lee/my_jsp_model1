<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.tj.*"%>
<jsp:useBean id="dao" class="com.tj.UserDAO"></jsp:useBean>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	pageContext.setAttribute("u", dao.getUser("C:/test/users.csv", num));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이용자 정보 수정</title>
<style>
	h1{text-align: center;}
	form { width:300px; border:1px solid black; margin:0 auto; padding:10px;}
	p#btn{text-align: center; }
</style>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script>
	function formCheck(){
		var num = $('#num').val();
		var name = $('#name').val();
		var phone = $('#phone').val();
		var email = $('#email').val();
		$.ajax({
			url:'update_json.jsp',
			method:'post',
			data:{'num':num,'name':name,'phone':phone,'email':email},
			dataType:'text',
			success:function(res){
				if(res.trim()=='true'){
					alert('정상적으로 수정했습니댜');
				}else{
					alert('수정작업에 실패했습니다');
				}
				location.href='list.jsp';
			},
			error:function(xhr,status,err){
				alert(status+', '+err);
			}
		});
	}
</script>
</head>
<body>
<h1>이용자 정보 수정</h1>
<form>
	<p><label for="num">번호</label>
	<input type="text" name="num" id="num" value="${u.num}" readonly></p>
	<p><label for="name">이름</label>
	<input type="text" name="name" id="name" value="${u.name}" readonly></p>
	<p><label for="phone">전화</label>
	<input type="text" name="phone" id="phone" value="${u.phone}" required></p>
	<p><label for="email">메일</label>
	<input type="text" name="email" id="email" value="${u.email}" required></p>
	<p id="btn">
		<button type="button" onclick="formCheck();">적용</button>
		<button type="reset">취소</button>
	</p>
</form>
</body>
</html>