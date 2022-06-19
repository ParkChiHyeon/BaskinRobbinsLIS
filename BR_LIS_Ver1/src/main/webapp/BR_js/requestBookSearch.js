/**
 * 신청도서 조회(관리자) 페이지 javascript
 */
// 신청도서의 List를 보여주는 스크립트
//function reqBookSearchKeyWord(){
//	var searchKey = $("#searchKey option:selected").val();
//	console.log = searchKey;
//	
//	var searchValue = document.getElementById("searchValue").value;
//	// 옵션을 선택했는지, 값이 들어왔는지 확인 후 리턴
//	// 옵션을 선택했고, 입력 값이 들어온 경우 아래 ajax실행
//	$.ajax({
//		url : "./requestBookListCode.do",
//		type : "post",
//		data : {
//				"searchKey":searchKey,
//				"searchValue": searchValue
//				},
//		dateType: "text",
//		async:false,
//		success:function(msg){
//			// 기존에 받아왔던 List 초기화
//			$("#requestList").empty();
//			// 새로운 List 뿌리기
//			for(i = 0; i < msg.length; i++){
//				var requestBook = msg[i]; 
//				var reqListHTML = '<tr><td>'+ requestBook.wish_serial +'</td>'
//										+'<td>'+ requestBook.member_id +'</td>'
//										+'<td>'+ requestBook.purchase_code +'</td>'
//										+'<td>'+ requestBook.isbn +'</td>'
//										+'<td>'+ requestBook.title +'</td>'
//										+'<td>'+ requestBook.publisher +'</td>'
//										+'<td>'+ requestBook.author +'</td>'
//										+'<td>'+ requestBook.translator +'</td>'
//										+'<td>'+ requestBook.price +'</td>'
//										+'<td>'+ requestBook.ea +'</td>'
//										+'<td>'+ requestBook.confirm +'</td>'
//										+'<td>'+ requestBook.history +'</td>'
//										+'<td>'+ requestBook.receive_date +'</td></tr>';
//						
//				// HTML 화면에 그리기				
//				$("#reqBookList tbody").append(reqListHTML);
//			}
//		},
//		error : function(){
//			alert("다시 입력해주세요");
//		}
//	});
//}



