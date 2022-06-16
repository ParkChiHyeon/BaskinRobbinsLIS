package com.br.lis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

@Controller
@SessionAttributes("member")
public class LendingBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILendingBookService service;
	
	@Autowired
	private ILibMemberService mService;
	
	@Autowired
	private IAdminService aService;
	
	@RequestMapping(value = "/lendingBookMem.do", method = RequestMethod.GET )
	public  String lendingLIst(HttpSession session,Model model) {
		
		logger.info("대출한 도서내역");
		logger.info("해당아이디로 조회하기-> 마이페이지 가야함");
		Map<String, Object> map2 = new  HashMap<String, Object>();
		//아이디 직접입력
//		map2.put("member_id", "user001");
		
		
		//세션받아서 id 값 가져와야함
//		String mb =mVo.getMember_id();
//		System.out.println(mb+"멤버아이디1111&&&&&&&&&&&&&&");
//		System.out.println(mVo+"멤버아이디22222&&&&&&&&&&&&&&");
		
		LibMemberVo mVo = (LibMemberVo) session.getAttribute("member");
		String memberid = mVo.getMember_id();
		List<LendBookBean> listBean = new ArrayList<LendBookBean>();
		listBean= service.lendingList(memberid);
		
		System.out.println(map2+"1111%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(listBean+"2222%%%%%%%%%%%%%%%%%%%%%");
		
		model.addAttribute("listBean",listBean);
		model.addAttribute("member",mVo);
		model.addAttribute("page", "lendingLIst");
		return "myPage";
	}

	
	
	
	@RequestMapping(value = "/adminLenList.do", method=RequestMethod.GET)
	public String adminLenList(Model model) {
		logger.info("예약목록 전체조회(관리자) ");
//		List<BookInfoVo>lists = service.allReserveLending();
		Map<String, Object> map1 = new  HashMap<String, Object>();
		List<LendBookBean> reBook = new ArrayList<LendBookBean>();
		reBook= service.allReserveLending(map1);
		model.addAttribute("reBook",reBook);
//		model.addAttribute("lists", lists);
		return "adminLenList";
	}
	
	@RequestMapping(value ="/reserveBookList.do", method = RequestMethod.GET)
	public String reserveBookList(Model model, HttpSession session) {
		logger.info("예약목록조회(회원)");
//		Map<String, String> map = new  HashMap<String, String>();
//		List<Map<String, Object>>  map = new ArrayList<Map<String,Object>>();
		
		LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
		String memberid = mVo.getMember_id();
		List<Map<String, Object>> lb =service.reserveLendingBook(memberid);

		for (int i = 0;  i< lb.size(); i++) {
			Map<String, Object> a = lb.get(i);
			List<String> lists2 = new ArrayList<String>();
			lists2.add( (String) a.get("MEMBER_CODE"));
			lists2.add( (String) a.get("ISBN"));
			lists2.add( (String) a.get("LENDING_SEQ"));
			lists2.add( (String) a.get("TITLE"));
			lists2.add( (String) a.get("PUBLISHER"));
			lists2.add( (String) a.get("AUTHOR"));
			lists2.add( (String) a.get("RESERVE_DATE"));

			
			model.addAttribute("a",a);

		}
		model.addAttribute("page", "reserveListMem");
		
		return "myPage";

	}
	
	
	@RequestMapping(value = "/cancelReseve.do", method = RequestMethod.GET) 
	public String cancelReseve(/* @RequestParam String lending_seq, String book_serial, */HttpServletRequest req, HttpServletResponse response) throws IOException {
		logger.info("회원이 예약 취소");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("j","lending_seq");
		String lending_seq = 	req.getParameter("lending_seq");
		String book_serial = 	req.getParameter("book_serial");
		int n  =service.selfDeleteResrve(lending_seq, book_serial);
		if(n==1) {
			PrintWriter out =response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('삭제되었습니다');</script>");
			out.flush();
			
			return "redirect:/myPage";
		}else {
			return "redirect:/myPage";
		}
	}
	
	@RequestMapping(value = "/confrimReserve.do",method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String confrimReserve(String seq, String code, String member, HttpServletRequest req,HttpServletResponse response) throws IOException{
		logger.info("예약된 도서 관리자가 대출 신청하기");
	//	String lending_seq = 	req.getParameter("lending_seq");
		//String book_serial = 	req.getParameter("book_serial");
		//String member_code = 	req.getParameter("member_code");
		System.out.println(seq+code+member);
		int n = service.confrimReserveBook(seq.trim(), code.trim(), member.trim());
		
		System.out.println(n+"n의 갯수======================");
		if(n>0) {
	//		PrintWriter out =response.getWriter();
	//		response.setContentType("text/html; charset=UTF-8");
	//		out.println("<script>alert('대출되었습니다');</script>");
	//		out.flush();
			
			return "true";
		}else {
			return "false";
		}
		
	}
	@RequestMapping(value = "/lendMainpage.do", method = RequestMethod.GET)
	public String lendMainpage() {
		logger.info("관리자 즉시대출화면으로 가기");
		return "lendingBook";
		
	}
	
	@RequestMapping(value = "/lendingBookAdmin.do",method = {RequestMethod.GET,RequestMethod.POST},produces = "application/text; charset=UTF-8")
	public String lendingBookAdmin (@RequestParam Map<String, Object> map, Model model,String name, HttpSession session,HttpServletRequest req,HttpServletResponse response) throws IOException {
		logger.info("lendingBookAdmin _ 관리자 즉시대출화면_도서정보보기");

		String book_serial = req.getParameter("book_serial");
		String member_id = req.getParameter("member_id");
		
		List<LendBookBean> listBean = new ArrayList<LendBookBean>();
		listBean= service.nowLendingBook(book_serial);
		System.out.println(listBean+"============================");
		model.addAttribute("listBean",listBean);
		
		if(listBean.equals(0) ) {
			logger.info("===========동작되나");
			PrintWriter out =response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.print("<script>alert('예약중인 도서입니다');</script>");
			out.flush();
		}else {
			logger.info("===========동작되나2");
		}
		
		
		logger.info("lendingBookAdmin _ 관리자 즉시대출화면_회원정보보기");
		Map<String,Object> mMap = new HashMap<String, Object>();
		mMap.put("member_id",member_id);
		LibMemberVo lVo = mService.selectMyInfo(mMap);
		model.addAttribute("lVo",lVo);
		
		
		return "lendingBook";
	}

	@RequestMapping(value = "/fastLending.do",method = RequestMethod.GET, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String fastLending(String bookserial,String member, LendingVo vo,HttpServletRequest req,HttpServletResponse response) throws IOException{
		logger.info("관리자가 즉시 대출하기");
		System.out.println(bookserial+member+vo);
		int n = service.lendingBook(bookserial.trim(), member.trim());
		
		System.out.println(n+"n의 갯수======================");
		if(n>0) {
//			PrintWriter out =response.getWriter();
//			response.setContentType("text/html; charset=UTF-8");
//			out.println("<script>alert('대출되었습니다');</script>");
//			out.flush();
			
			return "true";
		}else {
			return "false";
		}
	
	}
	
	@RequestMapping(value = "/reserveBook.do",method = RequestMethod.GET)
	public String selectPossibleReserve(Model model,HttpSession session) {
		logger.info("회원 예약 하기_ 가능도서 조회");
		
		LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
		String memberid = mVo.getMember_id();
		List<LendingVo> countBook = service.limitBookCount(memberid);
		
		
		List<LendBookBean> poBook = new ArrayList<LendBookBean>();
		poBook= service.selectPossibleReserve();
		model.addAttribute("poBook",poBook);
		model.addAttribute("countBook",countBook);
		
		return "reserveBook";
	}
	
	@RequestMapping(value = "/checkReserve.do", method = {RequestMethod.POST ,RequestMethod.GET},produces = "application/json; charset=UTF-8" )
	@ResponseBody
	public Map<String, Object> possibleReserve(Model model,@RequestParam String isbn ,String book_serial ) {
		logger.info("회원 예약 하기_ 반납예정일이 빠른도서 선택");
		System.out.println(isbn+"+"+book_serial+"+@@@@ISBN-BOOK SERIAL보기@@@@");
		
//		ModelAndView mav =new ModelAndView();
		
		List<LendBookBean> chkBook = service.possibleReserve(isbn);
		System.out.println("#####################"+chkBook);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chkBook", chkBook);
		System.out.println("********************"+map);
		
//		mav.addObject("chkBook",chkBook);
//		mav.setViewName("reserveBook");
//		model.addAttribute("chkBook",chkBook);
		return map;
	}
	
	@RequestMapping(value = "/realReserve.do", method = RequestMethod.POST ,produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String reSelect(@RequestParam String isbn ,String book_serial,HttpSession session) {
		logger.info("회원 예약 하기_ 예약도서 선택 후 버튼 누름~");
		
		LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
		String memberid = mVo.getMember_id();
		String member_code = mVo.getMember_code();
		System.out.println(isbn+" , "+book_serial+" , "+memberid+" , "+member_code);
		
		LendingVo vo = new LendingVo();
		vo.setBook_serial(book_serial);
		vo.setMember_code(member_code);
		System.out.println(vo);
		
		int n =service.bookReservation(vo, book_serial.trim());
		System.out.println(n+"n의 갯수======================");
		if(n>0) {
			return "true";
		}else {
			return "false";
		}
//		return "myPage";
	}
	
}






