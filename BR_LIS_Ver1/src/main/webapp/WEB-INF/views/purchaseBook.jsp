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
	<table class="table" id="table_id">
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
					<td id="purchase_codeIndex${vs.index}">${purchaseList.purchase_code}</td>
					<td id="total_priceIndex${vs.index}">${purchaseList.total_price}</td>
					<td id="total_eaIndex${vs.index}">${purchaseList.total_ea}</td>
					<td id="order_dateIndex${vs.index}">${purchaseList.order_date}</td>
					<td id="distributor_emailIndex${vs.index}">${purchaseList.distributor_email}</td>
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
					<td id="confirm_dateIndex${vs.index}">${purchaseList.confirm_date}</td>
					<td>
						<div class="container mt-3">
						  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal" onclick="purchaseCodeIndex(${vs.index})" >수정</button>
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
						      <div class="modal-body" id="modalId">
						      	<form>
						        구매코드 : <input type="text" id="purchase_code" value="" disabled><br>
						        구매금액 : <input type="text" id="total_price" value=""><br>
						        구매수량 : <input type="text" id="total_ea" value=""><br>
						        구매처 Email : <input type="text" id="distributor_email" value = ""><br>
						        <button type="button" class="btn btn-primary btn-update-modal" data-bs-dismiss="modal" onclick="purchaseInfoUpdateFirst();">수정</button><br>
								<!-- 반입/발주 확인버튼 생성 공간 -->
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