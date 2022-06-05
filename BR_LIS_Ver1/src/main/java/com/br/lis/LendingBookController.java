package com.br.lis;

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
		logger.info("대출현황조회 페이지로");
		List<BookInfoVo>lists = service.allReserveLending();
		model.addAttribute("lists", lists);
		
		
		Map<String, String> map = new  HashMap<String, String>();
		map.put("member_code", "M2205000005");
		LendBookBean lb =service.reserveLendingBook(map);
		model.addAttribute("lb",lb);
		
//		List<LendingVo> lists = (List<LendingVo>) service.reserveLendingBook(map);
		return "lendingBook";
		
	}

}
