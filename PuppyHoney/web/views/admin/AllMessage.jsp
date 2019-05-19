<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>



</head>
<body class="pl-5 pr-5">
	
	<div class="panel-heading pt-4 "><h2>전체쪽지 보내기</h2></div> 
	
	<form method="post" action="<%=request.getContextPath()%>/admin/AllMessageSend">
	<input type="hidden" name="admin" value='admin'>
	<table class="table table-hover ">
	     
	     <tr>
	         <th colspan="3"><h6>제목</h6><input class="form-control col-md-6" name="title" type="text" placeholder="제목" required></th>
	     </tr>
	     <tr>
	         <th colspan="1"><h6>내용</h6><textarea class="form-control" name="content" cols="150" rows="11" style='resize: none;' required></textarea></th>
	      
	     </tr>
	 </table>    
	 <button type="submit" class="btn m-2 alert-danger">보내기</button>
	 <button class="btn alert-danger" onclick="self.close();">닫기</button>
     </form>
       
                
</body>
</html>