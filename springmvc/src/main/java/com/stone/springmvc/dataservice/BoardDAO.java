package com.stone.springmvc.dataservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.stone.springmvc.common.Board;
import com.stone.springmvc.common.JDBCUtil;

//DAO
public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	//글 등록
	public int insertBoard(Board board) {
		int result = 0;
		String query = "insert into diaryboard(regdate, title, tag, contents, hit) values(now(), ?,?,?, default)";
		// no(글번호)는 auto_increment, title, tag, contents는 입력받고 hit(조회수)는 등록시 0부터 
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getTag());
			pstmt.setString(3,board.getContents());
			result = pstmt.executeUpdate();	//정상처리시 result=1
		} catch (SQLException e) {
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);	//pstmt, conn 닫기
		}
		return result;
	}
	
	//글 수정
	public int updateBoard(Board board) {
		int result = 0;
		String query = "update diaryboard set title=?, tag=?, contents=? where no=?";
		// 제목, 태그, 내용을 수정 
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getTag());
			pstmt.setString(3,board.getContents());
			pstmt.setInt(4,board.getNo());
			result = pstmt.executeUpdate();	//정상처리시 result=1
		} catch (SQLException e) {
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);	//pstmt, conn 닫기
		}
		return result;	
	}
	
	//글 삭제
	public int deleteBoard(int no) {
		int result = 0;
		String query = "delete from diaryboard where no=?";
		// 해당 no의 게시글 삭제
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);	//pstmt, conn 닫기
		}
		return result;	
	}
	
	//최신 게시물 가져오기
	public Board getLastArticle() {		
		Board board = null;
		// 테이블에서 no(글번호)기준 내림차순 0번째 포함 1개 가져오기 = 최신글 1개 
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기
			String query = "select * from diaryboard order by no desc limit 1";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			board = new Board();			
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setTag(rs.getString("tag"));
			board.setContents(rs.getString("contents"));
			board.setRegdate(rs.getTimestamp("regdate"));
			board.setHit(rs.getInt("hit"));
			}
//			addArticleHit(board.getNo());	//가져온 no로 조회수+1 (다시 불러오지 않으므로 view에 줄 데이터에는 반영x?)
		} catch (SQLException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);	//rs, pstmt, conn 닫기
		}
		return board;
	}
	

	//모든 게시글 가져오기	
	public List<Board> getArticleList() {	
		List<Board> boardList = new ArrayList<Board>();
		String query = "select * from diaryboard order by no desc";
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기			 
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
			Board board = new Board();	
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setTag(rs.getString("tag"));
			board.setContents(rs.getString("contents"));
			board.setRegdate(rs.getTimestamp("regdate"));
			board.setHit(rs.getInt("hit"));
			boardList.add(board);
			}
			return boardList;			
		} catch (SQLException e) {
			System.out.println("가져오기 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);	//rs, pstmt, conn 닫기
		}
		return boardList;
	}
	/*
	 		//일정 페이지만큼의 게시글 가져오기	
	public Object[] getArticleList() {	//오브젝트 배열 반환 : List<Board> boardList, int totalArticleNum
		int totalArticleNum = 0;	//총 게시글 수의 합(DB에서 count로 얻어온 현제 게시물 숫자
		List<Board> boardList = new ArrayList<Board>();
		String query = "select * from diaryboard order by no desc";
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기			 
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
			Board board = new Board();	
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setTag(rs.getString("tag"));
			board.setContents(rs.getString("contents"));
			board.setRegdate(rs.getTimestamp("regdate"));
			board.setHit(rs.getInt("hit"));
			boardList.add(board);
			}
			return boardList;			
		} catch (SQLException e) {
			System.out.println("가져오기 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);	//rs, pstmt, conn 닫기
		}
		return boardList;
	}
*/	
	
	//해당되는 태그의 게시글 가져오기	
	public List<Board> getArticlebyTag(String tag) {	
		List<Board> boardList = new ArrayList<Board>();
		String query = "select * from diaryboard where tag=? order by no desc";
		// 테이블에서 no(글번호)기준 내림차순 0번째 포함 1개 가져오기 = 최신글 1개
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기			 
			pstmt = conn.prepareStatement(query);
			String tag2 = tag;	// request로 가져온 tag 넣기
			pstmt.setNString(1, tag2);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
			Board board = new Board();	
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setTag(rs.getString("tag"));
			board.setContents(rs.getString("contents"));
			board.setRegdate(rs.getTimestamp("regdate"));
			board.setHit(rs.getInt("hit"));
			boardList.add(board);
			}
			return boardList;			
		} catch (SQLException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);	//rs, pstmt, conn 닫기
		}
		return boardList;
	}
	
	//선택한 번호의 게시글 가져오기(상세조회)
	public Board getArticlebyNo(int no) {	// 글번호를 String값으로 받아옴	-x	
		Board board = null; 
//		int num = Integer.parseInt(no);	//숫자로 변환 - 필요 x
//		System.out.println(num);//숫자로 변환된 no, num 전달 확인
		String query = "select * from diaryboard where no=?";
		// 테이블에서 no(글번호)가 ?인 게시글 가져오기
		try {
			conn = JDBCUtil.getConnection(); //커넥션 가져오기			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);	//
			rs = pstmt.executeQuery();
			if(rs.next()) {
			board = new Board();
			board.setNo(rs.getInt("no"));
			board.setTitle(rs.getString("title"));
			board.setTag(rs.getString("tag"));
			board.setContents(rs.getString("contents"));
			board.setRegdate(rs.getTimestamp("regdate"));
			board.setHit(rs.getInt("hit"));			
			}	
//			addArticleHit(board.getNo());	//가져온 no로 조회수+1 (다시 불러오지 않으므로 view에 줄 데이터에는 반영x?)
		} catch (SQLException e) {
			System.out.println("로딩 실패");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);	//rs, pstmt, conn 닫기
		}
		return board;
	}
	
	public void addArticleHit(int no) {	//boardDAO내에서 부르는 것이 좋은지 Service에서 부르는 것이 좋은지??	
		String query = "update diaryboard set hit = ifnull(hit,0) +1 where no=?";
		// no(글번호)는 auto_increment, title, tag, contents는 입력받고 hit(조회수)는 등록시 0부터 
				try {
					conn = JDBCUtil.getConnection();	//커넥션 가져오기
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1,no);				
					pstmt.executeUpdate();
				} catch (SQLException e) {
					System.out.println("조회수 처리 실패");
					e.printStackTrace();
				} finally {
					JDBCUtil.close(pstmt, conn);	//pstmt 닫기
				}
	}
	

}
