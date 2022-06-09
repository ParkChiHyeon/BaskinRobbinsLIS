package com.br.lis;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.lis.model.lendinginfo.service.ILendingBookService;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;

@Controller
public class LendingBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILendingBookService service;
	
	@RequestMapping(value = "/lendingBook.do", method = RequestMethod.GET, produces ="application/text; charset=UTF-8" )
	public  String lendingLIst(Model model) {
		
		logger.info("이전대출도서내역");
		Map<String, Object> map2 = new  HashMap<String, Object>();
		map2.put("member_code", "M2205000004");
		List<LendBookBean> listBean = new ArrayList<LendBookBean>();
		listBean= service.lendingList(map2);
		model.addAttribute("listBean",listBean);
		
//		List<LendingVo> lists = (List<LendingVo>) service.reserveLendingBook(map);
		return "lendingBook";
	}

	
	@RequestMapping(value ="/reserveBook.do", method = RequestMethod.GET)
	public String reserveBook(String lending_seq, LendingVo vo, Model model) {
		logger.info("예약목록 전체조회(관리자) ");
//		List<BookInfoVo>lists = service.allReserveLending();
		Map<String, Object> map1 = new  HashMap<String, Object>();
		List<LendBookBean> reBook = new ArrayList<LendBookBean>();
		reBook= service.allReserveLending(map1);
		model.addAttribute("reBook",reBook);
//		model.addAttribute("lists", lists);
		
		logger.info("예약목록조회(회원)");
//		Map<String, String> map = new  HashMap<String, String>();
		List<Map<String, Object>>  map = new ArrayList<Map<String,Object>>();
		String membercode = "M2205000005";
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
		
		
		
		
//		logger.info("대출신청하기");
//		int n = service.confrimReserveBook(lending_seq, vo);
//		if(n==1) {
//			logger.info("대출 신청 완료됨??");
//		}
//		return (n==1)?"redirect:/reserveBook.do":"redirect:/reserveBook.do"; 
	}
	
	
	@RequestMapping(value = "/cancelReseve.do", method = RequestMethod.POST) 
	@ResponseBody
	public String cancelReseve(String lending_seq, BookInfoVo vo) {
		logger.info("회원이 예약 취소");
		Map<String, String> map = new HashMap<String, String>();
		map.put("j","lending_seq");
		
		int n  =service.selfDeleteResrve(lending_seq,vo);
		
//		if(n==1) {
//			logger.info("취소중...........");
//		}
		return "redirect:/reserveBook.do";
	}
	
}






