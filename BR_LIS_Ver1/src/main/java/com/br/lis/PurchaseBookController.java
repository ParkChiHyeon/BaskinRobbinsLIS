package com.br.lis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 정기구매 페이지 이동
	@RequestMapping(value = "/purchaseBookPage.do", method = RequestMethod.GET)
	public String purchaseBookPage() {
		
		return "purchaseBook";
	}
	
	// 정기구매 코드 생성
//	@RequestMapping()
//	public String CreatePurchaseCode() {
//		
//		
//		return "";
//	}
	
	
	
}
