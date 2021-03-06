package com.br.lis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.model.board.service.IFAQBoardService;
import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.util.ElasticSearchModule;
import com.br.lis.vo.CalendarBoardVo;
import com.br.lis.vo.Notice_FAQBoardVo;


@Controller
@SessionAttributes("pageNum")
public class Board_Controller {

	/*
	 * 게시판 흐름제어 클래스
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICalendarBoardService icalService;
	@Autowired
	private IFAQBoardService ifaqService;
	@Autowired
	private INoticeBoardService inoticeService;

	@Autowired
	private IAdminService iadminService;

	//noticeboard & faq & calendar전체 조회 화면 이동
	@RequestMapping(value = "/viewAllBoard.do", method = RequestMethod.GET)
	public String BoardSelect(Model model,String kind) {
		logger.info("Board_Controller BoardSelect 리스트보기");
		//		List<Notice_FAQBoardVo> lists = inoticeService.viewAllNotice();
		//		model.addAttribute("lists", lists);
		model.addAttribute("kind", kind);
//		model.addAttribute("session", "admin");
		if(kind.equals("faq")||kind.equals("notice")) {

			return "noticeboard";
		}
		else if(kind.equals("calendar")){
			return "calendarboard";
		}
		else {
			return "home";
		}
	}

	//글입력화면 이동
	@RequestMapping(value = "/editor.do",method = RequestMethod.GET)
	public String ckEditorView(String kind,Model model) {
		logger.info("eidotr view 이동");
		//		if(kind.equals("notice")) {
		//			int n = inoticeService.nextVal();
		//			model.addAttribute("nextSeq", n);
		//		}
		model.addAttribute("kind", kind);
		return "insertBoard";
	}
	
	//공지글 수정화면 이동
	@RequestMapping(value = "/modifynotice.do",method = RequestMethod.GET)
	public String modifyNotice(String kind,Model model,String seq) {
		logger.info("modifynotice이동");
		
		model.addAttribute("kind", kind);
		Notice_FAQBoardVo vo = inoticeService.viewDetailNotice(seq);
		model.addAttribute("dto", vo);
				
		return "modifynotice";
	}
	

	
	//noticeboard 상세보기
	@RequestMapping(value = "/detailnotice.do", method = RequestMethod.GET)
	public String viewDetailNotice(Model model,String seq) {
		logger.info("Board_Controller_viewDetailNotice 공지사항 상세보기");
		Notice_FAQBoardVo vo = inoticeService.viewDetailNotice(seq);
		model.addAttribute("dto", vo);
		model.addAttribute("kind", "notice");
		return "detailboard";
	}
	//notice 다중삭제
	@RequestMapping(value = "/multiDelNotice.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDelNotice(@RequestParam List<String> chkBox) throws IOException{
		logger.info("Board_Controller multiDelNotice:{}", chkBox);
		//			if (aVo.getAdmin_id().equals(aVo)) {
		int n = inoticeService.multiDelNotice(chkBox);
		if(n>0) {
			ElasticSearchModule delete = new ElasticSearchModule();
			for (String id : chkBox) {
				delete.deleteElastic("notice_board",id);
			}
		}
		//			}else { //세션 붙일경우 고쳐야함
		//				return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
		//			}
		return "redirect:/viewAllBoard.do?kind=notice";
	}
	//--------------------------------------------FAQ-----------------------------------

	//FAQ상세보기
	@RequestMapping(value = "/detailfaq.do", method = RequestMethod.GET)
	public String viewDetailFAQ(Model model,String seq) {
		logger.info("Board_Controller_viewDetailFAQ FAQ 상세보기");
		Notice_FAQBoardVo vo = ifaqService.viewDetailFAQ(seq);
		model.addAttribute("vo", vo);
		model.addAttribute("kind", "faq");
		return "detailboard";
	}


	//FAQ게시판 새글 입력화면
	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.POST)
	public String insertFAQ(Model model, @RequestParam Map<String, Object> map) throws IOException{
		logger.info("Board_Controller insertNoticeBoard : {}",map);
		System.out.println(map);
		model.addAttribute("kind", "faq");
		int n = ifaqService.insertFAQ(map);

		if(n>0) {
			ElasticSearchModule insertFAQ = new ElasticSearchModule();
			insertFAQ.insertElasticMap(map, "faq_board", map.get("faq_seq").toString());
			return "redirect:/viewAllBoard.do";
		}else {
			StringBuffer sb= new StringBuffer();
			sb.append("<script>");
			sb.append("alert('입력실패 관리자에게 문의하세요');");
			sb.append("location.href='./home.do'");
			sb.append("</script>");
			return sb.toString();
		}
	}
	
	
	//FAQ글 수정화면 이동
	@RequestMapping(value = "/modifyFAQ.do",method = RequestMethod.GET)
	public String modifyFAQView(String kind,Model model,String seq) {
		logger.info("modifyFAQ 이동");

		model.addAttribute("kind", kind);
		Notice_FAQBoardVo fVo = ifaqService.viewDetailFAQ(seq);
		model.addAttribute("fVo", fVo);

		return "modifynotice";
	}
	//FAQ글 수정
	@RequestMapping(value = "/modifyFAQ.do",method = RequestMethod.POST)
	public String modifyFAQ(String kind,Model model,@RequestParam Map<String, Object> map) throws IOException {
		logger.info("Board_Controller modifyFAQ 수정");
		logger.info("map:{}", map);
		
		model.addAttribute("kind", "faq");
		int cnt = ifaqService.modifyFAQ(map);

		if (cnt>0) {
			ElasticSearchModule updateFAQ = new ElasticSearchModule();
			updateFAQ.updateElasticMap(map, "faq_board", map.get("faq_seq").toString());
			return "redirect:/viewAllBoard.do";
		}else {
			return "redirect:/detailfaq.do?seq="+map.get("faq_seq").toString();
		}
	}
	//FAQ 다중삭제
	@RequestMapping(value = "/multiDelFAQ.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDelFAQ(@RequestParam("chkBox") List<String> chkBox) throws IOException{
		System.out.println("---------------------------------------------------------------------------------------------");
		logger.info("Board_Controller multiDel:{}", chkBox);
		//		if (aVo.getAdmin_id().equals(aVo)) {
		int n = ifaqService.deleteFAQ(chkBox);
		
		if(n>0) {
			ElasticSearchModule delete = new ElasticSearchModule();
			for (String id : chkBox) {
				delete.deleteElastic("faq_board",id);
			}
		}
		//		}else { //세션 붙일경우 고쳐야함
		//			return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
		//		}
		return "redirect:/viewAllBoard.do?kind=faq";
	}
	//-------------------------------------Calendar-------------------------------------

	//일정게시판 상세보기
	@RequestMapping(value = "/detailcalendar.do", method = RequestMethod.GET)
	public String viewDetailCalendar(Model model,String seq) {
		logger.info("Board_Controller_viewDetailCalendar 캘린더 상세보기");
		CalendarBoardVo vo = icalService.viewDetailCalendar(seq);
		model.addAttribute("vo", vo);
		model.addAttribute("kind", "calendar");
		return "detailboard";
	}

	//일정게시판 새글입력
	@RequestMapping(value = "/insertCalendar.do", method = RequestMethod.POST)
	public String insertCalendarBoard(Model model, @RequestParam Map<String, Object> map) throws IOException {
		logger.info("Board_Controller  insertCalendarBoard : {}",map);
		System.out.println(map);
		model.addAttribute("kind", "calendar");
		int n = icalService.insertCalendar(map);

		if(n>0) {
			ElasticSearchModule insertFAQ = new ElasticSearchModule();
			insertFAQ.insertElasticMap(map, "calendar_board", map.get("calendar_seq").toString());
			return "redirect:/viewAllBoard.do";
		}else {
			StringBuffer sb= new StringBuffer();
			sb.append("<script>");
			sb.append("alert('입력실패 관리자에게 문의하세요');");
			sb.append("location.href='./home.do'");
			sb.append("</script>");
			return sb.toString();
		}
	}

	//Calendar글 수정화면 이동
	@RequestMapping(value = "/modifyCalendar.do",method = RequestMethod.GET)
	public String modifyCalendar(String kind,Model model,String seq) {
		logger.info("modifyFAQ 이동");

		model.addAttribute("kind", kind);
		CalendarBoardVo cVo = icalService.viewDetailCalendar(seq);
		model.addAttribute("cVo", cVo);

		return "modifynotice";
	}

	//일정게시판 수정
	@RequestMapping(value = "/modifyCalendar.do", method = RequestMethod.POST)
	public String modifyCalendar(@RequestParam Map<String, Object> map, Model model) throws IOException{
		logger.info("Board_Controller modifyCalendar 에디터로 입력받음");
		logger.info("map:{}", map);
		model.addAttribute("kind", "calendar");
		int cnt = icalService.modifyCalendar(map);

		if (cnt>0) {
			ElasticSearchModule updateCalendar = new ElasticSearchModule();
			updateCalendar.updateElasticMap(map, "calendar_board", map.get("calendar_seq").toString());
			return "calendarboard";

		}else {
			return "calendarboard";
		}
	}

	//일정게시판 삭제
	@RequestMapping(value = "/multiDelCalendar.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDelCalendar(@RequestParam List<String> chkBox) throws IOException{
		logger.info("Board_Controller multiDelCalendar:{}", chkBox);
		//				logger.info("Board_Controller multiDelCalendar:{}", seq);

		//				if (aVo.getAdmin_id().equals(aVo)) {
		int n = icalService.multiDelCalendar(chkBox);
		if(n>0) {
			ElasticSearchModule delete = new ElasticSearchModule();
			for (String id : chkBox) {
				delete.deleteElastic("calendar_board",id);
			}
		}
		//				}else { //세션 붙일경우 고쳐야함
		//					return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
		//				}
		return "calendarboard";
	}
}
