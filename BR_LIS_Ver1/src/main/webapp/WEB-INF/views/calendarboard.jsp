<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%@include file="./header.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./dist/fullcalendar-5.10.2/lib/main.css" />
<link rel="stylesheet" href="./dist/datetimepicker/jquery.datetimepicker.css">
<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
<script src="https://unpkg.com/tippy.js@6"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./dist/datetimepicker/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="./dist/fullcalendar-5.10.2/lib/main.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.5/datatables.min.js"></script>
<script type="text/javascript" src="./BR_js/cal.js"></script>
<script type="text/javascript" src="./BR_js/datatime.js"></script>

<style type="text/css">
  body { 
        padding: 0; 
        font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif; 
        font-size: 14px; 
    } 

    #calendar {
      max-width: 1100px;
      margin: 40px auto;
    }
    
     .fc-daygrid-dot-event{ 
     	cursor: pointer;
     } 
    .regularHoliday{
    	pointer-events: none;
    	
    }
    .calendar-btn{
    	display: none;
    }
    .table-btn{
    	display: block;
    }
</style>

<script type="text/javascript">
$(document).ready(function(){
	$(".fc-event-past").attr("href","http://naver.com")
})
function blockToCalendar(){
	selectCalendar();
}

function blockToList(){
	selectTable();  
}


</script>
<body>
	<div id="middle">
		<div id="contbody">

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

			<div id="contentcore">
				<div class="naviandtitle">
					<button class="btn btn-primary calendar-btn" onclick="blockToCalendar()" style="float: right;">달력형 보기</button>
					<button class="btn btn-primary table-btn" onclick="blockToList()" style="float: right;">리스트형 보기</button>
				</div>
				<div class="naviandtitle">
					<div id="calendar"></div>
					<table id="calendarBoardTable" class="cell-border" style="float:right;"> 
					</table>
				</div>
		</div>
	</div>
</div>
	
</body>
<%@include file="./footer.jsp" %>
</html>