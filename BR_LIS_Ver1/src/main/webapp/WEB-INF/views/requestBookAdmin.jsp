
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
<script type="text/javascript" src="./BR_js/requestBookSearch.js"></script>
<script type="text/javascript" src="./BR_js/phoneNumberSearch.js"></script>
<body>



<div class="container mt-3">
	<h3>도서신청 관리</h3> 
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" data-backdrop="static" data-keyboard="false">
    신청도서 문자알림
	</button>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">신청도서 문자알림</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <h3>메시지 보내기</h3>
		<div>
			<div>회원 아이디</div><input type="text" id="searchPhoneNumber" name="searchPhoneNumber" title="회원아이디 입력">
			<div class="btnRight">
				<span class="themeBtn"><a href="javascript:requestMessage();">조회</a></span>
			</div>
			
			<!-- 조회된 결과를 div태그 안에 넣어줌 -->
			<div id="phoneNumResult"></div>
			
			<form action="./sendMessage.do" method="post">
				<div>수신번호</div>
				<input type="text" id="phoneNumber" name="phoneNumber" value="">
				<div>메시지</div>
				<textarea id="requestBookMessage" name="requestBookMessage" rows="10" cols="50"></textarea>
			</form>
		</div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="javascript:sendMessage()"  data-bs-dismiss="modal">전송</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="javascript:modalClose()">취소</button>
      </div>

    </div>
  </div>
</div>

<!-- 검색옵션선택 후 검색 -->
	<div class="searchBox">
		<form name="reqSearchForm" id="reqSearchForm" method="post" action="javascript:reqBookSearchKeyWord();">
			<input type="hidden" name="searchLibraryArr" value="MA">
			<div class="bgWhite">
				<select id="searchKey" name="searchKey" title="검색방식선택">
					<option id="SELECT" value="ALL" selected="selected">선택</option>
					<option id="purchase_code" value="PURCHASE_CODE">구매코드</option>
					<option id="member_id" value="MEMBER_ID">아이디</option>
				</select>
				<input type="text" name="searchValue" id="searchValue" class="schInput1" title="검색어 입력">
			</div>
			<div class="btnRight"> <span class="themeBtn"><a href="javascript:reqBookSearchKeyWord();">조회</a></span> </div>
		</form>
	</div>
	
<!-- 도서신청목록 -->	
<div>
	<table id="reqBookList">
		<thead>
			<tr>
				<th>체크<br>
					<input type="checkbox" id="checkAll">
				</th>
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
				<th>수정</th>
			</tr>
		</thead>
		<tbody id="requestList">
			<c:forEach var="reqList" items="${reqList}" varStatus="vs" >
				<tr>
					<td>
						<c:if test="${reqList.receive_date == null && reqList.confirm == 'Y'}">
							<input type="checkbox" id="regularPurchaseCheck${vs.index}" name="regularPurchaseCheck" class="checkDel" value="${reqList.wish_serial}">
						</c:if>
					</td>
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
					<td>
					<div class="container mt-3">
						<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#myModal2">수정</button>
						<!-- The Modal -->
							<div class="modal" id="myModal2">
							  <div class="modal-dialog modal-sm">
							    <div class="modal-content">
							
							      <!-- Modal Header -->
							      <div class="modal-header">
							        <h4 class="modal-title">구매정보 수정</h4>
							        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							      </div>
							
							      <!-- Modal body -->
							      <div class="modal-body">
							        <div>
								        구매수량 변경 : <input type="text" id="changePurchaseCount" value="">
								        <button onclick="javascript:changePurchaseCount()">변경</button>
							        </div>
							        <div>
								        구매불가 사유 입력<br>
							          	<textarea id="notPurchaseReason" name="notPurchaseReason" style="resize: none; width: 200px;"></textarea>
							          	<button onclick="javascript:notPurchaseReason()">변경</button>
							        </div>
							      </div>
							
							      <!-- Modal footer -->
							      <div class="modal-footer">
							        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="modalClose()">닫기</button>
							      </div>
							    </div>
							  </div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 입고버튼, 반려버튼 -->
	<button class="btn btn-primary" onclick="recieveBook()">입고</button>	
	<button class="btn btn-danger" onclick="changeConfirmN()">반려</button>	
</div>

</body>
<%-- <%@ include file="./footer.jsp"%> --%>
</html>