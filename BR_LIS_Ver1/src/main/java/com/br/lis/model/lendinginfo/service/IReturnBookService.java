package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendBookBean;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.ReservationVo;

public interface IReturnBookService {
	
	public LendingVo lendingDetailForReturnBook(String book_serial);
	public LibMemberVo lendingDetailForReturnUser(String member_code);
	public ReservationVo returnBookReserveCheck(Map<String, Object> map);
	public int normalReturnBook(String lending_seq, String member_code);
	public int existReserveReturnBook(String lending_seq, String member_code);
	public int damegeReturnBook(String lending_seq, String member_code);
	public int lossReturnBook(String lending_seq, String member_code);
	public int overdueLendingBook();
	public void overdueThreeDayLendingBook();
	public int delayLendingBook(String lending_seq);
	public List<BookInfoVo> warehouseList();
	public List<BookInfoVo> supplementList();
	public void certifiedPhoneNumber(String phone, String name);
	public int reserveSelfDel(String lending_seq);
	public List<LendBookBean> returnBookList();
	public List<LendBookBean> returnBookListBanNap();
	public List<LendBookBean> yetReturnBookList();
	public List<BookInfoVo> possessingBookList();
	public List<LendBookBean> lendingListUser(String member_id);
}
