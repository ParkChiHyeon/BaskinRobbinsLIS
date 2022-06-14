package com.br.lis.model.purchaseinfo.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.PurchaseVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;

public interface IPurchaseDao {

	// 구매코드생성(BR_W_BM_601) : 해당 월의 구매 코드를 생성
	public int purchCodeInsert();
	
	// 정기구매(BR_W_BM_602) : 구매정보 전체조회
	public List<PurchaseVo> purchCodeList();
	
	// 정기구매(BR_W_BM_602) : 구매 해당 월의 구매코드를 조회
	public PurchaseVo purchCodeOne(String purchCodeVo);
	
	// 정기구매(BR_W_BM_602) : 정기구매 정보를 삽입
	public int purchRegulInsert(Map<String, Object> regulMap);
	
	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(권수 업데이트)
	public int regulQuantityUpdate(Map<String, Object> regulMap);
	
	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(구매여부(CONFIRM)업데이트)
	public int regulConfirmUpdate(String regulSerialVo);
	
	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(권수 업데이트)
	public int reqQuantityUpdate(Map<String, Object> reqMap);
	
	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(구매여부 업데이트)
	public int reqConfirmUpdate(String wishSerialVo);
	
	// 발주목록(BR_W_BM_606) : 신청구매
	public List<RequestPurchaseVo> orderReqBookList(String purchCodeVo);
	
	// 발주목록(BR_W_BM_606) : 정기구매
	public List<RegularPurchaseVo> orderRegulBookList(String purchCodeVo);
	
	// 발주(BR_W_BM_607) : (관리자)구매할 책의 총 가격, 권수, 구매 업체의 이메일 업데이트
	public int purchTotalUpdate(Map<String, Object> purchVo);
	
	// 발주(BR_W_BM_607) : (관리자)주문날짜, 확졍여부(발주O) 업데이트
	public int purchOrderDateUpdate(String purchCodeVo);
	
	// 발주(BR_W_BM_607) : (관리자)확정일, 확졍여부(반입C) 업데이트
	public int purchConfirmDateUpdate(String purchCodeVo);
	
	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(신청도서)
	public int reqReceiveUpdate(Map<String, Object> reqMap);
	
	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(정기구매도서)
	public int regulReceiveUpdate(Map<String, Object> regulMap);

	// 정기구매 정보 조회 : 정기구매 신청 된 도서의 목록 전체 조회 
//	public List<RegularPurchaseVo> purchRegulListSelectByCode(String purchCodeVo);
	public List<RegularPurchaseVo> purchRegulListSelectByCode();
	
}
