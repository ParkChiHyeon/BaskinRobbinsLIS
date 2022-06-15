package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.CalendarBoardVo;

public interface ICalendarBoardDao {

	public List<CalendarBoardVo> viewAllCalendar();
	
	public CalendarBoardVo viewDetailCalendar(String seq);
	
	public int insertCalendar(Map<String, Object> map);
	
	public int modifyCalendar(Map<String, Object> map);
	
	public int multiDelCalendar(List<String> list, String seq);

}
