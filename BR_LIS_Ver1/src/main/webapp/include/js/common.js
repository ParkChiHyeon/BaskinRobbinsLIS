/**
 * Common Js
 */
// Init
(function($){
	$(document).ready(function(){
		//readonly backspace 방지
		$('input[readonly]').keydown(function(e) {
		    if ((e.which && e.which === 8) || (e.keyCode && e.keyCode === 8)) {
		    	e.preventDefault();
		    	return false;
		    }
		});
		// 숫자만 입력
		/* 18-09-28 모바일의 경우 키 전체가 먹지않아 주석처리
		$(".numeric").css("ime-mode", "disabled").keydown(function(e) {
			e = e || window.e;
			var keyId = (e.which) ? e.which : e.keyCode;

			if( !((keyId >= 48 && keyId <= 57) || (keyId >= 96 && keyId <= 105) ||
					keyId == 8 || keyId == 9 || keyId == 46 || keyId == 37 || keyId == 39 )){
				e.preventDefault();
				return false;
			}
		});
		*/
	});
}(jQuery));



//Null 체크
function isEmpty( val ){
	if(val==null || typeof(val) === 'undefined' || $.trim(val) == '') {
		return true;
	}
	return false;
}


function isNotEmpty(val){
	return !isEmpty(val);
}


// 검색어 엔터키 처리
function fnSearchEnter(e){
	if((e.keyCode || e.which) == 13){
		fnSearch();
		e.preventDefault();
	}
}


function fnContentFileDownload(sFileNm, oFileNm) {
	var iframe = document.getElementById("hiddenFrame");
	iframe.src ="/content/fileDownload.do?sFileNm="+sFileNm+"&oFileNm="+oFileNm;
}

function shareTwitter(msg, url) {
	var shareUrl = "http://twitter.com/share?text=" + encodeURIComponent(msg) + "&url=" + encodeURIComponent(url);
	var objwin = window.open(shareUrl, 'twitterPopup', 'width=600,height=400,scrollbars=no,menubar=0,toolbar=0');
	if (objwin) {
		objwin.focus();
	}
	return false;
}

function shareFaceBook(msg, url) {
	var shareUrl = "https://www.facebook.com/sharer/sharer.php?t=" + encodeURIComponent(msg) + "&u=" + encodeURIComponent(url);
	var objwin = window.open(shareUrl, 'facebookPopup', 'width=600,height=300,scrollbars=no,menubar=0,toolbar=0');
	if (objwin) {
		objwin.focus();
	}
	return false;
}
