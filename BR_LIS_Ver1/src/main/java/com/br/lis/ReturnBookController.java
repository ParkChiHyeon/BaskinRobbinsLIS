package com.br.lis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.model.lendinginfo.service.IReturnBookService;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

@Controller
public class ReturnBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReturnBookService iService;
	
	@Autowired
	private ILendingBookService lService;
	
	// 반납 페이지 
	@RequestMapping(value = "/returnBookPage.do", method = RequestMethod.GET)
	public String returnBookPage (Model model) {
		logger.info("Welcome! ReturnBookController returnBookPage");
		String book_serial = "BKSR155997";
		LendingVo vo = iService.lendingDetailForReturnBook(book_serial);
		model.addAttribute("vo",vo);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("book_serial", book_serial);
		ReservationVo rVo = iService.returnBookReserveCheck(rMap); 
		model.addAttribute("rVo", rVo);
		LibMemberVo mVo = iService.lendingDetailForReturnUser(vo.getMember_code());
		model.addAttribute("mVo",mVo);
		return "returnBookPage";
	}
	
	// 정상반납
	@RequestMapping(value = "/returnNomal.do", method = RequestMethod.GET)
	public String returnBookProcessing(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Welcome! ReturnBookController returnBookProcessing");
		String lending_seq = request.getParameter("lending_seq");
		String member_code = request.getParameter("member_code");
		String reserve = request.getParameter("reserve_seq");
		if (reserve != null) {
			iService.existReserveReturnBook(lending_seq, member_code);
		}else {
			iService.normalReturnBook(lending_seq, member_code);
		}
		PrintWriter out = response.getWriter();
		out.println("<script>alert('반납완료');</script>");//외않됨?
		logger.info(lending_seq, member_code);
		return "home";
	}
	
	// 파손반납
	@RequestMapping(value = "/returnBookDamege.do", method = {RequestMethod.GET})
	public String returnBookDamege(HttpServletRequest request) {
		logger.info("Welcome! ReturnBookController returnBookDamege");
		String lending_seq = request.getParameter("lending_seq");
		String member_code = request.getParameter("member_code");
		String reserve_seq = request.getParameter("reserve_seq");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		if(reserve_seq == null) {
			iService.damegeReturnBook(lending_seq, member_code);
		}else {
			iService.certifiedPhoneNumber(phone, name);
			iService.reserveSelfDel(reserve_seq);
		}
		return "home";
	}
	
}
