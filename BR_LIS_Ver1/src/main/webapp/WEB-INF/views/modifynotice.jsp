<%@page import="com.br.lis.vo.Notice_FAQBoardVo"%>
<%@page import="com.br.lis.vo.CalendarBoardVo"%>
<%@page import="com.br.lis.vo.CalendarBoardVo"%>

<%@page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<style>
p{
	color: red;
}

</style>
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
				
				<c:if test="${kind == 'notice'}">
					<h3>공지사항 글 수정 입력폼</h3>
					<form id="modifyNotice"  method="post"  onsubmit="return modifynotice()" enctype="multipart/form-data">
						<input type="hidden" value="${dto.notice_seq}" name="notice_seq">
						<input type="hidden" value="${dto.file_path}" name="file_path" id="file_path">
						작성자:<input type="text" id="admin_id" name="admin_id" value="${dto.admin_id}" class="form-control" readonly>
						제목: <input type="text" id="title" name="title" value="${dto.title}" class="form-control"><br>
						<textarea name="content" id="content">${dto.content}</textarea>
						<input type="submit" class="btn btn-primary" value="저장" style="float: right;">
						<input type="button" onclick="history.back(-1)" class="btn btn-danger" value="취소" style="float: right;">
						<c:if test="${dto.file_path!=null}">
						<input type="file" name="file"  style="display:none; float: left;"/>
						<img id="deleteFile" onclick="fileDeleteOn()" src="./img/delete_mark.png" style="margin:10px 10px; width: 10px; height: 10px; display: none; cursor: pointer;"> 
						<span id="attatchfile">첨부파일 - ${fn:substring(dto.file_path,9,fn:indexOf(dto.file_path,'&'))}</span>
						<img id="deleteImg" onclick="fileDelete()" src="./img/delete_mark.png" style="margin:10px 10px; width: 10px; height: 10px; cursor: pointer;">
						</c:if>
						<c:if test="${dto.file_path==null}">
							<input type="file" name="file"  style="float: left;"/>
							<img id="deleteFile" onclick="fileDeleteOn()" src="./img/delete_mark.png" style="margin:10px 10px; width: 10px; height: 10px; display: none; cursor: pointer;">
						</c:if>
					</form>
				</c:if>
				

	
				
				<c:if test="${kind == 'faq' }">
					<h3>FAQ 글 수정 입력폼</h3>
					<form id="modifyFAQ" method="post" action="./modifyFAQ.do">
						<input type="hidden" name="faq_seq" value="${fVo.faq_seq}">
						작성자:<input type="text" id="admin_id" name="admin_id" value="${fVo.admin_id} " class="form-control">
						제목: <input type="text" id="title" name="title" value="${fVo.title}" class="form-control"><br>
						내용: <textarea name="content" id="content"> ${fVo.content} </textarea>
						<input type="submit" class="btn btn-default" value="저장">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
				
				
				<c:if test="${kind == 'calendar' }">
					<h3>도서관일정 글 수정 입력폼</h3>
					<form id="modifyCalendar" method="post" action="./modifyCalendar.do">
						<input type="hidden" name="calendar_seq" value="${cVo.calendar_seq}">
						작성자:<input type="text" id="admin_id" name="admin_id" value="${cVo.admin_id}" class="form-control">
						제목: <input type="text" id="title" name="title" class="form-control" value="${cVo.title} " style="margin-bottom: 10px;"><br>
						일정시작일: <input type="date" id="start_date" name="start_date" value="${fn:substring(cVo.start_date,0,10)}" >
						일정종료일: <input type="date" id="end_date" name="end_date" value="${fn:substring(cVo.end_date,0,10)}" style="margin-left: 40px;"><br>
						내용: <textarea name="content" id="content">${cVo.content} </textarea>
						<input type="submit" class="btn btn-default" value="저장" onclick="save()">
						<input type="reset" class="btn btn-default" value="초기화" onclick="resetCon()">
					</form>
				</c:if>
			</div>
		</div> <!-- contentcore끝 -->
	</div>
</div>

 <script type="text/javascript">
var directory_name=$("#file_path").val()
var regdateTemp='<c:out value="${dto.regdate}" escapeXml="false" />'
var kind='<c:out value="${kind}" escapeXml="false" />'
console.log(kind)
var tempDir;
if(directory_name!=''&&kind=='notice'){
	tempDir= directory_name.split('&')[2].substr(15);
}else{
	tempDir=$("#admin_id").val()+"_"+regdateTemp.replace(/\s/g,"").replace(/[\-\:]/gi,"").substr(0,12);
}

CKEDITOR.replace( 'content' ,{
									//language: 'en', //에디터의 언어 설정
									uiColor: '#E2427F', // 에디터 색상 변경
									extraPlugins: 'editorplaceholder', 
									bodyId : 'ckEditor',
								    editorplaceholder: // 에디터 화면에 띄운 글귀
								    '여기에 글을 입력하거나 파일을 드래그해주세요...', 
								    uploadUrl:"fileuploadImg.do?directory_name="+tempDir, 
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
	var modifynotice = document.getElementById("modifyNotice");
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
		modifynotice.action="./fileupload.do?directory_name="+tempDir;
	}
}

function fileDeleteOn(){
	$("input[name=file]").val("");
}
function fileDelete(){
	var file='<c:out value="${dto.file_path}" escapeXml="{false}"/>'
	console.log(file);
	   $.ajax({
		      method:"POST",
		      url:"./fileDelete.do?"+file,
		      success:function(){
				alert("파일이 삭제되었습니다.");
				$("#attatchfile").remove();
				$("#deleteImg").css("display","none");
				$("#deleteFile").css("display","block");
				$("input[name=file]").css("display","block");
				$("#file_path").val("");
		      }
		   })
}
</script>            
</body>
<%@include file="./footer.jsp"%>
</html>