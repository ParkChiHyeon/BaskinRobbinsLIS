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
<%@ include file="./header.jsp"%>
<body>
<%-- ${lb} --%>
${lists}

<h1>대출현황조회</h1>
<div id="container">
	<table  class="table table-bordered">
		<thead>
			<tr>
				<th>Book_Serial</th>
				<th>ISBN</th>
				<th>LD</th>
				<th>RV</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lVo" items="${lists}" varStatus="vs">
			<tr>
				<td>${lVo.book_serial}</td>
				<td>${lVo.isbn}</td>
				<td>${lVo.ld}</td>
				<td>${lVo.rv}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
			<h2>나의 도서관</h2>
			<ul class="nav nav-pills nav-stacked">
				<li class="side2m1 sel">	<a href="./index.php?g_page=mypage&amp;m_page=mypage01">내서재</a></li>
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