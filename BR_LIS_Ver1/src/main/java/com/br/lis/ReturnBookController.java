package com.br.lis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lis.model.lendinginfo.service.IReturnBookService;

@Controller
public class ReturnBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReturnBookService iService;
	
	// 반납 페이지 
	@RequestMapping(value = "/returnBookPage.do", method = RequestMethod.GET)
	public String returnBookPage () {
		logger.info("Welcome! ReturnBookController returnBookPage");
		return "returnBookPage";
	}
	
	
	
}