// 신청도서의 List를 보여주는 스크립트
function reqBookSearchKeyWord(){
	var searchKey = $("#searchKey option:selected").val();
//	console.log = searchKey;
	
	var searchValue = document.getElementById("searchValue").value;
	// 옵션을 선택했는지, 값이 들어왔는지 확인 후 리턴
	// 옵션을 선택했고, 입력 값이 들어온 경우 아래 ajax실행
	$.ajax({
		url : "./requestBookListCode.do",
		type : "post",
		data : {
				"searchKey":searchKey,
				"searchValue": searchValue
				},
		dataType: "json",
		async:false,
		success:function(msg){
			console.log(msg)
			// 기존에 받아왔던 List 초기화
			$("#requestList").empty();
			// 새로운 List 뿌리기
			var i = 0;
			for(i = 0; i < msg.length; i++){
				var requestBook = msg[i]; 
				var reqListHTML=''; 
					
					reqListHTML+='<tr>'                                                                                                                                                                     
					reqListHTML+='<td>'                                                                                                                                                                     
					if(requestBook.receive_date == null && requestBook.confirm == 'Y'){
					reqListHTML+='<input type="checkbox" id="regularPurchaseCheck'+i+'" name="requestPurchaseCheck" class="checkDel" value="'+requestBook.wish_serial+'">'
					reqListHTML+='<input type="hidden" id="reqIsbn'+i+'" name="reqIsbn" class="checkDel" value="'+requestBook.isbn+'">'
					}
					reqListHTML+='</td>'
					reqListHTML+='<td id="wishSerial_Index'+i+'">'+requestBook.wish_serial+'</td>'
					reqListHTML+='<td>'+ requestBook.member_id +'</td>'
					reqListHTML+='<td>'+ requestBook.purchase_code +'</td>'
					reqListHTML+='<td>'+ requestBook.isbn +'</td>'
					reqListHTML+='<td>'+ requestBook.title +'</td>'
					reqListHTML+='<td>'+ requestBook.publisher +'</td>'
					reqListHTML+='<td>'+ requestBook.author +'</td>'
					if(requestBook.translator == null){
						reqListHTML+='<td></td>'							
					}else{
						reqListHTML+='<td>'+ requestBook.translator +'</td>'
					}
					reqListHTML+='<td>'+ requestBook.price +'</td>'
					reqListHTML+='<td id="ea_Index'+i+'">'+ requestBook.ea +'</td>'
					if(requestBook.confirm=='Y'){
					reqListHTML+='<td>승인</td>'
					}else{
					reqListHTML+='<td>반려</td>'
					}
					if(requestBook.history == null){
						reqListHTML+='<td></td>'
					}else{
						reqListHTML+='<td id="requestHistory_Index'+i+'">'+ requestBook.history +'</td>'
					}
					
					if(requestBook.receive_date == null){
						reqListHTML+='<td></td>'
					}else{
						reqListHTML+='<td>'+ requestBook.receive_date +'</td>'
					}
					reqListHTML+='<td>'
					if(requestBook.receive_date==null){
					reqListHTML+='<div class="container mt-3">'// 수정버튼
					reqListHTML+='<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#myModal2" onclick="requestPurchaseInfoChange('+i+')">수정</button>'
					reqListHTML+='<div class="modal" id="myModal2">'
					reqListHTML+='<div class="modal-dialog modal-sm">'
					reqListHTML+='<div class="modal-content">'
					reqListHTML+='<div class="modal-header">'
					reqListHTML+='<h4 class="modal-title">구매정보 수정</h4>'
					reqListHTML+='<button type="button" class="btn-close" data-bs-dismiss="modal"></button>'
					reqListHTML+='</div>'
					reqListHTML+='<div class="modal-body">'
					reqListHTML+='<div>'
					reqListHTML+='구매수량 변경 : <input type="text" id="changePurchaseCount" value="">'
					reqListHTML+='<button onclick="javascript:changePurchaseCount()">변경</button>'
					reqListHTML+='</div>'
					reqListHTML+='<div>'
					reqListHTML+='구매불가 사유 입력<br>'
					reqListHTML+='<textarea id="notPurchaseReason" name="notPurchaseReason" style="resize: none; width: 200px;"></textarea>'
					reqListHTML+='<button onclick="javascript:notPurchaseReason()">변경</button>'
					reqListHTML+='</div>'
					reqListHTML+='</div>'
					reqListHTML+='<div class="modal-footer">'
					reqListHTML+='<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>'
					reqListHTML+='</div>'
					reqListHTML+='</div>'
					reqListHTML+='</div>'
					reqListHTML+='</div>'
					reqListHTML+='</div>'
					}
					reqListHTML+='</td>'
					reqListHTML+='</tr>'
						
				// HTML 화면에 그리기				
				$("#reqBookList tbody").append(reqListHTML);
			}
		},
		error : function(){
			alert("다시 입력해주세요");
		}
	});
}



// 구매 수량 변경
var modalIndex;

// 구매수량을 modal의 input value에 담아준다.
function requestPurchaseInfoChange(index){
	console.log("메소드 실행");
	//modal의 index를 증가시켜줌
	modalIndex = index;
	// 구매수량 데이터를 변수에 담아줌
	var ea_Index = $("#ea_Index"+index).text();

	// 구매 수량을 modal의 input value에 넣어줌
	$("#changePurchaseCount").attr('value', ea_Index);
}

