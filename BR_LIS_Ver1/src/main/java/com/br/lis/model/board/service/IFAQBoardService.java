package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.Notice_FAQBoardVo;

public interface IFAQBoardService {

	
	public List<Notice_FAQBoardVo> viewAllFAQ();
	
	public int insertFAQ(Map<String, String> map); 
	
	public int modifyFAQ(Notice_FAQBoardVo vo);
	
	public int deleteFAQ(List<String> list);

	
	
}
