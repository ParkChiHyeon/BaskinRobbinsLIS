package com.br.lis;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

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
import com.br.lis.vo.PurchaseVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

@Controller
public class RegularBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 정기도서구매관리 링크 이동
	@RequestMapping(value = "/regularPage.do", method = RequestMethod.GET)
	public String regularPage() {
		return "regularBookAdmin";
	}
	
//	// 정기구매도서의 리스트를 조회
//	@RequestMapping(value = "/purchRegulList.do", method = RequestMethod.POST)
//	@ResponseBody
//	public List<RegularPurchaseVo> purchRegulListSelectByCode(HttpServletRequest req) {
//		String searchValue = req.getParameter("searchValue");
//		List<RegularPurchaseVo> purchRegulList = PurcService.purchRegulListSelectByCode(searchValue);
//
//		return purchRegulList;
//	}
	
	
	
	
	// 정기구매도서의 리스트를 조회(데이터테이블 사용)
	@RequestMapping(value = "/purchRegulList.do", method = RequestMethod.GET)
//	@ResponseBody
	public String purchRegulListSelectByCode(HttpServletRequest req, Model model) {
		List<RegularPurchaseVo> purchRegulList = PurcService.purchRegulListSelectByCode();
		model.addAttribute("purchRegulList", purchRegulList);
		return "regularPurchaseDataTable";
	}
	

	
	
	// regularPurchaseBook DataTable Test
//	@RequestMapping(value = "/regularPurchaseBookDataTable.do", method = RequestMethod.GET)
//	public String regularPurchaseBookDataTable() {
//		
//		return "regularPurchaseDataTable";
//	}
	
	// 구매 수량을 변경해주는 메소드
	@RequestMapping(value = "/changePurchaseCount.do", method = RequestMethod.POST)
	@ResponseBody
	public int changePurchaseCount(HttpServletRequest req) {
		// 화면에서 구매수량과 시리얼번호를 받아옴
		String changePurchaseCount = req.getParameter("changePurchaseCount");
		String regular_serial = req.getParameter("regular_serial");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("ea", changePurchaseCount);		
		map.put("regular_serial", regular_serial);
		
		int result = PurcService.regulQuantityUpdate(map);
		
		return result;
	}
	
	// 구매불가사유를 업데이트하는 메소드
	@RequestMapping(value = "/notPurchaseReason.do", method = RequestMethod.POST)
	@ResponseBody
	public int notPurchaseReason(HttpServletRequest req) {
		String notPurchaseReaso = req.getParameter("notPurchaseReaso");
		String regular_serial = req. getParameter("regular_serial");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("history", notPurchaseReaso);		
		map.put("regular_serial", regular_serial);

		int result = registService.updateRegulHistory(map);
		
		return result;
	}
	
	// 체크된 row의 입고일을 업데이트하는 메소드
	@RequestMapping(value = "/recieveBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int regulReceiveUpdate(HttpServletRequest req) {	
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
			result = PurcService.regulReceiveUpdate(recieveBooks[i]);
		}
		return result;
	}
	
	// 체크 된 row의 확정여부를 N으로 변경하는 메소드
	@RequestMapping(value = "/changeConfirmN.do", method = RequestMethod.POST)
	@ResponseBody
	public int regulConfirmUpdate(HttpServletRequest req) {
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
			result = PurcService.regulConfirmUpdate(recieveBooks[i]);
		}
		return result;
	}
	
	
	

	
	
}
