package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.Notice_FAQBoardVo;

public interface INoticeBoardService {
	
	//상세조회 
	public Notice_FAQBoardVo viewDetailNotice(String seq);
	
	//전체조회 
	public List<Notice_FAQBoardVo> viewAllNotice();

	//searchNotice 엘라스틱
	
	//새 글 입력
	public int insertNotice(Map<String, Object> map);
	
	// 글 수정
	public int modifyNotice(Notice_FAQBoardVo noticeVo);
	
	//다중삭제
	public int multiDelNotice(List<String> chkBox);
	
	
//	//페이징
//	public List<Notice_FAQBoardVo> selectPaging(Map<String, Object> map); 
//	public int getCountBoardList();

	
}
