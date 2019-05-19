<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PuppyHoney</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
  	$(function(){
  		var idFlag=0;
  		var nickFlag=0;
  		var pw2Flag=0;
  		var emailFlag=0;
  		var pw1Flag=0;
  		var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
  		var idRules=/^[a-zA-Z0-9]{4,12}$/;
  		$("#userId").keyup(function()
  		{	
  			
  			$.ajax({
  				url:"<%=request.getContextPath()%>/user/checkId",
  				type:"post",
  				data:{"userId":$("#userId").val().trim()},
  				success:function(data)
  				{
  					if(idRules.test($("#userId").val()))
  					{
  						if(data>0)
  	  					{
  	  						$("#idResult").html("이미 존재하는 ID").css("color","red");
  	  						idFlag=0;
  	  					}
  	  					else
  						{
  	  						
  	  						$("#idResult").html("사용 가능한 ID").css("color","green");
  	  						idFlag=1;
  						}
  					}
  					else
 					{
  						$("#idResult").html("ID는 영문대소문자+숫자 4~12글자 입니다.").css("color","red");
	  					idFlag=0;
 					} 				
  				}
  			});
  		});
  		$("#userNick").keyup(function()
  		{	
  			
  			$.ajax({
  				url:"<%=request.getContextPath()%>/user/checkNick",
  				type:"post",
  				data:{"userNick":$("#userNick").val().trim()},
  				success:function(data)
  				{
  					if(data>0)
  					{
  						$("#nickResult").html("이미 존재하는 닉네임").css("color","red");
  						nickFlag=0;
  					}
  					else
  						
					{
  						$("#nickResult").html("사용 가능한 닉네임").css("color","green");
  						nickFlag=1;
					}
  				}
  			});
  		});
  		$("#userEmail").keyup(function()
  		{	
  			
  			$.ajax({
  				url:"<%=request.getContextPath()%>/user/checkEmail",
  				type:"post",
  				data:{"userEmail":$("#userEmail").val().trim()},
  				success:function(data)
  				{
  					if(data>0)
  					{
  						$("#emailResult").html("이미 존재하는 e-mail").css("color","red");
  						emailFlag=0;
  					}
  					else
  						
					{
  						$("#emailResult").html("사용 가능한 e-mail").css("color","green");
  						emailFlag=1;
					}
  				}
  			});
  		});
  		$("#userPw").keyup(function(){
  			if(passwordRules.test($("#userPw").val()))
  			{
  				$("#pwd1Result").html("사용가능한 비밀번호입니다").css("color","green");
  		        pw1Flag=1;
  			}
  			else
  			{
  				$("#pwd1Result").html("사용불가능한 비밀번호입니다").css("color","red");
  		        pw1Flag=0;
  			}
  		});
	    $("#userPw2").keyup(function()
	    {   
	    	
	      if($("#userPw").val()!=$("#userPw2").val())
	      {
	          $("#pwd2Result").html("비밀번호 불일치").css("color","red");
	          pw2Flag=0;
	      }
	      else
	      {
	          $("#pwd2Result").html("비밀번호 일치").css("color","green");
	          pw2Flag=1;
	      }
	    });
	  	$("#registerForm").submit(function(event){
	  		
	  		
	  		if(!(idFlag==1&&pwFlag==1&&nickFlag==1&&emailFlag==1))
	  		{
	  			if(idFlag==0)
		  		{
	  				event.preventDefault();
		  			alert("사용가능한 ID를 입력해주세요");
		  			$("#userId").focus();
		  			return;
		  		}
		  		else if(pw2Flag==0)
		  		{
		  			event.preventDefault();
		  			alert("비밀번호를 확인해주세요.");
		  			$("#userPw").focus();
		  			return;
		  		}
		  		else if(nickFlag==0)
		  		{
		  			event.preventDefault();
		  			alert("사용가능한 닉네임을 입력해주세요");
		  			$("#userNick").focus();
		  			return;
		  		}
		  		else if(emailFlag==0)
		  		{
		  			event.preventDefault();
		  			alert("사용가능한 E-mail을 입력해주세요");
		  			$("#userEmail").focus();
		  			return;
		  		}
	  		}
	  		
	  	});
  	});
  
  </script>    
</head>
<body>
	<div class="container mt-5 p-5">
		<div class="p-4"align="center"><label><a id="logo" class="navbar-brand" href="<%=request.getContextPath() %>/index.jsp" ><img src="<%=request.getContextPath() %>/images/dog.png"> PuppyHoney</a></label></div>
   		<div id="contentarea" class="container col-sm-7 shadow h-100 p-4 rounded" >
	        <form action="<%=request.getContextPath()%>/user/registerEnd" method="post" id="registerForm" enctype="multipart/form-data">
	          <div class="form-group">
	            <label for="userId">ID</label><span> *</span><label id="idResult" class="float-right"></label>
	            <input type="text" class="form-control" id="userId" name="userId" placeholder="영문대소문자+숫자로 이루어진 4~12글자" required>
	          </div>
	          <div class="form-group">
	            <label for="userPw">비밀번호</label><span> *</span><label id="pwd1Result" class="float-right"></label>
	            <input type="password" class="form-control" id="userPw" name="userPw" placeholder="8글자이상 16글자이하의 영문,숫자,특수문자조합" required>
	          </div>
	          <div class="form-group">
	            <label for="userPw2">비밀번호확인</label><span> *</span><label id="pwd2Result" class="float-right"></label>
	            <input type="password" class="form-control" id="userPw2" name="userPw2" placeholder="비밀번호를 한번더 입력하세요" required>
	          </div>
	          <div class="form-group">
	            <label for="userNick">닉네임</label><span> *</span><label id="nickResult" class="float-right"></label>
	            <input type="text" class="form-control" id="userNick" name="userNick"  required>
	          </div>
	          <div class="form-group">
	            <label for="userEmail" class="mb-0">E-Mail</label><span> *</span><label id="emailResult" class="float-right"></label>
		        <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="회원가입이 완료되면 인증메일이 발송됩니다."  required>
	          </div>
	          <div class="form-group">
	            <label for="userName">이름</label><span> *</span>
	            <input type="text" class="form-control" id="userName" name="userName" required>
	          </div>
	          <div class="form-group">
	            <label for="userBirth">생년월일</label><span> *</span>
	       		<input type="date" class="form-control" id="userBirth" name="userBirth" required>
	          </div>
	          <div class="form-group">
	            <label for="userDogName">반려견 이름</label>
	            <input type="text" class="form-control" id="userDogName" name="userDogName">
	          </div>
	          <div class="form-group">
	            <label for="userDogBirth">반려견 생년월일</label>
	       		<input type="date" class="form-control" id="userDogBirth" name="userDogBirth">
	          </div>
	          
	          <div class="form-group">
	          <label> 프로필 사진</label>
	          	<input type="file" class="form-control-file border" name="userImage" multiple accept="image/*">
	          </div>
	          <div class="form-group mt-3" align="center">
	              <button type="submit" class="btn mt-2 w-25" >가입하기</button>
	          </div>
	        </form>
  		</div>                     
  	</div> 
</body>
</html>