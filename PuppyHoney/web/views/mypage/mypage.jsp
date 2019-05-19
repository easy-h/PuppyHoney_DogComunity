<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

	<script>
	
	
      $(function(){
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
      })
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
        <div class="container mt-5" align="center">
            <h4><%=userLoggedIn.getUserId() %></h4>
            <br>
            <div class="col-sm-10" >
            <%if(userLoggedIn.getUserImageRename()!=null) {%>
            	<img src="upload/user/<%=userLoggedIn.getUserImageRename()%>" style="max-width: 60%; max-height: 500px;">
            <%}else{ %>
            	<img src="upload/user/basicprofile.png" style="max-width: 60%; max-height: 500px;">
            	<%} %>
            </div>
        </div>
    </div>

<%@ include file="/views/common/footer.jsp" %>