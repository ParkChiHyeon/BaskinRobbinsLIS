package com.br.lis.model.purchaseinfo.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.Possessing_BookVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;


@Repository
public class PurchaseRegistrationDaoImpl implements IPurchaseRegistrationDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS="com.br.lis.model.purchaseinfo.mapper.PurchaseRegistrationDaoImpl.";

	// 구매도서등록(BR_W_BM_701) : 보유도서에 삽입
	@Override
	public int insertPurchaseBook(String isbnVo) {
		return sqlSession.insert(NS + "insertPurchaseBook", isbnVo);
	}

	// 구매도서등록(BR_W_BM_701) : 보유도서의 책 갯수와 일치하도록 업데이트
	@Override
	public int updateBookCount(String isbnVo) {
		return sqlSession.update(NS + "updateBookCount", isbnVo);
	}

	// 신규도서조회(BR_W_BM_702) : (관리자) 등록된 도서 중 현재월로부터 등록일이 한 달 이내인 도서를 조회
	@Override
	public List<Possessing_BookVo> selectNewBookAdmin() {
		return sqlSession.selectList(NS + "selectNewBookAdmin");
	}

	// 신규도서조회(BR_W_BM_702) : (사용자) 선택된 ISBN으로 등록일이 한 달 이내인 도서정보를 조회
	@Override
	public List<BookInfoVo> selectNewBookUser() {
		return sqlSession.selectList(NS + "selectNewBookUser");
	}

	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(신청구매)
	@Override
	public List<RequestPurchaseVo> selectReqHistory(String purchCodeVo) {
		return sqlSession.selectList(NS + "selectReqHistory", purchCodeVo);
	}

	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(신청구매)
	@Override
	public int updateReqHistory(Map<String, Object> reqMap) {
		return sqlSession.update(NS + "updateReqHistory", reqMap);
	}

	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(정기구매)
	@Override
	public List<RegularPurchaseVo> selectRegulHistory(String purchCodeVo) {
		return sqlSession.selectList(NS + "selectRegulHistory", purchCodeVo);
	}

	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(정기구매)
	@Override
	public int updateRegulHistory(Map<String, Object> regulMap) {
		return sqlSession.update(NS + "updateRegulHistory", regulMap);
	}


	

	
	
	
}
