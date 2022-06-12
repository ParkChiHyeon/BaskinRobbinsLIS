package com.br.lis.model.purchaseinfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.purchaseinfo.mapper.IPurchaseDao;
import com.br.lis.vo.PurchaseVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	@Autowired
	private IPurchaseDao purchDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	// 구매코드생성(BR_W_BM_601) : 해당 월의 구매 코드를 생성
	@Override
	public int purchCodeInsert() {
		logger.info("PurchaseServiceImpl purchCodeInsert");
		return purchDao.purchCodeInsert();
	}

	// 정기구매(BR_W_BM_602) : 구매정보 전체조회 
	@Override
	public List<PurchaseVo> purchCodeList() {
		logger.info("PurchaseServiceImpl purchCodeList");
		return purchDao.purchCodeList();
	}

	// 정기구매(BR_W_BM_602) : 구매 해당 월의 구매코드를 조회
	@Override
	public PurchaseVo purchCodeOne(String purchCodeVo) {
		logger.info("PurchaseServiceImpl purchCodeOne");
		return purchDao.purchCodeOne(purchCodeVo);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보를 삽입
	@Override
	public int purchRegulInsert(Map<String, Object> regulMap) {
		logger.info("PurchaseServiceImpl purchRegulInsert");
		return purchDao.purchRegulInsert(regulMap);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(권수 업데이트)
	@Override
	public int regulQuantityUpdate(Map<String, Object> regulMap) {
		logger.info("PurchaseServiceImpl regulQuantityUpdate");
		return purchDao.regulQuantityUpdate(regulMap);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(구매여부(CONFIRM)업데이트)
	@Override
	public int regulConfirmUpdate(String regulSerialVo) {
		logger.info("PurchaseServiceImpl regulConfirmUpdate");
		return purchDao.regulConfirmUpdate(regulSerialVo);
	}

	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(권수 업데이트)
	@Override
	public int reqQuantityUpdate(Map<String, Object> reqMap) {
		logger.info("PurchaseServiceImpl reqQuantityUpdate");
		return purchDao.reqQuantityUpdate(reqMap);
	}

	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(구매여부 업데이트)
	@Override
	public int reqConfirmUpdate(String wishSerialVo) {
		logger.info("PurchaseServiceImpl reqConfirmUpdate");
		return purchDao.reqConfirmUpdate(wishSerialVo);
	}

	// 발주목록(BR_W_BM_606) : 신청구매
	@Override
	public List<RequestPurchaseVo> orderReqBookList(String purchCodeVo) {
		logger.info("PurchaseServiceImpl orderReqBookList");
		return purchDao.orderReqBookList(purchCodeVo);
	}

	// 발주목록(BR_W_BM_606) : 정기구매
	@Override
	public List<RegularPurchaseVo> orderRegulBookList(String purchCodeVo) {
		logger.info("PurchaseServiceImpl orderRegulBookList");
		return purchDao.orderRegulBookList(purchCodeVo);
	}

	// 발주(BR_W_BM_607) : (관리자)구매할 책의 총 가격, 권수, 구매 업체의 이메일 업데이트
	@Override
	public int purchTotalUpdate(Map<String, Object> purchVo) {
		logger.info("PurchaseServiceImpl purchTotalUpdate");
		return purchDao.purchTotalUpdate(purchVo);
	}

	// 발주(BR_W_BM_607) : (관리자)주문날짜, 확졍여부(발주O) 업데이트
	@Override
	public int purchOrderDateUpdate(String purchCodeVo) {
		logger.info("PurchaseServiceImpl purchOrderDateUpdate");
		return purchDao.purchOrderDateUpdate(purchCodeVo);
	}

	// 발주(BR_W_BM_607) : (관리자)확정일, 확졍여부(반입C) 업데이트
	@Override
	public int purchConfirmDateUpdate(String purchCodeVo) {
		logger.info("PurchaseServiceImpl purchConfirmDateUpdate");
		return purchDao.purchConfirmDateUpdate(purchCodeVo);
	}

	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(신청도서)
	@Override
	public int reqReceiveUpdate(Map<String, Object> reqMap) {
		logger.info("PurchaseServiceImpl reqReceiveUpdate");
		return purchDao.reqReceiveUpdate(reqMap);
	}

	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(정기구매도서)
	@Override
	public int regulReceiveUpdate(Map<String, Object> regulMap) {
		logger.info("PurchaseServiceImpl regulReceiveUpdate");
		return purchDao.regulReceiveUpdate(regulMap);
	}
	

	
}
