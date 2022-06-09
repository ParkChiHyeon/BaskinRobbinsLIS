<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%@include file="./header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<link rel="stylesheet" href="./include/booksearch/css/booksearch.css">
<link rel="stylesheet" href="./include/css/button.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script type="text/javascript" src="./BR_js/booksearch.js"></script>

<script type="text/javascript">
window.onload=function(){
	var kind = '<c:out value="${kind}"/>'
		console.log(kind)
	if(kind=="total"){
		$("#lnb1_1_a").css("color","#E2427F");
		$("#lnb1_1_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}else if(kind=="detail"){
		$("#lnb1_2_a").css("color","#E2427F");
		$("#lnb1_2_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}else{
		$("#lnb1_3_a").css("color","#E2427F");
		$("#lnb1_3_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
	}
		dataTableMake('bookSearchTotal');
	
 }
</script>
<style>
.form-control{
	margin-top: 3px;
}
.table_Area{
	margin-top: 20px;
}
.dataTable{
/* 	table-layout:fixed; */
font-size: 12px;
}

.bookImg_BR {
    max-width: 100%;
  	margin: auto;
}
td:nth-child(odd){
	text-align: center;
	width: 120px;
}
.thumbImg{
	float:left;
	position: relative; 
	top: 15px; 
	width: 15px; /*Desired width*/
	height: 15px; /*Desired height*/
	cursor: pointer;
	
}
#bookSearchTotal_wrapper{
	margin-bottom: 50px;
}
#result_title{
	font-weight: 800;
    font-size: 30px;
    padding: 7px 0;
    color:#555;
}

.wrap-loading div{ /*로딩 이미지*/
        top:30%;
        left:40%;
        position: fixed;
    }
    .wrap-loading img{
    	width: 80%;
    	height: 80%;
     } 
     .display-none{ /*감추기*/
         display:none; 
     }

</style>
<body>

	<div id="middle">
	<div class="wrap-loading display-none">
		    	<div><img src="./img/loading.gif"/></div>
			</div>
		<div id="contbody">

			<div id="contleft" class="contleft">
				<h2>자료검색</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						<li id="lnb1_1"><a id="lnb1_1_a" href="./bookSearch.do?kind=total">통합검색</a></li>
						<li id="lnb1_2"><a id="lnb1_2_a" href="./bookSearch.do?kind=detail">상세검색</a></li>
						<li id="lnb1_3"><a id="lnb1_3_a" href="./bookSearch.do?kind=request">희망도서신청</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>

			<div id="contentcore">
				<c:if test="${kind=='total'}">
				<div class="naviandtitle">
					<h3>통합검색</h3>
					<form class="form-inline" onsubmit="return false">
					<select class="form-control" id="searchKey" name="searchKey" title="검색 선택">
						<option value="title">서명</option>
						<option value="author">저자</option>
						<option value="publisher">출판사</option>
						<option value="isbn">ISBN</option>
					</select>
						<input	type="text"  class="form-control" id="bookSearchKeyword" name="searchKeyword" autocomplete="off" placeholder="검색어 입력" style="width: 80%">
						<button class="btn btn-primary" onclick="bookSearchTotal()">검색</button>
					</form>
				</div>
				</c:if>
				<c:if test="${kind=='detail'}">
				<div class="naviandtitle">
					<h3>상세검색</h3>
					<form class="form-horizontal" onsubmit="return false">
						<input	type="text"  class="form-control" id="detail_title" name="searchKeyword"	autocomplete="off" placeholder="도서명을 입력해 주세요">
						<input	type="text"  class="form-control" id="detail_author" name="searchKeyword"	autocomplete="off" placeholder="저자를 입력해 주세요">
						<input	type="text"  class="form-control" id="detail_publisher" name="searchKeyword"	autocomplete="off" placeholder="출판사를 입력해 주세요">
						<input	type="text"  class="form-control" id="detail_isbn" name="searchKeyword"	autocomplete="off" placeholder="ISBN을 입력해 주세요">
<!-- 						<button class="w-btn w-btn-green" onclick="">검색</button> -->
						<div style="text-align: left; margin-top: 10px">
						<button class="btn btn-primary" onclick="bookSearchDetail()">검색</button>
						<input type="reset" class="btn btn-default" value="초기화">
						</div>
					</form>
				</div>
				</c:if>
				<c:if test="${kind=='request'}">
				<div class="naviandtitle">
					<h3>희망도서신청</h3>
					<form class="form-inline" onsubmit="return false">
					<select class="form-control" id="searchKey" name="searchKey" title="검색 선택">
						<option value="title">서명</option>
						<option value="author">저자</option>
						<option value="publisher">출판사</option>
						<option value="isbn">ISBN</option>
					</select>
						<input	type="text"  class="form-control" id="requestSearchKeyword" name="searchKeyword" autocomplete="off" placeholder="검색어 입력" style="width: 80%">
						<button class="btn btn-primary" onclick="bookSearchRequest()">검색</button>
					</form>
				</div>
				</c:if>
				<c:if test="${kind=='request'|| kind=='total' || kind=='detail'}">
				<div class="table_Area">
						<table id="bookSearchTotal" class="cell-border" style="float:right;"> 
						</table>
				</div>
				</c:if>
		</div>
	</div>
</div>
			 
<script type="text/javascript">
function dataTableMake(table_id){
    $("#"+table_id).DataTable({
    	//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
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
        order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers" // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    
    });
} 

</script>


</body>
<%@include file="./footer.jsp"%>
</html>