// JavaScript Document

var GlobalHwak=0;

// Top Menu. belong script for GNB
function initNavigation2(seq,subq) {
	var isGnbImg = 0; //GNB menu item style is image constantNo is 1, else 0;
	/*if($('#gnb1 > a').html().indexOf('img src') > 0) {
		isGnbImg = 1;
	}
	else { isGnbImg = 0}*/

	if(isGnbImg == 0) {
		$('#gnb'+seq).addClass('fbC');
	}

	nav = document.getElementById("topmenu");
	nav.menu = new Array();
	nav.current = null;
	nav.menuseq = 0;
	navLen = nav.childNodes.length;

	//비쥬얼 이미지 변경 시작 //

//	if(seq>1) {
//		$('#subvisuala > img').attr('src',$('#subvisuala > img').attr('src').replace('01','0'+seq));
//	}
	//비쥬얼 이미지 변경 끝//


	allA = nav.getElementsByTagName("a");
	for(k = 0; k < allA.length; k++) {
		nav.onmouseover = allA.item(k).onfocus = function () {
			nav.isOver = true;
		}
		nav.onmouseout = allA.item(k).onblur = function () {
			nav.isOver = false;
			setTimeout(function () {
				if (nav.isOver == false) {
					 if(nav.current) {
						 if(isGnbImg==1) {
							menuImg = nav.current.childNodes.item(0);
							menuImg.src = menuImg.src.replace("_on.png", ".png");
						 }
						 else {
						 }
						if (nav.current.submenu){
							$('#topmenu>li').removeClass("fbl");
							$('#indexNavi').css('height','82px');
							$('#indexNavi').css('overflow','hidden');
							$('#indexNavi').css('border-bottom','0 none');
							$('#topmenu>li>ul').hide();
						}
						nav.current = null;
						if (nav.menu[seq]) 	{
							//	nav.menu[seq].onmouseover();
							menuImg = nav.menu[seq].childNodes.item(0);

							 if(isGnbImg==1) {
								if(menuImg.src.indexOf("_on")<0) {
									menuImg.src = menuImg.src.replace(".png", "_on.png");
								}
							 }
						}
					}
				}
			}, 200);
		}
	}

	for (i = 0; i < navLen; i++) {
		navItem = nav.childNodes.item(i);
		if (navItem.tagName != "LI")
			continue;

		navAnchor = navItem.getElementsByTagName("a").item(0);
		navAnchor.submenu = navItem.getElementsByTagName("ul").item(0);

		navAnchor.onmouseover = navAnchor.onfocus = function () {

			if (nav.current) {

				if(isGnbImg==1) {
					menuImg = nav.current.childNodes.item(0);
					menuImg.src = menuImg.src.replace("_on.png", ".png");
				}
				else {
				}

				$('#topmenu>li').removeClass("fbl");
				if (nav.current.submenu){
					$('#indexNavi').css('height','50px');
					$('#indexNavi').css('overflow','hidden');
					$('#indexNavi').css('border-bottom','0 none');
					$('#topmenu>li>ul').hide();
				}
				nav.current = null;
			}
			if (nav.current != this) {
				if(isGnbImg==1) {
					menuImg = this.childNodes.item(0);
						if(menuImg.src.indexOf("_on")<0) {
							menuImg.src = menuImg.src.replace(".png", "_on.png");
						}
				}
				if (this.submenu)
					{
						$('#indexNavi').css('height','100px');
						$('#indexNavi').css('overflow','visible');
						var lefTmp= (($(document).width()-990)/2 -4)*(-1) +"px";
						var xlefTmp= (($(document).width()-990)/2)+50 +"px";
						var widthTmp = $(document).width()-1;
						$('#submenuArea').css('width',widthTmp);
						$('#submenuArea').css('left',lefTmp);
						$('#subvisuala').css('left',xlefTmp);
						$('#topmenu>li>ul').css('display','block');
						$(this).parent(this).addClass("fbl");
						//2depth 현재 메뉴 처리
						if(subq !=0) {
						$('#gnb'+seq+'_'+ subq+' > a').addClass('current');
						}

					}
				nav.current = this;
			}
			nav.isOver = true;
		}
		nav.menuseq++;
		nav.menu[nav.menuseq] = navAnchor;
	}
	if (nav.menu[seq]) 	{
		if(isGnbImg==1) {
			menuImg = nav.menu[seq].childNodes.item(0);
			if(menuImg.src.indexOf("_on")<0) {
				menuImg.src = menuImg.src.replace(".png", "_on.png");
			}
		}
	}
}
// Top Menu. above are for GNB

