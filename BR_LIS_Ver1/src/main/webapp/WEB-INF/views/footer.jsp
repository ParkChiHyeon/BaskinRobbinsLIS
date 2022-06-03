<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>
$(function(){
	$("#goBtn").click(function(){
		var url = $("#relateSite").val();
		if(!$("#relateSite").val()){
			alert("관련기관을 선택하세요.");
			$("#relateSite").focus();
			return false;
		}else{
			window.open(url,"_blank");
		}
	});
});
</script>
<body>
<!--footer-->
	<div id="footer">
		<div id="footbody">
			<div class="footbottom">
				<ul class="futil">
					<li><a href="/jungang/10092/40014/content.do"><span class="fb">개인정보보호처리방침</span></a></li>
					<li><a href="/jungang/10093/40015/content.do">이메일주소무단수집거부</a></li>
					<li class="mobileHide"><a href="/jungang/10091/40013/content.do">사이트맵</a></li>
				</ul>
				<div class="famSite">
					<select name="relateSite" id="relateSite" title="관련기관 바로가기 선택">
						<option value="">관련기관 바로가기</option>
						<option value="https://nl.go.kr/">국립중앙도서관</option>
						<option value="http://www.nlcy.go.kr/">국립어린이청소년도서관</option>
						<option value="http://www.nanet.go.kr/">국회도서관</option>
						<option value="http://www.dlibrary.go.kr/">국가전자도서관</option>
						<option value="http://www.nl.go.kr/nill/">책바다</option>
						<option value="https://www.nld.go.kr/">국립장애인도서관</option>
					</select>
					<a href="#link" id="goBtn" title="새 창 열림"><img src="./include/image/jungang/common/btn_go.png" alt="바로가기"/></a>
				</div>
				<div class="mid">
					<div class="fAdd">
						<p class="fam">
							(우)08505 서울시 금천구 가산디지털2로 115 대륭3차(가산동)
							<img src="./include/image/jungang/common/icon_tel.png" alt="전화번호" class="ml30"/> (대출반납)  02-2108-5900, 5901
							<img src="./include/image/jungang/common/icon_fax.png" alt="팩스번호:" class="ml15" /> 02-2108-5909
						</p>
						<p>Copyrightⓒ woowang Library. All Rights Reserved.</p>
					</div>
<!-- 					<div class="flogo"></div> -->
				</div>
			</div>
		</div>
<!-- 		<div> -->
<!-- 			<p id="post-result"></p> -->
<!-- 		</div> -->
	</div>
	<!--footer-->
</body>
</html>