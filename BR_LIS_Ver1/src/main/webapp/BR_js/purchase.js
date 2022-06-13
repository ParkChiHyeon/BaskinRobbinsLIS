/**
 * 구매관련 자바스크립트
 */

// 구매코드를 생성
function createPurchaseCode(){	
	console.log("들어왔나");
	$.ajax({
		url : "./createPurchaseCode.do",
		type : "post",
		data : {
				"result": "result"
				},
		dataType : "text",
		async : false,
		success : function(msg){
			var result = msg;
			if(result == "SUCCESS"){
				console.log("들어왔다@@");
				alert("구매코드를 생성하였습니다.");
				history.go(0);
			}
		},
		error : function(){
			alert("당월 구매코드가 이미 생성되어 있습니다.");
			history.go(0);
		}
	});

}

// 구매금액, 구매수량, 구매처 email을 업데이트
function purchaseInfoUpdateFirst(){
	console.log("실행")
	var purchase_code = document.getElementById("purchase_code").value;
	var total_price = document.getElementById("total_price").value;
	var total_ea = document.getElementById("total_ea").value;
	var distributor_email= document.getElementById("distributor_email").value;
	
	$.ajax({
		url : "./purchaseInfoUpdateFirst.do",
		type : "post",
		data : {
				"purchase_code": purchase_code,
				"total_price": total_price,
				"total_ea": total_ea,
				"distributor_email": distributor_email
				},
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg > 0){
				alert("수정하였습니다.")
				history.go(0);
				console.log("성공");
			}else{
				alert("정보를 다시 확인해주세요.")
			}
		},
		error : function(){
			alert("다시 시도해주세요.")
			history.go(0);
			console.log("실패");
		}
	});
	
}

// 주문날짜를 업데이트하고 확정여부를 발주(O)로 업데이트
function purchaseInfoUpdateOrderChk(){
	var purchase_code = document.getElementById("purchase_code").value;
		$.ajax({
		url : "./purchOrderDateUpdate.do",
		type : "post",
		data : {
				"purchase_code": purchase_code
				},
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg > 0){
				console.log("성공");
				alert("발주 확인 완료");
				history.go(0);
			}
		},
		error : function(){
			alert("발주 확인 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
	
}

// 확정일을 업데이트하고 확정여부를 반입(C)로 업데이트
function purchaseInfoUpdateReceiveChk(){
		var purchase_code = document.getElementById("purchase_code").value;
		$.ajax({
		url : "./purchConfirmDateUpdate.do",
		type : "post",
		data : {
				"purchase_code": purchase_code
				},
		dataType : "text",
		async : false,
		success : function(msg){
			if(msg > 0){
				console.log("성공");
				alert("반입 확인 완료");
				history.go(0);
			}
		},
		error : function(){
			alert("반입 확인 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
}

// modal창이 닫힐 때 내용 초기화
function modalClose(){
   // 모달 창 닫기 버튼 클릭 시
	 $('.modal').on('hidden.bs.modal', function (e) {
	    console.log('modal close');
	  $(this).find('form')[0].reset()
	});
	
}














