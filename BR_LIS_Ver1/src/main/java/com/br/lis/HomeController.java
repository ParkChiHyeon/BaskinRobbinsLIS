package com.br.lis;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lis.model.test.service.ITestService;
import com.br.lis.vo.TestVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	/*
	 * 헤더 화면 흐름 제어 클래스
	 */
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ITestService service;
	
	
	@RequestMapping(value = "/home.do")
	public String home() {
		logger.info("HomeController Welcome home!");
		
		return "home";
	}
	
	
	@RequestMapping(value = "/booksearch.do")
	public String bookSearch() {
		logger.info("HomeController bookSearch 이동");
		return "booksearch";
	}
	
	
	@RequestMapping(value = "/test.do",method = RequestMethod.GET)
	public String test(Model model) {
//		List<Map<String, String>> lists = service.testSelect();
//		
//		model.addAttribute("lists", lists);
		return "test";
	}
	
	//로그아웃
			@RequestMapping(value= "/logout.do", method = RequestMethod.GET)
			public String logout(HttpSession session) {
				session.invalidate();
				
				return "redirect:/home.do";
			}
	
	
}
