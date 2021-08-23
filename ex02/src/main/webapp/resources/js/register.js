/**
 * 
 */
function checkExtension(fileName, fileSize) {
	// 정규식(파일의 확장자가 .exe, .sh, .zip, .alz등은 파일 첨부할 수 없게 정규식)
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxsize = 5242880; // 5MB
	
	if(fileSize>=maxsize) {
		alert("파일 사이즈 초과");
		return false;
	}
	
	if(regex.test(fileName)) {
		alert("해당 종류의 파일은 업로드 할 수 없습니다.");
		return false;
	}
	return true;
} // checkExtension end...	

function showUploadResult(uploadResultArr){
	if(!uploadResultArr || uploadResultArr.length==0){return;}
	
	var uploadUL = $(".uploadResult ul");
	var str="";
	
	$(uploadResultArr).each(function(i,obj){
		// image형태
		if(obj.filetype){
			var fileCallPath=encodeURIComponent(obj.uploadpath+"/s_"+obj.uuid+"_"+obj.filename);
			str+="<li data-path='"+obj.uploadpath+"'";
			str+=" data-uuid='"+obj.uuid+"' data-filename='"+obj.filename+"' data-type='"+obj.filetype+"'"+"><div>";
			str+="<span>"+obj.filename+"</span>";
			str+="<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i>닫기</button>";
			str+="<img src='/display?filename="+fileCallPath+"'>";
			str+="</div></li>";
		}else{// 그렇지 않은 형태
			var fileCallPath=encodeURIComponent(obj.uploadpath+"/"+obj.uuid+"_"+obj.filename);
			var fileLink=fileCallPath.replace(new RegExp(/\\/g),"/");
			
			str+="<li><div>";
			str+="<span>"+obj.filename+"</span>";
			str+="<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i>"+obj.filename+"</button>";
			str+="</li></div>";
			
		}
	})
	uploadUL.append(str);
}


$(document).ready(function() {
	// form태그를 선택 - submit을 해야 되니까
	var formObj = $("form[role='form']");
	
	// 글 쓰기 버튼을 클릭하면
	$("input[type='submit']").on("click", function(e) {
//		alert("aaa");
		e.preventDefault(); // 기존의 submit의 기능을 제거한 것이다.(제거를 해야만 밑의 기능을 수행할 수 있다.)
		console.log("submit clicked");
		var str="";
		
		$(".uploadResult ul li").each(function(i, obj) {
			var jobj = $(obj);
			console.dir(jobj);
			
			str += "<input type='text' name='attachList["+i+"].filename' value='"+jobj.data("filename")+"'>";
			str += "<input type='text' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			str += "<input type='text' name='attachList["+i+"].uploadpath' value='"+jobj.data("path")+"'>";
			str += "<input type='text' name='attachList["+i+"].filetype' value='"+jobj.data("type")+"'>";
		})
		// $(".uploadResult ul").html(str); // 결과 확인용
		formObj.append(str).submit();
		
	}); // $("input[type='submit']").on("click", function(e) end....
	
	
	$("input[type='file']").change(function(e) {
		alert("bbbb"); // 파일 선택을 누르면 alert("bbbb")가 나온다.
		// form태그와 같은 역할
		var formData = new FormData();
		// <input type="file" name="uploadFile" multiple> 선택
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		// add File Data to formData
		for(var i=0; i<files.length; i++) {
			// checkExtension함수 호출(파일 종류, 파일용량 체크)
			if(!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			formData.append("uploadFile", files[i])
		}
		
		$.ajax({
			url:"/uploadAjaxAction",
			processData: false, // 무조건 false
			contentType: false, // 무조건 false
			data: formData,
			type: 'post',
			dataType: "json",
			success: function(result) {
				console.log("aaaa");
				console.log(result);
				showUploadResult(result); // showUploadResult 함수 호출
			}
		});
	});
}); // $(document).ready(function) end..


