/**
 * 
 */
$(document).ready(function() {
	var bno = $("#bno").val()
	alert(bno);
	$.getJSON("getAttachList", {bno: bno}, function(arr) {
		// console.log(arr);
		var str = "";
		
		$(arr).each(function(i, attach) { // i와 attach는 변수명이기에 아무거나 줘도 된다.
			// 첨부파일이 이미지이면,
			if(attach.filetype) { // 대소문자 구분은 console에 찍혀있으니 확인하고 적어주자
				var fileCallPath = encodeURIComponent(attach.uploadpath+"/s_"+attach.uuid+"_"+attach.filename); // 대소문자 구분은 console에 찍혀있으니 확인하고 적어주자
					
				str+="<li data-path='"+attach.uploadpath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.filename+"' data-type='"+attach.filetype+"'><div>";
				str+="<img src='/display?filename="+fileCallPath+"'>";
				str+="</div></li>";
			} else { // 그렇지 않으면
				str+="<li data-path='"+attach.uploadpath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.filename+"' data-type='"+attach.filetype+"'><div>";
				str+="<span> "+attach.filename+"</s;apn><br>"
				str+="<img src='/display?filename="+fileCallPath+"'>";
				str+="</div></li>";
			}			
		}); // end each 함수
		
		$(".uploadResult ul").html(str);
		
	}); // end get JSON
}); // end function

// {bno: bno} : JSON 타입
// function : callback