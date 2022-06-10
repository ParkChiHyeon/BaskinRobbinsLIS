package com.br.lis;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.lis.model.member.mapper.ILibMemberDao;
import com.br.lis.model.test.service.ITestService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	/*
	 * 헤더 화면 흐름 제어 클래스
	 */
	
	
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/home.do", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView home(HttpSession session) {
		logger.info("HomeController Welcome home!");
		
		ModelAndView mav = new ModelAndView();
		
		LibMemberVo mVo = (LibMemberVo) session.getAttribute("member");
		AdminVo aVo = (AdminVo) session.getAttribute("admin");
		
		mav.addObject("member",mVo);
		mav.addObject("admin",aVo);
		
		mav.setViewName("home");
		
		
		
		return mav;
	}
	
	
	
	//로그아웃
			@RequestMapping(value= "/logout.do", method = RequestMethod.GET)
			public String logout(HttpSession session) {
				session.invalidate();
				
				return "redirect:/home.do";
			}
	
	
}

