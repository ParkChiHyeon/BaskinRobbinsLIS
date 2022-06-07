<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청구매 페이지</title>
</head>
<%@ include file="./header.jsp"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
function requestBook() {

	$.ajax({
		url : "./requestBook.do",
		type : "post",
		data : {
				"isbn":"9791164131686",
				"member_id":"gnldnd17",
				"title":"메이플스토리",
				"publisher":"출판사",
				"author":"김작가",
				"translator":"김번역",
				"price":"15000",
				},
		dataType:"text",
// 		async : false,
		success:function(msg){
			alert("도서 신청이 완료되었습니다.");
			console.log("성공했습니다.");
			
		},
		error : function(){
			alert("도서 신청에 실패하였습니다. 다시 확인해주세요");
			console.log("실패했습니다.");
		}

	});
}
</script>
<body>

	<div class="searchBox">
		<form name="topSearchForm" id="topSearchForm" method="post" action="/jungang/10010/booksearch/searchSimple.do">
			<input type="hidden" name="searchLibraryArr" value="MA">
			<div class="bgWhite">
				<select name="searchKey" title="검색방식선택">
					<option value="ALL" selected="selected">전체</option>
					<option value="TITLE">서명</option>
					<option value="AUTHOR">저자</option>
					<option value="PUBLISHER">출판사</option>
					<option value="KEYWORD">키워드</option>
				</select>
				<input type="text" name="searchKeyword" id="topSearchKeyword" class="schInput1" title="검색어 입력">
			</div>
			<div class="btnRight"> <span class="themeBtn"><a href="javascript:fnTopSearch();">도서검색</a></span> </div>
		</form>
	</div>
	
	<h3>도서 신청 버튼</h3>
	<input type="button" value="신청하기" onclick="requestBook()">
	
	
	
	
	
	
<!-- 	신청조건! -->
<!-- 	1. 한 책은 한사람만(한번만) 신청할 수 있음 -->
<!-- 	2. 한 사람은 한달에 한 권만 신청할 수 있음 -->
	
</body>
<%@include file="./footer.jsp"%>
</html>