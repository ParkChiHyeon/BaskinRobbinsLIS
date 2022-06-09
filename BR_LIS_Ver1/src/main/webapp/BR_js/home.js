function noticeList(){
	var html='';
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"https://f087-211-197-28-137.jp.ngrok.io/notice_board/_search",		
		data:JSON.stringify({"from":"0","size":"7","sort":{["notice_seq"]:"desc"}}),
		dataType:"json",
		success:function(res){
			 var data = res.hits.hits;
		for(let i=0;i<data.length;i++){
				html+='<li><span class="noticex"><a href="./detailnotice.do?seq='+data[i]._source.notice_seq+'">'+data[i]._source.title+'</a><span class="date">'+data[i]._source.regdate.substr(0,10)+'</span></li>'
			}
				$("#noticeList").append(html);
		}
	})
}

function faqList(){
	var html='';
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"https://f087-211-197-28-137.jp.ngrok.io/faq_board/_search",		
		data:JSON.stringify({"from":"0","size":"7","sort":{["faq_seq"]:"desc"}}),
		dataType:"json",
		success:function(res){
			 var data = res.hits.hits;
		for(let i=0;i<data.length;i++){
				html+='<li><span class="noticex"><a href="./detailfaq.do?seq='+data[i]._source.faq_seq+'">'+data[i]._source.title+'</a></li>'
			}
				$("#faqList").append(html);
		}
	})	
}

function homeNewBook(){
	var html='';
	$.ajax({
	  url : "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbclgusv10031002&QueryType=ItemNewAll&MaxResults=8&start=1&SearchTarget=Book&output=js&Version=20131101",
	  dataType : "jsonp",
	  jsonp : "callback",
	  success: function(data){
			for(let i=0;i<data.item.length;i++){
			html+='<li><a href="'+data.item[i].link+'"target="_blank"><img src="'+data.item[i].cover+'" height=72px; title="신규도서 - '+data.item[i].title+'"></a></li>'
		}
			$(".mainSvc").append(html);
	  }
	})
}




