package com.br.lis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.lis.model.lendinginfo.service.IReturnBookService;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

@Controller
public class ReturnBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReturnBookService iService;
	
	// 반납 페이지 
	@RequestMapping(value = "/returnBookPage.do", method = RequestMethod.GET)
	public String returnBookPage (Model model) {
		logger.info("Welcome! ReturnBookController returnBookPage");
		String book_serial = "BKSR155997";
		LendingVo vo = iService.lendingDetailForReturnBook(book_serial);
		model.addAttribute("vo",vo);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("book_serial", book_serial);
		ReservationVo rVo = iService.returnBookReserveCheck(rMap); 
		model.addAttribute("rVo", rVo);
		LibMemberVo mVo = iService.lendingDetailForReturnUser(vo.getMember_code());
		model.addAttribute("mVo",mVo);
		return "returnBookPage";
	}
	
	public String returnBookProcessing() {
		return null;
		
	}
	
	
}
