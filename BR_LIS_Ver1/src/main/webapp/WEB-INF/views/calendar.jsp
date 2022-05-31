<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.css" />
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.css" />
<link rel="stylesheet" type="text/css" href="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://uicdn.toast.com/tui.code-snippet/v1.5.2/tui-code-snippet.min.js"></script>
<script src="https://uicdn.toast.com/tui.time-picker/latest/tui-time-picker.min.js"></script>
<script src="https://uicdn.toast.com/tui.date-picker/latest/tui-date-picker.min.js"></script>
<script src="https://uicdn.toast.com/tui-calendar/latest/tui-calendar.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
	img {
		width:25px;
		height:25px;
	}
</style>
<script type="text/javascript">

window.onload = function(){
	
	var prevBtn = document.getElementById('prev'); // 이전 달 버튼
	var nextBtn = document.getElementById('next'); // 다음 달 버튼
	var calInfo = document.getElementById('calInfo');// 화면에 현재 날짜 표시
	var todayBtn = document.getElementById('today'); // 클릭하면 현재 날짜로 이동하는 버튼
	var yearInfo = document.getElementById('yearInfo');//보고있는 캘린더의 년 값
	
	var templates = { //https://nhn.github.io/tui.calendar/latest/Template 참고

		    titlePlaceholder: function() {
		      return '제목입력';
		    },
		    locationPlaceholder: function() {
		      return '내용입력';
		    },
		    startDatePlaceholder: function() {
		      return '시작일';
		    },
		    endDatePlaceholder: function() {
		      return '종료일';
		    },
		    popupSave: function() {
		      return '저장';
		    },
		    popupUpdate: function() {
		      return '수정';
		    },
		    popupEdit: function() {
		      return '수정';
		    },
		    popupDelete: function() {
		      return '삭제';
		    }
		  };

	const calendar = new tui.Calendar('#calendar', {
		  defaultView: 'month',
		  template: templates,
		  useCreationPopup: true,
		  useDetailPopup: true
		});

		calendar.clear();
		calendar.render();
		  

		
		calendar.on('beforeCreateSchedule', scheduleData => { //일정 생성 버튼 클릭시 발생하는 이벤트
			var startTime = scheduleData.start; //시작일
		    var endTime = scheduleData.end; //종료일
		    var isAllDay = scheduleData.isAllDay; //하루일정유무boolean
			console.log('생성');
			});
		calendar.on('beforeUpdateSchedule', scheduleData => {// 일정 수정 및 드래그를 하였을 때 발생하는 이벤트
			scheduleData.schedule // 수정 전 기존 일정 정보
			scheduleData.changes//변경사항
			console.log("업데이트 발생");
			});
		calendar.on('beforeDeleteSchedule', scheduleData => { //삭제할때 발생하는 이벤트
			scheduleData.schedule // 삭제할 일정 정보
			console.log("삭제");
			});		
		calInfo.innerText =calendar.getDate().getFullYear()+"년 "+(calendar.getDate().getMonth()+1)+"월";
		callHoliday(calendar.getDate().getFullYear());
		callSchedule();
		
		nextBtn.addEventListener('click', () => {
			
			calendar.next();
			calInfo.innerText =calendar.getDate().getFullYear()+"년 "+(calendar.getDate().getMonth()+1)+"월";
			console.log(yearInfo.value);
			var thisYear = calendar.getDate().getFullYear();
			console.log(thisYear);
			if(yearInfo.value != calendar.getDate().getFullYear()){
				yearInfo.value = calendar.getDate().getFullYear();	
				calendar.clear();
				callHoliday(yearInfo.value);
				callSchedule();
			}
		});

		prevBtn.addEventListener('click', () => {
			
			calendar.prev();
			calInfo.innerText =calendar.getDate().getFullYear()+"년 "+(calendar.getDate().getMonth()+1)+"월";
			console.log(yearInfo.value);
			var thisYear = calendar.getDate().getFullYear();
			console.log(thisYear);
			if(yearInfo.value != calendar.getDate().getFullYear()){
				yearInfo.value = calendar.getDate().getFullYear();
				calendar.clear();
				callHoliday(yearInfo.value);
				callSchedule();  
			}
		});
		todayBtn.addEventListener('click', () => {
			
			calendar.today();
			calInfo.innerText =calendar.getDate().getFullYear()+"년 "+(calendar.getDate().getMonth()+1)+"월";
		});
function callHoliday(year){
	$.ajax({
		url: "./restDay.do",
		type: "POST",
		data:{"year":year},
		success: function(data) {
			console.log(data)
			 $.each(data, function (i, item) {
				 calendar.createSchedules(item);
				 console.log(item)
                });
		},
		error:function(){
			alert("잘못된 요청입니다");
		}
	});
}

function callSchedule(){
	$.ajax({
		url: "./callSchedule.do",
		type:"GET",
		success: function(data) {
			console.log(data);
			calendar.createSchedules(data);
		 },
		error:function(){
			alert("잘못된 요청입니다");
		} 
	});
}

}

</script>
<body>
	<input id="yearInfo" type="text" value="${serverTime}"/>
	<div>
	<button id="prev">
		<img src="./images/ic-arrow-line-left.png">
	</button>
	<h2 style="display:inline" id="calInfo"></h2>
	<button id="next">
		<img src="./images/ic-arrow-line-right.png">
	</button>
	<button id="today"><b>Today</b></button>
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">일정추가</button>
	</div>
	<div id="calendar" style="width:900px; height: 80%; border:1px solid black; display:inline-block;">
	</div>

</body>
</html>

