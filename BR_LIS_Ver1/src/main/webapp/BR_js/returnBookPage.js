function returnBookSelect(){
	var serial = $("#book_serial").val();
	console.log(serial)
	var vo,mVo,rVo;
	var html ="";
	$(".buttons").empty();
	$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).empty();
	$("table").eq(1).children("tbody").eq(0).children("tr").eq(1).empty();
	$("table").eq(2).children("tbody").eq(0).children("tr").eq(1).empty();
	
	$.ajax({
		method:"POST",
		url:"./returnBookSelect.do",		
		data:{"book_serial":serial},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.vo==null){
			vo+='<td colspan="5">데이터가 없습니다</td>';
			mVo+='<td colspan="9">데이터가 없습니다</td>';
			rVo+='<td colspan="7">데이터가 없습니다</td>';
			$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).append(vo);
			$("table").eq(1).children("tbody").eq(0).children("tr").eq(1).append(mVo);
			$("table").eq(2).children("tbody").eq(0).children("tr").eq(1).append(rVo);
			}else{
			vo+='<td><input type="hidden" name="lending_seq" value='+data.vo.lending_seq+'>'+data.vo.lending_seq+'</td>';
			vo+='<td class="book_serial">'+data.vo.book_serial+'</td>';
			vo+='<td>'+data.vo.lending_delay+'</td>';
			vo+='<td>'+data.vo.lending_date+'</td>';
			vo+='<td>'+moment(data.vo.end_date).format('YYYY-MM-DD HH:mm:ss')+'</td>';
			
			mVo+='<td>'+data.mVo.member_id+'</td>'
			mVo+='<td><input type="hidden" name="member_code" value='+data.mVo.member_code+'>'+data.mVo.member_code+'</td>';
			mVo+='<td>'+data.mVo.name+'</td>'
			mVo+='<td>'+data.mVo.phone+'</td>'
			mVo+='<td>'+data.mVo.address+'</td>'
			mVo+='<td>'+data.mVo.email+'</td>'
			if(data.mVo.penalty==null){
				mVo+='<td></td>'
				}else{
				mVo+='<td>'+data.mVo.penalty+'</td>'
				}
			mVo+='<td>'+data.mVo.rental_count+'</td>'
			mVo+='<td>'+data.mVo.birth+'</td>'
			
			if(data.rVo!=null){
				rVo+='<td><input type="hidden" name="member_code" value='+data.rVo.lending_seq+'>'+data.rVo.lending_seq+'</td>';
				rVo+='<td>'+data.rVo.reserve_date+'</td>'
				rVo+='<td>'+data.book_serial+'</td>'
				rVo+='<td>'+data.member_code+'</td>'
				rVo+='<td><input type="hidden" name="member_code" value='+data.rVo.phone+'>'+data.rVo.phone+'</td>';
				rVo+='<td>'+data.rVo.email+'</td>'
				rVo+='<td><input type="hidden" name="member_code" value='+data.rVo.name+'>'+data.rVo.name+'</td>';
				}else{
					rVo+='<td colspan="7">데이터가 없습니다</td>';
				}
			$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).append(vo);
			$("table").eq(1).children("tbody").eq(0).children("tr").eq(1).append(mVo);
			$("table").eq(2).children("tbody").eq(0).children("tr").eq(1).append(rVo);
			}
			html+='<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnNomal.do" formmethod="get" formtarget="_self" onclick="baNap()">반납</button>'; 
			html+='<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnBookDamege.do" formmethod="get" formtarget="_self" onclick="paSon()">파손</button>';
			html+='<button class="w-btn-outline w-btn-gray-outline" type="submit" formaction="./returnBookLost.do" formmethod="get" formtarget="_self" onclick="bunSil()">분실</button>';
			$(".buttons").append(html);
			
		},
		error:function(data) {
			vo+='<td colspan="5">데이터가 없습니다</td>';
			mVo+='<td colspan="9">데이터가 없습니다</td>';
			rVo+='<td colspan="7">데이터가 없습니다</td>';
			$("table").eq(0).children("tbody").eq(0).children("tr").eq(1).append(vo);
			$("table").eq(1).children("tbody").eq(0).children("tr").eq(1).append(mVo);
			$("table").eq(2).children("tbody").eq(0).children("tr").eq(1).append(rVo);
			alert("대출한 내역이 없습니다.");
		}
	})
}

function baNap(){
	alert("반납이 완료되었습니다.")
}
function paSon(){
	alert("파손처리가 완료되었습니다.")
}
function bunSil(){
	alert("분실처리가 완료되었습니다.")
}