function initNavigation3(n1,seq,subq) {
	 $('#lnb > li > ul').hide();
	 var sub2=seq-1;
	 var sub3=subq-1;
	 var lcode = "#lnb" + n1 + "_" + seq;
	 $(lcode+ ' > a').addClass('current');
	 $('#lnb' + n1 +'_'+ seq +' > a').addClass('current');
	 if(subq>0) {
	  $('#lnb' + n1+'_' + seq+' > ul').hide();  // 서브를 탭으로 처리했음.
	 }

	//lnb toggle
	var cnt=0;
	var prev;
	$('#lnb>li>a').click(function() {
	$('#lnb>li>a').removeClass('current');
	$(this).addClass('current');
	if($(this).parent(this).parent().children().find('ul')){

		if(cnt==0) {
			$(this).parent(this).parent().children().find('ul').hide();
			if(seq!=1){
			$(this).parent(this).find('ul').hide();  // 서브를 탭으로 처리했음.
			}
			else
			{
			$(this).parent(this).find('.depth2').css('display','block');  // 서브를 탭으로 처리했던것을 원복
			}
			cnt=1;
		}
		else {
			$(this).parent(this).parent().children().find('ul').hide();
			if(seq!=1){
			$(this).parent(this).find('ul').hide();
			}
			else
			{
			$(this).parent(this).find('.depth2').css('display','block');  // 서브를 탭으로 처리했던것을 원복
			}
			cnt=0;
		}
		if(seq!=1){
			if(prev!=$(this)) $(this).parent(this).find('ul').hide();  // 서브를 탭으로 처리했음.
		}
		else
		{
		if(prev!=$(this)) $(this).parent(this).find('.depth2').css('display','block');  // 서브를 탭으로 처리했음.
		}
		prev=$(this);
	}
	});


	//3depth 메뉴 레이어 띄우기
	$('.depth2 > LI').hover(
		function () {
			$(this).find('UL').show();
			$(this).find('UL').css('top',$(this).position().top);
		},
		function () {
			$(this).find('UL').hide();
		}
	);


	// 롤 오버
	$('#lnb img').each(function() {
		$(this).mouseover(function() {
			if ($(this).attr('src').match('_off')) {
				$(this).css('cursor', 'pointer');
				$(this).attr('src', $(this).attr('src').replace('_off', '_on'));
				$(this).mouseout(function() {
					$(this).attr('src', $(this).attr('src').replace('_on', '_off'));
				});
			}
		});
	});
}
// above are for LNB till GNB



