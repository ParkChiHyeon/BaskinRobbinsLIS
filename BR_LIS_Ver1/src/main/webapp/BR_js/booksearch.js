function bookSearchTotal(){
	var jsonSource = [];
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
	
	var searchKey = $("#searchKey").val();
	var bookSearchKeyword=$("#bookSearchKeyword").val();
	console.log(bookSearchKeyword,searchKey)

    var queryStr={"from":"0","size":"10000","query":{"wildcard":{[searchKey]:"*"+bookSearchKeyword+"*"}}};
	console.log(JSON.stringify(queryStr))
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"http://localhost:9200/book_info/_search",		
		data:JSON.stringify(queryStr),
		dataType:"json",
//		headers:{
//			"Authorization" : "Basic " + btoa("GD"+":"+"gd1234")
//		},
		success:function(res){
			 var data = res.hits.hits;
			console.log("--★★★★★★--");
			jsonSource.splice(0, jsonSource.length);
			for(let i=0; i<data.length;i++){
//				jsonSource.push(JSON.parse(data[i]._source.status_code));
				jsonSource.push(data[i]._source)
			}
			// jsonSource[0].img="imgtestal" 제이슨 추가 
			for(let i=0; i<jsonSource.length;i++){
				jsonSource[i].img=kakaoBookImg(jsonSource[i].isbn)
			}
			console.log(jsonSource[0]);
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
//		lengthMenu: [ 10, 20, 30, 40, 50 ], //표시건수 
//      displayLength: 50, //기본표시건수 설정
        pagingType: "simple_numbers", // 페이징 타입 	
		     data: jsonSource, 
		 	 columns: [
		  		{ title:'도서',
		          render:function(data,type,row){
					var html = '<img src='+row.img+'>' 
						html+= row.title+'<br>'; 
					    html+= row.author+'<br>';
					    html+= row.publisher+'<br>';
					    html+= row.isbn;
					return html;
				  }
				}
		  	]
		  });
			
		});
}


function kakaoBookImg(str){
		var img;
		var key ='15b0f156b4d728ec98c5b8c31a0d9193';
		$.ajax({
			method:"GET",
			url:"https://dapi.kakao.com/v3/search/book",
			data:{query:str},
			headers:{Authorization:"KakaoAK "+key},
			async:false,
			success:function(data){
				img = data.documents[0].thumbnail;
			}
		})
		return img;
}














	
	
	
	
	
	
	
	