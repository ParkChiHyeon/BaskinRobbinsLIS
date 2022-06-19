<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반납</title>
<link rel="stylesheet" type="text/css" href="./hik_css/returnBookPage.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.3/moment.min.js"></script> 
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
</head>
<%@ include file="./header.jsp"%>
<script type="text/javascript" src="./BR_js/returnBookPage.js"></script>
<body>
<div class="container">
	<h1>반납</h1>
	<form action="#">
		<h2>도서대출내역</h2>
		<div id="searchBook">책번호 : <input type="text" class="book_serial_val" id="book_serial" name="book_serial" placeholder="책번호를 입력해주세요.">  <input id="check" type="button" value="조회" onclick="returnBookSelect()"></div>
		<table>
            <tr>
                <th>대출번호</th>
                <th class="book_serial">책번호</th>
                <th>연장여부</th>
                <th>대출일</th>
                <th>반납예정일</th>
            </tr>
            <tr id="vo">
            </tr>
        </table>
		<h2>회원내역</h2>
		<table>
			<tr>
				<th>아이디</th>
				<th>회원번호</th>
				<th>성명</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>이메일</th>
				<th>연체일</th>
				<th>대여권수</th>
				<th>생년월일</th>
			</tr>
			<tr id="mVo">
			</tr>
		</table>
		<h2>예약내역</h2>
		<table>
			<tr>
				<th>예약번호</th>
				<th>예약날짜</th>
				<th>책번호</th>
				<th>회원번호</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>성명</th>
			<tr>
			<tr id="rVo">
			</tr>
		</table>
		<div class="buttons"></div>
		</form>	
	</div>
    
</body>
<%@include file="./footer.jsp"%>
</html>