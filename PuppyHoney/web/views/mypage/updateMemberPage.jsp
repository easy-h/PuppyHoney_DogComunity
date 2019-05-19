<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
    <script>
    $(function(){
    	
  		var nickFlag=1;
  		var pw2Flag=0;
  		var emailFlag=1;
  		var pw1Flag=0;
  		var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
  		
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
  						if($("#userNick").val().trim()!=$("#userNickOrigin").val())
  						{
  							$("#nickResult").html("이미 존재하는 닉네임").css("color","red");
  	  						nickFlag=0;
  						}
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
  						if($("#userEmail").val().trim()!=$("#userEmailOrigin").val())
  						{
  							$("#emailResult").html("이미 존재하는 e-mail").css("color","red");
  	  						emailFlag=0;
  						}
  						
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
	  			
		  		if(pw2Flag==0)
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
  	
  		
  	
	  
          $('#updatePage').on('click',function(){
            location.href="<%=request.getContextPath()%>/updateMemberPage";
          });
          $('#message').on('click',function(){
        	 window.open('<%=request.getContextPath() %>/message/receiveMessage?userId=<%=userLoggedIn.getUserId()%>','message','top=50px, left=100px, height=600px, width=800px, resizable=no');
          });
          $('#bookmarkPage').on('click',function(){
            location.href="<%=request.getContextPath()%>/bookmarkPage";
          });
          $('#deleteMember').on('click',function(){
            location.href="<%=request.getContextPath()%>/mypage/deleteMemberPage";
          });
          $('#myHomepage').on('click',function(){
            location.href="<%=request.getContextPath()%>/myHomepage";
          });
	   	
    });
    
  </script>
  <style>
      td{
          width: 600px; height: 300px;
      }
      table{
          border: 5px outset;
          border-collapse: separate;
      } 
      tr td{
          border: 3px outset;
      }
  </style>
  <div class="container mt-5" style="text-align: center;">
        
        <h1>마이 페이지</h1>
        <br>
        
        <div class="btn-group m-2" style="display: inline-block;">
            <button id="updatePage" type="button" class="btn btn-outline-secondary">정보 수정</button>
            <button id="message" type="button" class="btn btn-outline-secondary">쪽지 함</button>
            <button id="deleteMember" type="button" class="btn btn-outline-secondary">회원 탈퇴</button>
        </div>
        <div class="p-3 pb-3">
            <div class="container col-sm-7">
            <h1 class="mt-3">정보 수정</h1>
            <hr>
            
     		<div class="container p-4 rounded" style="text-align: left;">
		        <form action="<%=request.getContextPath()%>/updateMemberPageEnd" method="post" id="registerForm" enctype="multipart/form-data">	
		          <div class="form-group">
		            <label for="userId">ID</label><label id="idResult" class="float-right"></label>
		            <input type="text" class="form-control" id="userId" name="userId" value="<%=userLoggedIn.getUserId() %>" readonly="readonly">
		          </div>
		          
		          <div class="form-group">
		            <label for="userPw">비밀번호</label><label id="pwd1Result" class="float-right"></label>
		            <input type="password" class="form-control" id="userPw" name="userPw" placeholder="8글자이상 16글자이하의 영문,숫자,특수문자조합" required>
		          </div>
		          
		          <div class="form-group">
		            <label for="userPw2">비밀번호 확인</label><label id="pwd2Result" class="float-right"></label>
		            <input type="password" class="form-control" id="userPw2" name="userPw2" placeholder="비밀번호를 한번더 입력하세요" required>
		          </div>
		          
		          <div class="form-group">
		            <label for="userNick">닉네임</label><label id="nickResult" class="float-right"></label>
		            <input type="text" class="form-control" id="userNick" name="userNick" value="<%=userLoggedIn.getUserNick()%>" required>
		            <input type="hidden" id="userNickOrigin" name="userNickOrigin" value="<%=userLoggedIn.getUserNick()%>">
		          </div>
		          
		          <div class="form-group">
			          <label for="userEmail" class="mb-0">E-Mail</label><span> *</span><label id="emailResult" class="float-right"></label>
				      <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="회원정보수정이 완료되면 인증메일이 발송됩니다." value="<%=userLoggedIn.getUserEmail()%>"  required>
				      <input type="hidden" id="userEmailOrigin" name="userEmailOrigin" value="<%=userLoggedIn.getUserEmail()%>">
	              </div>
		          
		          <div class="form-group">
		            <label for="userName">이름</label><span> *</span>
		            <input type="text" class="form-control" id="userName" name="userName" value="<%=userLoggedIn.getUserName()%>" readonly>
		          </div>
		          
		          <div class="form-group">
		            <label for="userBirth">생년월일</label><span> *</span>
		       		<input type="date" class="form-control" id="userBirth" name="userBirth" value="<%=userLoggedIn.getUserBirth()%>" readonly>
		          </div>
		          
		          <div class="form-group">
		            <label for="userDogName">반려견 이름</label>
		            <input type="text" class="form-control" id="userDogName" name="userDogName" value="<%=userLoggedIn.getUserDogName() %>">
		          </div>
		          
		          <div class="form-group">
		            <label for="userDogBirth">반려견 생년월일</label>
		       		<input type="date" class="form-control" id="userDogBirth" name="userDogBirth" value="<%=userLoggedIn.getUserDogBirth()%>">
		          </div>
		          
		          <div class="form-group">
		          <label> 프로필 사진</label>
		          	<input type="file" class="form-control-file border" name="userImage" accept="image/*" >
		          </div>
		          
		          <div class="form-group " align="center">
		              <button type="submit" class="btn mt-2 w-25" style="background: rgb(200, 152, 152);" >변경하기</button>
		          </div>
		        </form>
    		</div>                     
  		  </div>
        </div>
    </div>    
    



<%@ include file="/views/common/footer.jsp" %>