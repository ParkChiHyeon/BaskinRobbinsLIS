package com.br.lis;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.lis.model.lendinginfo.service.IReturnBookService;

@Controller
public class RequestBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReturnBookService iService;
	
	// 도서 신청 버튼을 눌렀을 때
	@RequestMapping(value = "/requestBook.do", method = RequestMethod.POST)
	public String requestBookButton (@RequestParam Map<String, Object> map) {
		logger.info("Welcome! requestBookButton");
		return "requestPurchase";
	}
	
	
	
}
