<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.stone.springmvc.common.Board" %>    
<%@page import="java.text.SimpleDateFormat"%>
<%	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");	%>
<% Board board = (Board)request.getAttribute("board");	//글번호로 가져온 board %>    
<% //	태그에 따라 테두리 색 변경
	String color="white";
	if(board.getTag() != null){	// 처리가 없으면 nullpointexception 발생
								// form에서의 required 처리로 현재는 null일 경우가 없을 것
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
	<title>게시물 상세 화면</title>
	<link href="resources/css/style.css" rel='stylesheet' />
</head>
<body>

<%@ include file="jsp/header.jsp" %>

<div class="header">
	<h3>게시물 상세 페이지</h3>
	<hr>
	글번호로 검색한 게시물의 정보를 표시합니다.<br>
	태그에 따라 그림자 색상이 다르게 표현됩니다.<br>
	내용이 길어지면 스크롤바가 표시됩니다.<br>
	수정, 삭제 페이지로 이동 가능합니다.<br>
	삭제할 경우 확인창이 뜨고, 확인을 입력시 게시글이 삭제됩니다.<br>
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
			 	<%= board.getTitle() %>
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
	<a href="list"><button class="list">목록</button></a>
	<a href="edit?no=<%=board.getNo()%>" class="edit"><button class="edit">수정</button></a>
	<button class="delete" onclick="del_confirm();">삭제</button>
	
	<script>
	function del_confirm(){
		if(!confirm("정말로 삭제하시겠습니까?")){
			// no일 경우
		} else {
			//yes일 경우		
			  location.href="delete?no=<%=board.getNo()%>";
		}
	}
	</script>
</div>

<%@ include file="jsp/footer.jsp" %>

</body>
</html>