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
			}else if(name == name){	
				swal('실패','기존에 쓰던 이름으로 변경할 수 없습니다');
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
				
				if(khu_id.value == "" || khu_id.value.trim ==""){
					swal('값을 입력해 주세요');
						khu_id.value="";
    					khu_id.focus();
				}else{
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
			}
			
				

			
		
				
				
				
				

			
			