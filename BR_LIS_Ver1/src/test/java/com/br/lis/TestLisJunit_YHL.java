package com.br.lis;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.lis.model.board.mapper.ICalendarBoardDao;
import com.br.lis.model.board.service.CalendarBoardServiceImpl;
import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.model.board.service.IFAQBoardService;
import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.model.board.service.NoticeBoardServiceImpl;
import com.br.lis.vo.CalendarBoardVo;
import com.br.lis.vo.Notice_FAQBoardVo;
import com.google.j2objc.annotations.AutoreleasePool;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml" })
public class TestLisJunit_YHL {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private ICalendarBoardService service;
	
	@Autowired
	private IFAQBoardService serviceFAQ;
	
	@Autowired
	private INoticeBoardService serviceNotice;
	
	@Autowired
	private CalendarBoardServiceImpl serviceCalendar;
	
	private final Logger logger = LoggerFactory.getLogger(TestLisJunit_YHL.class);
	
	
//	@Test
	public void viewAllFAQ() {
		
		System.out.println("Junit_viewAllFAQ_FAQ 전체 조회");
		List<Notice_FAQBoardVo> lists = serviceFAQ.viewAllFAQ();
		System.out.println(lists);
		System.out.println(lists);
		System.out.println(lists);
		
	}
	
//	@Test
	public void insertFAQ() {
		System.out.println("Junit_insertFAQ FAQ 입력");
		String admin_id ="admin001" ;
		String title = "대출한 책을 가까운 도서관에 반납할 수 있나요?";
		String content = "불가능 합니다. 죄송하지만 본 도서관은 상호대차 서비스를 제공하지 않고 있습니다.";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", admin_id);
		map.put("title", title);
		map.put("content", content);
		
		if (serviceFAQ.insertFAQ(map)>0) {
			System.out.println("새글 입력에 성공하였습니다.");
			
		}else {
			System.out.println("새글 입력에 실패하였습니다.");
		}
	}
		
//	@Test
	public void modifyFAQ() {
		System.out.println("Junit_modifyFAQ FAQ 수정");
		Notice_FAQBoardVo vo = new Notice_FAQBoardVo();
		vo.setFaq_seq("1");
		vo.setTitle("도서대출회원에 가입하려면 어떻게 해야 되나요?");
		vo.setContent("김포시민(주소가 등본 상 서울시로 되어 있는 사람, 서울시 소재 학교 및 직장을 다니는 사람)이여야 하며 회원가입절차는 다음과 같습니다.\r\n"
				+ "\r\n"
				+ "1. 본인인증(도서관 홈페이지 또는 방문)\r\n"
				+ "- 본인인증방법 : 인쇄된 등본 지참 혹은 홈페이지에 이미지 업로드\r\n"
				+ "- 도서관 홈페이지에서 이용시 :\r\n"
				+ "  로그인 화면으로 이동 > 도서대출 회원가입 > 회원가입\r\n"
				+ "- 도서관 방문하여 이용시 : 직원에게 문의하시면 안내해 드립니다.\r\n"
				+ "2. 회원가입 서류(확인)\r\n"
				+ "- 성 인 : 주소가 기재되어 있는 신분증(주소 미기재 시 등본 필요)\r\n"
				+ "- 재직자 : 김포시 주소가 기재된 재직증명서와 신분증\r\n"
				+ "- 학 생 : 학생증과 등본\r\n"
				+ "- 어린이(만14세 미만) : 부모님 신분증과 등본 지참, 부모님과 동행하여 방문\r\n"
				+ "(만14세 미만의 어린이는 법정대리인의 동의가 필요하므로 부모님 동행 필수)");
		serviceFAQ.modifyFAQ(vo);
	}
	
//	@Test
	public void deleteFAQ() {
		System.out.println("JUnit deleteFAQ FAQ 삭제");
		List<String> list = new ArrayList<String>();
		
		list.add("");
		list.add("");
		list.add("");
		
 		int cnt = serviceFAQ.deleteFAQ(list);
 		
		if (cnt>0) {
			System.out.println("글 삭제에 성공하였습니다 삭제한 글의 개수 : "+cnt);
		}else {
			System.out.println("글 삭제에 실패하였습니다.");
		}
	}
	
// Notice
	@Test
	public void viewAllNotice() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", "admin001");
		List<Notice_FAQBoardVo> lists = serviceNotice.viewAllNotice();
		System.out.println(lists);
		assertNotNull(lists);
	}
	