function initMenu(n1,n2,n3,n4,n5,mth) {
	if (n1==0) {
		initNavigation2(0,0);
		GlobalHwak=2;
		}
	else {
		initNavigation2(n1,n2);
//		if(n1 == 1) {
//			GlobalHwak=2;
//			docTitleNavi(n1,n2,n3,n4,n5,mth);
//			makeTab7(n1,n2,n3,n4,n5,mth);
//		}
//		else if(n1 != 1){
			GlobalHwak = 0;
			docTitleNavi(n1,n2,n3,n4,n5,mth);
			makeLnb(n1,n2,n3,n4,n5,mth);
			initNavigation3(n1,n2,n3,n4,n5,mth);
//		}
	}


	//making Tab with qued by the class 'mkTab', documnet were loaded..
	//makeTab
	if($('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+'_'+n5).parent('ul').hasClass('MkTab')) {
		makeTab5(n1,n2,n3,n4,n5,mth);
		tabAutoWidth2(this);
	}
	else if($('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4).parent('ul').hasClass('MkTab')) {
		makeTab4(n1,n2,n3,n4,n5,mth);
		tabAutoWidth2(this);
	}
	else if($('#gnb'+n1+'_'+n2+'_'+n3).parent('ul').hasClass('MkTab')) {
		makeTab3(n1,n2,n3,n4,n5,mth);
		tabAutoWidth2(this);
	}
	//tabAutoWidth(2);

	if ($('#lnb .current').parent().children('.MkSub').hasClass('MkSub')) {
		$('#lnb .current').parent().children('.MkSub').show();
	}
}


function makeTab7(n1,n2,n3,n4,n5,mth){
	if(n2 > 0){
		var autoTab = $('#gnb'+n1+' > .MkTab');
		$('#ttab3').html(autoTab.html().replace(/gnb/g,'tnb'));
		tabAutoWidth2(this);
		$('#tnb'+n1+'_'+n2).addClass('choiced');
		$('.ttabWrap').show();
		$('#bookCate h2').text($('.tabType5 .choiced').text());
	}
	$('.naviandtitle').hide();
	$('#contleft').hide();
	$('#bookCategory').show();
	$('#bookCate h2').show();
	if(n3 == 2){
		$('#bookCate h2').text('도서검색결과');
	}
}
function makeTab5(n1,n2,n3,n4,n5,mth){
	var autoTab = $('#lnb'+n1+'_'+n2+'_'+n3+'_'+n4+' > .MkTab');
	$('#ttab3').html(autoTab.html().replace(/lnb/g,'tnb'));
	$('.ttabWrap').show();
}
function makeTab4(n1,n2,n3,n4,n5,mth){
	var autoTab = $('#lnb'+n1+'_'+n2+'_'+n3+' > .MkTab');
	$('#ttab3').html(autoTab.html().replace(/lnb/g,'tnb'));
	$('.ttabWrap').show();
}
function makeTab3(n1,n2,n3,n4,n5,mth){
	var autoTab = $('#lnb'+n1+'_'+n2+' > .MkTab');
	$('#ttab3').html(autoTab.html().replace(/lnb/g,'tnb'));
	$('.ttabWrap').show();
}

function tabAutoWidth2(e) {
		$('#ttab3 a').css('text-decoration','none');

		var contWidth = $('#contents').width(); //컨텐츠 영역 폭 입력
		var apadding = 7;  // a태그가 가지는 패딩 값 입력
		var abording = 0;  // a태그가 가지는 패딩 값 입력
		var tabMargin = 2; // 탭간격 입력
		var ltab =  document.getElementById('ttab3');
		allB = ltab.getElementsByTagName("a");
		var cntTab = allB.length; // 탭의 갯수 계산
		var tabWidth=(contWidth+tabMargin)/cntTab-(apadding+abording+tabMargin)*2;
		$('#ttab3 a').css('min-width',tabWidth);

}
function tabAutoWidth(e) {
		$('#ttab3 a').css('text-decoration','none');

		var contWidth = $('#contents').width(); //컨텐츠 영역 폭 입력
		var apadding = 5;  // a태그가 가지는 패딩 값 입력
		var tabMargin = 8; // 탭간격 입력
		var comTab =5 ;
		if(e==1) {
			contWidth = 780;
			tabMargin = 10;
			comTab = 4;
		}
		var ltab =  document.getElementById('ttab3');
		allB = ltab.getElementsByTagName("a");
		var cntTab = allB.length; // 탭의 갯수 계산

		var cntText='';  //이하단은 탭의 텍스트 길이 평균값 계산
		$('#ttab3 a').each(function(index, element) {
			cntText= cntText+$(this).text();
		});
		if(cntText.length/cntTab <4 && cntTab <7 ) {
			//평균 문자열의 길이가 4보다 작다면.
			comTab = cntTab;
			apadding = 19;
			var tabWidth = (contWidth-apadding*2*cntTab-tabMargin*cntTab) / cntTab;
		}

		if(cntTab < comTab) {
			var tabWidth = (contWidth-apadding*2*cntTab-tabMargin*cntTab) / cntTab;
			$('#ttab3 a').css('width',tabWidth);
		}
		else {
			$('#ttab3 a').css('min-width',tabWidth);
			$('#ttab3 a').css('padding-left','19px');
			$('#ttab3 a').css('padding-right','19px');
		}
}

// ttab3 version 0.3 2013.09.06
// *simplfy code.
// *added makeTab function. that make ttab3 ul with in read GNB structure;
// Powered by Cho hyun jung
function ttab3()
{

    $('#ttab3 a').mouseover(function(){
		if(!$(this).parent().hasClass('on')){
		$(this).parent().addClass('on');}
    });

    $('#ttab3 a').focus(function(){
		if(!$(this).parent().hasClass('on')){
		$(this).parent().addClass('on');}
    });
    $('#ttab3 a').mouseout(function(){
		if($(this).parent().hasClass('on')){
		$(this).parent().removeClass('on');}
    });
    $('#ttab3 a').blur(function(){
		if($(this).parent().hasClass('on')){
		$(this).parent().removeClass('on');}
    });
    $('#ttab3 a').click(function(){
		ltab = document.getElementById("ttab3");
		allB = ltab.getElementsByTagName("a");
			for(var j = 0; j < allB.length; j++) {
				allB.item(j).parentNode.className = '';
				}

			$(this).parent().addClass('choiced');
    });
}


//Doc Title & navigation Changer
//ver 0.34
//This version improved, more detail navigation tracing, and page title, Document title were sperated for accessbillity.
//Powered by Cho Hyunjung
function docTitleNavi(n1,n2,n3,n4,n5,mth) {
	var navi;
	var pageTitle,docTitle,subDocTitle, pSort;
	var pName = window.location.href;
	var siteName = ""; //Sub Lib Name

	var basicTitle = "woowang Library";
	var homeUrl = $("#logo > a").attr("href");

	if(pName.indexOf('List.do')>0) { pSort = "목록보기";}
	else if(pName.indexOf('View.do')>0) { pSort = "상세보기";}
	else if(pName.indexOf('Write.do')>0) { pSort = "작성하기";}
	else if(pName.indexOf('Edit.do')>0) { pSort = "수정하기";}
	else if(pName.indexOf('/book/bookSearchResult.do')>0) { pSort = "도서검색결과";}
	else if(pName.indexOf('/member/join.do')>0) { pSort = "회원유형선택";}
	else if(pName.indexOf('/member/joinAgree.do')>0) { pSort = "약관동의";}
	else if(pName.indexOf('/member/joinCertify.do')>0) { pSort = "본인확인";}
	else if(pName.indexOf('/member/joinWrite.do')>0) { pSort = "회원정보입력";}
	else { pSort ="";}

	var gnb1Text; // determine gnb menu item(0) is image or Text
	if($('#gnb'+n1+ '>a ').html().indexOf('img src') <0) {
		var gnb1Text = $('#gnb'+n1+ '>a ').text();
	}
	else {
		var gnb1Text = $('#gnb'+n1+ '>a > img').attr('alt');
	}


	if (n5 > 0 ){
		navi = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> > " + $('#gnb'+n1+'_'+n2+' > a').text() + " > " + $('#gnb'+n1+'_'+n2+'_'+n3+' > a').text() + " > " + $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+' > a').text() + " > <strong>" + $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+'_'+n5+' > a').text() + "</strong></span>";

		pageTitle = $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+'_'+n5+' > a').text();
		docTitle = pageTitle + " "+ pSort;
		if($('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4).children('ul').hasClass('MkTab')) {
			 $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+'_'+n5).addClass('choiced');
		}
	}

	else if (n4 > 0 && n5==0){
		navi = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> > " + $('#gnb'+n1+'_'+n2+' > a').text() + " > " + $('#gnb'+n1+'_'+n2+'_'+n3+' > a').text() + " > <strong>" + $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+' > a').text() + "</strong></span>";

		pageTitle = $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4+' > a').text();
		docTitle = pageTitle + " "+ pSort;
		if($('#gnb'+n1+'_'+n2+'_'+n3).children('ul').hasClass('MkTab')) {
			 $('#gnb'+n1+'_'+n2+'_'+n3+'_'+n4).addClass('choiced');
		}
	}

	else if (n3 > 0 && n4==0){
		navi = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> > " + $('#gnb'+n1+'_'+n2+' > a').text() + " > <strong>" + $('#gnb'+n1+'_'+n2+'_'+n3+' > a').text() + "</strong></span>";
		pageTitle =  $('#gnb'+n1+'_'+n2+'_'+n3+' > a').text();
		if($('#gnb'+n1+'_'+n2+'>ul').hasClass('MkTab')) { pageTitle = $('#gnb'+n1+'_'+n2+' > a').text() + ' ('+ pageTitle +')';} //탭 괄호 처리
		docTitle = pageTitle + " "+ pSort;
		if($('#gnb'+n1+'_'+n2).children('ul').hasClass('MkTab')) {
			 $('#gnb'+n1+'_'+n2+'_'+n3).addClass('choiced');
		}
	}

	else if (n2 > 0 && n3==0){
		navi = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> > <strong>" + $('#gnb'+n1+'_'+n2+' > a').text() + "</strong></span>";

		pageTitle = $('#gnb'+n1+'_'+n2+' > a').text();
		docTitle = pageTitle + " "+ pSort;
	}

	else if (n1 > 0 && n2==0){
		navi = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> >";

		pageTitle = $('#gnb'+n1+' > a').text();
		docTitle = pageTitle + " "+ pSort;
	}

	//navi = navi + '  <a href="#print" id="pprint"><img src="/include/image/booktown/common/icon_print.gif" alt="프린트" style="vertical-align:middle"/></a>';
	document.title =  docTitle + " : "+ basicTitle;
	$('.naviandtitle > h3').text(pageTitle);
	$('.naviandtitle > .navi').html(navi);
	var globalD1 = "<a href='"+homeUrl+"'><img src='/include/image/global/common/icon_home.gif' alt='홈' /></a> "+ gnb1Text + "<span class='mobileHide'> >";
}

