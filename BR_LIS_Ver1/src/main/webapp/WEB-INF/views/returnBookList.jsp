<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>반납완료목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<link rel="stylesheet" type="text/css" href="./hik_css/returnBookList.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/returnBookList.js"></script>
</head>
<body>
<div class="container"><br>
	<table id="dataTable" class="hover">
		<thead>
			<tr>
				<td>대출번호</td>
				<td>책제목</td>
				<td>책번호</td>
				<td>회원코드</td>
				<td>대출일</td>
				<td>반납예정일</td>
				<td>반납일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${lists}"  varStatus="vs">
			<tr>
				<td>${vo.lending_seq}</td>
				<td>${vo.title}</td>
				<td>${vo.book_serial}</td>
				<td>${vo.member_code}</td>
				<td>${vo.lending_date}</td>
				<td>${vo.end_date}</td>
				<td>${vo.back_date}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="javascript:location.reload();" class="btn btn-primary" >대출반납완료 목록</button>
	<button onclick="lendingBookListBanNapAdmin();" class="btn btn-success" >반납완료된 책목록</button>
	<button onclick="lendingBookListAdmin()" class="btn btn-info">대출중목록</button>
</div>
</body>
<%@include file="./footer.jsp"%>
</html>