package com.br.lis.model.board.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.CalendarBoardVo;

@Repository
public class CalendarBoardDaoImpl implements ICalendarBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.board.mapper.CalendarBoardDaoImpl.";

	@Override
	public List<CalendarBoardVo> viewAllCalendar() {
		List<CalendarBoardVo> lists = sqlSession.selectList(NS+"viewAllCalendar");
		
		return lists;
	}
	
}
