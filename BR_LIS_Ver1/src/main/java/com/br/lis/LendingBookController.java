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

import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.model.member.service.ILibMemberService;
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
	
	@RequestMapping(value = "/lendingBook.do", method = RequestMethod.GET, produces ="application/text; charset=UTF-8" )
	public  String lendingLIst(/*HttpSession session,@RequestParam Map<String, String> map,*/Model model) {
		
		logger.info("대출한 도서내역");
		logger.info("해당아이디로 조회하기-> 마이페이지 가야함");
		Map<String, Object> map2 = new  HashMap<String, Object>();
		//아이디 직접입력
		map2.put("member_id", "user001");
		
		
		//세션받아서 id 값 가져와야함
//		LibMemberVo mVo = (LibMemberVo) session.getAttribute("member");
//		String mb =mVo.getMember_id();
//		System.out.println(mb+"멤버아이디1111&&&&&&&&&&&&&&");
//		System.out.println(mVo+"멤버아이디22222&&&&&&&&&&&&&&");
//		model.addAttribute("mb", mb);
		
		List<LendBookBean> listBean = new ArrayList<LendBookBean>();
		listBean= service.lendingList(map2);
		
		model.addAttribute("listBean",listBean);
		
//		List<LendingVo> lists = (List<LendingVo>) service.reserveLendingBook(map);
		return "lendingBook";
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
	
	@RequestMapping(value ="/reserveBook.do", method = RequestMethod.GET)
	public String reserveBook(String lending_seq, LendingVo vo, Model model) {
		logger.info("예약목록조회(회원)");
//		Map<String, String> map = new  HashMap<String, String>();
		List<Map<String, Object>>  map = new ArrayList<Map<String,Object>>();
		String membercode = "M2205000004";
		List<Map<String, Object>> lb =service.reserveLendingBook(membercode);

		for (int i = 0;  i< lb.size(); i++) {
			Map<String, Object> a = lb.get(i);
			List<String> lists2 = new ArrayList<String>();
			lists2.add( (String) a.get("MEMBER_CODE"));
			lists2.add( (String) a.get("ISBN"));
			lists2.add( (String) a.get("LENDING_SEQ"));
			lists2.add( (String) a.get("TITLE"));
			lists2.add( (String) a.get("PUBLISHER"));
			lists2.add( (String) a.get("AUTHOR"));
//			lists2.add( (String) a.get("reserve_date"));
			
//			String m= (String) a.get("MEMBER_CODE");
//			String n= (String) a.get("ISBN");
//			String o = (String) a.get("LENDING_SEQ");
//			String p = (String) a.get("TITLE");
//			String q= (String) a.get("PUBLISHER");
//			String r= (String) a.get("AUTHOR");
			
//			String[] m= (String[]) a.get("MEMBER_CODE");
//			String[] n= (String[]) a.get("ISBN");
//			String[] o = (String[]) a.get("LENDING_SEQ");
//			String[] p = (String[]) a.get("TITLE");
//			String[] q= (String[]) a.get("PUBLISHER");
//			String[] r= (String[]) a.get("AUTHOR");
			
			model.addAttribute("a",a);
//			model.addAttribute("m",m);
//			model.addAttribute("n",n);
//			model.addAttribute("o",o);
//			model.addAttribute("p",p);
//			model.addAttribute("q",q);
//			model.addAttribute("r",r);
		}
		
		
		return "reserveBook";

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
			
			return "reserveBook";
		}else {
			return "reserveBook";
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
	
	
	
}






