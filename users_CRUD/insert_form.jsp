<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사용자 정보 입력</title>
<style>
	div.container{ width:300px; margin:0 auto; border:1px solid black;
		padding:10px;
	}
	p.btn { text-align: center;}
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
		if(num!='' && name!='' && phone!='' && email!=''){
			return true;
		}
		return false;
	}
</script>
</head>
<body>
<div class="container">
	<h1>사용자 정보 입력</h1>
	<form action="insert.jsp" method="post" onsubmit="return formCheck();">
		<p><label for="num">번호</label>
		<input type="text" id="num" name="num" required></p>
		<p><label for="name">이름</label>
		<input type="text" id="name" name="name" required></p>
		<p><label for="phone">전화</label>
		<input type="text" id="phone" name="phone" required></p>
		<p><label for="email">메일</label>
		<input type="text" id="email" name="email" required></p>
		<p class="btn">
			<button type="submit">저장</button>
			<button type="reset">취소</button>
		</p>
	</form>
</div>
</body>
</html>