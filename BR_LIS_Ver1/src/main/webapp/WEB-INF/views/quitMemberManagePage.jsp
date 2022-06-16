<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/noticeboard.js"></script>
<link	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"	rel="stylesheet"	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"	crossorigin="anonymous">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
<script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
</head>

<body>
<script type="text/javascript">
</script>
<!-- https://datatables.net/examples/styling/ 
해당 페이지에 들어가면 부트스트랩처럼 class로 테이블 디자인 설정 가능 -->
  <div class="container">
  	
<!-- <form action="#" method="post" id="tt_table"> -->
<form action="#" method="post" id="tt_table">
<!-- <form action="./quitAcceptMember.do" method="post"> -->
	<table id="dataTable" class="cell-border"> <!-- https://datatables.net/examples/styling/ -->
		<thead>
			<tr>
				<td>번호</td>
				<td>MEMBER_ID</td>
				<td>NAME</td>
				<td>EMAIL</td>
				<td>PHONE</td>
				<td>RENTAL_COUNT</td>
				<td>PENALTY</td>
				<td>승인</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${lists}"  varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td id="member_id ${vs.index}"><input type="hidden" name="member_id" value="${vo.member_id}">${vo.member_id}</td>
				<td>${vo.name}</td>
				<td>${vo.email}</td>
				<td>${vo.phone}</td>
				<td id="rental_count${vs.index}"><input type="hidden" name="rental_count" value="${vo.rental_count}">${vo.rental_count}</td>
				<td id="penalty${vs.index}"><input type="hidden" name="penalty" value="${vo.penalty}">${vo.penalty}</td>
				<td><input type="submit" class="btn btn-outline-danger" value="탈퇴" onclick="btnQuit(${vs.index})"></td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
		</form>
	<button class="btn btn-info" onclick="javascript:location.href='./editor.do'">돌아가기</button>
</div>
  
     
</body>

<script type="text/javascript">
$(document).ready( function () {
    $('#dataTable').DataTable({
		//https://datatables.net/reference/option/language
		// DataTable은 기본적으로 영어로 표시되기 때문에 별도로 language를 통해서 변경해줘야 함
    	"language": { 
            "emptyTable": "데이터가 없어요.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
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
        ordering: true, // 정렬 기능 숨기기
        info: true, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
//         order: [ [ 3, "asc" ], [ 1, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
        order: [ [ 0, "asc" ] ],
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );

		function btnQuit(index) {
			console.log("성공")
			
			var id = $("input[name=member_id]").eq(index).val();
			var rc = $("input[name=rental_count]").eq(index).val();
			var pe = $("input[name=penalty]").eq(index).val();
// 			var rc = $("input[name=rental_count]" + index).val();
// 			var pe = $("input[name=penalty]" + index).val();
// 			var rc = $("rental_count" + index).text();
// 			var pe = $("penalty" + index).text();
			
			
			
			var frm = document.getElementById("tt_table");
			
			frm.action = "./quitAcceptMember.do"


			console.log(id);
			console.log(rc);
			console.log(pe);
		


			$.ajax({
				type : "post",
				url : "./quitRequirementChk.do",
				data : {"member_id" : id, "rental_count": rc, "penalty": pe},
				async : false,
				success : function(msg) {
					console.log(msg);
					if(msg.isc =="성공"){
						console.log("아작스 성공")
						swal({
	    					title : "승인\n회원 탈퇴를 승인하였습니다",					
	        				icon  : "success",
	        				closeOnClickOutside : false
	    					}, function(){
	    						console.log(msg.id)
	    						frm.submit();
	    						console.log(msg.isc);
	    					});
					}else{
						swal("에러");
					}
				}
			})
			
		}



</script>


<%@include file="./footer.jsp" %>
</html>