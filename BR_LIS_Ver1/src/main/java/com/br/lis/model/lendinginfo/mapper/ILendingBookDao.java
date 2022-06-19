package com.br.lis.model.lendinginfo.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

public interface ILendingBookDao {
	
	//BR_W_BM_201 즉시대출도서
	public List<LendBookBean> nowLendingBook(String book_serial);
	
	//BR_W_BM_202 예약목록전체조회(관리자)
	public List<LendBookBean> allReserveLending(Map<String, Object>map);
	
	//BR_W_BM_203 회원예약목록조회
	public List<Map<String, Object>> reserveLendingBook(String member_code);
	
	//BR_W_BM_204 대출 신청
	public int insertLendingBook(LendingVo vo);
	
	//BR_W_BM_205 대출 신청 상태변경
	public int insertLendingBookUpdate(String book_serial);
	
	//BR_W_BM_206 도서연체패널티대상 조회 -대출시 필요
	public List<LibMemberVo> deleyPenalty();
	
	//BR_W_BM_208 대출 내역조회
	public List<LendBookBean> lendingList(String member_code);
	
//	public LendBookBean lendingList(List<Map<String, String>> map);
	
	
	//BR_W_BM_209 반납일이 빠른 예약가능 도서
	public List<LendBookBean> possibleReserve(String isbn);
	
	//BR_W_BM_210  예약 가능한 도서 목록
	public List<LendBookBean> selectPossibleReserve();
	
	//BR_W_BM_211   대출예약 신청
	public int reservationBook(LendingVo vo);
	
	//BR_W_BM_212 대출 예약 신청 후 상태변경
	public int reservationBookUpdate(String book_serial);
	
	//BR_W_BM_213 예약 후 대출 확정
	public int realReserBook(String lending_seq);
	
	//BR_W_BM_214 예약 후 대출 확정 상태변겨
	public int realReserBookUpdate(String book_serial);
	
	//BR_W_BM_215 대출 예약 자동취소
	public int reserveAutoDel(String lending_seq);
	
	//BR_W_BM_216 대출 예약 취소
	public int reserveSelfDel(String lending_seq);
	
	//BR_W_BM_217 대출 예약 취소후 상태변경
	public int reserveDelUpdate(String book_serial);
	
	//BR_W_BM_218 대여가능 권수
	public int lendingCount(String member_code);
	
	//BR_W_BM_219 예약권수 제한
	public List<LendingVo> limitBookCount(String member_id);
	
	//해당 멤버코드로 대여가능 권수 조회
	public LibMemberVo rentalBookCount(String member_code);
	

}
