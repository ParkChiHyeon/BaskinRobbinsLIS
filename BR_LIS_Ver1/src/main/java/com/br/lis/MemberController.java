package com.br.lis;




import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "/sendSMS.do",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ModelAndView sendSMS(@RequestParam String phone, Map<String,Object> map) {
		
		ModelAndView mav = new ModelAndView();
		
//		LibMemberVo mVo = service.findPw(map);
		
		  Random rand  = new Random(); // 인증번호를 위한 난수 생성
	        String numStr = "";
	        for(int i=0; i<8; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }
	        
	        System.out.println("수신자 번호 : " + phone);
	        System.out.println("인증번호 : " + numStr);
	        cService.certifiedPhoneNumber(phone,numStr);
//	        mav.addObject("mVo",mVo);
	        mav.addObject("numStr", numStr);
	        mav.setViewName("updatePwPage");
	        logger.info("인증받은 번호 :{}", mav);
	        
	        return mav;
	    }
	
	@RequestMapping(value = "/updatePwPage.do" , method = RequestMethod.POST)
	public String updatePwPage(@RequestParam Map<String, Object> map, Model model) {
		
		LibMemberVo vo = service.findPw(map);
		
		model.addAttribute("mVo",vo);
		return "updatePwPage";	
	}
	
	@RequestMapping(value = "/updatePw.do", method = RequestMethod.GET)
	public String updatePw(@RequestParam Map<String, Object> map) {
		
		int n = service.updateNewPw(map);
		
		return (n==1)?"redirect:/loginPage.do":"reirect:/updatePwPage";	
	}
	
		
	/* sms 인증 */
	@RequestMapping(value = "/sendSMSmyPage.do",method = RequestMethod.POST)
	@ResponseBody
	public String sendSMSmyPage(String phone) {		
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
	public Map<String, Object> findIdChk(@RequestParam Map<String,Object> map) {
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
		@ResponseBody
		public Map<String, Object> findPwChk(@RequestParam Map<String,Object> map, Model model) {
			System.out.println(map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			
			LibMemberVo vo = service.findPw(map);
			
			model.addAttribute("mVo",vo);
			
			if(vo == null) {
				resultMap.put("isc", "실패");
			}else if(vo != null) {
				resultMap.put("isc", "성공");
			}
			
			return resultMap;
			
		}
		
		/*마이페이지 화면 이동*/
		@RequestMapping(value = "/myPage.do",method = RequestMethod.GET)
		public String bookSearch(Model model,String page) {
			logger.info("mypage memberInfoUpdate 이동");
			model.addAttribute("page", "update");
			model.addAttribute("page", page);
			return "redirect:/memberInfoUpdatePage.do";
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
		
		/* 회원 비밀번호 변경 */		
		@RequestMapping(value = "/memberInfoUpdatePw.do", method = RequestMethod.GET)
		public String memberInfoUpdatePw(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoPw(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		/* 회원 이름 변경 */	
		@RequestMapping(value = "/memberInfoUpdateName.do", method = RequestMethod.GET)
		public String memberInfoUpdateName(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoName(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		/* 회원 전화번호 변경 */	
		@RequestMapping(value = "/memberInfoUpdatePhone.do", method = RequestMethod.GET)
		public String memberInfoUpdatePhone(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoPhone(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		/* 회원 주소 변경 */
		@RequestMapping(value = "/memberInfoUpdateAddress.do", method = RequestMethod.GET)
		public String memberInfoUpdateAddress(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoAddress(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		/* 회원 이메일 인증번호 전송 */
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
		
		/*회원 이메일 변경 */
		@RequestMapping(value = "/memberInfoUpdateEmail.do", method = RequestMethod.GET)
		public String memberInfoUpdateEmail(@RequestParam Map<String, Object> map) {
			logger.info("MemberController = > memberInfoUpdate");
			int n = service.updateMyInfoEmail(map);
			
			return (n==1)? "redirect:/logout.do":"redirect:/memberInfoUpdate.do";
		}
		
		/*  알림 수신 변경 */
		@RequestMapping(value = "/memberInfoUpdateNotification.do", method = RequestMethod.GET)
		public String memberInfoUpdateNotification(@RequestParam Map<String, Object> map) {
			
			logger.info("MemberController memberInfoUpdateNotification: {}", map);
			
			boolean isc = service.notificationYN(map);
			
			return (isc==true)? "redirect:/logout.do":"redirect:/myPage.do";
		}
		
		/* 회원 탈퇴신청 페이지 이동 */
		@RequestMapping(value = "/memberQuitRequestPage.do", method = RequestMethod.GET)
		public String memberQuitRequestPage(@RequestParam Map<String, Object> map, Model model, String quitRequest, HttpSession session) {
			
			LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
						
			model.addAttribute("member",mVo);
			model.addAttribute("page","quitRequest");
			return "myPage";		
		}
		
		/* 회원 탈퇴 아작스 */
		@RequestMapping(value = "/memberQuitRequestChk.do", method = RequestMethod.POST)
		public Map<String, Object> memberQuitRequestChk(@RequestParam Map<String,String> map, Model model) {
			
			Map<String, Object> resultMap = new HashMap<String, Object>(map);
			
			LibMemberVo mVo = service.loginMember(map);
				
			if(mVo == null) {
				resultMap.put("isc", "실패");
			}else if(mVo != null){
				resultMap.put("isc", "성공");
			}	
			
			
			
			return resultMap;
					
		}
		
		/* 회원 탈퇴 */
		@RequestMapping(value = "/memberQuitRequest.do", method = RequestMethod.POST)
		public String memberQuitRequest(@RequestParam Map<String, Object> map, Model model) {
			
			int n = service.quitRequest(map);
				
			return (n==1)?"redirect:/logout.do":"redirect:/myPage.do";		
		}
		
		/* 관내회원 등업 페이지 이동*/ 
		@RequestMapping(value = "/updateToGhPage.do", method = RequestMethod.GET)
		public String updateToGhPage(Model model, HttpSession session, String changeAuth) {
			
			LibMemberVo  mVo = (LibMemberVo) session.getAttribute("member");
			
			model.addAttribute("member",mVo);
			model.addAttribute("page","changeAuth");
			
			
			
			return "myPage";
		}
		
		@RequestMapping(value = "/updateGH.do", method = RequestMethod.GET)
		public String updateGH(@RequestParam Map<String,Object> map) {
					
			int n = service.updateNmToGh(map);
			
			return (n==1)?"redirect:/memberCard.do":"redirect:/myPage.do";
			
		}
		
		@RequestMapping(value = "/memberCard.do", method = RequestMethod.GET)
		public String memberCard(HttpSession session, Model model, String memberCard) {
			
			LibMemberVo  mVo = (LibMemberVo) session.getAttribute("member");
			
			model.addAttribute("member",mVo);
			model.addAttribute("page","memberCard");
			
			return "myPage";
			
		}
		
		@RequestMapping(value = "/quitMemberManagePage.do" , method = RequestMethod.GET)
		public String quitMemberManagePage(Model model) {
			
			List<LibMemberVo> lists = service.quitSelectMember();
		
			model.addAttribute("lists",lists);
			
			return "quitMemberManagePage";
		}
		
		@RequestMapping(value = "/quitAcceptMember.do", method = RequestMethod.POST)
		public String quitAcceptMember(HttpServletRequest req) {
			
			String member_id = req.getParameter("member_id");
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("member_id", member_id);
			
			int n = service.quitApprove(resultMap);
			return (n==1)?"redirect:/quitMemberManagePage.do":"redirect:/quitMemberManagePage.do";			
		}
		
		@RequestMapping(value = "/quitRequirementChk.do", method =RequestMethod.POST)
		public Map<String, Object> quitRequirementChk(HttpServletRequest req) {
				
			String member_id = req.getParameter("member_id");
			String rental_count= req.getParameter("rental_count");
			String penalty = req.getParameter("penalty");
			
			logger.info("받은 아이디 :{}", member_id);
			logger.info("받은 rc :{}", rental_count);
			logger.info("받은 pe :{}", penalty);
			
			
		
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("member_id", member_id);
			resultMap.put("rental_count", rental_count);
			resultMap.put("penalty", penalty);
			
			int n = service.quitRequirementChk(resultMap);
			
			logger.info("결과 :{}", resultMap);
						
			logger.info("성공결과 : {}",n);
			if(n==1) {
				resultMap.put("isc", "성공");
			}else {
				resultMap.put("isc", "실패");
			}
			
			return resultMap;		
		}
		
}

		

		
	

