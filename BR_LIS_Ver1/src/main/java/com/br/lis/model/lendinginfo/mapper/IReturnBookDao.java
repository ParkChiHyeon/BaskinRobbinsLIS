package com.br.lis.model.lendinginfo.mapper;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

public interface IReturnBookDao {
	
	public LendingVo lendingDetailForReturnBook(String book_serial);
	public LibMemberVo lendingDetailForReturnUser(String member_code);
	public int allReturnBook(String lending_seq);
	public int returnBookDate(String lending_seq);
	public int damegeReturnBook(String lending_seq);
	public int lossReturnBook(String lending_seq);
	public int dgAndLsReturnBookStatus(String lending_seq);
	public int existReserveReturnBookStatus(String lending_seq);
	public int returnLendingBookCount(String member_code);
	public int overdueLendingBook();
	public List<LibMemberVo> overdueThreeDayLendingBook();
	public int delayLendingBook(String lending_seq);
	public int delayPossessingBook(String lending_seq);
	public List<BookInfoVo> warehouseList();
	public List<BookInfoVo> supplementList();
	public ReservationVo returnBookReserveCheck(Map<String, Object> map);
	public int penaltyAuthModify();
	public List<LendBookBean> returnBookList();
	public List<LendBookBean> yetReturnBookList();
	public List<BookInfoVo> possessingBookList();
	public List<LendBookBean> lendingListUser(String member_id);
}
