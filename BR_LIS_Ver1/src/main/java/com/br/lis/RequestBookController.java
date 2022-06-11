package com.br.lis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.lis.model.purchaseinfo.service.IPurchaseRegistrationService;
import com.br.lis.model.purchaseinfo.service.IPurchaseService;
import com.br.lis.model.purchaseinfo.service.IRequestPurchaseService;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.RequestPurchaseVo;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class RequestBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 희망도서 신청 링크 이동
	@RequestMapping(value = "/requestPage.do", method = RequestMethod.GET)
	public String requestPage() {
		
		return "requestPurchase";
	}
	
	// 도서 신청 버튼을 눌렀을 때
	@RequestMapping(value = "/requestBook.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String requestBookButton (HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Welcome! requestBookButton!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String isbn = req.getParameter("isbn");
		String member_id = req.getParameter("member_id");
		String title = req.getParameter("title");
		String publisher = req.getParameter("publisher");
		String auth = req.getParameter("author");
		String translator = req.getParameter("translator");
		String price = req.getParameter("price");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isbn",isbn);
		map.put("member_id",member_id);
		map.put("title",title);
		map.put("publisher",publisher);
		map.put("author",auth);
		map.put("translator",translator);
		map.put("price",price);
		
		
		reqPurcService.purchRequestInsert(map);
		return "requestPurchase";
	}
	
	
	// (관리자) 신청도서 목록첫화면
	@RequestMapping(value = "/requestBookList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String requestBookListAdmin(Model model) {
		String searchKey = "PURCHASE_CODE"; 
		String searchValue = "P2206";
		List<RequestPurchaseVo> reqList = reqPurcService.purchReqListSelectByCode(searchKey, searchValue);
		
		model.addAttribute("reqList", reqList);
		return "requestBookAdmin";
	}
	
	// (관리자) 신청도서 목록리스트(아이디, 구매코드 중 하나를 선택하여 조회)
	@RequestMapping(value = "/requestBookListCode.do", method = RequestMethod.POST)
	@ResponseBody
	public List<RequestPurchaseVo> requestBookListCode(HttpServletRequest req, HttpServletResponse resp, Model model) {
		String searchKey = req.getParameter("searchKey");
		String searchValue = req.getParameter("searchValue");	
		
		List<RequestPurchaseVo> reqList = reqPurcService.purchReqListSelectByCode(searchKey, searchValue);
		
		model.addAttribute("reqList", reqList);
		
		// 신청도서의 목록을 success:function(msg)리턴
		return reqList;
	}
	
	// 사용자의 아이디로 휴대폰 번호를 조회
	@RequestMapping(value = "/searchPhoneNumber.do", method = RequestMethod.POST)
	@ResponseBody
	public String searchPhoneNumber(HttpServletRequest req, Model model) {
		String searchPhoneNumber = req.getParameter("searchPhoneNumber");
		LibMemberVo phoneNumber = reqPurcService.purchReqPhoneSelect(searchPhoneNumber);
		
		String phoneNumResult = phoneNumber.getPhone();
		
		model.addAttribute("phoneNumResult",phoneNumResult);
		
		return phoneNumResult;
	}
	
	// modal에서 신청도서 알림 문자메시지를 전송
	@RequestMapping(value = "/sendMessage.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessage(HttpServletRequest req) {
		// 화면에서 전달받은 휴대폰 번호와 메세지
		String phoneNumber = req.getParameter("phoneNumber");
		String requestBookMessage = req.getParameter("requestBookMessage");
		
		// SMS 인증키
		String api_key = "NCSZADE5ZEC1DZR3"; // coolSMS 사이트에서 받은 인증키
        String api_secret = "HQMRDEF5F5WUBE15UHVGMYSOY4PVFBJS"; // coolSMS 자체에 발급된 비밀키
        Message coolsms = new Message(api_key, api_secret);

        // 4개의 입력값 필요
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01073780203");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨 -> 발신전화 번호는 coolSMS에 등록해줘야됨
        params.put("type", "SMS");// type 방식
        params.put("text", requestBookMessage);
  	    params.put("app_version", "test app 1.2"); // application name and version

  	    try {
        	JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            return "fail";
        }
	        return "sucssess";
	}
	
	// 사용자 희망도서 목록 조회 페이지로 이동
	@RequestMapping(value = "/requestBookUserPage.do", method = RequestMethod.GET)
	public String requestBookUserPage() {
		return "redirect:/myRequestBookList.do";
	}
	
	//member 유저세션 admin 관리자세션
	// 사용자가 신청한 도서의 목록(구매여부) 조회(50개 까지만 조회 가능)
	@RequestMapping(value = "/myRequestBookList.do", method = RequestMethod.GET)
	public String myRequestBook(HttpSession session, Model model) {
		String member_id = "gnldnd17";
		List<RequestPurchaseVo> myRequestBookList = reqPurcService.purchReqConfirmSelect(member_id);
		
//		LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
		
		model.addAttribute("myRequestBookList",myRequestBookList);
		model.addAttribute("reqPage","userRequestBook");
		
//		return "requestBookUser";
		return "myPage";
	}
	
	
}
