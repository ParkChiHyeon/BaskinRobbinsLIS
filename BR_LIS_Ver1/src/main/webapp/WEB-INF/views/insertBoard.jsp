<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <%@ include file="./header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.slim.js"></script>
<!-- <script src="https://cdn.ckeditor.com/4.18.0/standard-all/ckeditor.js"></script> -->
<script type="text/javascript" src="./ckeditor/ckeditor/ckeditor.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div id="middle" >
		<div id="contbody">
		
		<!-- 왼쪽 메뉴바 -->
			<div id="contleft" class="contleft">
				<h2>정보마당</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						 <li id="lnb4_1"><a id="lnb4_1_a" href="./viewAllBoard.do?kind=notice">공지사항</a></li>
		                 <li id="lnb4_2"><a id="lnb4_2_a" href="./viewAllBoard.do?kind=faq">FAQ</a></li>
		                 <li id="lnb4_3"><a id="lnb4_3_a" href="./viewAllBoard.do?kind=calendar">도서관일정</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>
		<!--  -->
			<div id="contentcore" style="margin-bottom: 30px">
				<hr>
				<div>
				
				<c:if test="${kind == 'notice' }">
					<h3>공지사항 새 글 입력폼</h3>
					<form id="insertNotice"  method="post" onsubmit="return insertBoard()" enctype="multipart/form-data">
						작성자:<input type="text" id="admin_id" name="admin_id" value="admin001" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control"><br>
						내용: <textarea id="content" name="content"></textarea>
						<input type="file" name="file" class="form-control">
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
				<c:if test="${kind == 'faq' }">
					<h3>FAQ 새 글 입력폼</h3>
					<form id="insertFAQ" method="post" action="./insertFAQ.do">
						작성자:<input type="text" id="admin_id" name="admin_id" value="admin001" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control"><br>
						내용: <textarea name="content" id="content"></textarea>
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
				<c:if test="${kind == 'calendar' }">
					<h3>도서관일정 새 글 입력폼</h3>
					<form id="insertCalendar" method="post" action="./insertCalendar.do">
						작성자:<input type="text" id="admin_id" name="admin_id" value="admin001" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control"><br>
						내용: <textarea name="content" id="content"></textarea>
						일정시작일: <input type="date" id="start_date" name="start_date" class="form-control"><br>
						일정종료일: <input type="date" id="end_date" name="end_date" class="form-control"><br>
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
			</div>
		</div> <!-- contentcore끝 -->
	</div>
</div>

 <script type="text/javascript">
 var kind='<c:out value="${kind}"/>'
 var today = new Date();   
 var year = today.getFullYear(); // 년도
 var month = today.getMonth() + 1;  // 월
 var date = today.getDate();  // 날짜
 var hh = today.getHours();
 var mm = today.getMinutes();
 
 var tempDir = $("#admin_id").val()+"_"+year+(month<10?'0'+month:month)+(date<10?'0'+date:date)+(hh<10?'0'+hh:hh)+(mm<0?'0'+mm:mm);
if(kind=='notice'){
	console.log("notice insert")
CKEDITOR.replace( 'content' ,{
									//language: 'en', //에디터의 언어 설정
									uiColor: '#E2427F', // 에디터 색상 변경
									extraPlugins: 'editorplaceholder', 
								    editorplaceholder: // 에디터 화면에 띄운 글귀
								    '여기에 글을 입력하거나 파일을 드래그해주세요...', 
									uploadUrl:"fileuploadImg.do?directory_name="+tempDir, //여기 참고하세용
});
}else{
	CKEDITOR.replace( 'content' ,{
					//language: 'en', //에디터의 언어 설정
					uiColor: '#E2427F', // 에디터 색상 변경
					extraPlugins: 'editorplaceholder', 
				    editorplaceholder: // 에디터 화면에 띄운 글귀
				    '파일 업로드는 되지 않습니다. 참고해 주세요', 
});	
}
CKEDITOR.on('dialogDefinition', function( ev ){
	var dialog = ev.data.definition.dialog;
	var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
//  this file format is not
    switch (dialogName) {
        case 'image': 
			dialogDefinition.removeContents('advanced'); // 자세히탭 제거
			dialogDefinition.removeContents('Link'); // 링크탭 제거
            
            dialog.on('show', function (obj) {
        		this.selectPage('Upload'); //업로드탭으로 시작
            });
			
            break;
    }
});
		 
function search(){
	// <textarea>의 입력값 가져오기
	// CKEDITOR.instances.content.getData(); 의 content는 <textarea>에 설정된 id값
	 var data = CKEDITOR.instances.content.getData();
	 document.getElementById("result").innerHTML = data;
}

function insert(){
	var data = document.getElementById("inputContent").value;
	console.log(data);
	// <textarea>에 넣어줄 값을 data 부분에 넣어주면 됨 
	CKEDITOR.instances.content.setData(data); 
}

function resetCon(){
	CKEDITOR.instances.content.setData("");
}

function insertBoard(){
	var editorFrm = document.getElementById("insertNotice");
	
	var title = document.getElementById("title").value;
	var content = CKEDITOR.instances.content.getData();
	console.log(title, content);
	
	if(title==""){
		alert("제목을 입력해주세요");
		return false;
	}else if(content==""){
		alert("내용을 입력해주세요");
		return false;
	}else{
		editorFrm.action = "./fileupload.do?directory_name="+tempDir+"&regdate="+today.toISOString();
	}
}
</script>              
</body>
<%@include file="./footer.jsp"%>
</html>