//Make LnB
function makeLnb(n1,n2,n3,n4,n5,mth) {
	var userArea = 15;  //GNB에 있는 메뉴인지 여부를 판단하기 위해 부여하는 숫자.
	if (n1 < userArea){
		var h2Text; // determine gnb menu item(0) is image or Text
		if($('#gnb'+n1+ '>a ').html().indexOf('img src') <0) {
			var h2Text = $('#gnb'+n1+ '>a ').text();
		}
		else {
			var h2Text = $('#gnb'+n1+ '>a > img').attr('alt');
		}

		var h2html = h2Text;
		$('#contleft > h2').html(h2html);

		var tmp2 = $('#gnb'+n1+'> ul').html();
		tmp2=tmp2.replace(/gnb/g,'lnb');
		$('#lnb').html(tmp2);
		$('#lnb > li').each(function(index, element) {
			if(!$(this).attr('id')) {
				var tmpNo= $('#lnb > li').index(this) +1;
				$(this).attr('id','lnb' + tmpNo + '_0');
			}
		});
		$('#lnb > li > ul').addClass('submenu');
		$('#lnb'+n1+'_'+n2+'_'+n3+'>a').addClass('current');
	}
}


//Make LnB2 for Special Submenu hotSix. -_-;

function makeLnb2(n1,n2,n3,n4,n5,mth) {
	var userArea = 15;  //GNB에 있는 메뉴인지 여부를 판단하기 위해 부여하는 숫자.
	if (n1 < userArea){
		var ht2Text; // determine gnb menu item(0) is image or Text
		var ht2Text = $('#gnb'+n1+ '_'+n2 + '>a ').text();
		$('.hTitle2').html(ht2Text);
		var h2Text; // determine gnb menu item(0) is image or Text
		var h2Text = $('#gnb'+n1+ '_'+n2+ '_'+n3 + '>a ').text();
		$('#gnb'+n1+ '_'+n2+ '_'+n3 + '>a ').addClass('on'); //현재메뉴 표시
		$('#contleft > h2').html(h2Text);
		$('#middle').addClass('subTheme'+n2); // 분야별정보에서만 레이아웃 바뀜
		$('#middle').addClass('subTheme'); // 분야별정보에서만 레이아웃 바뀜

		var tmp3 = $('#gnb'+n1+ '_'+n2+'> ul').html();
		tmp3=tmp3.replace(/gnb/g,'g2nb');
		$('#subGnb > ul').html(tmp3);
		$('#subGnb > ul > li').each(function(index, element) {
			if(!$(this).attr('id')) {
				var tmpNo= $('#subGnb > li').index(this) +1;
				$(this).attr('id','g2nb' + tmpNo + '_0');
			}
		});
		$('#subGnb > ul > li > ul').hide();


		if(n3>0) {
			var tmp2 = $('#gnb'+n1+'_' + n2 +'_' + n3 + ' > ul').html();
			tmp2=tmp2.replace(/gnb/g,'lnb');
			$('#lnb').html(tmp2);
			$('#lnb > li').each(function(index, element) {
				if(!$(this).attr('id')) {
					var tmpNo= $('#lnb > li').index(this) +1;
					$(this).attr('id','lnb' + tmpNo + '_0');
				}
			});
			$('#lnb > li > ul').addClass('submenu');
			$('#lnb'+n1+'_'+n2+'_'+n3+'>a').css('text-decoration','none');
			$('#lnb'+n1+'_'+n2+'_'+n3+'>a').addClass('current');
		}
	}
}


