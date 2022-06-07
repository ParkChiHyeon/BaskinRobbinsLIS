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
		logger.info("예약목록 전체조회 ");
		List<BookInfoVo>lists = service.allReserveLending();
		model.addAttribute("lists", lists);
		
		logger.info("예약목록조회(회원)");
		Map<String, String> map = new  HashMap<String, String>();
		map.put("member_code", "M2205000005");
		LendBookBean lb =service.reserveLendingBook(map);
		model.addAttribute("lb",lb);
		
		logger.info("대출도서내역");
		Map<String, Object> map2 = new  HashMap<String, Object>();
		map2.put("member_code", "M2205000004");
		List<LendBookBean> listBean = new ArrayList<LendBookBean>();
		listBean= service.lendingList(map2);
		model.addAttribute("listBean",listBean);
		
//		List<LendingVo> lists = (List<LendingVo>) service.reserveLendingBook(map);
		return "lendingBook";
	}

	@RequestMapping(value ="/reserveBook.do", method = RequestMethod.GET)
	public String reserveBook() {
		logger.info("대출신청하기 ");
		
		return "reserveBook";
	}
	
}
