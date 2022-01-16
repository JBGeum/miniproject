package com.stone.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.springmvc.common.Board;
import com.stone.springmvc.dataservice.BoardDAO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
	public int insertBoard(Board board) {
		//업무 규칙 없음
		//글 등록
		return boardDAO.insertBoard(board);
	}
	
	public Board getArticlebyNo(int no) {		
		Board board = boardDAO.getArticlebyNo(no);
		boardDAO.addArticleHit(board.getNo());
		return board;
	}
	
	public Board getLastArticle() {
		Board board = boardDAO.getLastArticle();
		boardDAO.addArticleHit(board.getNo());
		return board;
	}
	
	public void writeArticle() {
		// 글쓰기화면용
	}

	public int updateBoard(Board board) {		
		return boardDAO.updateBoard(board);
	}

	public int deleteBoard(int no) {
		return boardDAO.deleteBoard(no);
	}
	public List<Board> getArticleList() {		
		return boardDAO.getArticleList();
	}

	
	public List<Board> getArticlebyTag(String tag) {		
		return boardDAO.getArticlebyTag(tag);
	}
}
