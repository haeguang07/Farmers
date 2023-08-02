package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.vo.BoardVO;
import com.yedam.app.board.vo.ReplyVO;

public interface BoardMapper {

	// 메인 게시판 전체 조회
	public List<BoardVO> boardList(String postCtg, int pageNum, String dst1, String dst2);
	// 총 게시물 카운트
	public int listCount(String postCtg);
	// 메인 게시판 상세 조회
	public List<BoardVO> getBoardInfo(String postCtg, String boardNo);
	// 등록
	public int insertBoard(BoardVO vo); 
	// 수정
	public int updateBoard(BoardVO vo);
	// 삭제
	public int deleteBoard(String boardNo);
	
	
	// 댓글 조회
	public List<BoardVO> selReplyList(String boardNo);
	// 댓글 작성
	public int addReply(ReplyVO vo);
	// 댓글 삭제
	public int deleteReply(String cmtNo);
	
	// 조회수 증가
	public int hitCounting(String boardNo);
}
