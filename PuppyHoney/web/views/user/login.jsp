
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
   int no=1;
   int cPage = 1;
   String searchType = "";
   String inputText = "";
   String sort = "";
   
      if(request.getParameter("searchType")!=null){
         no = Integer.parseInt(request.getAttribute("no").toString());
         cPage = Integer.parseInt(request.getAttribute("cPage").toString());
         searchType =(String)request.getAttribute("searchType");
         inputText =(String)request.getAttribute("inputText");
         sort =(String)request.getAttribute("sort");
      }
   
   Cookie[] cookies=request.getCookies();
   String saveId=null;
   if(cookies!=null)
   {
      for(Cookie c:cookies)
      {
         if(c.getName().equals("saveId"))
         {
            saveId=c.getValue();
         }
      }
   }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PuppyHoney</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

 <body >
      <div class="container mt-5 p-5">
      <div class="p-4"align="center"><label><a id="logo" class="navbar-brand" href="<%=request.getContextPath() %>/index.jsp"><img src="<%=request.getContextPath() %>/images/dog.png" > PuppyHoney</a></label></div>
        <div class="container col-sm-5 shadow p-4 rounded" style="background:white;">
            <form action="<%=request.getContextPath() %>/user/loginEnd" method="post">
            <%if(!searchType.equals("")){ %>
                 <input type="hidden" name="no" value="<%=no %>">
                 <input type="hidden" name="cPage" value="<%=cPage %>">
              <input type="hidden" name="searchType" value="<%=searchType %>">
              <input type="hidden" name="inputText" value="<%=inputText %>">
              <input type="hidden" name="sort" value="<%=sort %>">
            <%} %>
            <div class="form-group">
              <label for="userId">ID </label>
              <div class="form-group form-check-inline float-right">
                <label class="form-check-label ml-3">
                  <input class="form-check-input" type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":"" %>>ID기억하기
                </label>
              </div>
              <input type="text" class="form-control" id="userId" name="userId" placeholder="ID를 입력하세요" value='<%=saveId!=null?saveId:"" %>' required>
            </div>
            <div class="form-group">
              <label for="userPw">Password</label>
              <input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요" required>
            </div>
              <div class="form-group">
                <button type="submit" class="btn w-25 "  align="center">로그인</button>
                <div class="float-right">
                   <a href="<%=request.getContextPath() %>/user/register"  class="p-2">회원가입</a>
                   <a href="<%=request.getContextPath() %>/user/findPwd"  class="p-2">비밀번호찾기</a>
                </div>
              </div>
              
            </form>
        </div>
   </div>
</body>
</html>