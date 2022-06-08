<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%-- <%@ include file="/WEB-INF/views/header.jsp" %> --%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="./BR_js/requestBookSearch.js"></script>
<body>

<h3>도서신청 관리</h3>

<!-- <input id="reqbookSerch" name="reqbookSerch" type="text" value="검색어를 입력해주세요" ><input type="button" value="조회"> -->

	<div class="searchBox">
		<form name="reqSearchForm" id="reqSearchForm" method="post" action="/jungang/10010/booksearch/searchSimple.do">
			<input type="hidden" name="searchLibraryArr" value="MA">
			<div class="bgWhite">
				<select id="searchKey" name="searchKey" title="검색방식선택">
					<option id="SELECT" value="ALL" selected="selected">선택</option>
					<option id="purchase_code" value="PURCHASE_CODE">구매코드</option>
					<option id="member_id" value="MEMBER_ID">아이디</option>
				</select>
				<input type="text" name="searchValue" id="searchValue" class="schInput1" title="검색어 입력">
			</div>
			<div class="btnRight"> <span class="themeBtn"><a href="javascript:reqBookSearchKeyWord();">도서검색</a></span> </div>
		</form>
	</div>
<div>
	<form action="./requestBookListId.do" method="post" >
		<table id="reqBookList">
			<thead>
				<tr>
					<th>신청구매코드</th>
					<th>id</th>
					<th>구매코드</th>
					<th>ISBN</th>
					<th>제목</th>
					<th>출판사</th>
					<th>지은이</th>
					<th>번역</th>
					<th>가격</th>
					<th>신청수량</th>
					<th>확정여부</th>
					<th>비고</th>
					<th>입고일</th>
				</tr>
				<c:forEach var="reqList" items="${reqList}" varStatus="vs">
					<tr>
						<td>${reqList.wish_serial}</td>
						<td>${reqList.member_id}</td>
						<td>${reqList.purchase_code}</td>
						<td>${reqList.isbn}</td>
						<td>${reqList.title}</td>
						<td>${reqList.publisher}</td>
						<td>${reqList.author}</td>
						<td>${reqList.translator}</td>
						<td>${reqList.price}</td>
						<td>${reqList.ea}</td>
						<td>${reqList.confirm}</td>
						<td>${reqList.history}</td>
						<td>${reqList.receive_date}</td>
					</tr>
				</c:forEach>
			</thead>
		</table>
	</form>
</div>


</body>
</html>