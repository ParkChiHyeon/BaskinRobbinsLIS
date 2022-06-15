<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>도서 예약하기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js" defer></script>
</head>
<%@ include file="./header.jsp"%>
<body>
<h1>예약선택한 도서</h1>
${countBook}
<table class="table table-bordered">
			<thead>
				<tr>
					<th>ISBN</th>
					<th>도서코드</th>
					<th>도서제목</th>
					<th>저자</th>
					<th>예약</th>
				</tr>
			</thead>
			<c:if test="${countBook eq ' '}">
			<tbody>
				<tr id="chkBook">
<%-- 					<td><input type="hidden" id="isbn"  value=" ${po.isbn}">${po.isbn}</td> --%>
<%-- 					<td><input type="hidden" id="book_serial" value=" ${po.book_serial}">${po.book_serial}</td> --%>
<%-- 					<td>${po.title}</td> --%>
<%-- 					<td>${po.author}</td> --%>
<!-- 					<td><input class="checkReserve"type="button" value="예약하기"></td> -->
				
				
				</tr>
			</tbody>
			</c:if>
		</table>


<h1>예약가능도서 조회</h1>
<div class="container">
		<form action="./checkReserve.do" id="oneBook" method="post">
			<table  id="dataTable" class="cell-border">
				<thead>
					<tr>
						<th>ISBN</th>
						<th>도서코드</th>
						<th>도서제목</th>
						<th>저자</th>
						<th>반납예정일</th>
						<th>예약</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="po" items="${poBook}" varStatus="vs">
					<tr>
						<td><input type="hidden" id="isbn" name="isbn" value=" ${po.isbn}">${po.isbn}</td>
						<td><input type="hidden" id="book_serial" name="book_serial" value=" ${po.book_serial}">${po.book_serial}</td>
						<td>${po.title}</td>
						<td>${po.author}</td>
						<td>${po.end_date}</td>
<!-- 						<td><button class="btn btn-info"type="button"  onclick="checkReserve()">예약하기</button> </td> -->
						<td><input class="checkReserve"type="button" value="선택"></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>	
</div>	

<script type="text/javascript">
$(document).ready(function () {
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
        order: [ [ 4, "desc"] ], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} );



$(".checkReserve").click(function(){
	var str =""
	var tdArr =  new Array();
	var checkReserve =$(this);
	
// 	var frm = document.getElementById("s")
	
// 	 frm.action = "./checkReserve.do"
	var tr = checkReserve.parent().parent();
	var td = tr.children();
	
	console.log("클릭한 버튼의 값 : " +tr.text());
	
	var isbn =td.eq(0).text();
	var book=td.eq(1).text();
	
	
	console.log(isbn,book);
	
	td.each(function(i){
		tdArr.push(td.eq(i).text());
	});
	console.log(tdArr);
	
	var chkBook;
	
// 	$("#chkBook1").empty();
// 	$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).empty();
	
	$.ajax({
		url:"./checkReserve.do",
		data:{"isbn":isbn,"book_serial":book},
		dataType:"json",
		type:"post",
		async : true,
		success : function(data){
			console.log("성공");
			var chkBook;
			
			$("#chkBook").empty();
// 			$("#chkBook").children().empty();
			
			console.log(data);
			console.log(chkBook);
			console.log(data.chkBook[0].isbn);
			console.log(data.chkBook[0].book_serial);
			console.log(data.chkBook[0].title);
			console.log(data.chkBook[0].author);
			chkBook="";
			chkBook+="<td><input type='hidden' id='isbn' name='isbn' value='"+data.chkBook[0].isbn+"'>"+data.chkBook[0].isbn+"</td>";
			chkBook+='<td><input type="hidden" id="book_serial" value='+data.chkBook[0].book_serial+'>'+data.chkBook[0].book_serial+'</td> ';
			chkBook+='<td>'+data.chkBook[0].title+'</td>  ';
			chkBook+='<td>'+data.chkBook[0].author+'</td>  ';
// 			chkBook+='<td>'+data.chkBook[0].author+'</td>  ';
			chkBook+='<td><input class="checkReserve"type="button"  onclick="checkReserve()" value="예약하기"></td>  ';
		
			var check = $("#chkBook").append(chkBook);
			console.log("================"+check);
	//		$("#chkBook").children().append(chkBook);
	
				
// 			$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).append(chkBook);
		
		}
	});
	
})

function checkReserve(){
	var isbn =document.getElementById("isbn").value;
	var book_serial =document.getElementById("book_serial").value;
	console.log(isbn, book_serial,"예약하러가기");
	$.ajax({
		url:"./realReserve.do",
		data:{"isbn":isbn,"book_serial":book_serial},
		type:"post",
		async : true,
		success : function(data){
			console.log("성공")
			alert('도서 예약이 되었습니다');
			location.href="./reserveBookList.do";
		},
		error:function(){
			alert('ERROR\n다시 시도 하십시오');
// 			location.href="./reserveBook.do";
		}
	});
}	


</script>
</body>
<%@include file="./footer.jsp"%>
</html>