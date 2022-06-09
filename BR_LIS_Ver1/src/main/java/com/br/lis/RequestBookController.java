package com.br.lis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.lis.model.purchaseinfo.service.IPurchaseRegistrationService;
import com.br.lis.model.purchaseinfo.service.IPurchaseService;
import com.br.lis.model.purchaseinfo.service.IRequestPurchaseService;
import com.br.lis.vo.RequestPurchaseVo;

@Controller
public class RequestBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 희망도서 신청 링크
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
	public String requestBookListCodeTest(Model model) {
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
		return reqList;
	}

	
	
	
}
