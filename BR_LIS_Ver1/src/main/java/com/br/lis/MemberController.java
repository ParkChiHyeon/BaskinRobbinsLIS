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
@SessionAttributes("member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILibMemberService service;
	
	@Autowired
	private IAdminService aService;
	
	@Autowired
	private API_Service cService;
	
	/* 로그인 페이지로 이동 */
	@RequestMapping(value = "/loginPage.do",method = RequestMethod.GET)
	public String test() {		
		return "loginPage";
	}
	
	/* 마이 페이지로 이동 */
	@RequestMapping(value = "/myPage.do",method = RequestMethod.GET)
	public String myPage() {		
		return "myPage";
	}
	
	/* 관리자 페이지로 이동 */
	@RequestMapping(value = "/adminPage.do",method = RequestMethod.GET)
	public String adminPage() {		
		return "adminPage";
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
	@RequestMapping(value = "/loginMember.do", method=RequestMethod.POST)
	public String loginMember(@RequestParam Map<String, String> map/*,Map<String,Object> map2,*/, Model model) {
		logger.info("MemberController loginMember : {}", map);
		LibMemberVo mVo = service.loginMember(map);
//		AdminVo aVo = aService.loginAdmin(map2);
		
		model.addAttribute("member", mVo);
		logger.info("현재 세션의 정보는 :{}", mVo);
//		model.addAttribute("admin", aVo);
		return "redirect:/home.do";
	}
	
	/* 회원가입 폼 이동 */
	@RequestMapping(value = "/signUpPage.do")
	public String signUpMemberPage() {
		return "signUpPage";	
	}
	
	/* 회원 가입 */
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUpMember(@RequestParam Map<String, Object> map) {
		logger.info("MemberController register : {}",map);
		int n = service.signUpMember(map);
		
		return(n==1)?"redirect:/loginPage.do":"redirect:/signUpPage.do";	
	}
	
	/* 중복아이디 체크 */
	@RequestMapping(value = "/idDuplicateCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> idDuplicateCheck(@RequestParam Map<String, Object> map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		LibMemberVo mVo = service.idDuplicateCheck(map);
		
		if(mVo == null) {
			resultMap.put("isc", "실패");
		}else if(mVo != null){
			resultMap.put("isc", "성공");
		}	
		
		return resultMap;
	}
	/* sms 인증 */
	@RequestMapping(value = "/sendSMS.do",method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(String phone) {
		  Random rand  = new Random(); // 인증번호를 위한 난수 생성
	        String numStr = "";
	        for(int i=0; i<8; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }
	        System.out.println("수신자 번호 : " + phone);
	        System.out.println("인증번호 : " + numStr);
	        cService.certifiedPhoneNumber(phone,numStr);
	        
	        return numStr;
	    }
	
	/* 회원가입 폼 이동 */
	@RequestMapping(value = "/findIdPage.do")
	public String findIdPage() {
		return "findIdPage";	
	}
	
	@RequestMapping(value = "/findIdChk.do" ,method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findIdChk(@RequestParam Map<String,Object> map, Model model) {
		logger.info("MemberController",map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		LibMemberVo mVo = service.findId(map);
		
		logger.info("찾은아이디 : {}",mVo);
		
		if(mVo == null) {
			resultMap.put("isc", "실패");
		}else if(mVo != null){
			resultMap.put("isc", "성공");
		}	
		
				
		return resultMap;		
	}
	
	
		@RequestMapping(value = "/findId.do", method=RequestMethod.GET)
		public String findId(@RequestParam Map<String, Object> map,Model model) {
	
		LibMemberVo mVo = service.findId(map);
		model.addAttribute("mVo",mVo);
		
		return "loginPage";
	}
		
		@RequestMapping(value = "/findPwChk.do", method = RequestMethod.POST)
		public Map<String, Object> findPwChk(@RequestParam Map<String,Object> map) {
			System.out.println(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			int cnt = service.resetUpdatePw(map);
			
			if(cnt == 0) {
				resultMap.put("isc", "실패");
			}else if(cnt != 0) {
				resultMap.put("isc", "성공");
			}
			
			return resultMap;
			
		}

	
	
	
	
	
	
}
