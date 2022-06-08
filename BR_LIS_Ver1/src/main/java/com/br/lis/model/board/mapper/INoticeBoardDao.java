package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.Notice_FAQBoardVo;

public interface INoticeBoardDao {

		//전체조회 
		public List<Notice_FAQBoardVo> viewAllNotice();
		
		//상세조회 
		public Notice_FAQBoardVo viewDetailNotice(String seq);
		
		//새 글 입력
		public int insertNotice(Map<String, Object> map);
		// 글 수정
		public int modifyNotice(Notice_FAQBoardVo noticeVo);
		
		//삭제
		public int deleteNotice(List<String> seqs);
		

		//페이징
		public List<Notice_FAQBoardVo> selectPaging(Map<String, Object> map); 
		public int getCountBoardList();
		
	
}
