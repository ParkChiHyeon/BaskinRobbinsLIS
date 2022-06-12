//fullcalendar
var listData;
document.addEventListener('DOMContentLoaded', callList());

function callList(){
	$.ajax({
		type: "get",
		url: "./calendarAjax.do",
		dataType: "json",
		success: function(data) {
				listData=data;
				console.log(listData)
			selectCalendar("onload");
		}
	});
}

function selectCalendar(str){
	if(str!='onload'){
		var table = $("#calendarBoardTable").DataTable();
		table.destroy();
		$("#calendarBoardTable").empty();
		$(".calendar-btn").css("display","none");
		$(".table-btn").css("display","block");
	}
	
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				googleCalendarApiKey: 'AIzaSyDt2NdqBPid1Q3tIC7l5LK296WGe1il3YE', // 구글캘린더 윤혜림키
				initialView: 'dayGridMonth',
				eventSources: [
					{
						// 구글 캘린더에서 가져올 이벤트의 ID
						googleCalendarId: "ko.south_korea.official#holiday@group.v.calendar.google.com"
						// 클릭 이벤트를 제거하기 위해 넣은 클래스
						, className: "regularHoliday"
						, editable: false
						//이벤트의 색
						, color: "#ff0000"
						//이벤트의 텍스트 컬러
						, textColor: "#FFFFFF"
					},
					{
						// 구글 캘린더에서 가져올 이벤트의 ID
						googleCalendarId: "1hsqii2d9rdu0upsqfh31h4o20@group.calendar.google.com"
						// 클릭 이벤트를 제거하기 위해 넣은 클래스
						, className: "regularHoliday"
						, editable: false
						//이벤트의 색
						, color: "#CE2939"
						//이벤트의 텍스트 컬러
						, textColor: "#FFFFFF"
					}
				],
				editable: false,
				events:listData,
				eventClick: function(data) {
					var seq = data.event._def.extendedProps.seq
				   		location.href="./detailcalendar.do?seq="+seq;
					console.log(seq);			    
//			        arg.jsEvent.preventDefault() // don't navigate in main tab
			      }
			});
			// 달력 초기화시 필수
			calendar.render();
}

function selectTable() {
		$(".calendar-btn").css("display","block");
		$(".table-btn").css("display","none");
	$("#calendar").empty();
			$("#calendarBoardTable").DataTable({
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
           data: listData, 
           columns: [
            {    
            	title:'일정',
               render:function(data,type,row){
					console.log(row.seq)
                  var   html= '<span><a href="./detailcalendar.do?seq='+row.seq+'">'+row.title+'</a></span> <span style="float: right;">'+row.start.substr(0,10)+'</span>';  
               return html;
               }
			}
           ]
        });
		
}


function dateVal(dtp, dtp2) {
	// start 의 date 값 ex) Wed May 11 2022 11:10:17 GMT+0900 (한국 표준시)
	let date1 = $("#" + dtp + "")
	let i = date1.datetimepicker('getValue');
	// end 의 date 값 ex) Wed May 11 2022 11:10:17 GMT+0900 (한국 표준시)
	let date2 = $("#" + dtp2 + "")
	let i2 = date2.datetimepicker('getValue');
	console.log(i, i2)
	// test.valueOf() 밀리세컨트로 반환
	// 밀리세컨트로 반환한 i 와 i2 의 시간을 비교하여 시작일보다 종료일이 작은 경우를 방지
	if (i.valueOf() >= i2.valueOf()) {
		date1.val("")
		date2.val("")
		alert("일정 종료일이 시작일보다 과거또는 같은 시간입니다.")
		return false;
	}
}

// date( Thu May 12 2022 09:30:00 GMT+0900 (한국 표준시) ) 를 202205120903 형식으로 바꾸어줌
function dateFormat(date) {
	let year = date.getFullYear();
	let month = zeroPlus(date.getMonth() + 1);
	let day = zeroPlus(date.getDate());
	let hour = zeroPlus(date.getHours());
	let min = zeroPlus(date.getMinutes());
	let startVal = year + "" + month + "" + day + "" + hour + "" + min
	return startVal;
}

// 만약 1~9 월일 경우 뒤에 0을 붙여주어 01, 02, 03으로 바꾸어줌
// 월, 일, 시간, 분 을 바꿀때 사용
// ex) 1월 => 2월
function zeroPlus(time) {
	console.log("###################", time)
	return time < 10 ? "0" + time : time;
}