//	@Test
	public void insertNotice() {
		logger.info("JUnit insertNotice Test");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", "admin001");
		map.put("title", "쿠키런과 함께하는 글짓기 대회");
		map.put("content", "");
		int result = serviceNotice.insertNotice(map);
		System.out.println(result);
	}
	
//	@Test
	public void modifyNotice() {
		System.out.println("JUnit Test 10)글 수정 modifyBoard");
		Notice_FAQBoardVo vo = new Notice_FAQBoardVo();
		vo.setNotice_seq("notice_seq");
		vo.setTitle("글수정합니다");
		vo.setContent("수정수정");
		serviceNotice.modifyNotice(vo);
	}
	
//	@Test
	public void deleteNotice(List<String> chkBox) {
		System.out.println("JUnit deleteNotice");
		List<String> list = new ArrayList<String>();
		
		list.add("");
		list.add("");
		list.add("");
		
 		int cnt = serviceNotice.multiDelNotice(chkBox);
 		
		if (cnt>0) {
			System.out.println("글 삭제에 성공하였습니다 삭제한 글의 개수 : "+cnt);
		}else {
			System.out.println("글 삭제에 실패하였습니다.");
		}
	}
	
	@Test
	public void viewAllCalendar() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("admin_id", "admin001");
		List<CalendarBoardVo> lists = serviceCalendar.viewAllCalendar();
		System.out.println(lists);
		assertNotNull(lists);
		
	}
	
	
	
	
	
//	@Test
	public void insertCalendar(){
		/* (Map<String, String> map); */
		System.out.println("Junit_insertCalendar notice 입력");
		String admin_id ="admin001" ;
		String title = "여름휴가지를 도서관으로!";
		String content = "6월, 여름휴가지로 떠나지 않는 여러분을 위해 도서관에서 서비스를 제공합니다. 영화 상영 및 컨텐츠를 제공하고 있으니, 세부 일정은 공지사항을 확인 부탁드립니다.";
		String start_date = "2022-06-04";
		String end_date = "2022-06-30";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("admin_id", admin_id);
		map.put("title", title);
		map.put("content", content);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		
		if (serviceCalendar.insertCalendar(map)>0) {
			System.out.println("새글 입력에 성공하였습니다.");
			
		}else {
			System.out.println("새글 입력에 실패하였습니다.");
		}
		
		
	}

//	@Test
	public void modifyCalendar(){
		System.out.println("Junit_modifyCalendar notice수정");
		String calendar_seq = "8";
		String admin_id ="admin001" ;
		String title = "여름휴가지를 도서관으로!";
		String content = "6월, 여름휴가지로 떠나지 않는 여러분을 위해 도서관에서 서비스를 제공합니다. 영화 상영 및 컨텐츠를 제공하고 있으니, 세부 일정은 공지사항을 확인 부탁드립니다.";
		String start_date = "2022-06-04";
		String end_date = "2022-06-25";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("admin_id", admin_id);
		map.put("title", title);
		map.put("content", content);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		/* (Map<String, Object> map); */
	}

//	@Test
	public void deleteCalendar(){
		/*(List<String> seqs);  */    
		System.out.println("JUnit Test 12)글 삭제(DB삭제, ) deleteCalendar");
		List<String> list = new ArrayList<String>();
		
		list.add("21");
		list.add("23");
		list.add("24");
		
 		int cnt = serviceCalendar.deleteCalendar(list);
 		
		if (cnt>0) {
			System.out.println("글 삭제에 성공하였습니다 삭제한 글의 개수 : "+cnt);
		}else {
			System.out.println("글 삭제에 실패하였습니다.");
		}
	}
}         


