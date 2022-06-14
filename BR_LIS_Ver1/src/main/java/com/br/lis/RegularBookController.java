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
	
	@RequestMapping(value = "./purchRegulList.do", method = RequestMethod.POST)
	public String purchRegulListSelectByCode(Model model) {
		
		
		return null;
	}
	
	
	

	
	
}
