			/* 비밀번호 변경 */ 
		 	function updatePw(){
			var frm = document.getElementById("khu_updateInfoForm")
			var pw = document.getElementById("khu_pw_textbox").value;
			frm.action = "./memberInfoUpdatePw.do"
			var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
			if(pw.match(reg)){
				swal({
    					title : "성공\n비밀번호를 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)}else{
						swal('실패','8~20자리 대소문자 특수문자 숫자 포함');
						return false;
					}
			}
			/* 알림 설정 변경 */
			function khu_notification(){
			var frm = document.getElementById("khu_updateInfoForm");
			frm.action ="./memberInfoUpdateNotification.do"
		swal({
    		title : "성공\n알림 설정을 변경하였습니다",					
        	icon  : "success",
        	closeOnClickOutside : false
    		}, function(){
			frm.submit();
				}
			)
		}
			
			/* 이름 변경 */
			function updateName(){
			var frm = document.getElementById("khu_updateInfoForm")
			frm.action = "./memberInfoUpdateName.do"
			var name = document.getElementById("khu_name_textbox").value;
			var reg = /^[가-힣a-zA-Z]+$/;
			
			if(name==""|| name.trim==""){
				swal('실패','공백이 있거나 정보를 기입하지 않았습니다');
				return false;		
			}else if(!name.match(reg)){
				swal('실패','이름에 특수문자가 들어갈 수 없습니다 ');	
				}else{
					swal({
    					title : "성공\n이름을 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
			}
			
			}
			
			/* 번호 변경 */
			function updatePhone(){
			var frm = document.getElementById("khu_updateInfoForm")			
			frm.action = "./memberInfoUpdatePhone.do"
			swal({
    					title : "성공\n전화번호를 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
			}	
			
			/* 주소 변경 */
			function updateAddress(){
			var frm = document.getElementById("khu_updateInfoForm")
			frm.action = "./memberInfoUpdateAddress.do"
			swal({
    					title : "성공\n주소를 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
			}
			
			/* 이메일 변경 */
			function updateEmail(){
			var frm = document.getElementById("khu_updateInfoForm")
			frm.action = "./memberInfoUpdateEmail.do"
			swal({
    					title : "성공\n이메일 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
			}						
			
			
			/* eye */
			$(document).ready(function(){
        $('#eye i').on('click',function(){
            $('#eye input').toggleClass('active');
            if($('input').hasClass('active')){
                $(this).attr('class',"fa fa-eye-slash fa-lg")
                .prev('input').attr('type',"text");
            }else{
                $(this).attr('class',"fa fa-eye fa-lg")
                .prev('input').attr('type','password');
            }
        });
    });
			
			
			
			/* 카카오 주소찾기 */
			function kakaopost() {
			    new daum.Postcode({
			        oncomplete: function(data) {
			           document.querySelector("#khu_detailAddress_textbox").value ='('+ data.zonecode + ')' + data.address
			        }
			    }).open();
			}
			
			/* 탈퇴 신청 */
			function khu_quitRequest(){
				var khu_frm = document.getElementById("khu_quitRequestForm")
				var khu_id = document.getElementById("khu_hiddenId")
				var khu_phone = document.getElementById("khu_pw");
				var khu_rc = document.getElementById("khu_hiddenRentalCount");
				
				khu_frm.action = "./memberQuitRequest.do"
	
					$.ajax({
						type : "post",
						url: "./memberQuitRequestChk.do",
						data : "member_id =" +khu_id.value +"&phone="+khu_phone.value+"&rental_count="+khu_rc.value,
						success:function(msg){ 				
    					console.log(msg.isc , typeof msg);
    					if (msg.isc=="성공") {
    						swal({
    					title : "탈퇴 성공\n로그아웃 합니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
    						khu_frm.submit();
    						console.log(msg.isc);
    					});
    					}else{
    						swal("탈퇴 실패","재인증 해주세요");
    					}
    				},
    				error:function(){
    					swal("탈퇴 실패","에러가 발생하였습니다")
    				}
					})
				}
			
			    /* qr 체크 사이즈*/
				function checkSize(){
				console.log("checkSize : " + "start");

				// div 객체 사이즈 확인
				var width = document.getElementById("qrcode").offsetWidth;
				var height = document.getElementById("qrcode").offsetHeight;
				
				
				 
				console.log("qrcode [width] : " + width);
				console.log("qrcode [height] : " + height);


				// QR 생성 함수 호출
				createQR(width-5, height-8, "hello Twok"); 			
			}


			/* QR 생성 함수 */
			window.onload = function(width, height, data){
				var a = document.getElementsByName("khu_qr")[0].value  
                var b = document.getElementsByName("khu_qr")[1].value 
				var c =	document.getElementsByName("khu_qr")[2].value
				var d =	document.getElementsByName("khu_qr")[4].value
				var data =	a+b+c+d;
				

				
				
				console.log("createQR : " + "start");
//				var c = encodeURIComponent(data);
				
				console.log(data);
				
				console.log(typeof(data));
				
//				$('#qrcode').qrcode(data);

//				 실제 QR 생성 부분
				$('#qrcode').qrcode({
					width: 340,
					height: 340,
					text: data
				});    		

				console.log("createQR : " + "end");
			};
	
	
	
			/* HTML2CANVAS */
			function getPDF(fileName){
   // 기본 A4형식의 사이즈로 조정
    var HTML_Width = $(".canvas_div_pdf").width();
    var HTML_Height = $(".canvas_div_pdf").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.2)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;

    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;

    
    html2canvas($(".canvas_div_pdf")[0],{allowTaint:true}).then(function(canvas) {
        canvas.getContext('2d');
        var imgData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
        pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);

        for (var i = 1; i <= totalPDFPages; i++) { 
            pdf.addPage(PDF_Width, PDF_Height);
            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
        }
        
        pdf.save(fileName + ".pdf");
                
    });
};

/* PDF화 */
$(document).ready(function() {
    $('#pdf').on('click',function(){
        window.scrollTo(0,0);
        let fileName = "HTML-TO-PDF"
        getPDF(fileName);
    })
});


/* 핸드폰 유효성 */
$(document).ready(function(){
	$("#khu_phone_textbox").keyup(function(){
		var phoneVal = $(this).val();
		
		var reg = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})";
		if(phoneVal.match(reg)){
			$("#khu_result_phone").html("")
		}else{
			$("#khu_result_phone").css("color","red");
			$("#khu_result_phone").html("핸드폰 번호를 다시 확인해주세요");			
		}
	});
});


/* 비밀번호 유효성 */
$(document).ready(function(){
	$("#khu_pw_textbox").keyup(function(){
		var pwVal = $(this).val();
		var reg = "^(?=.*[a-zA-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$";
		console.log(pwVal);
		if(pwVal.match(reg)){
			$("#khu_resultPw").css("color","blue");
			$("#khu_resultPw").html("사용 가능한 비밀번호입니다")
		}else{
			$("#khu_resultPw").css("color","red");
			$("#khu_resultPw").html("최소 8 자 및 최대 20 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자");
		}
	});
});

/* 이름 유효성 */
$(document).ready(function(){
	$("#khu_name_textbox").keyup(function(){
		var reg = /^[가-힣a-zA-Z]+$/;
		var nameVal = $(this).val();
		if(nameVal.match(reg)){
			$("#khu_resultName").html("")
		}else{
			$("#khu_resultName").css("color","red");
			$("#khu_resultName").html("이름엔 영어와 한글만 들어갑니다");
		}
	});
});	


function updateGhComplete(){
	var frm = document.getElementById("khu_quitRequestForm");
	frm.action = "./updateGH.do";
	
	swal({
    	title : "등업 성공\n다시 로그인 해주세요",					
        icon  : "success",
        closeOnClickOutside : false
    	}, function(){
        frm.submit();
       console.log(msg.isc);
    	});
}

		


				
				

			
			