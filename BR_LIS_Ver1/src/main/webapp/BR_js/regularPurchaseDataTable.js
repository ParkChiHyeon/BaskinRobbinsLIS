/**
 * 정기도서구매와 관련된 스크립트
 */

// 구매 수량 변경
var modalIndex;

function regularPurchaseInfoChange(index){
	console.log("메소드 실행");
	//modal의 index를 증가시켜줌
	modalIndex = index;
	// 구매수량 데이터를 변수에 담아줌
	var ea_Index = $("#ea_Index"+index).text();
//	var regularHistory_Index = $("#regularHistory_Index" + index).text();
//	console.log(ea_Index+"입니다");

	// 구매 수량을 modal의 input value에 넣어줌
	$("#changePurchaseCount").attr('value', ea_Index);
}

// 변경 버튼을 눌렀을 때 수정한 구매 수량이 반영됨, 버튼에 해당하는 regular_serial을 가져옴
function changePurchaseCount(){
	// Controller로 넘겨줄 값(ea, regular_serial)
	var changePurchaseCount = document.getElementById("changePurchaseCount").value;
	var regular_serial = $("#regularSerial_Index" + modalIndex).text();
	
	console.log(changePurchaseCount);
	console.log(regular_serial);
	
	// 구매 수량을 변경하는 ajax
	$.ajax({
		url : "./changePurchaseCount.do",
		type : "post",
		data : {
				"changePurchaseCount" : changePurchaseCount,
				"regular_serial" : regular_serial
				},
		dataType : "json",
		async : false,
		success : function(msg){
			//변경 성공
			console.log(msg);
			if(msg > 0){
				alert("수정이 완료되었습니다.");
				console.log("아작스 성공")
				history.go(0);				
			}
			
		},
		error : function(){
			//변경 실패
			alert("수정 실패. 다시 시도해주세요.");
			history.go(0);
		}
	
		
	})
}

// 구매불가 사유를 업데이트하는 메소드
function notPurchaseReason(){
	console.log("구매불가사유 업데이트 메소드 실행")
	var notPurchaseReaso = document.getElementById("notPurchaseReason").value;
	var regular_serial = $("#regularSerial_Index" + modalIndex).text();
	
	console.log(notPurchaseReaso);
	console.log(regular_serial);
	// 구매불가사유를 업데이트
	$.ajax({
		url : "./notPurchaseReason.do",
		type : "post",
		data : {
				"notPurchaseReaso":notPurchaseReaso,
				"regular_serial" : regular_serial
				},
		dataType : "json",
		async : false,
		success : function(msg){
			if(msg > 0){
				//변경 성공
				alert("수정이 완료되었습니다.");
				console.log("아작스 성공")
				history.go(0);
			}
		},
		error : function(){
			//변경 실패
			alert("수정 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
	
}

// 확정여부를 N으로 변경
function changeConfirmN(){ 
	console.log("확정여부 N으로 변경하는 메소드")
	
}


// 입고 버튼을 눌렀을 때, 체크 된 구매 정보의 입고일이 업데이트
function recieveBook(){
	console.log("입고일을 업데이트하는 메소드")
//	modalIndex
	var recieveBook = [];
//	recieveBook = $("input:checkbox[name='regularPurchaseCheck']:checked").val();

	$("input:checkbox[name=regularPurchaseCheck]:checked").each(function(modalIndex) {
		recieveBook.push(modalIndex);
	});
	
	console.log(recieveBook);
	
	
	// 체크박스가 체크된 row의 regular_serial을 담아줌(하나만 됨..ㅎ)
//	var recieveBook = $("input:checkbox[name='regularPurchaseCheck']:checked").val();
//	console.log(recieveBook[0]);
	

}








// modal창이 닫힐 때 내용 초기화
//function modalClose(){
//   // 모달 창 닫기 버튼 클릭 시
//	$('#myModal').on('hide.bs.modal', function () {
//	   $('#myModal').removeData();
//	});
//}
//function modalClose(){
//   // 모달 창 닫기 버튼 클릭 시
//	 $('.modal').on('hidden.bs.modal', function () {
//	    console.log('modal close');
//	  $(this).find('form')[0].reset();
//	});
//	
//}
















