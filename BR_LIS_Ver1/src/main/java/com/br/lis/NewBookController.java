package com.br.lis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.RegularPurchaseVo;

@Controller
public class NewBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 신규도서 페이지 이동
//	@RequestMapping(value = "/newBookPage.do", method = RequestMethod.GET)
//	public String newBookPage() {
//		
//		return "newBookDataTable";
//	}
	
	
	// 정기구매도서의 리스트를 조회(데이터테이블 사용)
//	@RequestMapping(value = "/purchRegulList.do", method = RequestMethod.GET)
//	public String purchRegulListSelectByCode(HttpServletRequest req, Model model) {
//		List<RegularPurchaseVo> purchRegulList = PurcService.purchRegulListSelectByCode();
//		model.addAttribute("purchRegulList", purchRegulList);
//		return "regularPurchaseDataTable";
//	}
	
	// 신규도서 목록 조회
	@RequestMapping(value = "/newBookList.do", method = RequestMethod.GET)
	public String selectNewBookUser(Model model) {
		List<BookInfoVo> newBookList = registService.selectNewBookUser();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ newBookList);
		model.addAttribute("newBookList",newBookList);
		
		return "newBookDataTable";
	}
	
	
	
}
