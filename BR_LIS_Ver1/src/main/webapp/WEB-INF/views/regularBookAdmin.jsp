<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="./BR_js/regularBook.js"></script>
<body>
	<h3>도서 정기구매</h3>
	도서구매정보삽입
	정기구매 리스트 조회,발주버튼 추가
	<div class="searchBox">
		<form name="reqSearchForm" id="reqSearchForm" method="post" action="javascript:reqBookSearchKeyWord();">
			<input type="hidden" name="searchLibraryArr" value="MA">
			<div class="bgWhite">
				<select id="searchKey" name="searchKey" title="검색방식선택">
					<option id="purchase_code" value="PURCHASE_CODE">구매코드</option>
				</select>
				<input type="text" name="searchValue" id="searchValue" class="schInput1" title="검색어 입력">
			</div>
			<div class="btnRight"> <span class="themeBtn"><a href="javascript:regulBookSearchKeyWord();">조회</a></span> </div>
		</form>
	</div>
	<div>
		<table id="regularPurchaseTable">
			<thead>
				<tr>
					<th>정기구매코드</th>
					<th>구매코드</th>
					<th>ISBN</th>
					<th>제목</th>
					<th>출판사</th>
					<th>지은이</th>
					<th>번역</th>
					<th>가격</th>
					<th>구매수량</th>
					<th>확정여부</th>
					<th>비고</th>
					<th>입고일</th>
				</tr>
			</thead>
			<tbody id="regularPurchaseList">
<%-- 				<c:choose > --%>
<%-- 					<c:when test="${regularBook.confirm == 'Y'}"> --%>
<!-- 						<td>승인</td> -->
<%-- 					</c:when> --%>
<%-- 					<c:when test="${regularBook.confirm == 'N'}"> --%>
<!-- 						<td>반려</td> -->
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
				<!-- 조회 결과가 나타날 영역 -->
				
			</tbody>
		</table>
	</div>

</body>
</html>