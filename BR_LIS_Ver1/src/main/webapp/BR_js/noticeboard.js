function checkAll(bool){
	console.log(bool);
	var chs= document.getElementsByName("chkBox");
	for(let i=0; i<chs.length; i++){
		chs[i].checked=bool;
	}
}
/*
	하위 checkbox의 선택된 갯수를 판단하는 function	
*/

function chsConfirm(){
	var chs=document.getElementsByName("chkBox");
	var cnt=0;
	for(let i=0; i<chs.length;i++){
		if(chs[i].checked){
			cnt++;
		}
	}
	return cnt;
}

/*
	하위에 있는 모든 checkbox가 체크가 된다면 모두 체크 
	아닌경우 thead에 있는 checkbox를 false로 만든다
*/
window.onload = function(){
	console.log("js onload");
	var chkBox= document.getElementsByName("chkBox");
	var allCheck=document.getElementById("allCheck");
	
	for(let i=0; i<chkBox.length;i++){
		chkBox[i].onclick=function(){
			console.log(chkBox[i].value);
			if(chkBox.length==chsConfirm()){
				allCheck.checked=true;
			}else{
				allCheck.checked=false;
			}
		}
	}
}


function multiDeleteFAQ(){
	chsSubmit();
}


function chsSubmit(){
	if(chsConfirm()>0){
	swal({
        title: "다중삭제",
        text: "삭제를 진행하시겠습니까?",
        type: "warning",
        showCancelButton: true,
        /*confirmButtonColor: "#DD6B55",*/
		confirmButtonClass:"btn-danger" ,
        confirmButtonText: "예",
        cancelButtonText: "아니오",
        closeOnConfirm: true,
        closeOnCancel: false 
    },
    function(isConfirm) {
        if (isConfirm) {
            swal("삭제!", "작성글이 삭제 되었습니다.", "success");
			submitForm();
        } else {
            swal("취소", "작성글 삭제를 취소합니다. :)", "error");
        }
    }
	);
	}else{
		swal('','선택된글이 없습니다');
	}
	console.log("chsSubmit 마지막라인")
	return false;
}


//sweetalert 처리방식
//https://stackoverflow.com/questions/33414259/response-from-sweet-alert-confirm-dialog
//https://sweetalert.js.org/ => confirm을 사용하여 처리=>callback

/*
 js document를 통해서 submit()함수를 실행
기존형태 : input[type='submit'] -> <form action="">
*/
function submitForm(){
	document.getElementById("formBoard").submit();
}
	