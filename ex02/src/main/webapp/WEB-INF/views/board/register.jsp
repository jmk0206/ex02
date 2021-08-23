<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jquery 연결 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 댓글처리하기 위한 javascript(reply.js) 파일 연결 -->
<script src="../resources/js/register.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form role="form" action="register" method="post">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="20" rows="10" name="content"></textarea></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer"></td>
			</tr>		
			<tr>
				<td colspan="2"><input type="submit" value="글쓰기"></td>
			</tr>
		</table>
		
		<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple>
		</div>
		
		<!-- 
		<div>
			<img src="../resources/img/0b2967c0-43c0-4d2e-9e4c-208616ad88cd_2020-11-22-14-01-59-627.jpg">
		</div>
		 -->
		 
		 <div class="uploadResult">
		 	<ul>
		 	</ul>
		 </div>
		
	</form>
	
	
</body>
</html>