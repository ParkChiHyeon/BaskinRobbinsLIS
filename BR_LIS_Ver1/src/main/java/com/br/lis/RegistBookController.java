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
public class RegistBookController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	
	private IRequestPurchaseService reqPurcService;
	@Autowired
	private IPurchaseService PurcService;
	@Autowired
	private IPurchaseRegistrationService registService;
	
	// 정기도서구매관리 링크 이동
	@RequestMapping(value = "/registPage.do", method = RequestMethod.GET)
	public String regularPage() {
		return "registPurchaseBookDataTable";
	}
	

	
	
}
