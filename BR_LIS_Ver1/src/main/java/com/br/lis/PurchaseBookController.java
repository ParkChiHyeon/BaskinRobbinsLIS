package com.br.lis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lis.model.purchaseinfo.service.IPurchaseRegistrationService;
import com.br.lis.model.purchaseinfo.service.IPurchaseService;
import com.br.lis.model.purchaseinfo.service.IRequestPurchaseService;

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
	public String CreatePurchaseCode(Model model) {
		int creatPurchaseCodeSuccess = purcService.purchCodeInsert();
		model.addAttribute("creatPurchaseCodeSuccess", creatPurchaseCodeSuccess);
//		String result = null;
//		if(creatPurchaseCodeSuccess > 0) {
//			result ="SUCCESS"; 
//		}else {
//			result = "FAIL"; 	
//		}
//		return result;
		return "purchaseBook";
	}
	
	
	
}
