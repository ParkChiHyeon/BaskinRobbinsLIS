<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="./BR_js/purchase.js"></script>
<title>정기구매</title>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<button id="createPurchaseCode" name="createPurchaseCode" onclick="createPurchaseCode();">구매코드 생성</button>
<%-- <input type="button" value="${creatPurchaseCodeSuccess}" onclick="createPurchaseCode()"> --%>
<!-- <a id="creatPurchaseCodeSuccess" onclick="createPurchaseCode()">구매코드 생성</a> -->

 <div>
	<table>
		<thead>
			<tr>
				<th>구매코드</th>
				<th>구매금액</th>
				<th>구매수량</th>
				<th>주문일자</th>
				<th>이메일</th>
				<th>확정여부</th>
				<th>확정일자</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="purchaseList" items="${purchaseList}" varStatus="vs">
				<tr>
					<td>${purchaseList.purchase_code}</td>
					<td>${purchaseList.total_price}</td>
					<td>${purchaseList.total_ea}</td>
					<td>${purchaseList.order_date}</td>
					<td>${purchaseList.distributor_email}</td>
					<c:choose>
						<c:when test="${purchaseList.confirm == 'C'}">
							<td>반입확인</td>
						</c:when>
						<c:when test="${purchaseList.confirm == 'Q'}">
							<td>발주대기</td>
						</c:when>
						<c:when test="${purchaseList.confirm == 'O'}">
							<td>발주</td>
						</c:when>
					</c:choose>
					<td>${purchaseList.confirm_date}</td>
					<td>
						<div class="container mt-3">
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>
						</div>
						<!-- The Modal -->
						<div class="modal" id="myModal">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						
						      <!-- Modal Header -->
						      <div class="modal-header">
						        <h4 class="modal-title">구매정보 수정</h4>
						        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						      </div>
						
						      <!-- Modal body -->
						      <div class="modal-body" id="modalClose">
						      <form>
						        구매코드 : <input type="text" id="purchase_code" value="${purchaseList.purchase_code}" disabled><br>
						        구매금액 : <input type="text" id="total_price" value="${purchaseList.total_price}"><br>
						        구매수량 : <input type="text" id="total_ea" value="${purchaseList.total_ea}"><br>
						        구매처 Email : <input type="text" id="distributor_email" ${purchaseList.distributor_email}><br>
						        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="purchaseInfoUpdateFirst();">수정</button><br>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${purchaseList.order_date == '' && purchaseList.confirm_date == ''}"> --%>
<!-- 							        	<button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="purchaseInfoUpdateOrderChk();">발주확인</button> -->
<%-- 									</c:when> --%>
<%-- 									<c:when test="${purchaseList.order_date != '' && purchaseList.confirm_date == ''}"> --%>
<!-- 							        	<button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="purchaseInfoUpdateReceiveChk();">반입확인</button><br> -->
<%-- 									</c:when> --%>
<%-- 								</c:choose> --%>
							        	<button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="purchaseInfoUpdateOrderChk();">발주확인</button>
							        	<button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="purchaseInfoUpdateReceiveChk();">반입확인</button><br>
						        </form>
						      </div>
						
						      <!-- Modal footer -->
						      <div class="modal-footer">
						        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="javascript:modalClose()">닫기</button>
						      </div>
						
						    </div>
						  </div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table> 
 </div>

</body>
</html>