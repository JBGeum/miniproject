<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.stone.springmvc.common.Board" %>    
<%@page import="java.text.SimpleDateFormat"%>
<%	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");	%>
<% 	Board board = (Board)request.getAttribute("board");	//글번호로 가져온 board %>    
<%
//	태그에 따라 테두리 색 변경
	String color="white";
	if(board.getTag() != null){	// 처리가 없으면 nullpointexception 발생
		if(board.getTag().equals("good")){color="#BAFFB4";}
		else if(board.getTag().equals("bad")){color="#FF6363";}
		else if(board.getTag().equals("soso")){color="#FFE699";}
		else if(board.getTag().equals("special")){color="#B5DEFF";}
	}
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>홈 화면</title>
	<link href="resources/css/style.css" rel='stylesheet' />
</head>

<body>

<%@ include file="jsp/header.jsp" %>

<div class="header">
	<h3>홈 화면</h3>
	<hr>
	처음으로 표시되는 홈 화면입니다.<br>
	최신 게시글 하나를 표시합니다.<br>
	제목을 클릭하면 게시글 상세 정보 페이지가 표시됩니다.<br>
	태그에 따라 그림자 색상이 다르게 표현됩니다.<br>
	 <br>
	 <br>
</div>
<div class="main">
	<div class="article" style="box-shadow: 0px 0px 10px 5px <%=color%>;">
		<table class="tb_home"> 
			<!-- 제목, 날짜, 작성자(개인용이기 때문에 고정), 태그, 내용, 여유 되면 작성 확인용 패스워드 -->
			<tr>
			 <td class="tb_label" >
			 	<label class="lb_write" for="title">제목</label>
			 </td>
			 <td colspan="3">
			 	<a href="detail?no=<%=board.getNo()%>"> <%= board.getTitle() %> </a>
			 </td>
			</tr>
			<tr>
			 <td class="tb_label">
			 	<label class="lb_write">글번호</label>
			 </td>
			 <td class="center">
			 	<%=board.getNo() %>
			 </td>
			 <td class="tb_label">
			 	<label class="lb_write">날짜</label>
			 </td>
			 <td>
			 	<%=sdf.format(board.getRegdate()) %>
			 </td>
			 
			</tr>
			<tr>
			 <td class="tb_label">
			 	<label class="lb_write">조회수</label>
			 </td>
			 <td class="center">
			 	<%=board.getHit()+1 %>
			 </td>	
			 <td class="tb_label">
			 	<label class="lb_write" for="tag">태그</label>
			 </td>
			 <td>
			 	${board.tag }	
			 </td>
			</tr>
			<tr>
			 <td colspan="4" class="tb_label">
			 	<label class="lb_write" for="context">내용</label>
			 </td>
			</tr>
			<tr>
			 <td colspan="4" class="tb_context">
			 	<div class="context">
			 	<%= board.getContents() %>
				</div>
			 </td>
			</tr>
		</table>
	</div>
</div>

<div class="menu">
	<a href="write"><button class="write">작성</button></a>
	<a href="list"><button class="list">목록</button></a>
</div>

<%@ include file="jsp/footer.jsp" %>
</body>
</html>