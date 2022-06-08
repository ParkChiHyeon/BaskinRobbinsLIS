<%@page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.slim.js"></script>
<!-- <script src="https://cdn.ckeditor.com/4.18.0/standard-all/ckeditor.js"></script> -->
<script type="text/javascript" src="./ckeditor/ckeditor/ckeditor.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <%@ include file="./header.jsp"%>
</head>
<body>

<div id="middle" >
		<div id="contbody">
		
		<!-- 왼쪽 메뉴바 -->
			<div id="contleft" class="contleft">
				<h2>정보마당</h2>
				<div class="lnbBody" style="min-height: 550px;">
					<ul id="lnb" class="lnbul">
						<li id="lnb4_1"><a id="lnb4_1_a" href="./noticeboard.do">공지사항</a></li>
						<li id="lnb4_2"><a id="lnb4_2_a" href="./faqboard.do">FAQ</a></li>
						<li id="lnb4_3"><a id="lnb4_3_a">도서관일정</a></li>
					</ul>
					<div class="lnbBottom"></div>
				</div>
			</div>
			
			<!--  -->
			<div id="contentcore" style="margin-bottom: 30px">
			
				<hr>
				<div>
					<h3>정보마당 새 글 입력폼</h3>
				<c:if test="${kind == 'notice' }">
					<form id="modifynotice"  method="post" action="./insertNotice.do">
						작성자:<input type="text" id="admin_id" name="admin_id" value="admin001" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control"><br>
						내용: <textarea name="content" id="content"></textarea>
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
				<c:if test="${kind == 'faq' }">
					<form id="modifyfaq" method="post" action="./insertFAQ.do">
						작성자:<input type="text" id="admin_id" name="admin_id" value="admin001" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control"><br>
						내용: <textarea name="content" id="content"></textarea>
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
				
				
			</div>
			
			
			</div>
	</div>
</div>


 <script>
CKEDITOR.replace( 'content' ,{
									//language: 'en', //에디터의 언어 설정
									uiColor: '#E2427F', // 에디터 색상 변경
									extraPlugins: 'editorplaceholder', 
								    editorplaceholder: // 에디터 화면에 띄운 글귀
								    '여기에 글을 입력하거나 파일을 드래그해주세요...', 
  									filebrowserUploadUrl: "fileupload.do", //여기참고하세요 하나는 파일 업로드 하나는 이미지 업로드
									uploadUrl:"fileupload.do", //여기 참고하세용
							
}
);

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

function modifynotice(){
	var modifynotice = document.getElementById("modifynotice");
	modifynotice.action = "./modifynotice.do";
	
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
		modifynotice.submit();
	}

}
</script>              
</body>
<%@include file="./footer.jsp"%>
</html>
