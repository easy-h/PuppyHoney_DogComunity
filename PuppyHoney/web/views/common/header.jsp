<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ph.user.model.vo.User"%>
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
    	$(function() {
    		$('#myPage').on('click',function(){
    			var userId=userLoggedIn.getUserId();
    			var url=href="<%=request.getContextPath()%>/myHomepage";
    			this.attr("value",userId);
    			this.attr("action",url);
    			this.method("post");
    			this.submit();
    		})
			
		})
    </script>
</head>
<body>
  <!-- 전체 div -->
  <div class="container"> 
    <!-- 배경 제외한 전체 div -->
    <div class="mt-5 pl-0 pr-0 bg-light shadow">
      <!-- 메뉴바 -->
      <nav id="menu_bar" class="navbar navbar-expand-lg navbar-light sticky-top mb-3">
        <div class="container">
          <label><a id="logo" class="navbar-brand" href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/images/dog.png" > PuppyHoney</a></label>
          <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/board/newsBoardList"><strong>News</strong></a>
              </li> 
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="community" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 	<strong>커뮤니티</strong>
                </a>

                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="community">
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/freeBoard/boardList">자유게시판</a>
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/board/imageBoardList">사진게시판</a>

                </div>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/lostBoard"><strong>분실</strong></a>
              </li> 
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/board/placeBoardList"><strong>장소</strong></a>
              </li>
              <%if(userLoggedIn==null){ %> 
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/user/login"><strong>로그인</strong></a>
              </li>
              <%}else{ %>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="myPage" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 	<strong>MyPage</strong>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="myPage">
                  <a id="myPage" class="dropdown-item" href="<%=request.getContextPath()%>/myPage">내정보</a>
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/user/logout">로그아웃</a>
                </div>
              </li>
              <%} %>
              <%if(userLoggedIn!=null){%>
              <%if(userLoggedIn.getUserId().equals("admin")){ %>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="adminPage" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 	<strong>ADMIN</strong>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="adminPage">
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/admin/userList">회원 리스트</a>
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/admin/AcceptBoardtList">영업승인 게시판</a>
                </div>
              </li>
              <%} 
              }%>
            </ul>
          </div>
        </div>
      <!-- 메뉴바 끝 -->
      </nav>
    
      