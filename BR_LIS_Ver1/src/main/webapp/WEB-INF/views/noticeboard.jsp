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
         
         <!-- 테이블 -->
     <div id="contentcore" style="margin-bottom: 30px">
     
     <form action="./multiDelFAQ.do" method="post" onsubmit="return false" id="formBoard">
      <table id="noticeBoardTable" class="cell-border" style="float:right;"> 
      </table>
      </form>
      <div>
      
       <form action="./multiDelNotice.do" method="post" onsubmit="return false" id="formBoard">
      <table id="noticeBoardTable" class="cell-border" style="float:right;"> 
      </table>
      </form>
      
       <form action="./multiDelCalendar.do" method="post" onsubmit="return false" id="formBoard">
      <table id="calendarBoardTable" class="cell-border" style="float:right;"> 
      </table>
      </form>
      
      <div>
         <c:if test="${kind == 'notice' && session !='user'}">
            <button class="btn btn-primary" onclick="javascript:location.href='./editor.do?kind=notice'">공지작성</button>
            <input class="btn btn-info btn-primary" onclick="multiDeleteNotice()" style="width: 90px;" value="다중삭제">
         </c:if>
      
         <c:if test="${kind =='faq' && session !='user'}">
            <button class="btn btn-primary" onclick="javascript:location.href='./editor.do?kind=faq'">FAQ작성</button>
            <input class="btn btn-info btn-primary" onclick="multiDeleteFAQ()" style="width: 90px;" value="다중삭제">
         </c:if>
         
         <button class="btn btn-success" onclick="javascript:location.href='./home.do'" style="float:right;">HOME</button>
      </div>
   	</div>
  </div>
  </div>
  </div>
<script type="text/javascript">
var kind = '<c:out value="${kind}"/>'
var jsonSource = [];
var elaIndex;
var testSession = '<c:out value="${session}"/>';

