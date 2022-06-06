<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청구매 페이지</title>
</head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
function requestBook() {
	$.ajax({
		url : "./requestBook.do",
		type : "post",
		data : {"wish_serial":"pw123123","id": "GD001", "title":"메이플스토리",
				"publisher":"출판사","auth":"김작가","translator":"김번역",
				"price":"15000","ea":"3","confirm":"Y"},
		async : false,
		success:function(msg){
			console.log("성공했습니다.");
			
		},
		error : function(){
			console.log("실패했습니다.");
		}

	});
}
</script>
<body>
	
	<h3>신청</h3>
	<input type="button" value="신청하기" onclick="requestBook()">
	
	
	
<!-- 	신청조건! -->
<!-- 	1. 한 책은 한사람만(한번만) 신청할 수 있음 -->
<!-- 	2. 한 사람은 한달에 한 권만 신청할 수 있음 -->
	
</body>
</html>