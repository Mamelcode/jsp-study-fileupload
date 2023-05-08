<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일업로드</title>
</head>
<body>
	<h2>파일 업로드하기</h2>
	
	<form action="/file" method="post" enctype="multipart/form-data">
		<p>이름을 입력 하세요.</p>
		<input type="text" name="name">
		<p>파일을 업로드 하세요.</p>
		<input type="file" name="file">
		<button type="submit">전송</button>
	</form>
</body>
</html>