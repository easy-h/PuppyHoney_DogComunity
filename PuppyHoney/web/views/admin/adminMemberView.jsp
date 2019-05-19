

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.ph.user.model.vo.User,java.util.*"%>
<%
   User user=(User)request.getAttribute("user");
%>

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

        <div class="panel-heading pt-4 "><h2><%=user.getUserId() %>의 회원 정보</h2></div> 
        <div class="pb-4">
            
            <!--검색창-->
            <!--ex) ?searchType=선택$searchKeyword=입력-->
            
        </div>
            <table class="table table-bordered"  style="table-layout:fixed; word-break:break-all;">
            <tbody>
                
                <tr>
                   <%if(user.getUserImageRename()!=null){ %>
                    <td rowspan="4" style="width: 160px;" class="align-middle"><img class="card-img-top" src="<%=request.getContextPath()%>/upload/user/<%=user.getUserImageRename() %>" width="140" height="140"></td>
                    <%}else{ %>
                    <td rowspan="4" style="width: 160px;" class="align-middle"><img class="card-img-top" src="<%=request.getContextPath()%>/upload/user/basicprofile.png" width="140" height="140"></td>
                    <%} %>
                    <td colspan="4">아이디 : <%=user.getUserId() %></td>
                    
                </tr>
                <tr>
                    <td colspan="4" class="align-middle"> 이름 : <%=user.getUserName() %></td>
                </tr>
                <tr>
                    
                    <td colspan="4" class="align-middle">닉네임 : <%=user.getUserNick() %></td>
                    <!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
                </tr>
                <tr>
                    <td colspan="4" class="align-middle">아메일 : <%=user.getUserEmail() %> </td>
                </tr>
                <tr>
                    <th class="align-middle text-center">생일</th>
                    <th class="align-middle text-center">강아지 이름</th>
                    <th class="align-middle text-center">강아지 생일</th>
                    <th class="align-middle text-center">가입일</th>
                    <th class="align-middle text-center">탈퇴여부</th>
                </tr>
                <tr>
                    <td class="align-middle text-center"><%=user.getUserBirth() %></td><!--생일-->
                    <td class="align-middle text-center" ><%=user.getUserDogName() %></td><!--강아지이름-->
                    <td class="align-middle text-center"><%=user.getUserDogBirth() %></td><!--강아지생일-->
                    <td class="align-middle text-center"><%=user.getUserJoinDate() %></td><!--가입일-->
                    <td class="align-middle text-center"><%=user.getUserLeave() %></td><!--탈퇴여부-->
                </tr>
                
             
                        
                    </tbody>
                </table>
                <div class="form-group pt-4 " align='center'>
                    <form method="post" action="<%=request.getContextPath()%>/admin/memberDelete">
                        <button type="submit" onclick="return fn_delete()" class="btn">회원탈퇴</button>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="hidden" name="userId" value="<%= user.getUserId()%>"><!--네임값에 불러온 아이디 넣기-->
                        
                        <button class="btn" onclick="self.close();">닫기</button>
                    </form>
                        
                </div>
                <script>

    function fn_delete(){
       var check = '<%=user.getUserLeave()%>';
       if(check == 'Y'){
          alert("이미 탈퇴된 회원입니다.");
          return false;
       }
        var flag = confirm("정말로 탈퇴시키겠습니까?");
        if(flag==false){
            return false;
        }
    }
</script>
                
</body>
</html>