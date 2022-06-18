package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;

public interface ILendingBookService {
	
		//즉시대출도서
	public List<LendBookBean> nowLendingBook(String book_serial);
		
		//예약목록전체조회
//		public List<BookInfoVo> allReserveLending();
		public List<LendBookBean> allReserveLending(Map<String, Object>map);
		
		//회원예약목록조회
		public List<Map<String, Object>> reserveLendingBook(String member_code);
		
		
		//BR_W_BM_204 대출 신청 트렌젝션 처리
		public int lendingBook(String book_serial,String member_code);
		
		//도서연체패널티대상 조회 -대출시 필요
		public List<LibMemberVo> deleyPenalty();
		
		//대출 내역조회
		public List<LendBookBean> lendingList(String member_code);
		
		//추가 : 대출중면서 예약이 안된책의 빠른대출일 조회
		public List<LendBookBean> possibleReserve(String isbn);
		//예약가능한 도서
		public List<LendBookBean> selectPossibleReserve();
		
		//BR_W_BM_208 대출 예약 신청 후 상태변경 _트렌젝션 처리
		public int bookReservation(LendingVo vo,String book_serial);
		
		
		//BR_W_BM_209 예약건 대출 확정 후 상태변경
		// ++ 반납일이 빠른도서로 예약
		public int confrimReserveBook(String lending_seq,String book_serial,String member_code);
		
		//반납일이 빠른도서조회
		public List<LendingVo> fastReturnDayBook(LendingVo vo);
		
		//BR_W_BM_211 대출예약 자동취소 후 상태변경
		public int autoDeleteResrve(String lending_seq,String book_serial);
		
		//BR_W_BM_212 대출예약 취소 후 상태변경
		public int selfDeleteResrve(String lending_seq,String book_serial);
		
		//대여가능 권수
		public int lendingCount(String member_code);
		
		//예약권수 제한
		public List<LendingVo> limitBookCount(String member_id);
		
		//해당 멤버코드로 대여가능 권수 조회
		public LibMemberVo rentalBookCount(String member_code);

}
