package com.stone.springmvc.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stone.springmvc.common.Board;
import com.stone.springmvc.service.BoardService;

@Controller
public class BoardControl {
	@Autowired
	BoardService boardService;
	

	@RequestMapping("test")	// html 테스트용 임시 페이지
	ModelAndView fortest( ) {		
		ModelAndView  mv =new ModelAndView();
		mv.setViewName("NewFile");
		return mv;
	}
	
	@RequestMapping("write")	// 글 작성
	ModelAndView writeArticle( ) {
		boardService.writeArticle();
		ModelAndView  mv =new ModelAndView();
		mv.setViewName("write");
		return mv;
	}
	
	@RequestMapping("insert")	// 글 DB에 등록
	ModelAndView insertBoard(Board board) {
		int result = boardService.insertBoard(board);
		ModelAndView mv = new ModelAndView();
		if(result == 1) {
			mv.setViewName("forward:/home");	//일단 home으로 연결(입력완료 페이지 가능하면)	
		} else {
			System.out.println("등록오류>write로");
			mv.setViewName("write");
		}		
		return mv;
	}
	
	@RequestMapping("detail")	// 게시글 상세 표시(글번호로 조회)
	ModelAndView getArticlebyNo(int no) { // @RequestParam("no") String no) {	//requestparam을 이용해 query string방식으로 글번호 가져오기
//		System.out.println("control에서, no : " + no);
		Board board = boardService.getArticlebyNo(no);	//글번호 전달해서 서비스로
		ModelAndView mv = new ModelAndView();
		mv.setViewName("detail");		//detail.jsp로 이동
		mv.addObject("board",board);	//no에 따라 조회한 board 전송
		return mv;
	}
	
	@RequestMapping("home")	//최신 게시글 표시
	ModelAndView defaultList() {
		Board board = boardService.getLastArticle();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("board",board);
		return mv;
	}
	
	@RequestMapping("edit")	// 게시글 수정 페이지(글번호로 조회) - detail과 동일하게 글번호로 조회하지만 수정가능한 페이지로
	ModelAndView editArticle(int no) {	//int no 로 두어도 가능한지
//		System.out.println("control에서, no : " + no);
		Board board = boardService.getArticlebyNo(no);	//글번호 전달해서 서비스로
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edit");		// edit.jsp로 이동
		mv.addObject("board",board);	//no에 따라 조회한 board 전송
		return mv;
	}
	
	@RequestMapping("update")	// 글 DB에 수정
	ModelAndView updateBoard(Board board) {	//edit.jsp에서 form으로 받아온 데이터 포함
		int result = boardService.updateBoard(board);
		ModelAndView mv = new ModelAndView();
		if(result == 1) {

			mv.setViewName("detail");		//detail.jsp로 이동
			Board board2 = boardService.getArticlebyNo(board.getNo());	//그대로 board 재전송하면 날짜가 null이 되어서 새로 로딩	
			mv.addObject("board",board2);			
		} else {

			mv.setViewName("write");
		}		
		return mv;
	}
	
	@RequestMapping("delete")	// 글 삭제
	ModelAndView deleteBoard(int no) {	
		int result = boardService.deleteBoard(no);
		ModelAndView mv = new ModelAndView();
		if(result == 1) {
			mv.setViewName("forward:/home");
		} else {
			System.out.println("삭제오류(되돌아가기)");
			mv.setViewName("list");
		}		
		return mv;
	}
	
	@RequestMapping("error")	// 오류시 연결 페이지
	ModelAndView errorPage() {	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");				
		return mv;
	}

	@RequestMapping("list")	// 전체게시물목록확인
	ModelAndView getArticleList() {
		List<Board> boards = boardService.getArticleList();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("entirelist");
		mv.addObject("boards",boards);
		return mv;
	}
	/*	
	@RequestMapping("list")
	// url의 pageno(필수아님, 디폴트 값 1)를 받아와 int pageNo로 등록
	ModelAndView getArticleList(@RequestParam(value="pageno",required=false,defaultValue="1")int pageNo) {
		int topNo = 0;		// 해당 페이지 맨 위(최신) 게시글 숫자
		int bottomNo = 0;	// 해당 패이지 맨 아래(구) 게시글 숫자
		int totalArticleNum = 0;	//총 게시글 수의 합(DB에서 count얻어온 현제 게시물 숫자
		int articlePerPage=5;	//페이지 당 게시글 수
//		해당 페이지 맨 위 게시글 번호 = 총 게시물 수 - (현재 페이지 숫자 - 1) * 페이지 당 게시글 수
//		해당 페이지 맨 아래 게시글 번호 = 총 게시물 수 - (현재 페이지 숫자) * 페이지 당 게시글 수 + 1
//		ex> 총 게시물 수 79, 현재 2 페이지 일때 : 79-((2-1)*5)=74 ~ 79-(2*5)+1=70  -> 74, 73, 72, 71, 70 이 표시
		topNo = totalArticleNum - ((pageNo-1)*articlePerPage);
		bottomNo=totalArticleNum - (pageNo*articlePerPage) +1;

		Object[] listBoardwithNum = boardService.
		Object[] 수집된게시물들과게시물수=게시물관리.게시물목록을수집하다(시작게시물일련번호,페이지당게시물수);
		
		int 수집된게시물수=(int)수집된게시물들과게시물수[1];             //테이블 내의 게시물수가 아니다.
        //페이지당게시물수로 나눈 나머지가 있으면  한페이지를 추가
        int 마지막페이지번호=(int)(수집된게시물수/페이지당게시물수)+((수집된게시물수%페이지당게시물수>0)?1:0);
		
		ModelAndView  mv =new ModelAndView();
		mv.setViewName("게시물목록창");
		mv.addObject("boards", 수집된게시물들과게시물수[0]);
		mv.addObject("pageNo", 페이지번호);
		mv.addObject("lastPageNo", 마지막페이지번호);
		return mv;
	}
	
*/	
	
	
	@RequestMapping("search")	// 게시글 태그로 검색 > 목록표시
	ModelAndView getArticlebyTag(String tag) { // @RequestParam("no") String no) {	//requestparam을 이용해 query string방식으로 글번호 가져오기
//		System.out.println("control에서, no : " + no);
		List<Board> boards = boardService.getArticlebyTag(tag);	//글번호 전달해서 서비스로
		ModelAndView mv = new ModelAndView();
		mv.setViewName("taglist");		//detail.jsp로 이동
		mv.addObject("boards",boards);	//no에 따라 조회한 board 전송
		return mv;
	}
	
}
