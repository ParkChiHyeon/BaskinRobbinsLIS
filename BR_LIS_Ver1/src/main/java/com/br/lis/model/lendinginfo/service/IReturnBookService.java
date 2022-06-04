package com.br.lis.model.lendinginfo.service;

import java.util.List;
import java.util.Map;

import com.br.lis.vo.BookInfoVo;
import com.br.lis.vo.LendingVo;
import com.br.lis.vo.ReservationVo;

public interface IReturnBookService {
	
	public ReservationVo returnBookReserveCheck(Map<String, Object> map);
	public int normalReturnBook(String lending_seq, String member_code);
	public int existReserveReturnBook(String lending_seq, String member_code);
	public int damegeReturnBook(String lending_seq, String member_code);
	public int lossReturnBook(String lending_seq, String member_code);
	public int overdueLendingBook();
	public List<LendingVo> overdueThreeDayLendingBook();
	public int delayLendingBook(String lending_seq);
	public List<BookInfoVo> warehouseList();
	public List<BookInfoVo> supplementList();
}