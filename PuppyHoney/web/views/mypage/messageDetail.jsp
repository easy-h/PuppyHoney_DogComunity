<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ph.user.model.vo.User, com.ph.message.model.vo.Message"%>
    <%
    	Message m=(Message)request.getAttribute("message");
    %>
<%
	User userLoggedIn=(User)session.getAttribute("userLoggedIn");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PuppyHoney</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
    	$(function(){
    		$('#back').on('click',function(){
    			location.href="<%=request.getContextPath()%>/message/receiveMessage?userId=<%=userLoggedIn.getUserId()%>";
    		})
    	})
    </script>
</head>
<body>
	<table class="table table-hover ">
         <tr>
             <th>
              <h6>발신자</h6>
              <input class="form-control col-md-2"type="text" value="<%=m.getMessageWriter()%>" readonly>
             </th>
         </tr>
         <tr>
             <th>
              <h6>제목</h6>
              <input class="form-control  col-md-6" type="text" value="<%=m.getMessageTitle()%>" readonly>
             </th>
         </tr>
         <tr>
             <th>
              <h6>내용</h6>
              <textarea class="form-control" name='messageTextarea' cols="150" rows="11" style='resize: none;' readonly>
              <%=m.getMessageContent() %>
              </textarea>
             </th>
         </tr>
     </table>
     <button id="back" type="button" class="btn m-2 alert-danger">뒤로가기</button>
</body>
</html>