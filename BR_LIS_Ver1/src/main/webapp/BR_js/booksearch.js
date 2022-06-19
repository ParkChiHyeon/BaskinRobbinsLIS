var key ='15b0f156b4d728ec98c5b8c31a0d9193';
var jsonSource;

function bookSearchTotal(){
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
	$("#bookSearchTotal").empty();
	var regx = /^.{2,}$/
	var queryStr;
	var searchKey = $("#searchKey").val();
	var bookSearchKeyword=$("#bookSearchKeyword").val();
	if(!regx.test(bookSearchKeyword)){
//		queryStr={"from":"0","size":"50","query":{"wildcard":{[searchKey]:"*"+bookSearchKeyword+"*"}}};
		queryStr={"from":"0","size":"10000","query":{"match":{[searchKey]:bookSearchKeyword}}};
	}else{
		queryStr={"from":"0","size":"10000","query":{"match":{[searchKey]:bookSearchKeyword}}};
	}
	
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"http://152.67.196.32:9200/book_info/_search",		
		data:JSON.stringify(queryStr),
		dataType:"json",
		beforeSend:function(){
	       	 $(".wrap-loading").css("display","block");  
	    },
		success:function(res){
			jsonSource = res.hits.hits;
			console.log(jsonSource);
			// jsonSource[0].img="imgtestal" 제이슨 추가 
			for(let i=0; i<jsonSource.length;i++){
				jsonSource[i]._source.img=kakaoBookImg(jsonSource[i]._source.isbn);
			}
				$(".wrap-loading").css("display","none");
		}
	})
		.done(function(){
			 $("#bookSearchTotal").DataTable({
			//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "결과 내 검색: ",
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
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입 	
		     data: jsonSource, 
		 	 columns: [
				{ 	
					render:function(data,type,row){
					var html = '<input type="checkbox" class="thumbImg"><img class="bookImg_BR" src='+row._source.img+'>' 
					return html;
				  }},
		  		{ title:'도서',
				 
		          render:function(data,type,row){
					var	html= '<p id="result_title">'+row._source.title+'</p>'; 
					    html+= row._source.author+'<br>';
					    html+= row._source.publisher+'<br>';
					    html+= row._source.isbn;						
					return html;
				  }
				}
		  	]
		  });
		});
}


function kakaoBookImg(str){
		var img;
		$.ajax({
			method:"GET",
			url:"https://dapi.kakao.com/v3/search/book",
			data:{query:str},
			headers:{Authorization:"KakaoAK "+key},
			async:false,
			success:function(data){
				if(data.documents[0]!=undefined){
					img=data.documents[0].thumbnail;
				}else{
					img="./img/noImg.gif"
				}
			}
		})
		return img;
}


function bookSearchRequest(uid){
	var str = $("#requestSearchKeyword").val();
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
	$("#bookSearchTotal").empty();
		$.ajax({
			method:"GET",
			url:"https://dapi.kakao.com/v3/search/book",
			data:{query:str,size:50},
			headers:{Authorization:"KakaoAK "+key},
			beforeSend:function(){
	        	 $(".wrap-loading").css("display","block");  
	    	},
			success:function(data){
				jsonSource = data.documents;
				for(let i=0; i<data.documents.length;i++){
					if(jsonSource[i].thumbnail==''){
						jsonSource[i].thumbnail="./img/noImg.gif";
					}
				}				
				$(".wrap-loading").css("display","none");
			}
		})
		.done(function(){
			 $("#bookSearchTotal").DataTable({
			//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "결과 내 검색: ",
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
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입 	
		     data: jsonSource, 
		 	 columns: [
				{ 	
					render:function(data,type,row){
					var html = '<input type="checkbox" class="thumbImg"><img class="bookImg_BR" src='+row.thumbnail+'>' 
					return html;
				  }},
		  		{ title:'도서',
				 
		          render:function(data,type,row){
					var arr_isbn= row.isbn.split(" ");
					var isbn;
					if(arr_isbn.length==1){
						isbn=arr_isbn[0]
					}else{
						isbn=arr_isbn[1]
					}
					var	html= '<p id="result_title">'+row.title+'</p>'; 
					    html+= row.authors+'<br>';
					    html+= row.publisher+'<br>';
//					    html+= row.translator+'<br>';
//					    html+= row.price+'<br>';
					    html+= isbn;
						if(uid!=undefined){
							html+= '<input type="button" id="bookRequest" value="도서구매신청" onclick="purchaseRequestBook('+"'"+uid+"'"+')">';
						}	
					return html;
				  }
				}
		  	  ]
		  });
		});
}

