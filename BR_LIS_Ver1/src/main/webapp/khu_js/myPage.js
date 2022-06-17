		 /* 핸드폰 번호 */
		 $(document).ready(function(){
				$("#khu_phone_textbox").keyup(function(){
					var phoneVal = $(this).val();
					
					var reg = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})";
					if(phoneVal.match(reg)){
						$("#khu_resultPhone").html("")
					}else{
						$("#khu_resultPhone").css("color","red");
						$("#khu_resultPhone").html("핸드폰 번호를 다시 확인해주세요");

					}
				});
			});
		 
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
			
			
			function updateName(){
			var frm = document.getElementById("khu_updateInfoForm")
			frm.action = "./memberInfoUpdateName.do"
			var name = document.getElementById("khu_name_textbox").value;
			var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
			
			if(name==""|| name.trim==""){
				swal('실패','공백이 있거나 정보를 기입하지 않았습니다');
				return false;		
			}else if(name.match(reg)){
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
			
			function updatePhone(){
			var frm = document.getElementById("khu_updateInfoForm")
			
//			var phoneChk = document.getElementById('phoneCheck_textbox');
					
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
			
			
			
			
			function kakaopost() {
			    new daum.Postcode({
			        oncomplete: function(data) {
			           document.querySelector("#khu_detailAddress_textbox").value ='('+ data.zonecode + ')' + data.address
			        }
			    }).open();
			}
			
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
//				var a = document.getElementById("khu_member_id").innerHTML;
				var data = document.getElementsByName("khu_qr")[0].value + 
					document.getElementsByName("khu_qr")[1].value +
					document.getElementsByName("khu_qr")[2].value +
//					document.getElementsByName("khu_qr")[3].value +
					document.getElementsByName("khu_qr")[4].value;

//				var a = a.value
//				var b = b.value
					
//				var b = document.getElementById("khu_member_name").innterHTML;
//				var c = document.getElementById("khu_member_code").value;
//				var d = document.getElementById("khu_email").value;
//				var e = document.getElementById("khu_phone").value;
				
//				var data = b.value;
//				var data =	a+b;
				

				
				
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

    // ------------------------------테스트 중------------------------------
//    
    // ------------------------------------------------------------------
    
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
        
        // ------------------------------테스트 중------------------------------
//        document.getElementById("loader").style.display = "none"; //로딩 끝
        // ------------------------------------------------------------------
        
    });
};
$(document).ready(function() {
    $('#pdf').on('click',function(){
        window.scrollTo(0,0);
        let fileName = "HTML-TO-PDF"
        getPDF(fileName);
    })
});
			
		


				
				

			
			