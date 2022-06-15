<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반납</title>
<link rel="stylesheet" type="text/css" href="./hik_css/returnBookPage.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<%@ include file="./header.jsp"%>
<body>
<div class="container">
	<h1>반납</h1>
	<form action="#">
		<h2>도서대출내역</h2>
		<table>
            <tr>
                <th>대출번호</th>
                <th class="book_serial" >시리얼번호</th>
                <th>연장여부</th>
                <th>대출일</th>
                <th>반납예정일</th>
            </tr>
            <tr>
                <td><input type="hidden" name="lending_seq" value="${vo.lending_seq}">${vo.lending_seq}</td>
                <td class="book_serial" ><input type="text"  class="book_serial_val" id="book_serial" name="book_serial" placeholder="BKSR155997" onfocus="this.value=''"><input type="button" value="조회" onclick="returnBookSelect()"></td>
                <td>${vo.lending_delay}</td>
                <td>${vo.lending_date}</td>
                <td>${vo.end_date}</td>
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
			<tr>
				<td>${mVo.member_id}</td>
				<td><input type="hidden" name="member_code" value="${mVo.member_code}">${mVo.member_code}</td>
				<td>${mVo.name}</td>
				<td>${mVo.phone}</td>
				<td>${mVo.address}</td>
				<td>${mVo.email}</td>
				<td>${mVo.penalty}</td>
				<td>${mVo.rental_count}</td>
				<td>${mVo.birth}</td>
			</tr>
		</table>
		<h2>예약내역</h2>
		<table>
			<tr>
				<th>예약번호</th>
				<th>예약날짜</th>
				<th>시리얼번호</th>
				<th>회원코드</th>
				<th>회원전화번호</th>
				<th>회원이메일</th>
				<th>회원이름</th>
			<tr>
			<tr>
				<td><input type="hidden" name="reserve_seq" value="${rVo.lending_seq}">${rVo.lending_seq}</td>
				<td>${rVo.reserve_date}</td>
				<td>${rVo.book_serial}</td>
				<td>${rVo.member_code}</td>
				<td><input type="hidden" name="phone" value="${rVo.phone}">${rVo.phone}</td>
				<td>${rVo.email}</td>
				<td><input type="hidden" name="name" value="${rVo.name}">${rVo.name}</td>
			</tr>
		</table>
		<div class="buttons">
		<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnNomal.do" formmethod="get" formtarget="_self">반납</button>
		<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnBookDamege.do" formmethod="get" formtarget="_self">파손</button>
		<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnBookLost.do" formmethod="get" formtarget="_self">분실</button>
		</div>
		</form>	
	</div>
    
</body>
<%@include file="./footer.jsp"%>
</html>