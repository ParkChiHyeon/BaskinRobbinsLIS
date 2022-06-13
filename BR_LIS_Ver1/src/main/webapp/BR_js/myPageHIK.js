$(document).ready(function() {
    $('#dataTableHIK').DataTable({
		"language": {
            "emptyTable": "데이터 없음.",
            "lengthMenu": "_MENU_개씩",
            "info": "대출현황건수 : _TOTAL_건",
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
        
        lengthChange: false, 
        searching: false, 
        ordering: false, 
        info: true, 
        paging:false,
        order: [],
		lengthMenu: [  ], 
        pagingType:  ""
    
    });
} );

var id = document.getElementById("lend").value;
console.log(id);