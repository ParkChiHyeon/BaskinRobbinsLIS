package com.br.lis;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.lis.model.board.mapper.ICalendarBoardDao;
import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.model.test.service.ITestService;
import com.br.lis.vo.CalendarBoardVo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_YHL {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private ICalendarBoardService service;

	
	@Test
	public void test() {
		List<CalendarBoardVo> lists = service.viewAllCalendar();
		System.out.println("--------CalendarBoardVo--------------------viewAllCalendar---------------------------------------------------");

		System.out.println(lists);
	
	}
}
