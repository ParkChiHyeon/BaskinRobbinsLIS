package com.br.lis;

import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String requestBookButton (HttpServletRequest req,@RequestParam Map<String, Object> map) {
		logger.info("Welcome! requestBookButton!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String isbn = req.getParameter("isbn");
		String member_id = req.getParameter("member_id");
		String title = req.getParameter("title");
		String publisher = req.getParameter("publisher");
		String auth = req.getParameter("author");
		String translator = req.getParameter("translator");
		String price = req.getParameter("price");
		
		// 한 권의 책이 중복신청되지 않도록 하기 위한 쿼리// 왜안됨?ㅋ
//		List<RequestPurchaseVo> purchReqOnceCheck = reqPurcService.purchReqOncePerBook(isbn);
		
//		Map<String, Object> map = new HashMap<String, Object>(); 
		
		
		// 해당 월에 중복되는 isbn이 없다면 map에 값을 넣어준다
//		if(purchReqOnceCheck == null) {
//			map.put("isbn",isbn);
//			map.put("member_id",member_id);
//			map.put("title",title);
//			map.put("publisher",publisher);
//			map.put("author",auth);
//			map.put("translator",translator);
//			map.put("price",price);
			
			reqPurcService.purchRequestInsert(map);
			
			return "success";
//		}else {
//			map.put();
//			return "fail";
//		}
		
		
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
		LibMemberVo mVo =  (LibMemberVo) session.getAttribute("member");
		
		List<RequestPurchaseVo> myRequestBookList = reqPurcService.purchReqConfirmSelect(mVo.getMember_id());
		
		model.addAttribute("myRequestBookList",myRequestBookList);
		model.addAttribute("reqPage","userRequestBook");
		
		return "myPage";
	}
	
	// 신청도서의 구매 수량을 변경해주는 메소드
	@RequestMapping(value = "/changePurchaseCountReq.do", method = RequestMethod.POST)
	@ResponseBody
	public int changePurchaseCount(HttpServletRequest req) {
		// 화면에서 구매수량과 시리얼번호를 받아옴
		String changePurchaseCount = req.getParameter("changePurchaseCount");
		String wish_serial = req.getParameter("wish_serial");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ea", changePurchaseCount);		
		map.put("wish_serial", wish_serial);
		
		int result = PurcService.reqQuantityUpdate(map);
		
		return result;
	}
	
	// 구매불가사유를 업데이트하는 메소드
	@RequestMapping(value = "/notPurchaseReasonReq.do", method = RequestMethod.POST)
	@ResponseBody
	public int notPurchaseReason(HttpServletRequest req) {
		String notPurchaseReason = req.getParameter("notPurchaseReason");
		String wish_serial = req. getParameter("wish_serial");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("history", notPurchaseReason);		
		map.put("wish_serial", wish_serial);

		int result = registService.updateReqHistory(map);
		
		return result;
	}
	
	
	// 체크된 row의 입고일을 업데이트하는 메소드
	@RequestMapping(value = "/recieveBookReq.do", method = RequestMethod.POST)
	@ResponseBody
	public int reqReceiveUpdate(HttpServletRequest req) {	
		String recieveBooks[] = null;
		Map map = req.getParameterMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			recieveBooks = (String[]) map.get(key);
		}
		
		int result = 0;
		
		int i = 0;
		for(i = 0; i < recieveBooks.length; i++) {
			result = PurcService.reqReceiveUpdate(recieveBooks[i]);
		}
		return result;
	}
	
	// 체크 된 row의 확정여부를 N으로 변경하는 메소드
	@RequestMapping(value = "/changeConfirmNReq.do", method = RequestMethod.POST)
	@ResponseBody
	public int reqConfirmUpdate(HttpServletRequest req) {
		String recieveBooks[] = null;
		Map map = req.getParameterMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			recieveBooks = (String[]) map.get(key);
		}
		
		int result = 0;
		
		int i = 0;
		for(i = 0; i < recieveBooks.length; i++) {
			result = PurcService.reqConfirmUpdate(recieveBooks[i]);
		}
		return result;
	}
	
	
	
	
	
	
	
}
