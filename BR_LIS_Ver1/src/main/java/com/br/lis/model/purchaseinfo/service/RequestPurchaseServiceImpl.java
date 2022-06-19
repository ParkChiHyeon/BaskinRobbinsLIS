package com.br.lis.model.purchaseinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lis.model.purchaseinfo.mapper.IRequestPurchaseDao;
import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.RequestPurchaseVo;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class RequestPurchaseServiceImpl implements IRequestPurchaseService {

	
	@Autowired
	private IRequestPurchaseDao reqDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	// 신청도서 조회(BR_W_BM_501) : 제목으로 조회
	@Override
	public List<BookInfoVo> purchSelectByTitle(String titleVo) {
		logger.info("RequestPurchaseServiceImpl purchSelectByTitle" );
		return reqDao.purchSelectByTitle(titleVo);
	}

	// 신청도서 조회(BR_W_BM_501) : 출판사로 조회
	@Override
	public List<BookInfoVo> purchSelectByPublisher(String publisherVo) {
		logger.info("RequestPurchaseServiceImpl purchSelectByPublisher" );
		return reqDao.purchSelectByPublisher(publisherVo);
	}

	// 신청도서 신청(BR_W_BM_502) : 신청도서정보 삽입
	@Override
	public int purchRequestInsert(Map<String, Object> reqMap) {
		logger.info("RequestPurchaseServiceImpl purchRequestInsert" );
		return reqDao.purchRequestInsert(reqMap);
	}

	// 신청도서 신청(BR_W_BM_502) : 신청도서 월 1회만 신청하기 위한 쿼리(해당 구매 월에 MEMBER_ID가 있다면 신청하지 못함)
	@Override
	public List<RequestPurchaseVo> purchReqOnceAMonth() {
		logger.info("RequestPurchaseServiceImpl purchReqOnceAMonth" );
		return reqDao.purchReqOnceAMonth();
	}

	// 신청도서 신청(BR_W_BM_502) : 한 권의 책을 중복 신청하는 것을 방지하기 위한 쿼리 (NULL이 아닐 경우 신청 불가)
	@Override
	public List<RequestPurchaseVo> purchReqOncePerBook(String isbn) {
		logger.info("RequestPurchaseServiceImpl purchReqOncePerBook" );
		return reqDao.purchReqOncePerBook(isbn);
	}

	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 구매코드로 조회
	@Override
	public List<RequestPurchaseVo> purchReqListSelectByCode(String searchKey, String searchValue) {
		logger.info("RequestPurchaseServiceImpl purchReqListSelectByCode" );
		return reqDao.purchReqListSelectByCode(searchKey, searchValue);
	}

	// 신청도서 신청목록 조회(BR_W_BM_503) : 사용자가 신청한 도서의 목록을 조회할 수 있다. 사용자 ID로 조회
//	@Override
//	public List<RequestPurchaseVo> purchReqListSelectById(String memIdVo) {
//		logger.info("RequestPurchaseServiceImpl purchReqListSelectById" );
//		return reqDao.purchReqListSelectById(memIdVo);
//	}

	// 신청도서 상태조회(BR_W_BM_504) : 사용자는 자신이 신청한 도서의 구매여부(승인이 됐는지)를 조회할 수 있다.(로그인 된 사용자의 것만 조회)
	@Override
	public List<RequestPurchaseVo> purchReqConfirmSelect(String memIdVo) {
		logger.info("RequestPurchaseServiceImpl purchReqConfirmSelect" );
		return reqDao.purchReqConfirmSelect(memIdVo);
	}

	// 신청도서 알림관리(BR_W_BM_505) : 신청도서 중 구매승인이 된 도서에 한하여 신청한 사용자에게 알린다.
	@Override
	public LibMemberVo purchReqPhoneSelect(String memIdvo) {
		logger.info("RequestPurchaseServiceImpl purchReqPhoneSelect" );
		return reqDao.purchReqPhoneSelect(memIdvo);
	}

	// 신청도서 전체목록 조회(BR_W_BM_506) : 신청도서 중 구매 승인이 된 도서의 목록을 조회한다(리스트 전체조회)
	@Override
	public List<RequestPurchaseVo> purchReqConfirmYList(String purchCodeVo) {
		logger.info("RequestPurchaseServiceImpl purchReqConfirmYList" );
		return reqDao.purchReqConfirmYList(purchCodeVo);
	}		

}
