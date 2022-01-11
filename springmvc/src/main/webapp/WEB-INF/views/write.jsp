<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="resources/css/style.css" rel='stylesheet' />
	<meta charset="UTF-8">
	<title>게시물 작성</title>
</head>

<body>
<%@ include file="jsp/header.jsp" %>
<div class="header">
	<h3>새 게시물 작성하기</h3>
	<hr>
	새 게시물 작성 페이지입니다.<br>
	제목, 태그, 내용은 필수 항목입니다.<br>
	 <br>
</div>

<div class="main">
	<form action="insert" method="post">
		<table class="tb_write"> 
		<!-- 제목, 날짜, 작성자(개인용이기 때문에 고정), 태그, 내용, 여유 되면 작성 확인용 패스워드 -->
			<tr>
			 <td class="tb_label">
			 	<label class="lb_write" for="title">제목</label>
			 </td>
			 <td class="td_input">
			 	<input type="text" name="title" id="title" style="width:90%;" required>
			 </td>
			</tr>
			<tr>
			 <td class="tb_label">
			 	<label class="lb_write" for="tag">태그</label>
			 </td>
			 <td class="align_left">
			 	<input type="radio" name="tag" id="good" value="good" required><label class="tag good" for="good">GOOD</label>
			 	<input type="radio" name="tag" id="bad" value="bad"><label class="tag bad" for="bad">BAD</label>
			 	<input type="radio" name="tag" id="soso" value="soso"><label class="tag soso" for="soso">SOSO</label>
			 	<input type="radio" name="tag" id="special" value="special"><label class="tag special" for="special">SPECIAL</label>
			 </td>
			</tr>
			<tr>
			 <td colspan="2"  class="tb_label">
			 	<label class="lb_write" for="context">내용</label>
			 </td>
			</tr>
			<tr>
			 <td colspan="2">
			 	<textarea name="contents" id="contents" class="input_context" required></textarea>
			 </td>
			</tr>
			<tr>
			 <td colspan="2">
			 	<button type="submit" class="write">작성</button>
			 </td>
			</tr>
		</table>
	</form>
	
</div>
	
<div class="menu">
	<a href="home"><button class="home">홈 화면</button></a>
	<a href="list"><button class="list">목록</button></a>
</div>
	
<%@ include file="jsp/footer.jsp" %>
</body>
</html>