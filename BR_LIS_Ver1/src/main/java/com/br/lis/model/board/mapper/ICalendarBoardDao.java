package com.br.lis.model.board.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.CalendarBoardVo;

public interface ICalendarBoardDao {

	public int insertCalendar(Map<String, String> map);
	public int modifyCalendar(Map<String, Object> map);
	public int deleteCalendar(List<String> seqs);

}
