package com.br.lis.model.purchaseinfo.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.Possessing_BookVo;
import com.br.lis.vo.RegularPurchaseVo;
import com.br.lis.vo.RequestPurchaseVo;

public interface IPurchaseRegistrationService {

	// 구매도서등록(BR_W_BM_701) : 보유도서에 삽입
	public int insertPurchaseBook(String isbnVo);
	
	// 구매도서등록(BR_W_BM_701) : 보유도서의 책 갯수와 일치하도록 업데이트
	public int updateBookCount(String isbnVo);
	
	// 신규도서조회(BR_W_BM_702) : (관리자) 등록된 도서 중 현재월로부터 등록일이 한 달 이내인 도서를 조회
	public List<Possessing_BookVo> selectNewBookAdmin();
	
	// 신규도서조회(BR_W_BM_702) : (사용자) 선택된 ISBN으로 등록일이 한 달 이내인 도서정보를 조회
	public List<BookInfoVo> selectNewBookUser();
	
	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(신청구매)
	public List<RequestPurchaseVo> selectReqHistory(String purchCodeVo);
	
	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(신청구매)
	public int updateReqHistory(Map<String, Object> reqMap);
	
	// 등록취소(BR_W_BM_707) :  입고일이 없는 것 조회(정기구매)
	public List<RegularPurchaseVo> selectRegulHistory(String purchCodeVo);
	
	// 등록취소(BR_W_BM_707) :  도서 목록에서 history 업데이트(정기구매)
	public int updateRegulHistory(Map<String, Object> regulMap);
	
	
	
}
