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
function purchaseCodeIndex(index){
	// 테이블의 값을 modal안의 input태그에 넣어줌 
	var purchase_codeIndex = $("#purchase_codeIndex"+index).text();
	var total_priceIndex = $("#total_priceIndex"+index).text();
	var total_eaIndex = $("#total_eaIndex"+index).text();
	var distributor_emailIndex = $("#distributor_emailIndex"+index).text();
	
	// 주문일, 확정일에 따라 보이는 버튼이 다르게 함
	var order_dateIndex = $("#order_dateIndex"+index).text();
	var confirm_dateIndex = $("#confirm_dateIndex"+index).text();


//	console.log(purchase_codeIndex)
	$('#purchase_code').attr('value', purchase_codeIndex);
	$('#total_price').attr('value', total_priceIndex);
	$('#total_ea').attr('value', total_eaIndex);
	$('#distributor_email').attr('value', distributor_emailIndex);
	
	$('#order_date').attr('value', order_dateIndex);
	$('#confirm_date').attr('value', confirm_dateIndex);
	
//	console.log(order_dateIndex);
	
	// 발주확인, 반입확인버튼 초기화
	$('#modalId').find('.btn-success').remove();

	// 필수데이터 작성 했는지 확인
	if(purchase_codeIndex != '' && total_priceIndex != '' && total_eaIndex != '' && distributor_emailIndex != '') {
		// 발주확인/반입확인 버튼 조건 확인		
		if(order_dateIndex == '' && confirm_dateIndex== '' ){
			// 화면에 발주화면 버튼 보이기
			var orderButtonHTML = '<button type="button" class="btn btn-success btn-order" data-bs-dismiss="modal" onclick="purchaseInfoUpdateOrderChk();">발주확인</button>'
			$(".btn-update-modal").after(orderButtonHTML);
		}else if(order_dateIndex != '' && confirm_dateIndex== ''){
			// 화면에 반입확인 버튼 보이기
			var confirmButtonHTML = '<button type="button" class="btn btn-success btn-receive" data-bs-dismiss="modal" onclick="purchaseInfoUpdateReceiveChk();">반입확인</button><br>'
			$(".btn-update-modal").after(confirmButtonHTML);
		}
	}
}













