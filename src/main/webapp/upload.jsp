<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>



<div>  
    
</div>  
	<form action="${pageContext.request.contextPath}/upload/data" method="post"   modelAttribute="person"  enctype="multipart/form-data">
	<div>  
    <label><span>*</span>信件内容：</label>  
    <textarea id="content" name="content" ></textarea>  
</div>
	<div>  
    <label>附  件：</label>   
    <input type="file" name="pic" id=pic />     
	</div>  
		<input type="submit" value="上传文件 "/>
	</form>

</body>
</html>