package com.br.lis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.br.lis.model.member.service.IAdminService;
import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;


@Controller
@SessionAttributes({"member","admin"})
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILibMemberService service;
	
	@Autowired
	private IAdminService aService;
	
	/* 로그인 페이지로 이동 */
	@RequestMapping(value = "/loginPage.do",method = RequestMethod.GET)
	public String test() {		
		return "loginPage";
	}
	
	
	/* 로그인 유효성 확인 아작스 처리 */
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginCheck(@RequestParam Map<String, String> map /*, Map<String,Object> map2 */) {
		Map<String, String> resultMap = new HashMap<String, String>();
		logger.info("MemberController loginCheck : {}", map);

		LibMemberVo mVo = service.loginMember(map);
		
//		logger.info("MemberController loginCheck : {}", map2);
//		AdminVo aVo = aService.loginAdmin(map2);
		logger.info("MemberController loginCheck 로그인 정보 : {}", mVo);
		
//		logger.info("MemberController loginCheck 로그인 정보 : {}", aVo);
		
		if(mVo == null) {
			resultMap.put("isc", "실패");
		}else if(mVo != null){
			resultMap.put("isc", "성공");
		}	
//		if(aVo == null) {
//			resultMap.put("isc", "실패");
//		}else if(aVo != null) {
//			resultMap.put("isc", "성공");
//		}
		return resultMap;
	}
	
	
	
	
	/* 로그인 시 세션에 정보를 담음 */
	@RequestMapping(value = "/loginMember.do", method= RequestMethod.POST)
	public String loginMember(@RequestParam Map<String, String> map/*,Map<String,Object> map2,*/, Model model) {
		logger.info("MemberController loginMember : {}", map);
		LibMemberVo mVo = service.loginMember(map);
//		AdminVo aVo = aService.loginAdmin(map2);
		
		model.addAttribute("member", mVo);
//		model.addAttribute("admin", aVo);
		return "home";
	}
	
	/* 회원가입 폼 이동 */
	@RequestMapping(value = "/signUpPage.do")
	public String signUpMemberPage() {
		return "signUpPage";	
	}
	
	/* 회원 가입 */
	@RequestMapping(value = "/signUp.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String signUpMember(@RequestParam Map<String, Object> map) {
		logger.info("MemberController register : {}",map);
		int n = service.signUpMember(map);
		
		return(n==1)?"redirect:/loginPage.do":"redirect:/signUpPage.do";	
	}
	
	
	
}
