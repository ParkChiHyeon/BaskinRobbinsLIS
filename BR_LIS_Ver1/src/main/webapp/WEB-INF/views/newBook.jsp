<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> -->
<!-- <title>DataTables Ajax</title> -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>DataTables</title>
<%@ include file="/WEB-INF/views/header.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/regularPurchaseDataTable.js"></script>


</head>
<body>
<div class="container">
	<table id="dataTable" class="cell-border"> <!-- https://datatables.net/examples/styling/ -->
		<thead>
				<tr>
					<th>체크<input type="checkbox" id="checkAll"> </th>
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
					<th>수정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${purchRegulList}" var="purchRegulList" varStatus="vs">
					<tr>
						<td>
							<c:if test="${purchRegulList.receive_date == null && purchRegulList.confirm == 'Y'}">
								<input type="checkbox" id="regularPurchaseCheck${vs.index}" name="regularPurchaseCheck" class="checkDel" value="${purchRegulList.regular_serial}">
							</c:if>
						</td>
						<td id="regularSerial_Index${vs.index}">${purchRegulList.regular_serial}</td>
						<td>${purchRegulList.purchase_code}</td>
						<td>${purchRegulList.isbn}</td>
						<td>${purchRegulList.title}</td>
						<td>${purchRegulList.publisher}</td>
						<td>${purchRegulList.author}</td>
						<td>${purchRegulList.translator}</td>
						<td>${purchRegulList.price}</td>
						<td id="ea_Index${vs.index}">${purchRegulList.ea}</td>
						<c:choose>
							<c:when test="${purchRegulList.confirm == 'Y'}">
								<td>승인</td>
							</c:when>
							<c:when test="${purchRegulList.confirm == 'N'}">
								<td>반려</td>
							</c:when>
							<c:otherwise>
								<td>반려</td>
							</c:otherwise>
						</c:choose>
<%-- 						<td>${purchRegulList.confirm}</td> --%>
						<td id="regularHistory_Index${vs.index}">${purchRegulList.history}</td>
						<td>${purchRegulList.receive_date}</td>
						<td>
							<c:if test="${purchRegulList.receive_date == null}">
								<div class="container">
								  <!-- Trigger the modal with a button -->
								  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="regularPurchaseInfoChange(${vs.index})">수정</button>
								
								  <!-- Modal -->
								  <div class="modal fade" id="myModal" role="dialog">
								    <div class="modal-dialog">
								    
								      <!-- Modal content-->
								      <div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">구매정보수정</h4>
								        </div>
								        <div class="modal-body" id="modalReset">
								          <p>구매수량 변경 : 
								          	<input type="text" id="changePurchaseCount" name="changePurchaseCount" value="">
								          	<button onclick="javascript:changePurchaseCount()">변경</button>
								          </p>
								          <p>
								          	구매불가 사유 입력<br>
								          	<textarea id="notPurchaseReason" name="notPurchaseReason" style="resize: none; width: 300px;"></textarea>
								          	<button onclick="javascript:notPurchaseReason()">변경</button>
								          </p>
								        </div>
								        <div class="modal-footer">
								          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="modalClose()">닫기</button>
								        </div>
								      </div>
								      
								    </div>
								  </div>
								</div>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<button class="btn btn-primary" onclick="recieveBook()">입고</button>	
	<button class="btn btn-danger" onclick="changeConfirmN()">반려</button>	
<!-- 	<button class="btn btn-primary" onclick="createAjax()">AJAX</button>	 -->
<!-- 	<button class="btn btn-info" onclick="javascript:location.href='./editor.do'">BACK</button> -->
</div>

<script type="text/javascript">
$(document).ready( function () {
    $('#dataTable').DataTable({
		//https://datatables.net/reference/option/language
		// DataTable은 기본적으로 영어로 표시되기 때문에 별도로 language를 통해서 변경해줘야 함
    	"language": { 
            "emptyTable": "데이터가 없어요.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
//             "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "데이터 없음",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "검색: ",
            "zeroRecords": "일치하는 데이터가 없어요.",
            "loadingRecords": "로딩중...",
            "processing":     "잠시만 기다려 주세요...",
            "paginate": {
                "next": "다음",
                "previous": "이전"
            }
        },
        
        lengthChange: true, // 표시 건수기능 숨기기
        searching: true, // 검색 기능 숨기기
        ordering: false, // 정렬 기능 숨기기
        info: false, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
//         order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
        order: [ [ 1, "desc" ] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
		lengthMenu: [ 5, 15, 30 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );
</script>



</body>
<%@ include file="./footer.jsp"%>
</html>