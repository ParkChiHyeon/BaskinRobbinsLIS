function noticeList(){
	var jsonSource=[];
	var html='';
	$.ajax({
		method:"POST",
		contentType:"application/json;",
		url:"https://f087-211-197-28-137.jp.ngrok.io/notice_board/_search",		
		data:JSON.stringify({"from":"0","size":"7","sort":{["notice_seq"]:"desc"}}),
		dataType:"json",
		success:function(res){
			 var data = res.hits.hits;
			jsonSource.splice(0, jsonSource.length);
			for(let i=0; i<data.length;i++){
				jsonSource.push(data[i]._source)
			}
			}
		})
		.done(function(){
			for(let i=0;i<jsonSource.length;i++){
				html+='<li><span class="noticex"><a href="./detailnotice.do?seq='+jsonSource[i].notice_seq+'">'+jsonSource[i].title+'</a><span class="date">'+jsonSource[i].regdate.substr(0,10)+'</span></li>'
			}
				$(".noticeList").append(html);
		});
}

function homeNewBook(){
	var jsonSource=[];
	$.ajax({
	  url : "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbclgusv10031002&QueryType=ItemNewAll&MaxResults=8&start=1&SearchTarget=Book&output=js&Version=20131101",
	  dataType : "jsonp",
	  jsonp : "callback",
	  success: function(data){
		
		jsonSource.splice(0, jsonSource.length);
		for(let i=0; i<data.length;i++){
			jsonSource.push(data[i]._source)
		}
	  }
	})
	.done(function(){
		for(let i=0;i<jsonSource.length;i++){
			html+='<li><span class="noticex"><a href="./detailnotice.do?seq='+jsonSource[i].notice_seq+'">'+jsonSource[i].title+'</a><span class="date">'+jsonSource[i].regdate.substr(0,10)+'</span></li>'
		}
			$(".noticeList").append(html);
	});
}