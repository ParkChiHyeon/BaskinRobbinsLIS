$(document).ready(function() {
    $('#dataTable').DataTable({
		"language": {
            "emptyTable": "데이터 없음.",
            "lengthMenu": "_MENU_개씩",
            "info": "현재 _START_ - _END_ / _TOTAL_건",
            "infoEmpty": "데이터 없음",
            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
            "search": "검색: ",
            "zeroRecords": "데이터가 없습니다.",
            "loadingRecords": "loading...",
            "processing":     "잠시만 기다려 주세요...",
            "paginate": {
                "next": "다음",
                "previous": "이전"
            }
        },
        
        lengthChange: true, 
        searching: true, 
        ordering: true, 
        info: true, 
        paging:true,
        order: [ [ 0, "desc"] ],
		lengthMenu: [ 5, 10, 15, 20 ], 
        pagingType: "simple_numbers" 
    
    });
} );


function warehouseBookList(){
	var id = $("#inputId").val();
	$("#dataTable").DataTable().destroy(); 
	$.ajax({
		url:"./warehouseBookList.do",
		type:"get",
		success:function(data){  
			$('#dataTable').DataTable({
				"language": {
		            "emptyTable": "데이터 없음.",
		            "lengthMenu": "_MENU_개씩",
		            "info": "현재 _START_ - _END_ / _TOTAL_건",
		            "infoEmpty": "데이터 없음",
		            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
		            "search": "검색: ",
		            "zeroRecords": "데이터가 없습니다.",
		            "loadingRecords": "loading...",
		            "processing":     "잠시만 기다려 주세요...",
		            "paginate": {
		                "next": "다음",
		                "previous": "이전"
		            }
		        },
				data:data,
				columns: [ { data: 'book_serial'}, 
					{ data: 'title' }, 
					{ data: 'author' }, 
					{ data: 'publisher',"defaultContent": "출판사 미상"}, 
					{ data: 'isbn' }, 
					{ data: 'nm' }],
					lengthChange: true, 
			        searching: true, 
			        ordering: true, 
			        info: true, 
			        paging:true,
			        order: [ [ 5, "desc"] ],
					lengthMenu: [ 5, 10, 15, 20 ], 
			        pagingType: "simple_numbers" 
			});
		},
		error:function(){
			alert("통신실패");
			}
	})
}	
	
function supplyBookList(){
	var id = $("#inputId").val();
	$("#dataTable").DataTable().destroy();  
	$.ajax({
		url:"./supplyBookList.do",
		type:"get",
		success:function(data){ 
			$('#dataTable').DataTable({
				"language": {
		            "emptyTable": "데이터 없음.",
		            "lengthMenu": "_MENU_개씩",
		            "info": "현재 _START_ - _END_ / _TOTAL_건",
		            "infoEmpty": "데이터 없음",
		            "infoFiltered": "( _MAX_건의 데이터에서 필터링됨 )",
		            "search": "검색: ",
		            "zeroRecords": "데이터가 없습니다.",
		            "loadingRecords": "loading...",
		            "processing":     "잠시만 기다려 주세요...",
		            "paginate": {
		                "next": "다음",
		                "previous": "이전"
		            }
		        },
				data:data,
				columns: [ { data: 'book_serial'}, 
					{ data: 'title' }, 
					{ data: 'author' }, 
					{ data: 'publisher',"defaultContent": "출판사 미상"}, 
					{ data: 'isbn' }, 
					{ data: 'nm' }],
					lengthChange: true, 
			        searching: true, 
			        ordering: true, 
			        info: true, 
			        paging:true,
			        order: [ [ 5, "desc"] ],
					lengthMenu: [ 5, 10, 15, 20 ], 
			        pagingType: "simple_numbers" 
			});
		},
		error:function(){
			alert("통신실패");
			}
	})
}		

