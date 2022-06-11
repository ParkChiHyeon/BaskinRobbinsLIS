package com.br.lis.util;

import java.util.List;

import com.br.lis.vo.CalendarBoardVo;

public final class CalendarUtil {

	
	//한자리의 날짜를 두자리의 날짜로 변경함 ex)1->01
	public static String isTwoWord(int month) {
		String m = String.valueOf(month);
		return (m.length()==1) ? "0"+m:m; 
	}
	
	//요일별 날짜 표기의 색 변경
	public static String fontColor(int date, int dayOfWeek) {
		int dayCal = (dayOfWeek-1+date)%7;
		if (dayCal==0) {
			return "blue";
		}else if(dayCal==1) {
			return "red";
		}else {
			return "black";
		}
	}
	
	//일의 리스트의 화면을 구성
	public static String getCalView(int i, List<CalendarBoardVo> cList) {
		String res = "";
		String d = isTwoWord(i);
		for (CalendarBoardVo dto : cList) {
			if(dto.getRegdate().substring(6, 8).equals(d)) { // 입력받은 일과 List의 일의 같은 값을 확인
				StringBuffer sb = new StringBuffer();
				sb.append("<p title="+dto.getTitle()+">");
				//제목의 길이 표시
				if(dto.getTitle().length()>5) {
					sb.append(dto.getTitle().subSequence(0, 6));
					sb.append("...");
				}else {
					sb.append(dto.getTitle());
				}
				sb.append("</p>");
				res+=sb.toString();
			}
		}
		return res;
	}
}
