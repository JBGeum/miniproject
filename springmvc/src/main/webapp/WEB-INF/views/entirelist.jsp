<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.stone.springmvc.common.Board" %>    
<%@page import="java.text.SimpleDateFormat"%>
<% List<Board> boards = (List<Board>)request.getAttribute("boards"); %>   
<!DOCTYPE html>
<html>
	<head>
	<link href="resources/css/style.css" rel='stylesheet' />
	<meta charset="UTF-8">
	<title>전체 게시물 목록</title>
</head>

<body>

<%@ include file="jsp/header.jsp" %>
<div class="header">
	<h3>게시물 목록</h3>
	<hr>
	전체 게시물 목록 페이지입니다.<br>
	글번호의 배경색은 게시물의 태그와 대응합니다.<br>
	제목을 클릭해서 상세 게시글 페이지로 이동합니다.<br>
	태그를 선택(필수항목)해서 게시물을 검색할 수 있습니다.<br>
	 <br>
	 <br>
</div>
<div class="main">
  <table class="list">
	  <tr class="listheader">
		  <td class="listno">글번호</td>
		  <td>제목</td>
		  <td class="listhit">조회수</td>
		  <td class="listdate">날짜</td>
	  </tr>
	   <tr>
	   <%
		   for(int i=1; i<= boards.size(); i++){	   
		   Board board=boards.get(i-1);
		 %>
	    <td class="bold <%=board.getTag() %>"> <%= board.getNo()%> </td>
	    <td class="list_title"> <a href="detail?no=<%= board.getNo()%>"> <%=board.getTitle()%></a> </td>
	    <td><%=board.getHit() %></td>
	    <td>
			<% SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");%><%=sdf.format(board.getRegdate()) %>
	   </tr>
	   <%
	       }
	   %>  
  </table>
  
  
	<div class="search">
		<table>
			<tr><td><b>태그로 검색 </b></td>
				<td>
					<form action="search" method="post">
						<input type="radio" name="tag" id="good" value="good" required><label class="tag good" for="good">GOOD</label>
						<input type="radio" name="tag" id="bad" value="bad"><label class="tag bad" for="bad">BAD</label>
					 	<input type="radio" name="tag" id="soso" value="soso"><label class="tag soso" for="soso">SOSO</label>
					 	<input type="radio" name="tag" id="special" value="special"><label class="tag special" for="special">SPECIAL</label>
						<button type="submit" class="search">검색</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
</div> 

<div class="menu">
	<a href="home"><button class="home">홈 화면</button></a>
	<a href="write"><button class="write">작성</button></a>
	<a href="javascript:history.back()"><button class="back">뒤로 가기</button></a>
</div>

  
<%@ include file="jsp/footer.jsp" %>
</body>
</html>