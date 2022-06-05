function bookSearchTotal(){
	var table = $("#bookSearchTotal").DataTable();
	table.destroy();
//	var str = $("#queryStr").val();
//	var sizeNum = $("select option:selected").eq(1).val();
	var jsonSource = [];
	var html='';
	$.ajax({
		method:"GET",
		url:"http://localhost:9200/book_info/_search",
		data:{"from":"0","size":"5000"},
//		headers:{
//			"Authorization" : "Basic " + btoa("GD"+":"+"gd1234")
//		},
		success:function(res){
			 var data = res.hits.hits;
			console.log("--★★★★★★--");
			jsonSource.splice(0, jsonSource.length);
			for(var i=0; i<data.length;i++){
//				jsonSource.push(JSON.parse(data[i]._source.status_code));
				jsonSource.push(data[i]._source)
			}
			 $("#bookSearchTotal").DataTable({
			//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
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
		  		{ data: 'title' }
		  	]
		  });
		}
	})
//		.done(function(res){
//			 var data = res.hits.hits;
//			 console.log(data)
//			jsonSource.splice(0, jsonSource.length);
//			for(var i=0; i<data.length;i++){
////				jsonSource.push(JSON.parse(data[i]._source.status_code));
//				jsonSource.push(data[i]._source)
//				html += '<tr>';
//				html += '<td>'+jsonSource[i].title+'<br>'+jsonSource[i].author+'</td>';
//				html += '</tr>';
//			}
//				console.log(jsonSource[0].title)
//				$("#resultList").empty();
//				$("#resultList").append(html);
//		});
	
	
}

function bookSearchTotalTest(){
    $("#"+table_id).DataTable({
    	//https://datatables.net/reference/option/language
    		"language": {
            "emptyTable": "검색을 해주시기 바랍니다.",
            "lengthMenu": "페이지당 _MENU_ 개씩 보기",
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
        pagingType: "simple_numbers", // 페이징 타입 설정 : simple, simple_numbers, full_numbers 등
    	ajax : {
                    "url":"/getUserList.do",
                    "type":"POST",
                    "data": function (d) {
                        d.userStatCd = "NR";
                    }
                },
                columns : [
                    {data: "email"},
                    {data: "fullNmKr"},
                    {data: "userStatCd"},
                    {data: "superUser"}
                ]	
	
    });

	
}