/**
 * 정기구매할 도서에 관련된 js
 */

// 정기구매 리스트 조회
function regulBookSearchKeyWord(){
	var searchValue = document.getElementById("searchValue").value; 
//	console.log(searchValue);
	
	$.ajax({
		url : "./purchRegulList.do",
		type : "post",
		data : {
				"searchValue": searchValue
				},
		dataType : "json",
		async: false,
		success : function(msg){
			console.log(msg);
			// 기존에 받아왔던 List 초기화
			$("#regularPurchaseList").empty();
			
			// 새로운 List 뿌리기
			for(i = 0; i < msg.length; i++){
				var regularBook = msg[i];
//				var regularListHTML = '<tr><td>'+ regularBook.regular_serial+'</td>'
//												+'<td>'+ regularBook.purchase_code+'</td>'
//												+'<td>'+ regularBook.isbn+'</td>'
//												+'<td>'+ regularBook.title+'</td>'
//												+'<td>'+ regularBook.publisher+'</td>'
//												+'<td>'+ regularBook.author+'</td>'
//												+'<td>'+ regularBook.translator+'</td>'
//												+'<td>'+ regularBook.price+'</td>'
//												+'<td>'+ regularBook.ea+'</td>'
//												if(regularBook.confirm == "Y"){
//													+'<td>승인</td>'	
//												}else if(regularBook.confirm== "N"){
//													+'<td>반려</td>'
//												}
//												+'<td>'+ regularBook.history+'</td>'
//												+'<td>'+ regularBook.receive_date+'</td></tr>'
												
				var regularListHTML = '<tr><td>'+ regularBook.regular_serial+'</td>'
												+'<td>'+ regularBook.purchase_code+'</td>'
												+'<td>'+ regularBook.isbn+'</td>'
												+'<td>'+ regularBook.title+'</td>'
												+'<td>'+ regularBook.publisher+'</td>'
												+'<td>'+ regularBook.author+'</td>'
												+'<td>'+ regularBook.translator+'</td>'
												+'<td>'+ regularBook.price+'</td>'
												+'<td>'+ regularBook.ea+'</td>'
												+'<td>'+ regularBook.confirm+'</td>'
												+'<td>'+ regularBook.history+'</td>'
												+'<td>'+ regularBook.receive_date+'</td></tr>'
//					(regularPurchaseRow.confirm == 'Y')							
				// HTML 화면에 그리기
				$("#regularPurchaseTable tbody").append(regularListHTML);
			}
			
			console.log("성공")
		},
		error : function(){
			alert("일치하는 구매코드가 존재하지 않습니다. 다시 입력해주세요.")
		}
		
	});

}