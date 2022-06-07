package com.br.lis.model.lendinginfo.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

public interface ILendingBookDao {
	
	//즉시대출도서
	public List<LendingVo> nowLendingBook();
	
	//예약목록전체조회
	public List<BookInfoVo> allReserveLending();
	
	//회원예약목록조회
	public List<Map<String, Object>> reserveLendingBook(String member_code);
//	public LendBookBean reserveLendingBook(Map<String, String> map);
	

	
	
	//대출 신청
	public int insertLendingBook(LendingVo vo);
	
	//대출 신청 상태변경
	public int insertLendingBookUpdate(LendingVo vo);
	
	//도서연체패널티대상 조회 -대출시 필요
	public List<LibMemberVo> deleyPenalty();
	
	
	//	BR_W_BM_207 
	//대출 내역조회
	public List<LendBookBean> lendingList(Map<String, Object>map);
	
//	public LendBookBean lendingList(List<Map<String, String>> map);
	
	//대출 예약 신청
	public int reservationBook(LendingVo vo);
	
	//대출 예약 신청 후 상태변경
	public int reservationBookUpdate(BookInfoVo vo);
	
	//예약 후 대출 확정
	public int realReserBook(String lending_seq);
	
	//예약 후 대출 확정 상태변겨
	public int realReserBookUpdate(LendingVo vo);
	
	//반납일이 빠른도서조회
	public List<LendingVo> fastReturnDayBook(LendingVo vo);
	
	//대출 예약 자동취소
	public int reserveAutoDel(String lending_seq);
	
	//대출 예약 취소
	public int reserveSelfDel(String lending_seq);
	
	//대출 예약 취소후 상태변경
	public int reserveDelUpdate(BookInfoVo vo);
	
	//대여가능 권수
	public int lendingCount(String member_code);
}
