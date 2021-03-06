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
	var isc = '<c:out value="${homeSearch.searchLibraryArr}"/>'
	var searchKey = '<c:out value="${homeSearch.searchKey}"/>'
	var searchKeyword = '<c:out value="${homeSearch.searchKeyword}"/>'
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
		if(isc=='MA'){
			bookSearchHome(searchKey,searchKeyword);
		}
	
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

.wrap-loading div{ /*?????? ?????????*/
        top:30%;
        left:40%;
        position: fixed;
    }
    .wrap-loading img{
    	width: 80%;
    	height: 80%;
     } 
     .display-none{ /*?????????*/
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
				<h2>????????????</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						<li id="lnb1_1"><a id="lnb1_1_a" href="./bookSearch.do?kind=total">????????????</a></li>
						<li id="lnb1_2"><a id="lnb1_2_a" href="./bookSearch.do?kind=detail">????????????</a></li>
<!-- 						<li id="lnb1_3"><a id="lnb1_3_a" href="./bookSearch.do?kind=request">??????????????????</a></li> -->
						<c:choose>
							<c:when test="${admin.admin_id != null}">
								<li id="gnb1_3" class=""><a	href="./bookSearch.do?kind=request">??????????????????</a></li>
							</c:when>
							<c:otherwise>
								<li id="gnb1_3" class=""><a	href="./bookSearch.do?kind=request">??????????????????</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>

			<div id="contentcore">
				<c:if test="${kind=='total'}">
				<div class="naviandtitle">
					<h3>????????????</h3>
					<form class="form-inline" onsubmit="return false">
					<select class="form-control" id="searchKey" name="searchKey" title="?????? ??????">
						<option value="title">??????</option>
						<option value="author">??????</option>
						<option value="publisher">?????????</option>
						<option value="isbn">ISBN</option>
					</select>
						<input	type="text"  class="form-control" id="bookSearchKeyword" name="searchKeyword" autocomplete="off" placeholder="????????? ??????" style="width: 80%">
						<c:if test="${member.member_id != null}">
						<button class="btn btn-primary" onclick="bookSearchTotal('${member.member_id}')">??????</button>
						</c:if>
						<c:if test="${member.member_id == null}">
						<button class="btn btn-primary" onclick="bookSearchTotal()">??????</button>
						</c:if>
					</form>
				</div>
				</c:if>
				<c:if test="${kind=='detail'}">
				<div class="naviandtitle">
					<h3>????????????</h3>
					<form class="form-horizontal" onsubmit="return false">
						<input	type="text"  class="form-control" id="detail_title" name="searchKeyword"	autocomplete="off" placeholder="???????????? ????????? ?????????">
						<input	type="text"  class="form-control" id="detail_author" name="searchKeyword"	autocomplete="off" placeholder="????????? ????????? ?????????">
						<input	type="text"  class="form-control" id="detail_publisher" name="searchKeyword"	autocomplete="off" placeholder="???????????? ????????? ?????????">
						<input	type="text"  class="form-control" id="detail_isbn" name="searchKeyword"	autocomplete="off" placeholder="ISBN??? ????????? ?????????">
<!-- 						<button class="w-btn w-btn-green" onclick="">??????</button> -->
						<div style="text-align: left; margin-top: 10px">
						<button class="btn btn-primary" onclick="bookSearchDetail()">??????</button>
						<input type="reset" class="btn btn-default" value="?????????">
						</div>
					</form>
				</div>
				</c:if>
				<c:if test="${kind=='request'}">
				<div class="naviandtitle">
					<c:choose>
						<c:when test="${admin.admin_id != null}">
							<h3>??????????????????</h3>
						</c:when>
						<c:otherwise>
							<h3>??????????????????</h3>
						</c:otherwise>
					</c:choose>
					<form class="form-inline" onsubmit="return false">
<!-- 					<select class="form-control" id="searchKey" name="searchKey" title="?????? ??????"> -->
<!-- 						<option value="title">??????</option> -->
<!-- 						<option value="author">??????</option> -->
<!-- 						<option value="publisher">?????????</option> -->
<!-- 						<option value="isbn">ISBN</option> -->
<!-- 					</select> -->
						<input	type="text"  class="form-control" id="requestSearchKeyword" name="searchKeyword" autocomplete="off" placeholder="????????? ??????" style="width: 90%">
						<c:if test="${member.member_id != null}">
						<button class="btn btn-primary" onclick="bookSearchRequest('${member.member_id}')" style="margin-left: 15px">??????</button>
						</c:if>
						<c:if test="${member.member_id == null}">
						<button class="btn btn-primary" onclick="bookSearchRequest()" style="margin-left: 15px">??????</button>
						</c:if>
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
            "emptyTable": "????????? ???????????? ????????????.",
            "lengthMenu": " _MENU_ ?????? ??????",
            "info": "?????? _START_ - _END_ / _TOTAL_???",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_?????? ??????????????? ???????????? )",
            "search": "?????? ??? ??????: ",
            "zeroRecords": "???????????? ???????????? ?????????.",
            "loadingRecords": "?????????...",
            "processing":     "????????? ????????? ?????????...",
            "paginate": {
                "next": "??????",
                "previous": "??????"
            }
        },
        
        lengthChange: true, // ?????? ???????????? ?????????
        searching: true, // ?????? ?????? ?????????
        ordering: true, // ?????? ?????? ?????????
        info: true, // ?????? ?????? ?????????
        paging:true, // ????????? ?????? ?????????
        order: [], //??????????????? ??????, ?????? ????????? ???????????? => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //?????? ?????? ?????? 
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //???????????? 
//      displayLength: 50, //?????????????????? ??????
        pagingType: "simple_numbers" // ????????? ?????? ?????? : simple, simple_numbers, full_numbers ???
    
    });
} 

</script>


</body>
<%@include file="./footer.jsp"%>
</html>