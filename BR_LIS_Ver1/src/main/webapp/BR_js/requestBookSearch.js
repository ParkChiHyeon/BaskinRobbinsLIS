/**
 * 신청도서 조회(관리자) 페이지 javascript
 */
function reqBookSearchKeyWord(){
	var searchKey = $("#searchKey option:selected").val();
	console.log = searchKey;
	
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
		dateType: "text",
		async:false,
		success:function(msg){
			// List 뿌리기
			for(i = 0; i < msg.length; i++){
				var requestBook = msg[i]; 
				var reqListHTML = '<tr><td>'+ requestBook.wish_serial +'</td>'
										+'<td>'+ requestBook.member_id +'</td>'
										+'<td>'+ requestBook.purchase_code +'</td>'
										+'<td>'+ requestBook.isbn +'</td>'
										+'<td>'+ requestBook.title +'</td>'
										+'<td>'+ requestBook.publisher +'</td>'
										+'<td>'+ requestBook.author +'</td>'
										+'<td>'+ requestBook.translator +'</td>'
										+'<td>'+ requestBook.price +'</td>'
										+'<td>'+ requestBook.ea +'</td>'
										+'<td>'+ requestBook.confirm +'</td>'
										+'<td>'+ requestBook.history +'</td>'
										+'<td>'+ requestBook.receive_date +'</td></tr>';
						
				// HTML 화면 그리기				
				$("#reqBookList thead").append(reqListHTML);
			}
		


			
		},
		error : function(){
			alert("다시 입력해주세요");
		}

	});



}