package com.br.lis.model.board.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarBoardDaoImpl implements ICalendarBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="";
	
}