// 변경 버튼을 눌렀을 때 수정한 구매 수량이 반영됨, 버튼에 해당하는 wish_serial을 가져옴
function changePurchaseCount(){
	console.log("수량 변경메소드 실행")
	var changePurchaseCount = document.getElementById("changePurchaseCount").value;
	var wish_serial = $("#wishSerial_Index" + modalIndex).text();
	
//	console.log(changePurchaseCount);
//	console.log(regular_serial);
	
	// 구매 수량을 변경하는 ajax
	$.ajax({
		url : "./changePurchaseCountReq.do",
		type : "post",
		data : {
				"changePurchaseCount" : changePurchaseCount,
				"wish_serial" : wish_serial
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
			}else{
				alert("수정 실패. 다시 시도해주세요.");
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
	var notPurchaseReason = document.getElementById("notPurchaseReason").value;
	var wish_serial = $("#wishSerial_Index" + modalIndex).text();
	
	console.log(notPurchaseReason);
	console.log(wish_serial);
	// 구매불가사유를 업데이트
	$.ajax({
		url : "./notPurchaseReasonReq.do",
		type : "post",
		data : {
				"notPurchaseReason":notPurchaseReason,
				"wish_serial" : wish_serial
				},
		dataType : "json",
		async : false,
		success : function(msg){
			if(msg > 0){
				//변경 성공
				alert("수정이 완료되었습니다.");
				console.log("아작스 성공")
				history.go(0);
			}else{
				alert("수정 실패.");
			}
		},
		error : function(){
			//변경 실패
			alert("수정 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
}


// 입고 버튼을 눌렀을 때, 체크 된 구매 정보의 입고일이 업데이트
function recieveBook(){
	console.log("입고일을 업데이트하는 메소드")
	
	// 값을 담을 객체 생성
	// 체크박스가 체크된 row의 wish_serial을 담아줌
	var recieveBook = $("input:checkbox[name='requestPurchaseCheck']:checked");
	
	var recieveBooks = []; // = {}; 랑 똑같음
	var reqIsbnValues = [];/// 여기까지 작업함
	for( var i = 0; i < recieveBook.length; i++) {
	    var bookChecked = $(recieveBook.get(i));
		recieveBooks[i] = bookChecked.val();
		var self = bookChecked.closest('td');
		reqIsbnValues[i] = self.find('input[name=reqIsbn]').val();	// td영역내에서 input[name=re~]를 찾아서 가져온다
		
		
	}
	console.log(recieveBooks);
	
	$.ajax({
		url : "./recieveBookReq.do",
		type : "post",
		data : {
				"recieveBooks":recieveBooks,
				"reqIsbnValues":reqIsbnValues
				},
		dataType : "json",
		async : false,
		success : function(msg){
		if(msg > 0){
				//변경 성공
				alert("입고처리 되었습니다.");
				console.log("아작스 성공")
				history.go(0);
			}
		},
		error : function(){
			//변경 실패
			alert("입고 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
}


// 확정여부를 N으로 변경
function changeConfirmN(){ 
	console.log("확정여부 N으로 변경하는 메소드")
	// 값을 담을 객체 생성
	// 체크박스가 체크된 row의 regular_serial을 담아줌
	var recieveBook = $("input:checkbox[name='requestPurchaseCheck']:checked");
	var recieveBooks = []; // = {}; 랑 똑같음
	for( var i = 0; i < recieveBook.length; i++) {
	    var bookChecked = $(recieveBook.get(i));
	    console.log(bookChecked.val());	
		recieveBooks[i] = bookChecked.val();
	}
	console.log(recieveBooks);
	
	$.ajax({
		url : "./changeConfirmNReq.do",
		type : "post",
		data : {
				"recieveBooks":recieveBooks
				},
		dataType : "json",
		async : false,
		success : function(msg){
		if(msg > 0){
				//변경 성공
				alert("반려처리 되었습니다.");
				console.log("아작스 성공")
				history.go(0);
			}
		},
		error : function(){
			//변경 실패
			alert("반려처리 실패. 다시 시도해주세요.");
			history.go(0);
		}
	});
}





// modal창이 닫힐 때 내용 초기화 왜안되징?ㅎㅎ
//function modalClose2(){
//   // 모달 창 닫기 버튼 클릭 시
//	 $('#myModal2').on('hidden.bs.modal', function (e) {
//	    console.log('modal close');
//	  $(this).find('form')[0].reset()
//	});
//}