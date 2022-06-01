package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.Notice_FAQBoardVo;

public interface IFAQBoardDao {

	List<Notice_FAQBoardVo> viewAllFAQ();

	int insertFAQ(Map<String, String> map);

	int modifyFAQ(Notice_FAQBoardVo vo);

	int deleteFAQ(List<String> list);

}
