package com.br.lis.model.board.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.CalendarBoardVo;
import com.br.lis.vo.Notice_FAQBoardVo;

public interface ICalendarBoardService {
	
	public List<CalendarBoardVo> viewAllCalendar();
	
	public CalendarBoardVo viewDetailCalendar(String seq);
	
	public int insertCalendar(Map<String, Object> map);
	
	public int modifyCalendar(Map<String, Object> map);
	
	public int multiDelCalendar(List<String> list);
	
}
