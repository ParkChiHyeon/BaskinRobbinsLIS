package com.br.lis.model.purchaseinfo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.RequestPurchaseVo;

@Repository
public class RequestPurchaseDaoImpl implements IRequestPurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String  NS = "com.br.lis.model.purchaseinfo.mapper.RequestPurchaseDaoImpl.";

	
	// 신청도서 조회(BR_W_BM_501) : 제목으로 조회
	@Override
	public List<BookInfoVo> purchSelectByTitle(String titleVo) {
		return sqlSession.selectList(NS + "purchSelectByTitle", titleVo);
	}

	// 신청도서 조회(BR_W_BM_501) : 출판사로 조회
	@Override
	public List<BookInfoVo> purchSelectByPublisher(String publisherVo) {
		return sqlSession.selectList(NS + "purchSelectByPublisher", publisherVo);
	}

	// 신청도서 신청(BR_W_BM_502) : 신청도서정보 삽입
	@Override
	public int purchRequestInsert(Map<String, Object> reqMap) {
		return sqlSession.insert(NS + "purchRequestInsert", reqMap);
	}

	// 신청도서 신청(BR_W_BM_502) : 신청도서 월 1회만 신청하기 위한 쿼리(해당 구매 월에 MEMBER_ID가 있다면 신청하지 못함)
	@Override
	public List<RequestPurchaseVo> purchReqOnceAMonth() {
		return sqlSession.selectList(NS + "purchReqOnceAMonth");
	}

	// 신청도서 신청(BR_W_BM_502) : 한 권의 책을 중복 신청하는 것을 방지하기 위한 쿼리 (NULL이 아닐 경우 신청 불가)
	@Override
	public List<RequestPurchaseVo> purchReqOncePerBook(String isbnVo) {
		return sqlSession.selectList(NS + "purchReqOncePerBook");
	}

	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 구매코드로 조회
	@Override
	public List<RequestPurchaseVo> purchReqListSelectByCode(String searchKey, String searchValue) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		return sqlSession.selectList(NS + "purchReqListSelectByCode", map);
	}

	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 사용자 ID로 조회
//	@Override
//	public List<RequestPurchaseVo> purchReqListSelectById(String memIdVo) {
//		return sqlSession.selectList(NS + "purchReqListSelectById", memIdVo);
//	}

	// 신청도서 상태조회(BR_W_BM_504) : 사용자는 자신이 신청한 도서의 구매여부(승인이 됐는지)를 조회할 수 있다.(로그인 된 사용자의 것만 조회)
	@Override
	public List<RequestPurchaseVo> purchReqConfirmSelect(String memIdVo) {
		return sqlSession.selectList(NS + "purchReqConfirmSelect", memIdVo);
	}

	// 신청도서 알림관리(BR_W_BM_505) : 신청도서 중 구매승인이 된 도서에 한하여 신청한 사용자에게 알린다.
	@Override
	public LibMemberVo purchReqPhoneSelect(String memIdvo) {
		return sqlSession.selectOne(NS + "purchReqPhoneSelect", memIdvo);
	}

	// 신청도서 전체목록 조회(BR_W_BM_506) : 신청도서 중 구매 승인이 된 도서의 목록을 조회한다(리스트 전체조회)
	@Override
	public List<RequestPurchaseVo> purchReqConfirmYList(String purchCodeVo) {
		return sqlSession.selectList(NS + "purchReqConfirmYList", purchCodeVo);
	}
	
	
	

}
