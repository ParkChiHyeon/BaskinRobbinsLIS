package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.CalendarBoardVo;
import com.br.lis.vo.Notice_FAQBoardVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.board.mapper.ICalendarBoardDao;

@Service
public class CalendarBoardServiceImpl implements ICalendarBoardService {

	@Autowired
	private ICalendarBoardDao dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<CalendarBoardVo> viewAllCalendar() {
		logger.info("CalendarBoardServiceImpl viewAllNotice:{}");
		return dao.viewAllCalendar();
	}
	
	@Override
	public CalendarBoardVo viewDetailCalendar(String seq) {
		logger.info("CalendarBoardServiceImpl viewAllNotice:{}");
		return dao.viewDetailCalendar(seq);
	}
	
	@Override
	public int insertCalendar(Map<String, Object> map) {
		logger.info("CalendarBoardServiceImpl insertBoardAnswer : {}",map);
		return dao.insertCalendar(map);
	}

	@Override
	public int modifyCalendar(Map<String, Object> map) {
		logger.info("CalendarBoardServiceImpl modifyCalendar : {}",map);
		return dao.modifyCalendar(map);
	}

	@Override
	public int multiDelCalendar(List<String> list) {
		logger.info("CalendarBoardServiceImpl multiDelCalendar : {}");
		return dao.multiDelCalendar(list);
	}
	
}