// 도서구매신청버튼을 눌렀을 때 실행되는 메서드
function purchaseRequestBook(member){
	//isbn, title, publisher, author, translator, price 필요
	console.log("도서구매신청 메서드 실행 user:",member);
	$.ajax({
			url : "./requestBook.do",
			type : "post",
			data : {
					"isbn":"9791170440079",
					"member_id":member,
					"title":,
					"publisher":,
					"author":,
					"translator":,
					"price":
					},
			dataType:"text",
			async : false,
			success:function(msg){
				if(msg == "success"){
					alert("도서 신청이 완료되었습니다.");
					console.log("성공했습니다.");
					history.go(0);
				}else{
					alert("다른 사용자에 의해 신청이 완료 된 도서입니다.");
					console.log("실패.");
					history.go(0);
				}
				
			},
			error : function(){
				alert("도서 신청에 실패하였습니다. 다시 확인해주세요");
				console.log("실패했습니다.");
				history.go(0);
			}
	
		});
	
}


function bookSearchDetail(){
	var title= $("#detail_title").val();
	var author= $("#detail_author").val();
	var publisher= $("#detail_publisher").val();
	var isbn= $("#detail_isbn").val();
//	var queryStr={"query":{"bool":{"must":[{"wildcard":{"title": "*"}},{"wildcard":{"author":"*"+author+"*"}},{"wildcard":{"publisher": "*"}},{"wildcard":{"isbn":"*"}}]}}}
	var queryStr={
    "query":{
        "query_string":{
            "fields":["title", "author", "publisher", "isbn"],
            "query":"*"+title+"*" + " *"+author+"*"+" *"+publisher+"*"+" *"+isbn+"*"
        }
    }
}
	console.log(title,author,publisher,isbn);
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
	$("#bookSearchTotal").empty();
	
	
	
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"http://152.67.196.32:9200/book_info/_search",		
		data:JSON.stringify(queryStr),
		dataType:"json",
//		headers:{
//			"Authorization" : "Basic " + btoa("GD"+":"+"gd1234")
//		},
		beforeSend:function(){
	        	 $(".wrap-loading").css("display","block");  
	    },
		success:function(res){
			 jsonSource = res.hits.hits;
			console.log(jsonSource);
//				jsonSource.push(JSON.parse(data[i]._source.status_code));
			// jsonSource[0].img="imgtestal" 제이슨 추가 
			for(let i=0; i<jsonSource.length;i++){
				jsonSource[i]._source.img=kakaoBookImg(jsonSource[i]._source.isbn);
			}
				$(".wrap-loading").css("display","none");
		}
	})
		.done(function(){
			 $("#bookSearchTotal").DataTable({
			//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "결과 내 검색: ",
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
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입 	
		     data: jsonSource, 
		 	 columns: [
				{ 	
					render:function(data,type,row){
					var html = '<input type="checkbox" class="thumbImg"><img class="bookImg_BR" src='+row._source.img+'>' 
					return html;
				  }},
		  		{ 
					title:'도서',
		        	render:function(data,type,row){
						var	html= '<p id="result_title">'+row._source.title+'</p>'; 
					    html+= row._source.author+'<br>';
					    html+= row._source.publisher+'<br>';
					    html+= row._source.isbn;						
					return html;
				  }
				}
		  	]
		  });
			
		});
}

function bookSearchHome(searchKey,searchKeyword){
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
	$("#bookSearchTotal").empty();
	var regx = /^.{2,}$/
	var queryStr;
	var searchKey = searchKey;
	var bookSearchKeyword=$("#bookSearchKeyword").val();
	if(!regx.test(bookSearchKeyword)){
//		queryStr={"from":"0","size":"50","query":{"wildcard":{[searchKey]:"*"+bookSearchKeyword+"*"}}};
		queryStr={"from":"0","size":"1000","query":{"match":{[searchKey]:searchKeyword}}};
	}else{
//		queryStr={"from":"0","size":"1000","query":{"match":{[searchKey]:"*"+searchKeyword+"*"}}};
		queryStr={"from":"0","size":"1000","query":{"match":{[searchKey]:searchKeyword}}};
	}
	
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"http://152.67.196.32:9200/book_info/_search",		
		data:JSON.stringify(queryStr),
		dataType:"json",
		beforeSend:function(){
	       	 $(".wrap-loading").css("display","block");  
	    },
		success:function(res){
			 jsonSource = res.hits.hits;
			console.log(jsonSource);
			// jsonSource[0].img="imgtestal" 제이슨 추가 
			for(let i=0; i<jsonSource.length;i++){
				jsonSource[i]._source.img=kakaoBookImg(jsonSource[i]._source.isbn);
			}
				$(".wrap-loading").css("display","none");
		}
	})
		.done(function(){
			 $("#bookSearchTotal").DataTable({
			//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": " _MENU_ 개씩 보기",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "0/0",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "결과 내 검색: ",
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
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입 	
		     data: jsonSource, 
		 	 columns: [
				{ 	
					render:function(data,type,row){
					var html = '<input type="checkbox" class="thumbImg"><img class="bookImg_BR" src='+row._source.img+'>' 
					return html;
				  }},
		  		{ title:'도서',
				 
		          render:function(data,type,row){
					var	html= '<p id="result_title">'+row._source.title+'</p>'; 
					    html+= row._source.author+'<br>';
					    html+= row._source.publisher+'<br>';
					    html+= row._source.isbn;						
					return html;
				  }
				}
		  	]
		  });
		});
}
	