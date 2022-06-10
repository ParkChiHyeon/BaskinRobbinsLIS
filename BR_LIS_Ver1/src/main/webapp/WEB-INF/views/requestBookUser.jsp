<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- <script type="text/javascript" src="./BR_js/requestBookSearch.js"></script>  -->
<title>사용자 신청도서 조회 페이지</title>
</head>
<%@ include file="/WEB-INF/views/header.jsp" %>
<body>
	<h1>나의 신청도서</h1>
	<div>
		<!-- 사용자의 신청도서 승인여부를 볼 수 있는 리스트 -->
		<table id="myRequestBook">
			<thead>
				<tr>
					<th>도서명</th>
					<th>지은이</th>
					<th>출판사</th>
					<th>승인여부</th>
				</tr>			
			</thead>
			<tbody id="">
				<c:forEach var="myRequestBookList" items="${myRequestBookList}" varStatus="vs">
					<tr>
						<td>${myRequestBookList.title}</td>
						<td>${myRequestBookList.author}</td>
						<td>${myRequestBookList.publisher}</td>
						<!-- 승인여부 조회결과가 Y면 승인, N이면 미승인 -->
						<c:choose>
							<c:when test="${myRequestBookList.confirm == 'Y'}">
								<td>승인</td>
							</c:when>
							<c:when test="${myRequestBookList.confirm == 'N'}">
								<td>미승인</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
	
</body>
<%@include file="./footer.jsp"%>
</html>