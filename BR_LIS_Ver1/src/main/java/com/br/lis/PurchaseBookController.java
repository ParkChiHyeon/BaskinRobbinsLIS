package com.br.lis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.br.lis.model.purchaseinfo.service.PurchaseServiceImpl;
import com.br.lis.vo.PurchaseVo;
import com.google.logging.type.HttpRequest;

@Controller
public class PurchaseBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService purcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 정기구매 페이지 이동
	@RequestMapping(value = "/purchaseBookPage.do", method = RequestMethod.GET)
	public String purchaseBookPage() {
		logger.info("정기구매 페이지로 이동");
		return "purchaseBook";
	}
	
//	 정기구매 코드 생성
	@RequestMapping(value = "/createPurchaseCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String CreatePurchaseCode(Model model) {
		int creatPurchaseCodeSuccess = purcService.purchCodeInsert();
//		model.addAttribute("creatPurchaseCodeSuccess", creatPurchaseCodeSuccess);
		String result = null;
		if(creatPurchaseCodeSuccess > 0) {
			result ="SUCCESS"; 
		}else {
			result = "FAIL"; 	
		}
		return result;
//		return "purchaseBook";
	}
	

	// 정기구매 정보조회
	@RequestMapping(value = "/purchaseCodeList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String purchaseCodeList(Model model) {
		logger.info("purchaseCodeList Controller 실행");
		List<PurchaseVo> purchaseList  = purcService.purchCodeList();
		model.addAttribute("purchaseList", purchaseList);
		
		
//		return "redirect:/purchaseBookPage.do";
		return "purchaseBook";
	}
	
	
	// 정기구매 정보수정(구매금액, 구매수량, 이메일 업데이트)
	@RequestMapping(value = "/purchaseInfoUpdateFirst.do", method = RequestMethod.POST)
	@ResponseBody
	public int purchaseInfoUpdateFirst(HttpServletRequest req, Model model) {
		logger.info("purchaseInfoUpdateFirst Controller 실행");
		
		String purchase_code = req.getParameter("purchase_code");
		String total_price = req.getParameter("total_price");
		String total_ea = req.getParameter("total_ea");
		String distributor_email = req.getParameter("distributor_email");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchase_code", purchase_code);
		map.put("total_price", total_price);
		map.put("total_ea", total_ea);
		map.put("distributor_email", distributor_email);
		
//		model.addAttribute("total_price");
		
		int purchTotalUpdate = purcService.purchTotalUpdate(map);
		
		return purchTotalUpdate;
	}
	
	@RequestMapping(value = "/purchOrderDateUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public int purchOrderDateUpdate(HttpServletRequest req) {
		String purchase_code = req.getParameter("purchase_code");
		
		int purchOrderDateUpdate = purcService.purchOrderDateUpdate(purchase_code);
		
		return purchOrderDateUpdate;
	}

	@RequestMapping(value = "/purchConfirmDateUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public int purchConfirmDateUpdate(HttpServletRequest req, Model model) {
		String purchase_code = req.getParameter("purchase_code");
		
		int purchConfirmDateUpdate = purcService.purchConfirmDateUpdate(purchase_code);
		
//		model.addAttribute("confirmDate");
		
		return purchConfirmDateUpdate;
	}
	
	
	
	
}