$(document).ready(function(){
   if(kind=="notice"){
      $("#lnb4_1_a").css("color","#E2427F");
      $("#lnb4_1_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
   }else if(kind=="faq"){
      $("#lnb4_2_a").css("color","#E2427F");
      $("#lnb4_2_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
   }else{
      $("#lnb4_3_a").css("color","#E2427F");
      $("#lnb4_3_a").css("background","#fff url(./include/image/jungang/common/bg_lnbSubOn.png) 170px 50% no-repeat");
   }

   if(kind=='notice'){elaIndex='notice_';}
   else{elaIndex='faq_';}
   
   console.log(testSession, elaIndex, kind)
   
   $.ajax({
      method:"POST",
      contentType:"application/json;",
      url:"https://f087-211-197-28-137.jp.ngrok.io/"+elaIndex+"board/_search",      
      data:JSON.stringify({"from":"0","size":"10000","sort":{[elaIndex+"seq"]:"desc"}}),
      dataType:"json",
      success:function(res){
          var data = res.hits.hits;
         jsonSource.splice(0, jsonSource.length);
         for(let i=0; i<data.length;i++){
            jsonSource.push(data[i]._source)
         }
      }
   })
      .done(function(){
         if(testSession=='admin'&&kind=='notice'){
          $("#noticeBoardTable").DataTable({
         //https://datatables.net/reference/option/language
          "language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "검색: ",
            "zeroRecords": "일치하는 데이터가 없어요.",
            "loadingRecords": "로딩중...",
            "processing":     "잠시만 기다려 주세요...",
            "paginate": {
                "next": "다음",
                "previous": "이전"
            }
        },
        lengthChange: true, // 표시 건수기능 숨기기
        searching: true, // 검색 기능 숨기기
        ordering: true, // 정렬 기능 숨기기
        info: true, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
        order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//      lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입    
           data: jsonSource, 
           columns: [
            {    
            	title:'<input type="checkbox" id="ch" onclick="checkAll(this.checked)">',
               render:function(data,type,row){
                  var   html= '<input type="checkbox" name="chkBox" value="'+row.notice_seq+'">'; 
               return html;
               }
            },
            {    
               title:'글번호',
               data:'notice_seq',
            },
              { 
                title:'제목',
                   render:function(data,type,row){
               var   html= '<a href="./detailnotice.do?seq='+row.notice_seq+'">'+row.title+'</a>'; 
               return html;
              }
            },
            { 
                title:'등록일',
                   render:function(data,type,row){
               var   html= row.regdate.substr(0,10); 
               return html;
              }
            }
           ]
        });
      }else if(testSession!='admin'&&kind=='notice'){
         $("#noticeBoardTable").DataTable({
            //https://datatables.net/reference/option/language
             "language": {
               "emptyTable": "검색을 해주시기 바랍니다.",
               "lengthMenu": " _MENU_ 개씩 보기",
               "info": "현재 _START_ - _END_ / _TOTAL_건",
               "infoEmpty": "0/0",
               "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
               "search": "검색: ",
               "zeroRecords": "일치하는 데이터가 없어요.",
               "loadingRecords": "로딩중...",
               "processing":     "잠시만 기다려 주세요...",
               "paginate": {
                   "next": "다음",
                   "previous": "이전"
               }
           },
           lengthChange: true, // 표시 건수기능 숨기기
           searching: true, // 검색 기능 숨기기
           ordering: true, // 정렬 기능 숨기기
           info: true, // 정보 표시 숨기기
           paging:true, // 페이징 기능 숨기기
           order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//         columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//         lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//         displayLength: 50, //기본표시건수 설정
           pagingType: "simple_numbers", // 페이징 타입    
              data: jsonSource, 
              columns: [
               {    
                  title:'글번호',
                  data:'notice_seq',
               },
                 { 
                   title:'제목',
                      render:function(data,type,row){
                  var   html= '<a href="./detailnotice.do?seq='+row.notice_seq+'">'+row.title+'</a>'; 
                  return html;
                 }
               },
               { 
                   title:'등록일',
                      render:function(data,type,row){
                  var   html= row.regdate.substr(0,10); 
                  return html;
                 }
               }
              ]
           });
      }   else if(testSession=='admin'&&kind=='faq'){
          $("#noticeBoardTable").DataTable({
         //https://datatables.net/reference/option/language
          "language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "검색: ",
            "zeroRecords": "일치하는 데이터가 없어요.",
            "loadingRecords": "로딩중...",
            "processing":     "잠시만 기다려 주세요...",
            "paginate": {
                "next": "다음",
                "previous": "이전"
            }
        },
        lengthChange: true, // 표시 건수기능 숨기기
        searching: true, // 검색 기능 숨기기
        ordering: true, // 정렬 기능 숨기기
        info: true, // 정보 표시 숨기기
        paging:true, // 페이징 기능 숨기기
        order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//      columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//      lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입    
           data: jsonSource, 
           columns: [
            {   
            	title:'<input type="checkbox" id="ch" onclick="checkAll(this.checked)">',
               render:function(data,type,row){
                  var   html= '<input type="checkbox" name="chkBox" value="'+row.faq_seq+'">'; 
               return html;
               }
            },
            {    
               title:'글번호',
               data:'faq_seq',
            },
              { 
                title:'제목',
                   render:function(data,type,row){
               var   html= '<a href="./detailfaq.do?seq='+row.faq_seq+'">'+row.title+'</a>'; 
               return html;
              }
            },
            { 
                title:'등록일',
                   render:function(data,type,row){
               var   html= row.regdate.substr(0,10); 
               return html;
              }
            }
           ]
        });
      }else if(testSession!='admin'&&kind=='faq'){
         $("#noticeBoardTable").DataTable({
            //https://datatables.net/reference/option/language
             "language": {
               "emptyTable": "검색을 해주시기 바랍니다.",
               "lengthMenu": " _MENU_ 개씩 보기",
               "info": "현재 _START_ - _END_ / _TOTAL_건",
               "infoEmpty": "0/0",
               "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
               "search": "검색: ",
               "zeroRecords": "일치하는 데이터가 없어요.",
               "loadingRecords": "로딩중...",
               "processing":     "잠시만 기다려 주세요...",
               "paginate": {
                   "next": "다음",
                   "previous": "이전"
               }
           },
           lengthChange: true, // 표시 건수기능 숨기기
           searching: true, // 검색 기능 숨기기
           ordering: true, // 정렬 기능 숨기기
           info: true, // 정보 표시 숨기기
           paging:true, // 페이징 기능 숨기기
           order: [], //초기표기시 정렬, 만약 정렬을 안하겠다 => order: []
//         columnDefs: [{ targets: 1, width: 100 }] //열의 넓이 조절 
//         lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//         displayLength: 50, //기본표시건수 설정
           pagingType: "simple_numbers", // 페이징 타입    
              data: jsonSource, 
              columns: [
               {    
                  title:'글번호',
                  data:'faq_seq',
               },
                 { 
                   title:'제목',
                      render:function(data,type,row){
                  var   html= '<a href="./detailfaq.do?seq='+row.faq_seq+'">'+row.title+'</a>'; 
                  return html;
                 }
               },
               { 
                   title:'등록일',
                      render:function(data,type,row){
                  var   html= row.regdate.substr(0,10); 
                  return html;
                 }
               }
              ]
           });
      }
      });
});
</script>
</body>
<%@include file="./footer.jsp" %>
</html>