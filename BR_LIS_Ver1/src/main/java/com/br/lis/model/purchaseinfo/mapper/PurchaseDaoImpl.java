package com.br.lis.model.purchaseinfo.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.PurchaseVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;


@Repository
public class PurchaseDaoImpl implements IPurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.purchaseinfo.mapper.PurchaseDaoImpl.";

	// 구매코드생성(BR_W_BM_601) : 해당 월의 구매 코드를 생성
	@Override
	public int purchCodeInsert() {
		return sqlSession.insert(NS + "purchCodeInsert");
	}

	// 정기구매(BR_W_BM_602) : 구매정보 전체조회 
	@Override
	public List<PurchaseVo> purchCodeList() {
		return sqlSession.selectList(NS + "purchCodeList");
	}

	// 정기구매(BR_W_BM_602) : 구매 해당 월의 구매코드를 조회
	@Override
	public PurchaseVo purchCodeOne(String purchCodeVo) {
		return sqlSession.selectOne(NS + "purchCodeOne", purchCodeVo);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보를 삽입
	@Override
	public int purchRegulInsert(Map<String, Object> regulMap) {
		return sqlSession.insert(NS + "purchRegulInsert", regulMap);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(권수 업데이트)
	@Override
	public int regulQuantityUpdate(Map<String, Object> regulMap) {
		return sqlSession.update(NS + "regulQuantityUpdate", regulMap);
	}

	// 정기구매(BR_W_BM_602) : 정기구매 정보 업데이트(구매여부(CONFIRM)업데이트)
	@Override
	public int regulConfirmUpdate(String regulSerialVo) {
		return sqlSession.update(NS + "regulConfirmUpdate", regulSerialVo);
	}

	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(권수 업데이트)
	@Override
	public int reqQuantityUpdate(Map<String, Object> reqMap) {
		return sqlSession.update(NS + "reqQuantityUpdate", reqMap);
	}

	// 신청구매(BR_W_BM_603) : 신청구매 정보 업데이트(구매여부 업데이트)
	@Override
	public int reqConfirmUpdate(String wishSerialVo) {
		return sqlSession.update(NS + "reqConfirmUpdate", wishSerialVo);
	}

	// 발주목록(BR_W_BM_606) : 신청구매
	@Override
	public List<RequestPurchaseVo> orderReqBookList(String purchCodeVo) {
		return sqlSession.selectList(NS + "orderReqBookList", purchCodeVo);
	}

	// 발주목록(BR_W_BM_606) : 정기구매
	@Override
	public List<RegularPurchaseVo> orderRegulBookList(String purchCodeVo) {
		return sqlSession.selectList(NS + "orderRegulBookList", purchCodeVo);
	}

	// 발주(BR_W_BM_607) : (관리자)구매할 책의 총 가격, 권수, 구매 업체의 이메일 업데이트
	@Override
	public int purchTotalUpdate(Map<String, Object> purchVo) {
		return sqlSession.update(NS + "purchTotalUpdate", purchVo);
	}

	// 발주(BR_W_BM_607) : (관리자)주문날짜, 확졍여부(발주O) 업데이트
	@Override
	public int purchOrderDateUpdate(String purchCodeVo) {
		return sqlSession.update(NS + "purchOrderDateUpdate", purchCodeVo);
	}

	// 발주(BR_W_BM_607) : (관리자)확정일, 확졍여부(반입C) 업데이트
	@Override
	public int purchConfirmDateUpdate(String purchCodeVo) {
		return sqlSession.update(NS + "purchConfirmDateUpdate", purchCodeVo);
	}

	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(신청도서)
	@Override
	public int reqReceiveUpdate(String reqSerial) {
		return sqlSession.update(NS + "reqReceiveUpdate", reqSerial);
	}

	// 입고(BR_W_BM_608) : 구매 완료되어 입고된 도서의 입고일을 업데이트(정기구매도서)
	@Override
	public int regulReceiveUpdate(String regulSerial) {
		return sqlSession.update(NS + "regulReceiveUpdate", regulSerial);
	}
	
//	// 정기구매 정보 조회 : 정기구매 신청 된 도서의 목록 전체 조회 
//	@Override
//	public List<RegularPurchaseVo> purchRegulListSelectByCode(String purchCodeVo) {
//		return sqlSession.selectList(NS + "purchRegulListSelectByCode", purchCodeVo);
//	}
	
	// 정기구매 정보 조회 : 정기구매 신청 된 도서의 목록 전체 조회 
	@Override
	public List<RegularPurchaseVo> purchRegulListSelectByCode() {
		return sqlSession.selectList(NS + "purchRegulListSelectByCode");
	}
	

	
	
	
}
