package com.br.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.json.XML;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.vo.CalendarBoardVo;

@Controller
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICalendarBoardService service;
	
	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET, produces = "application/text;charset-UTF-8" )
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date today = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
		String year  = simpleDateFormat.format(today);
		model.addAttribute("serverTime", year);
		
		
		return "calendar";
	}
	
	/**
	 * API를 요청하여 공휴일 정보를 받아온다음 toast의 createSchedule 형식에 맞게 데이터를 가공하여 JSONArray로 만들어 보내준다
	 * 1월1일 신정의 경우 이름이 다른 경우가 있어 처리해주었음
	 * @param sunYear 받은 양력 년도
	 * @param sunMonth 받은 양력 월
	 * @param sunDay 받은 양력 일
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/restDay.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONArray restDay(@RequestParam String year) throws IOException{
		String key = "fET3a8HPdgurj21NIvYv%2BpPB3hJ5cgyRtZj4oV%2FU36V7d80cPhkpJ4O5SUR4FHhdenvXtgrCH9Upl6VO8YfFBQ%3D%3D";
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(year, "UTF-8")); /*연*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String result = rd.readLine();
        rd.close();
        conn.disconnect();
        System.out.println(result);
        
        JSONObject json =  XML.toJSONObject(result);//읽어온 xml을 JSON으로 parsing(이 API의 경우 응답을 xml타입으로 줌)
        System.out.println(json);
        JSONObject i = (JSONObject)json.get("response");
        JSONObject i2 = (JSONObject)i.get("body");
        JSONObject i3 = (JSONObject)i2.get("items");
        org.json.JSONArray i4 = i3.getJSONArray("item");
        
        JSONArray arr = new JSONArray();//JSONArray[JSONObject] 형태로 만들어서 보내줌
        JSONArray sulArr = new JSONArray();// 설날-설날-설날 의 형태로 나오기 때문에 바꾸어주어야함
        JSONArray chuArr = new JSONArray();// 추석-추석-추석
        
		for (int j = 0; j < i4.length(); j++) {
			String date = i4.getJSONObject(j).get("locdate").toString().substring(0,4) + "-"
					+i4.getJSONObject(j).get("locdate").toString().substring(4,6) + "-"
					+i4.getJSONObject(j).get("locdate").toString().substring(6,8);
			JSONObject jsonObj = new JSONObject();
			if(i4.getJSONObject(j).get("dateName").toString().equals("신정")||date.equals(year+"-01-01")) { 
				jsonObj.put("title", "신정");// 1월1일이라고 표기되어 있는 년도가 있음
			}else{
				jsonObj.put("title", i4.getJSONObject(j).get("dateName"));
			}
			jsonObj.put("id", "holiday"+i4.getJSONObject(j).get("dateName"));
			jsonObj.put("category", "time");
			jsonObj.put("start", date);
			jsonObj.put("end", date);
			jsonObj.put("isAllDay","true");
			jsonObj.put("isReadOnly","true");
			jsonObj.put("color","white");
			jsonObj.put("bgColor","red");
			if(i4.getJSONObject(j).get("dateName").toString().equals("설날")) {//설날 정보 따로 저장
				sulArr.add(jsonObj);
			}else if(i4.getJSONObject(j).get("dateName").toString().equals("추석")) {//추석 정보 따로 저장
				chuArr.add(jsonObj);
			}else {
				arr.add(jsonObj);
			}
		}
		
		//설날의 마지막날의 end 값을 설 첫날의 end로 변경 
//		JSONObject endInfo = (JSONObject)sulArr.get(sulArr.size()-1);
//		String endDate = endInfo.getString("end");
//		JSONObject sulNal = (JSONObject)sulArr.get(0);
//		sulNal.put("end", endDate);
//		arr.add(sulNal);
		
		arr.add(startEndChange(sulArr));
		arr.add(startEndChange(chuArr));
		
		
		
		System.out.println(arr.toJSONString());
		return arr;
	}

	/**
	 * DB에서 일정 목록을 불러와서 JSONArray[JSONObject] 형태로 만들어서 보내줌
	 * key의 경우 https://nhn.github.io/tui.calendar/latest/Schedule 에서 확인
	 * @return DB에 저장된 일정 목록
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/callSchedule.do",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray callSchedule() {
		List<CalendarBoardVo> lists = service.viewAllCalendar();//DB에서 일정목록을 불러옴
		JSONArray arr = new JSONArray();//JSONArray[JSONObject] 형태로 만들어서 보내줌
		for (int i = 0; i < lists.size(); i++) {
			org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();
			jsonObj.put("admin_id", lists.get(i).getAdmin_id());
			jsonObj.put("title", lists.get(i).getTitle());
			jsonObj.put("category", "time");
			jsonObj.put("start", lists.get(i).getStart_date());
			jsonObj.put("end", lists.get(i).getEnd_date());
			jsonObj.put("isAllDay","true");
			arr.add(jsonObj);
		}
		System.out.println(arr.toJSONString());
		return arr;
	}
	
	
	
	/**
	 * 시작일의 end를 마지막날의 end로 바꾸어줌
	 * 추석, 설날의 경우 설날-설날-설날의 형태로 출력되어 수정이 필요하였음
	 * @return 끝나는 날의 end 날짜를 가져와 첫날의 end로 바꾸어준 첫날의 값
	 */
	public JSONObject startEndChange(JSONArray jsonArr) {
		JSONObject endInfo = (JSONObject)jsonArr.get(jsonArr.size()-1);
		String endDate = endInfo.getString("end");//
		JSONObject info = (JSONObject)jsonArr.get(0);
		info.put("end", endDate);	//
		return info;
	
	}
	
	
	
	
}

