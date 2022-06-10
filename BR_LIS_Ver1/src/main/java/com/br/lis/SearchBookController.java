package com.br.lis;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*도서 조회 화면 이동*/
	@RequestMapping(value = "/bookSearch.do",method = RequestMethod.GET)
	public String bookSearch(Model model,String kind) {
		logger.info("HomeController bookSearch 이동");
		model.addAttribute("kind", kind);
		return "booksearch";
	}
	
	
	
	@RequestMapping(value = "/homeSearch.do",method = RequestMethod.POST)
	public String homeSearch(@RequestParam Map<String, String> map,Model model) {
		logger.info("homeSearch bookSearch 이동");
		System.out.println(map);
		model.addAttribute("homeSearch", map);
		model.addAttribute("kind", "total");
		return "booksearch";
	}
	

}
