var jsonSource = [];

$(document).ready(function(){
//	var table = $("#noticeBoardTable").DataTable();
//	table.destroy();
	
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"https://f087-211-197-28-137.jp.ngrok.io/notice_board/_search",		
		data:JSON.stringify({"from":"0","size":"10000","sort":{"notice_seq":"desc"}}),
		dataType:"json",
		success:function(res){
			 var data = res.hits.hits;
			console.log("--공지사항이동--");
			jsonSource.splice(0, jsonSource.length);
			for(let i=0; i<data.length;i++){
				jsonSource.push(data[i]._source)
			}
		}
	})
		.done(function(){
			 $("#noticeBoardTable").DataTable({
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
				{ 	
					title:'글번호',
					data:'notice_seq',
				},
		  		{ 
				 	title:'제목',
		          	render:function(data,type,row){
					var	html= '<a href="./detailnotice.do?seq='+row.notice_seq+'">'+row.title+'</a>'; 
					return html;
				  }
				}
		  	]
		  });
			
		});
});
