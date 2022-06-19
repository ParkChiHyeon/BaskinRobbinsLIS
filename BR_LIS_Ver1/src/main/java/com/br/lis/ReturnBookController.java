package com.br.lis;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.model.lendinginfo.service.IReturnBookService;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ReturnBookController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IReturnBookService iService;
	


	// 반납 페이지
	@RequestMapping(value = "/returnBookPage.do", method = RequestMethod.GET)
	public String returnBookPage() {
		logger.info("Welcome! ReturnBookController returnBookPage");
		
		return "returnBookPage";
	}
	
	// 반납 책 관련 정보 내역 조회 
	@RequestMapping(value = "/returnBookSelect.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> returnBookSelectAjax(Model model, @RequestParam String book_serial) {
        System.out.println("----------------전달받은 파라미터  :"+book_serial);
        LendingVo vo = iService.lendingDetailForReturnBook(book_serial);
        if(vo!=null) {
        	Map<String, Object> rMap = new HashMap<String, Object>();
        	rMap.put("book_serial", book_serial);
        	
        	ReservationVo rVo = iService.returnBookReserveCheck(rMap);
        	LibMemberVo mVo = iService.lendingDetailForReturnUser(vo.getMember_code());
        	
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("rVo", rVo);
        	map.put("vo", vo);
        	map.put("mVo", mVo);
        	
        	
        	return map;
        }else {
        	return null;
        }
        
    }
	
	// 정상반납
	@RequestMapping(value = "/returnNomal.do", method = RequestMethod.GET)
	public String returnBookProcessing(HttpServletRequest request, HttpServletResponse response){
		logger.info("Welcome! ReturnBookController returnBookProcessing");
		String lending_seq = request.getParameter("lending_seq");
		String member_code = request.getParameter("member_code");
		String reserve = request.getParameter("reserve_seq");
		if (reserve != null) {
			iService.existReserveReturnBook(lending_seq, member_code);
		} else {
			iService.normalReturnBook(lending_seq, member_code);
		}
		logger.info(lending_seq, member_code);
		return "redirect:/returnBookPage.do";
	}

	// 파손반납
	@RequestMapping(value = "/returnBookDamege.do", method = RequestMethod.GET)
	@Transactional
	public String returnBookDamege(HttpServletRequest request) {
		logger.info("Welcome! ReturnBookController returnBookDamege");
		String lending_seq = request.getParameter("lending_seq");
		String member_code = request.getParameter("member_code");
		String reserve_seq = request.getParameter("reserve_seq");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		// 예약자가 없을 경우 일반 파손반납진행
		if (reserve_seq == null) {
			iService.normalReturnBook(lending_seq, member_code);
			iService.damegeReturnBook(lending_seq, member_code);
		} else {
			// 예약자가 있을 경우 알림문자 발송 후 파손반납진행
			iService.certifiedPhoneNumber(phone, name);
			iService.reserveSelfDel(reserve_seq);
			iService.normalReturnBook(lending_seq, member_code);
			iService.damegeReturnBook(lending_seq, member_code);
		}
		return "redirect:/returnBookPage.do";
	}

	// 분실 반납
	@RequestMapping(value = "/returnBookLost.do", method = RequestMethod.GET)
	@Transactional
	public String returnBookLost(HttpServletRequest request) {
		logger.info("Welcome! ReturnBookController returnBookLost");
		String lending_seq = request.getParameter("lending_seq");
		String member_code = request.getParameter("member_code");
		String reserve_seq = request.getParameter("reserve_seq");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		// 예약자가 없을 경우 일반 분실반납진행
		if (reserve_seq == null) {
			iService.normalReturnBook(lending_seq, member_code);
			iService.lossReturnBook(lending_seq, member_code);
		} else {
			// 예약자가 있을 경우 알림문자 발송 후 분실반납진행
			iService.certifiedPhoneNumber(phone, name);
			iService.reserveSelfDel(reserve_seq);
			iService.normalReturnBook(lending_seq, member_code);
			iService.lossReturnBook(lending_seq, member_code);
		}
		return "redirect:/returnBookPage.do";
	}

	// 대출반납 완료 목록
	@RequestMapping(value = "/returnBookList.do", method = RequestMethod.GET)
	public String returnBookList(Model model) {
		logger.info("Welcome! ReturnBookController returnBookList");
		List<LendBookBean> lists = iService.returnBookList();
		model.addAttribute("lists", lists);
		return "returnBookList";
	}
	// 반납 완료된 책 목록
	@RequestMapping(value = "/returnBookListBanNap.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String returnBookListBanNap() {
		logger.info("Welcome! ReturnBookController returnBookListBanNap");
		List<LendBookBean> bLists = iService.returnBookListBanNap();
		Gson data = new GsonBuilder().create();
		return data.toJson(bLists); 
	}

	// 대출 중인 목록 - 관리자
	@RequestMapping(value = "/lendingBookListAdmin.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String lendingBookListAdmin() {
		logger.info("Welcome! ReturnBookController lendingBookListAdmin");
		List<LendBookBean> Lists = iService.yetReturnBookList();
		Gson data = new GsonBuilder().create();
		return data.toJson(Lists); 
	}
	// 대출중인 목록 - 회원
	@RequestMapping(value = "/lendingBookListUser.do", method = RequestMethod.GET)
	public String lendingBookListUser(HttpSession session, Model model) {
		LibMemberVo lVo = (LibMemberVo)session.getAttribute("member");
		logger.info("Welcome! ReturnBookController lendingBookListAdmin");
		logger.info("Welcome! ReturnBookController lendingBookListAdmin SESSION {}",lVo);
		List<LendBookBean> lists = iService.lendingListUser(lVo.getMember_id());
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("lists",lists);
		logger.info("Welcome! ReturnBookController lendingBookListAdmin LISTS! {}", lists);
		model.addAttribute("page", "lendingBookListUserHIK");
		model.addAttribute("lVo_HIK",lVo);
		return "myPage";
	}
	// 대출 연장하기 - 회원
	@RequestMapping(value = "/delayLendingBook.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int delayLendingBook(HttpServletRequest req) {
		logger.info("Welcome! ReturnBookController delayLendingBook");
		String lending_seq = req.getParameter("lending_seq");
		logger.info("대출 번호 {}", lending_seq);
		int n = iService.delayLendingBook(lending_seq);
		return n;
	}
	
	// 보유 도서 목록
		@RequestMapping(value = "/possessingBookList.do", method = RequestMethod.GET)
		public String possessingBookList(Model model) {
			logger.info("Welcome! ReturnBookController possessingBookList");
			List<BookInfoVo> lists = iService.possessingBookList();
			model.addAttribute("lists", lists);
			return "possessingBookList";
		}
		
	// 서고에 있는 책목록
	@RequestMapping(value = "/warehouseBookList.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String warehouseBookList() {
		logger.info("Welcome! ReturnBookController warehouseBookList");
		List<BookInfoVo> Lists = iService.warehouseList();
		Gson data = new GsonBuilder().create();
		return data.toJson(Lists); 
	}
	
	// 부록 있는 책목록
	@RequestMapping(value = "/supplyBookList.do", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String supplyBookList() {
		logger.info("Welcome! ReturnBookController supplyBookList");
		List<BookInfoVo> Lists = iService.supplementList();
		Gson data = new GsonBuilder().create();
		return data.toJson(Lists); 
	}
	

}
