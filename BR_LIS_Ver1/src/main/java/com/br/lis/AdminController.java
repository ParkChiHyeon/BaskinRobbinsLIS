package com.br.lis;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.br.lis.model.member.service.API_Service;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;



@Controller
@SessionAttributes("admin")
public class AdminController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILibMemberService service;
	
	@Autowired
	private IAdminService aService;
	
	@Autowired
	private API_Service cService;
	
	
	/* 관리자 페이지 이동 */
	@RequestMapping(value = "/adminLoginPage.do")
	public String adminLoginPage() {
		return "adminLoginPage";	
	}
	
	/* 세션 정보 담기*/
	@RequestMapping(value = "/loginAdmin.do", method=RequestMethod.POST)
	public String loginAdmin(@RequestParam Map<String, Object> map/*,Map<String,Object> map2,*/, Model model) {
		logger.info("MemberController loginMember : {}", map);
		AdminVo aVo = aService.loginAdmin(map);	
		model.addAttribute("admin", aVo);
		return "home";
	}
	
	/* 로그인 유효성 확인 아작스 처리 */
	@RequestMapping(value = "/adminLoginCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminLoginCheck(@RequestParam Map<String, Object> map /*, Map<String,Object> map2 */) {
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("MemberController adminLoginCheck : {}", map);
		
		
		AdminVo aVo = aService.loginAdmin(map);
		
		logger.info("MemberController adminLoginCheck 로그인 정보 : {}", aVo);
			
		if(aVo == null) {
			resultMap.put("isc", "실패");
		}else if(aVo != null){
			resultMap.put("isc", "성공");
		}	

		return resultMap;
	}
	
	/* 관리자 인증 페이지 */
	@RequestMapping(value = "/adminCertification.do")
	public String adminCertification() {
		return "adminCertificationPage";	
	}
	
		
}
