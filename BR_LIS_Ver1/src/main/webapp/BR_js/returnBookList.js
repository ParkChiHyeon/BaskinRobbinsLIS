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
        order: [ [ 6, "desc"] ],
		lengthMenu: [ 5, 10, 15, 50 ], 
        pagingType: "simple_numbers" 
    
    });
} );

function lendingBookListAdmin(){
	var id = $("#inputId").val();
	$("#dataTable").DataTable().destroy(); // 기존 테이블 박살 
	$.ajax({
		url:"./lendingBookListAdmin.do",
		type:"get",
		success:function(data){ //return해주는 변수명은 data여야 한다. 
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
				columns: [ { data: 'lending_seq'}, 
					{ data: 'title' }, 
					{ data: 'book_serial' }, 
					{ data: 'member_code' }, 
					{ data: 'lending_date' }, 
					{ data: 'end_date' }, 
					{ data: 'null',"defaultContent": " "}],
					lengthChange: true, 
			        searching: true, 
			        ordering: true, 
			        info: true, 
			        paging:true,
			        order: [ [ 5, "desc"] ],
					lengthMenu: [ 5, 10, 15, 50 ], 
			        pagingType: "simple_numbers" 
			});
		},
		error:function(){
			alert("통신실패");
			}
	})
}		
function lendingBookListBanNapAdmin(){
	var id = $("#inputId").val();
	$("#dataTable").DataTable().destroy(); // 기존 테이블 박살 
	$.ajax({
		url:"./returnBookListBanNap.do",
		type:"get",
		success:function(data){ //return해주는 변수명은 data여야 한다. 
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
				columns: [ { data: 'lending_seq'}, 
					{ data: 'title' }, 
					{ data: 'book_serial' }, 
					{ data: 'member_code' }, 
					{ data: 'lending_date' }, 
					{ data: 'end_date' }, 
					{ data: 'back_date'}],
					lengthChange: true, 
			        searching: true, 
			        ordering: true, 
			        info: true, 
			        paging:true,
			        order: [ [ 5, "desc"] ],
					lengthMenu: [ 5, 10, 15, 50 ], 
			        pagingType: "simple_numbers" 
			});
		},
		error:function(){
			alert("통신실패");
			}
	})
}		