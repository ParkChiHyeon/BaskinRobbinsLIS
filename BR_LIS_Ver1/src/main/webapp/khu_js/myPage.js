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
			frm.action = "./memberInfoUpdatePw.do"
				swal({
    					title : "성공\n비밀번호를 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
			}
			
			function updateName(){
			var frm = document.getElementById("khu_updateInfoForm")
			frm.action = "./memberInfoUpdateName.do"
			swal({
    					title : "성공\n이름을 변경하였습니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
						frm.submit();
							}
					)
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
				var khu_pw = document.getElementById("khu_pw")
				var khu_id = document.getElementById("khu_hiddenId")
				var khu_rc = document.getElementById("khu_hiddenRentalCount");
				var khu_frm = document.getElementById("khu_quitRequestForm")
				
				console.log(khu_id.value);
				console.log(khu_pw.value);
				
				khu_frm.action="./memberQuitRequest.do"
				
				if(khu_pw.value =="" ||khu_pw.value.trim ==""){
					swal('비밀번호를 입력해주세요');
					khu_pw.value="";
    				khu_pw.focus();
				}else{
					$.ajax({
						type:"POST",
						url: "./memberQuitRequestChk.do",
						data :"member_id="+khu_id.value+"&password="+khu_pw.value+"&rental_count="+khu_rc.value,
						success:function(msg){
							if (msg.isc=="성공") {
    						swal({
    					title : "탈퇴신청 성공\n로그아웃 됩니다",					
        				icon  : "success",
        				closeOnClickOutside : false
    					}, function(){
    						khu_frm.submit();
    						console.log(msg.isc);
    					});
    					}else{
    						swal("탈퇴신청 실패","정보가 틀렸습니다");
    					}
					},
    				error:function(){
    					swal("로그인 실패","에러가 발생하였습니다")
    					console.log(khu_id.value);
    	     			console.log(khu_pw.value);
    				
						}
					});
				}	
			}
			
			