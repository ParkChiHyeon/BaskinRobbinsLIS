<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>보유도서목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="./hik_css/returnBookList.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/possessingBookList.js"></script>
</head>
<body>
<div class="container"><br>
	<table id="dataTable" class="hover">
		<thead>
			<tr>
				<td>책번호</td>
				<td>책제목</td>
				<td>저자</td>
				<td>출판사</td>
				<td>ISBN</td>
				<td>책상태</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${lists}"  varStatus="vs">
			<tr>
				<td>${vo.book_serial}</td>
				<td>${vo.title}</td>
				<td>${vo.author}</td>
				<td>${vo.publisher}</td>
				<td>${vo.isbn}</td>
				<td>${vo.nm}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="javascript:location.reload();" class="btn btn-primary" >보유도서목록</button>
	<button onclick="warehouseBookList()" class="btn btn-success" >서고자료목록</button>
	<button onclick="supplyBookList()" class="btn btn-info">부록목록</button><div id="nmMessege">책상태 : Y=정상 / N=파손,분실</div>
</div>
</body>
</html>