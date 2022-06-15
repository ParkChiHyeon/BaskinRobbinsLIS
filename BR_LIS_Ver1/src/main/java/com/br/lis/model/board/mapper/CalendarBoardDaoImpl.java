package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

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
		return sqlSession.selectList(NS+"viewAllCalendar");
	}
	
	@Override
	public CalendarBoardVo viewDetailCalendar(String seq) {
		return sqlSession.selectOne(NS+"viewDetailCalendar", seq);
	}
	
	@Override
	public int insertCalendar(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertCalendar", map);
	}

	@Override
	public int modifyCalendar(Map<String, Object> map) {
		return sqlSession.update(NS+"modifyCalendar", map);
	}

	@Override
	public int multiDelCalendar(List<String> list) {
		return sqlSession.delete(NS+"multiDelCalendar", list);
	}




	
}
