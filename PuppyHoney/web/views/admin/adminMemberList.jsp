
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.ph.user.model.vo.User"%>
<%@ include file="/views/common/header.jsp" %>
<%
   List<User> list = (List<User>)request.getAttribute("list");
   int cPage = Integer.parseInt(request.getAttribute("cPage").toString());
   String pageBar = request.getAttribute("pageBar").toString();
   String searchType = "no";
   String inputText ="";
   if( request.getAttribute("searchType")!=null&&request.getAttribute("inputText")!=null){
      searchType = request.getAttribute("searchType").toString();
      inputText = request.getAttribute("inputText").toString();
   }
   int allUserCount = 0;
   if(request.getAttribute("allUserCount") !=null){
      allUserCount = Integer.parseInt(request.getAttribute("allUserCount").toString());
   }
   int searchCount = 0;
   if(request.getAttribute("searchCount")!=null){
      searchCount = Integer.parseInt(request.getAttribute("searchCount").toString());
   }

%>
<script>

$(document).ready(function(e){

    //상세보기 버튼 정보가지고 팝업창 오픈하기. 
    $('.btnInfo').on("click",function(){
        var userId = $(this).val();
        var url = "<%=request.getContextPath()%>/admin/memberView?userId="+userId;
        var title ="memberView";
      var status="left=100px, top=50px, width=800px; height=600px";
      var popup = window.open(url,title,status);
    });
    
   $('.btnAllMessage').click(function(){
       var url = "<%=request.getContextPath()%>/admin/allMessage";
       var title = "allMessage";
       var status="left=100px, top=50px, width=800px; height=600px";
       var popup = window.open(url,title,status);
    });
});

//검색창에 빈값 확인
function check() {

if(fr.inputText.value == "") {

  alert("검색어를 입력해 주세요.");

  fr.inputText.focus();

  return false;

}else{
 return true;
    }
}

$(function(){
    $('.btnCheck').click(function(){
       var option = $('select#sel1 option:selected').val();
       if(option=="no"){
           alert("분류를 선택해 주세요");
           return false;
       }
    });
    
});


</script>


        <div class="mt-4 form-group">
      <label><h3>회원리스트</h3></label>
       
        <div class="float-right pt-2">
            <form name="fr" action="<%=request.getContextPath()%>/admin/searchUser" onsubmit="return check()">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <select class="form-control" id="sel1" name="searchType">
                            <option value="no">선택</option>
                            <option value="USER_ID">ID</option>
                            <option value="USER_NICK">닉네임</option>
                            <option value="USER_EMAIL">E-MAIL</option>
                        </select>
                    </div>
                    <div class="input-group-append">
                       <input type="text" class="form-control" name="inputText" placeholder="검색어 입력" id=inputText2>
                        <button class="btn btnCheck" type="submit"><img src="<%=request.getContextPath() %>/images/search.png"/></button> 
                    </div>
                </div>
            </form>
        </div>
            
   </div>      
   
   <script>
   <%if(searchType!=null){%>
   var type = '<%=searchType%>';
   var text = '<%=inputText%>';
   document.getElementById('sel1').value=type;
   document.getElementById('inputText2').value=text;

   <%}%>
   </script>
   
   <div class="pr-5 pb-4 pl-4">
       <button class="btn btn-danger btnAllMessage">전체쪽지 보내기</button>
    </div>
   
   <%if(allUserCount!=0){%>
    <div class='pr-5'>
       <p class="float-right pr-5">총 회원수 : <%=allUserCount %>명 </p>
    </div>
    <%}else if(searchCount!=0){ %>
    <div class='pr-5'>
       <p class="float-right pr-5">검색된 회원수 : <%=searchCount %>명 </p>
    </div>
    <%}else{ %>
    <div class='pr-5'>
       <p class="float-right pr-5">총 회원수 : 0명 </p>
    </div>
    <%} %>
            <table class="table table-hover  style="table-layout:fixed; word-break:break-all;">
            <tbody>
                
                <tr style="background: gray">
                    <th>아이디</th>
                    <th>닉네임</th>
                    <th colspan="2">이메일</th>
                </tr>
                <%for(User u : list){
                   if(!u.getUserId().equals("admin")){%>   
                      <tr class="btnb">
                    
                             <td class="align-middle"><%=u.getUserId()%></td>
                         
                    
                          <td class="align-middle">
                              <%=u.getUserNick() %>
                          </td>
                          <td class="align-middle">
                              <%=u.getUserEmail() %>
                          </td>
                          <!-- 클래스이름을 지정하고 벨류값에 아이디를를 넣어서 보내면 됌. function 안에선 벨류값을 $(this).val()로 불러옴.
                            자세한건 보드뷰의 답글기능을 확인해봐
                        -->
                    <td class="align-middle">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btnInfo" value="<%=u.getUserId()%>">상세보기</button>
                    </td>
                  
                </tr>
                <%}
                   }%>
                
                        
                        
                    </tbody>
                </table>        
                <hr/>
        
    <br><br>
    
    
    <!--페이징-->
    <div class="container">
        <div class="form-group">
            <nav class="align-content-center">
                <ul class="pagination justify-content-center">
                    <%=pageBar%>
                </ul>
            </nav>
        </div>
    </div>
<%@ include file="/views/common/footer.jsp" %>