package com.br.lis.model.board.service;

import java.util.List;
import com.br.lis.vo.CalendarBoardVo;

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
		logger.info("CalendarBoardServiceImpl viewAllCalendar");
		return dao.viewAllCalendar();
	}
	
}
