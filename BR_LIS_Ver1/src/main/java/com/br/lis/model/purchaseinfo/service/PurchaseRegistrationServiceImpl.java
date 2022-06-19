package com.br.lis.model.purchaseinfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.purchaseinfo.mapper.IPurchaseDao;
import com.br.lis.model.purchaseinfo.mapper.IPurchaseRegistrationDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.Possessing_BookVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;

@Service
public class PurchaseRegistrationServiceImpl implements IPurchaseRegistrationService {

	@Autowired
	private IPurchaseRegistrationDao registDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 구매도서등록(BR_W_BM_701) : 보유도서에 삽입
	@Override
	public int insertPurchaseBook(String isbnVo) {
		logger.info("PurchaseRegistrationServiceImpl insertPurchaseBook");
		return registDao.insertPurchaseBook(isbnVo);
	}

	// 구매도서등록(BR_W_BM_701) : 보유도서의 책 갯수와 일치하도록 업데이트
	@Override
	public int updateBookCount(String isbnVo) {
		logger.info("PurchaseRegistrationServiceImpl updateBookCount");
		return registDao.updateBookCount(isbnVo);
	}

	// 신규도서조회(BR_W_BM_702) : (관리자) 등록된 도서 중 현재월로부터 등록일이 한 달 이내인 도서를 조회
	@Override
	public List<Possessing_BookVo> selectNewBookAdmin() {
		logger.info("PurchaseRegistrationServiceImpl selectNewBookAdmin");
		return registDao.selectNewBookAdmin();
	}

	// 신규도서조회(BR_W_BM_702) : (사용자) 선택된 ISBN으로 등록일이 한 달 이내인 도서정보를 조회
	@Override
	public List<BookInfoVo> selectNewBookUser() {
		logger.info("PurchaseRegistrationServiceImpl selectNewBookUser");
		return registDao.selectNewBookUser();
	}

	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(신청구매)
	@Override
	public List<RequestPurchaseVo> selectReqHistory(String purchCodeVo) {
		logger.info("PurchaseRegistrationServiceImpl selectReqHistory");
		return registDao.selectReqHistory(purchCodeVo);
	}

	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(신청구매)
	@Override
	public int updateReqHistory(Map<String, Object> reqMap) {
		logger.info("PurchaseRegistrationServiceImpl updateReqHistory");
		return registDao.updateReqHistory(reqMap);
	}

	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(정기구매)
	@Override
	public List<RegularPurchaseVo> selectRegulHistory(String purchCodeVo) {
		logger.info("PurchaseRegistrationServiceImpl selectRegulHistory");
		return registDao.selectRegulHistory(purchCodeVo);
	}

	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(정기구매)
	@Override
	public int updateRegulHistory(Map<String, Object> regulMap) {
		logger.info("PurchaseRegistrationServiceImpl updateRegulHistory");
		return registDao.updateRegulHistory(regulMap);
	}

	

	
	
	

	
}
