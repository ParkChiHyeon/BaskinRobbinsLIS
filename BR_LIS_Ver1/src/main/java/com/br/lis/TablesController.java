package com.br.lis;

import java.util.List;





import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.br.lis.model.board.service.INoticeBoardService;

import com.br.lis.vo.Notice_FAQBoardVo;


@Controller
public class TablesController {
	
	private static final Logger logger = LoggerFactory.getLogger(Board_Controller.class);
	
	@Autowired
	private INoticeBoardService service;
	
	@RequestMapping(value = "/dataTables.do", method = RequestMethod.GET)
	public String dataTables(Model model, @RequestParam Map<String, Object> map) {
		logger.info("HomeController dataTables 리스트보기 : {}", map);
		List<Notice_FAQBoardVo> lists = service.viewAllNotice(null);
		model.addAttribute("lists", lists);
		return "dataTables";
	}
	
	@RequestMapping(value = "/ajaxTables.do", method = RequestMethod.GET)
	public String ajaxTables() {
		logger.info("HomeController ajaxTables 데이터테이블 아작스처리 페이지로 가기");
		return "AjaxTables";
	}

//	@RequestMapping(value="/createAjax.do", method = RequestMethod.GET, 
//					produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public String createAjax(){
//		logger.info("HomeController createAjax 아작스처리 시작");
//		List<Notice_FAQBoardVo> lists = service.AjaxTables();
//		
//		Gson data = new GsonBuilder().create();
//		return data.toJson(lists); //return해주는 변수명은 data여야 한다. 
//	}

}
