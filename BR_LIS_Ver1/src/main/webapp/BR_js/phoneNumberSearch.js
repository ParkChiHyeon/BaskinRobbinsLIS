/**
 * 문자알림을 보내기 위한 script
 */

// 메시지를 보내기 위해 휴대폰 번호를 조회하고, 수신번호에 조회한 번호를 넣어줌
function requestMessage() {
	var searchPhoneNumber = document.getElementById("searchPhoneNumber").value;
	console.log(searchPhoneNumber);
	
	$.ajax({
		url : "./searchPhoneNumber.do",
		type : "post",
		data : {
				"searchPhoneNumber":searchPhoneNumber,
				},
		dateType: "text",
		async:false,
		success:function(msg){
			var phoneNumber = msg;
			// 기존에 받아왔던 List 초기화
			$("#phoneNumResult").empty();
			console.log(msg);
			
			// 조회 된 번호를 화면에 표시하기 위해 변수에 담아줌
			var phoneNumberHTML = '조회 된 휴대폰 번호는 '+ msg +'입니다.';
			
			// HTML 화면에 그리기				
			$("#phoneNumResult").append(phoneNumberHTML);
			
			// 수신번호에 자동으로 휴대폰 번호를 넣어준다
			$('input[name=phoneNumber]').attr('value',phoneNumber);
		},
		error : function(){
			alert("아이디를 입력해주세요");
		}

	});	
}	

// coolSMS 문자 메시지 전송
function sendMessage(){
	var phoneNumber = document.getElementById("phoneNumber").value;
	var requestBookMessage = document.getElementById("requestBookMessage").value;
	
            $.ajax({
                type: "post",
                url: "./sendMessage.do",
                data: {
						"phoneNumber" : phoneNumber,
						"requestBookMessage":requestBookMessage
						}, // 핸드폰 값이 넘어감
                success: function(msg){ 
					console.log(msg);
					if(msg == "sucssess"){
	      		    	alert('문자를 발송하였습니다.');
						
					}else{
	      		    	alert('문자 발송에 실패하였습니다.');
					}
                },
                error: function () {
					alert("메시지 전송에 실패하였습니다. 다시 시도해주세요")
				}
            });
}

//[전체 선택] 체크박스를 클릭하면 개별 체크박스를 전체 선택하거나 선택을 해제하기
 $(document).on('click','#checkAll',function(){
	if($('#checkAll').is(':checked')){
		$('.checkDel').prop('checked',true);
	}else{
		$('.checkDel').prop('checked',false);
		}
	});



$(document).on('click','.checkDel',function(){
	if($('input[class=del-chk]:checked').length == $('.checkDel').length){
		$('#checkAll').prop('checked',true);
	}else{
		$('#checkAll').prop('checked',false);
    }
 });





// 모달 창이 닫힐 때, 내용이 초기화됨
function modalClose(){
   // 모달 창 닫기 버튼 클릭 시
	 $('.modal').on('hidden.bs.modal', function (e) {
	    console.log('modal close');
	  $(this).find('form')[0].reset()
	});
	
}

	