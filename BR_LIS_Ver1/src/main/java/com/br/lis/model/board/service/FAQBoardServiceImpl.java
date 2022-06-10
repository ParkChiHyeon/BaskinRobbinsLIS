package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.board.mapper.IFAQBoardDao;
import com.br.lis.vo.Notice_FAQBoardVo;


@Service
public class FAQBoardServiceImpl implements IFAQBoardService {

	@Autowired
	private IFAQBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Notice_FAQBoardVo> viewAllFAQ() {
		logger.info("FAQBoardServiceImpl viewAllFAQ");
		return dao.viewAllFAQ();
	}

	@Override
	public int insertFAQ(Map<String, String> map) {
		logger.info("FAQBoardServiceImpl insertFAQ()");
		return dao.insertFAQ(map);
	}

	@Override
	public int modifyFAQ(Notice_FAQBoardVo vo) {
		logger.info("FAQBoardServiceImpl modifyFAQ()");
		return dao.modifyFAQ(vo);
	}


	@Override
	public Notice_FAQBoardVo viewDetailFAQ(String seq) {
		logger.info("FAQBoardServiceImpl viewDetailFAQ()");
		return dao.viewDetailFAQ(seq);
	}

	@Override
	public int deleteFAQ(List<String> list) {
		logger.info("FAQBoardServiceImpl deleteFAQ()");
		return dao.deleteFAQ(list);
	}
	
	
}