var prev_view = "";
function sendview(value){
    if(prev_view != ""){
        $("#answer_" + prev_view).hide();
    }
    $("#answer_" + value).show();
    prev_view = value;
}


//창 크기 변화 재 계산
$(document).ready(function(e) {

	//상세보기 책커버 크기 자동 조절
	if($('.bookCover img').width() < 223) {
		if($('.bookCover img').width() > $('.bookCover img').height()*0.6656716417910448) {
			$('.bookCover img').css('width','223px');
		}
		else {
			$('.bookCover img').css('height','335px');
		}
	}
	// ContTabWrap 마감처리
	if ($('.ContTabWrap h2 a').length<2) {
		//$('.ContTabWrap .tabArea02 > h2 > a').css('width','778px');
		$('.ContTabWrap .tabArea02 > h2 > a').css('padding-right','390px');
		$('.ContTabWrap .tabArea02 > h2 > a').css('border-right','1px solid #e3e3e3');
	}

	//button 4 total Menu
	$('.btnTotal a').toggle(function(){
			$('#topmenu>li>ul').show();
			$('#indexNavi').css('height','100px');
			$('#indexNavi').css('overflow','visible');
			$('#topmenu>li>ul').css('display','block');
		},function(){
			$('#indexNavi').css('height','82px');
			$('#indexNavi').css('overflow','hidden');
			$('#indexNavi').css('border-bottom','0 none');
			$('#topmenu>li>ul').hide();
		});
		var lefTmp= (($(document).width()-990)/2 -4)*(-1) +"px";
		var xlefTmp= (($(document).width()-990)/2)+50+"px";
		var widthTmp = $(document).width()-1;
		$('#submenuArea').css('width',widthTmp);
		$('#submenuArea').css('left',lefTmp);
		$('#subvisuala').css('left',xlefTmp);


	//검색창 열고 닫기

	var DocHeight = $(document).height() + 180 + "px";
	$('#blackBG').height(DocHeight);
	$('#indexNavi').before("<div id='indexNavibefore'></div>");
			$('#indexNavibefore').hide();
	$('#btnSearchTop a').click(function(e) {
		if($('#searchBoxArea').css('height') == "0px"){
			$('#searchBoxArea').show();
			$('#indexNavibefore').show();
			$('#blackBG').show();
			$('#searchBoxArea').animate({height:'73px',opacity:'1'},'fast');
		}
		else{
			$('#blackBG').hide();
			$('#indexNavibefore').hide();
			$('#searchBoxArea').animate({height:'0px',opacity:'0'},'slow');
		}
	});



	/*lnbBody Length*/
	var basicHeight = $('#contentcore').height();
	basicHeight = basicHeight + "px";
//	$('.lnbBody').css('min-height',basicHeight);

	//find Last Tab and addClass
	if($('#ttab3').html()) {
		ltab = document.getElementById("ttab3");
		allB = ltab.getElementsByTagName("a");
		var cntTab = allB.length -1; // 탭의 갯수 계산
		$('#ttab3 > li:eq('+cntTab+')').addClass('ttabLast');
		$('.ttabWrap >ul>li').each(function() {
			if($(this).html().indexOf('br') >= 0 || $(this).html().indexOf('BR') >= 0) {
				$(this).addClass('ttab2Line');
			}
		});
	}
	if($('#ttab5').html()) {
		ltab2 = document.getElementById("ttab5");
		allC = ltab2.getElementsByTagName("a");
		var cntTab2 = allC.length -1; // 탭의 갯수 계산
		$('#ttab5 > li:eq('+cntTab2+')').addClass('ttabLast');
		$('.ttabWrap >ul>li').each(function() {
			if($(this).html().indexOf('br') >= 0 || $(this).html().indexOf('BR') >= 0) {
				$(this).addClass('ttab2Line');
			}
		});
	}

	//Verify Empty Contents
	//noContent();

	//TopPopupLayer
	$('#btnCloser').click(function(e) {
		$('#popLayer').animate({height:'0px',opacity:'0'},'slow');
		$('.PLWrap').animate({height:'0px',opacity:'0'},'slow');
	});


	//새창 발견 시 접근성 처리 루틴
	$('a').each(function(index, element) {

		if($(this).attr('target')){
			if($(this).attr('target').indexOf('blank')>0 && !$(this).hasClass('img'))
			{
				$(this).attr('title','새 창 열림');
				$(this).addClass('newWindow');
			}
		}

		if($(this).attr('href')){
			if($(this).attr('href').indexOf('mailto')>0 )
			{
				$(this).attr('title','새 창 열림');
				$(this).addClass('newWindow');
			}
		}

		if($(this).attr('onclick')){
			if($(this).parent().html().indexOf('window.open')>0 )
			{
				$(this).attr('title','새 창 열림');
				$(this).addClass('newWindow');
			}
		}
	});

	//프린터 버튼 클릭시 페이지 프린트
	$('#pprint').click(function(e) {
		window.print();
	});


	noContent();



	//responsive Mobile Menu
	var tmpHtml = $('#indexNavi').html();
	var DocHeight = $(document).height() - 100 + "px";
	$('#blackBG').height(DocHeight);


	$('#mobileIndex').html($('#indexNavi').html());
//	$('#mobileIndex').html($('#mobileIndex').html().replace('topmenu','topmenuM'));
//	$('#mobileIndex').html($('#mobileIndex').html().replace(/gnb/g,'mmb'));

//		$('#mobileIndex > ul > li > a').each(function(index, element) {
//			//$(this).text($(this).find('img').attr('alt'));
//			$(this).css('color',$('.themeFC').css('color'));
//			$(this).css('border-bottom-color',$('.themeColor').css('background-color'));
//		});

	// mobile menu opne
	$('#BtnmobileIndexa').click(function(e) {
		$('#mobileIndex').show();
		$('#blackBG').show();
		$('#blackBG').css('top','0');
		$('#blackBG').height(DocHeight);

		//mobile height
		var basicHeight2 = $('#middle').height() + 190 + 'px';
		$('#mobileIndex').css('min-height',basicHeight2);
	});
	// remove link dep1
	$('#topmenuM > li > a').each(function(index, element) {
		$(this).attr('href','#link');
		$(this).click(function(e) {
			$('#topmenuM > li > ul').hide();
			$(this).parent('li').find('ul').show();
			$('#topmenuM > li > ul > li > ul').hide();
		});
	});
	$('#topmenuM > li > ul > li > ul.MkSub').each(function(index, element) {
		$(this).parent('li').children('a').attr('href','#link');
		$(this).parent('li').children('a').click(function(e) {
			$('#topmenuM > li > ul>li>ul').hide();
			$(this).parent('li').children('ul').show();
		});
		
	});
	//blackBG hide
	$('#blackBG').click(function(e) {
		$('#blackBG').hide();
		$('#mobileIndex').hide();
		$('#indexNavibefore').hide();
		$('#blackBG').css('top','200px');
		if(GlobalHwak !=2) {
			$('#searchBoxArea').animate({height:'0px',opacity:'0'},'slow');
		}
	});
	//blackBG hide
	$('#indexNavibefore').click(function(e) {
		$('#blackBG').hide();
		$('#searchBoxArea').animate({height:'0px',opacity:'0'},'slow');
		$('#mobileIndex').hide();
		$('#indexNavibefore').hide();
		$('#blackBG').css('top','200px');

	});


	//button 4 relation lib
	$('#siteIndex .WebHide a').click(function(e) {
		if($('.siteRole').css('display') == 'none') {
			$('.siteRole').show();
		}
		else {
			$('.siteRole').hide();
		}

	});

});




function noContent(){
	var CompLength = 300; // length of Contents string to compare
	var notePrep = "/include/image/common/waiting.jpg"; // notice to output
	notePrep = '<p  style="text-align:center"><img src="'+notePrep+'" alt="죄송합니다. 현재 서비스를 준비중입니다. 홈으로" /></p>';
	if($('.contents').html()) {
		var tmpStr=$('.contents').html();//specific Area for Contents

		if(tmpStr.length < CompLength) {
			$('.contents').html($('.contents').html()+notePrep);
		}
	}
}


function moveFamilySite(){
	if($('#select1').val() != '')
	{
		document.forms.sitelink.action = $('#select1').val();
		document.forms.sitelink.submit();
	}
}
