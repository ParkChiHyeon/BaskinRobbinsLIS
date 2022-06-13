package com.br.lis;




import java.util.HashMap;


import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

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


import com.br.lis.model.member.service.API_Service;

import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.model.member.service.EmailService;
import com.br.lis.vo.LibMemberVo;



@Controller
@SessionAttributes("member")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILibMemberService service;
	
	@Autowired
	private EmailService eService;
	

	@Autowired
	private API_Service cService;
	
	/* 로그인 페이지로 이동 */
	@RequestMapping(value = "/loginPage.do",method = RequestMethod.GET)
	public String test() {		
		return "loginPage";
	}
	
	/* 마이 페이지로 이동 */
//	@RequestMapping(value = "/myPage.do",method = RequestMethod.GET)
//	public String myPage() {		
//		return "myPage";
//	}
	
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
	
	/* 아이디 찾기 ajax */
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
	
		/* 아이디 찾기 */
		@RequestMapping(value = "/findId.do", method=RequestMethod.GET)
		public String findId(@RequestParam Map<String, Object> map,Model model) {
	
		LibMemberVo mVo = service.findId(map);
		model.addAttribute("mVo",mVo);
		
		return "loginPage";
	}
		/* 비밀번호 찾기 아작스 */
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
		
		/*마이페이지 화면 이동*/
		@RequestMapping(value = "/myPage.do",method = RequestMethod.GET)
		public String bookSearch(Model model,String page) {
			logger.info("mypage memberInfoUpdate 이동");
			model.addAttribute("page", page);
			return "myPage";
		}
		
		/* 회원정보 수정 페이지 이동 */
		@RequestMapping(value = "/memberInfoUpdatePage.do",method = RequestMethod.GET)
		public String memberInfoUpdatePage(@RequestParam Map<String, Object> map, Model model, HttpSession session) {
			logger.info("memberInfoUpdate passwordForUpdate 이동");
			
			LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
			
//			int n = service.updateMyInfo(map);
			
			model.addAttribute("member",mVo);
			model.addAttribute("page", "update");
//			
			return "myPage";
		}
				
		@RequestMapping(value = "/memberInfoUpdatePw.do", method = RequestMethod.GET)
		public String memberInfoUpdatePw(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoPw(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		@RequestMapping(value = "/memberInfoUpdateName.do", method = RequestMethod.GET)
		public String memberInfoUpdateName(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoName(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		@RequestMapping(value = "/memberInfoUpdatePhone.do", method = RequestMethod.GET)
		public String memberInfoUpdatePhone(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoPhone(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		@RequestMapping(value = "/memberInfoUpdateAddress.do", method = RequestMethod.GET)
		public String memberInfoUpdateAddress(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoAddress(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		@RequestMapping(value = "/memberInfoUpdateEmailChk.do", method = RequestMethod.POST)
		@ResponseBody
		public String memberInfoUpdateEmailChk(String email) throws Exception {
			Random rand  = new Random(); // 인증번호를 위한 난수 생성
	        String numStr = "";
	        for(int i=0; i<8; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }
	        
	        System.out.println("수신자 이메일 : " + email);
	        System.out.println("인증번호 : " + numStr);
			
	        eService.sendMail(email,numStr);
	        
			return numStr;
			
		}
		
		
		@RequestMapping(value = "/memberInfoUpdateEmail.do", method = RequestMethod.GET)
		public String memberInfoUpdateEmail(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoEmail(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		
		@RequestMapping(value = "/memberQuitRequestPage.do", method = RequestMethod.GET)
		public String memberQuitRequestPage(@RequestParam Map<String, Object> map, Model model, String quitRequest, HttpSession session) {
			
			LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
						
			model.addAttribute("member",mVo);
			model.addAttribute("page","quitRequest");
			return "myPage";		
		}
		
		@RequestMapping(value = "/memberQuitRequestChk.do", method = RequestMethod.POST)
		public Map<String, Object> memberQuitRequestChk(@RequestParam Map<String,String> map) {
			
			Map<String, Object> resultMap = new HashMap<String, Object>(map);
			
			LibMemberVo mVo = service.loginMember(map);
				
			if(mVo == null) {
				resultMap.put("isc", "실패");
			}else if(mVo != null){
				resultMap.put("isc", "성공");
			}	
			
			return resultMap;
					
		}
		
		@RequestMapping(value = "/memberQuitRequest.do", method = RequestMethod.GET)
		public String memberQuitRequest(@RequestParam Map<String, Object> map, Model model) {
			
			int n = service.quitRequest(map);
				
			return (n==1)?"redirect:/logout.do":"redirect:/myPage.do";		
		}
		
		
//		./memberQuitRequest.do
		
		
		
	
}
