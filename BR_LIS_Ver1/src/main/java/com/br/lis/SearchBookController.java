package com.br.lis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value = "/bookSearch.do",method = RequestMethod.GET)
	public String bookSearch(Model model) {
		logger.info("HomeController bookSearch 이동");
		model.addAttribute("kind", "total");
		return "booksearch";
	}
	
	
	@RequestMapping(value = "/bookSearchDetail.do",method = RequestMethod.GET)
	public String bookSearchDetail(Model model) {
		logger.info("HomeController bookSearchDetail 이동");
		model.addAttribute("kind", "detail");
		return "booksearch";
	}
	
	@RequestMapping(value = "/requestBookSearch.do",method = RequestMethod.GET)
	public String requestBookSearch(Model model) {
		logger.info("HomeController requestBookSearch 이동");
		model.addAttribute("kind", "request");
		return "booksearch";
	}
	
	

}
