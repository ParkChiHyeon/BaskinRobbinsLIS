<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/noticeboard.js"></script>
</head>

<body>
<script type="text/javascript">
</script>
<!-- https://datatables.net/examples/styling/ 
해당 페이지에 들어가면 부트스트랩처럼 class로 테이블 디자인 설정 가능 -->
  <div class="container">
	<table id="dataTable" class="cell-border"> <!-- https://datatables.net/examples/styling/ -->
		<thead>
			<tr>
				<td>번호</td>
				<td>MEMBER_ID</td>
				<td>NAME</td>
				<td>EMAIL</td>
				<td>PHONE</td>
				<td>RENTAL_COUNT</td>
				<td>PENALTY</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${mVo}"  varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${mVo.member_id}</td>
				<td>${mVo.name}</td>
				<td>${mVo.email}</td>
				<td>${mVo.phone}</td>
				<td>${mVo.rental_count}</td>
				<td>${mVo.penalty}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	<button class="btn btn-info" onclick="javascript:location.href='./editor.do'">돌아가기</button>
</div>
  
     
</body>
<%@include file="./footer.jsp" %>
</html>