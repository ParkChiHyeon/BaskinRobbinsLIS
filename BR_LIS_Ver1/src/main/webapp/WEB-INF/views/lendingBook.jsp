<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출현황조회</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
    .row.content {height: 1500px}
    
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    
  </style>
  <script type="text/javascript">
  		function reservation(){
  			console.log("예약화면");
//   			console.log(val);
  			
  		
  			var i = document.getElementById("reserveListVal").innerText;
  			var j = document.getElementById("lending_seq").innerText;
  			console.log(i);
  			console.log(j);
			location.href='./reserveBook.do?lending_seq='+j;
  			
  		}
  
  </script>
<%@ include file="./header.jsp"%>
<body>

<%-- ${lists} --%>

<div id="container">
<h1>예약목록전체조회(관리자)</h1>
	<table  class="table table-bordered">
		<thead>
			<tr>
				<th>도서코드</th>
				<th>ISBN</th>
				<th>LD</th>
				<th>RV</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lVo" items="${lists}" varStatus="vs">
			<tr>
				<td><a href="./reserveBook.do">${lVo.book_serial}</a></td>
				<td>${lVo.isbn}</td>
				<td>${lVo.ld}</td>
				<td>${lVo.rv}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

<h1>회원예약목록조회</h1>
${a}
${a.ISBN}
<%-- ${lb.ISBN} --%>
<form action="./reserveBook.do" >
	<table  class="table table-bordered">
		<thead>
			<tr id="reserveList">
				<th>회원코드</th>
				<th>ISBN</th>
				<th>예약번호</th>
				<th>도서제목</th>
				<th>출판사</th>
				<th>저자</th>
				<th>대출신청</th>
			</tr>
		</thead>
		<tbody>
<%-- 			<c:forEach var="a" items="${a}" varStatus="vs"> --%>
			<tr id="reserveListVal">
				<td id="mem">${a.MEMBER_CODE}</td>
				<td>${a.ISBN}</td>
				<td id="lending_seq">${a.LENDING_SEQ}</td>
				<td>${a.TITLE}</td>
				<td>${a.PUBLISHER}</td>
				<td>${a.AUTHOR}</td>
				<td><button>신청</button> </td>
			</tr>
<%-- 			</c:forEach> --%>
		</tbody>
	</table>
	<input type="button" class="btn btn-warning" value="대출신청" onclick="reservation()">
</form>	
</div>
<br>
<br>
<br>
<h1>이전대출도서내역</h1>

	<table  class="table table-bordered">
		<thead>
			<tr>
				<th>대출코드</th>
				<th>회원번호</th>
				<th>도서제목</th>
				<th>저자</th>
				<th>연장여부</th>
				<th>대출일</th>
				<th>반납예정일</th>
				<th>반납일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lBean" items="${listBean}" varStatus="vs">
			<tr>
				<td>${lBean.lending_seq}</td>
				<td>${lBean.member_code}</td>
				<td>${lBean.title}</td>
				<td>${lBean.author}</td>
				<td>${lBean.lending_delay}</td>
				<td>${lBean.lending_date}</td>
				<td>${lBean.end_date}</td>
				<td>${lBean.back_date}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>





<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
			<h2>나의 도서관</h2>
			<ul class="nav nav-pills nav-stacked">
				<li class="side2m1 sel">	<a href="./lendingBook.do">내서재</a></li>
				<li class="side2m1 sel">	<a href="./lendingBook.do">대출중인도서</a></li>
				<li class="side2m1 sel">	<a href="./lendingBook.do">이전대출도서내역</a></li>
				<li class="side2m1 sel">	<a href="./lendingBook.do">예약내역</a></li>
				<li class="side2m3 "><a href="./index.php?g_page=mypage&amp;m_page=mypage03">희망도서 신청내역</a></li>
				<li class="side2m4 "><a href="/dls_lu/member/index.php?act=joinAct&step=userJoin">기본정보</a></li>
			</ul>
		</div>		
	 </div>
	</div> 

<!-- 		<div class="subtit_area"> -->
<!-- 			<h1>내서재</h1> -->
<!-- 			<div class="web_locator"><span class="home">홈</span> -->
<!-- 				<span class="wdepth">나의 도서관</span> -->
<!-- 				<span class="wdepth">내서재</span> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 	<div id="design"> -->
<!-- 		<div id="content"> -->
<!-- 			<div class="mypage"> -->

<!-- <iframe width="100%" height="841" title="내서재" class="dls_main" id="searchFrame" src="../dls_le/index.php?mod=wdDataSearch&act=dataLoanList" style="border: currentColor; border-image: none; width: 100%;"></iframe> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->



</body>
<%@include file="./footer.jsp"%>
</html>