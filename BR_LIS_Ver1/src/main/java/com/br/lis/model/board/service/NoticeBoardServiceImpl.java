package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.board.mapper.INoticeBoardDao;
import com.br.lis.vo.Notice_FAQBoardVo;

@Service
public class NoticeBoardServiceImpl implements INoticeBoardService {

	@Autowired
	private INoticeBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Notice_FAQBoardVo viewDetailNotice(String seq) {
		logger.info("NoticeBoardServiceImpl viewDetailNotice :{}", seq);
		return dao.viewDetailNotice(seq);
	}

	@Override
	public int insertNotice(Map<String, Object> map) {
		logger.info("NoticeBoardServiceImpl insertNotice :", map);
		return dao.insertNotice(map);
	}

//	@Override
//	public List<Notice_FAQBoardVo> selectPaging(Map<String, Object> map) {
//		logger.info("NoticeBoardServiceImpl selectPaging :", map);
//		return dao.selectPaging(map);
//	}
//
//	@Override
//	public int getCountBoardList() {
//		logger.info("NoticeBoardServiceImpl getCountBoardList :{}");
//		return dao.getCountBoardList();
//	}

	@Override
	public int modifyNotice(Notice_FAQBoardVo noticeVo) {
		logger.info("NoticeBoardServiceImpl modifyNotice ()");
		return dao.modifyNotice(noticeVo);
	}

	@Override
	public List<Notice_FAQBoardVo> viewAllNotice() {
		logger.info("NoticeBoardServiceImpl viewaAllNotice()");
		return dao.viewAllNotice();
	}

	@Override
	public int multiDelNotice(List<String> chkBox) {
		logger.info("NoticeBoardServiceImpl deleteNotice :{}");
		return dao.multiDelNotice(chkBox) ;
	}
	
}
