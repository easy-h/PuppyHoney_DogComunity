<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ph.infoBoard.model.vo.InfoBoard,com.ph.infoBoard.model.vo.InfoReply,java.util.*"%>
<% 
   InfoBoard infoBoard=(InfoBoard)request.getAttribute("infoBoard");
   List<InfoReply> replyList=(List)request.getAttribute("replyList");
   int cPage = Integer.parseInt(request.getAttribute("cPage").toString());
   String searchType = request.getAttribute("searchType").toString();
   String inputText = request.getAttribute("inputText").toString();
   String sort = request.getAttribute("sort").toString();
   
 

   
   
%>
<%@ include file="/views/common/header.jsp" %>
   <style>
       #gibon{
          width:20px;
       }
       #reply{
          width:65px; height:65px;
       }
        #replyContent img{
           max-width:300px; max-height:100%; width:100%; height:100%;
        }
        
        img{max-width:600px; max-height:100%; width:100%; height:100%;}
        
    </style>
    <!-- Smart Editor -->
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
    
<script>




//답글의반응
$(function(){
  $('.btn-reply').on('click',function(){
   <%if(userLoggedIn==null){%>
      alert("로그인후 이용해주세요.");
   <%} else{%>
   if(<%=userLoggedIn!=null %>)
   {
   var form=$("<form action='<%=request.getContextPath()%>/infoReply/infoReplyInsert' method='post'></form>");//<form>요소를 만듬!
    var html="<div class='row form-group pl-5 '>";
    html+= "<textarea rows='3' name='infoReplyContent' id='infoReplyContent' style='resize: none; width: 90%' ></textarea>";
    //html+= 해서 히든으로몇개넣을것(가져온 게시판의 번호, 세션값의 아이디, 레벨(2로 고정), 해당댓글의번호 $(this).val() <<) 
    html+="<input type='hidden' name='infoBoardRef' value='<%=infoBoard.getBoardNumber()%>'/>";
    html+="<input type='hidden' name='infoReplyWriter' value='<%=userLoggedIn.getUserId()%>'/>";
    html+="<input type='hidden' name='cPage' value='<%=cPage%>'/>";
    html+="<input type='hidden' name='searchType' value='<%=searchType%>'/>";
    html+="<input type='hidden' name='inputText' value='<%=inputText%>'/>";
    html+="<input type='hidden' name='sort' value='<%=sort%>'/>";
    html+="<input type='hidden' name='reName' value='<%=userLoggedIn.getUserImageRename()%>'/>";
    html+="<input type='hidden' name='infoReplyLevel' value='2'/>";
    html+="<input type='hidden' name='infoReplyRef' value='"+$(this).val()+"'/>";
    html+="<input type='submit' class='btn pull-right' style='width: 80px;' value='등록'>"
    form.html(html);
    form.insertAfter($(this).parent().parent().parent()).slideDown(800);
    $(this).off('click'); //답글버튼 한번만 인식되게..
   }
  <%}%>
  });
  
  //수정버튼의 반응
  $('.btn-revise').on('click',function(){
    var num = <%=infoBoard.getBoardNumber()%>;
    reviseFrm.boardNum.value=num;
    var url="<%=request.getContextPath()%>/freeBoard/boardRevise";
    reviseFrm.action=url
    reviseFrm.method="post";
    reviseFrm.submit();
  });

  //삭제버튼 반응
  $('.btn-delete').on('click',function(){
    var flag = confirm("정말로 삭제 하시겠습니까?");
    if(flag==false){
      return;
    }
    var num = <%=infoBoard.getBoardNumber()%>;
    deleteFrm.boardNum.value=num;
    var url="<%=request.getContextPath()%>/freeBoard/boardDelete";
    deleteFrm.action=url;
    deleteFrm.method="post";
    deleteFrm.submit();
  });

//추천버튼 반응 (나중에 url 서블릿으로 바꿔주면댐) btn에 board값을 넣지말고 num값에 가져온 board객체의 번호로 줘도됌..
  $('.btnRecommend').on("click",function(){
     <%if(userLoggedIn==null){%>
      alert("로그인후 이용해주세요.");
      
   <%} else{%>
    var num = $(this).val();
    recommendFrm.boardNum.value=num;
    var url="<%=request.getContextPath()%>/freeBoard/boardRecommend";
    recommendFrm.action=url
    recommendFrm.method="post";
    recommendFrm.submit();
    <%}%>
  });

//댓글공감버튼(나중에 url 서블릿으로 바꿔주면댐)
  $(".btn-gonggam").on("click",function(){
     <%if(userLoggedIn==null){%>
      alert("로그인후 이용해주세요.");
      
   <%} else{%>
    var num = $(this).val();
    gonggamFrm.replyNum.value=num;
    var url="<%=request.getContextPath()%>/infoReply/replyGonggam";
    gonggamFrm.action=url
    gonggamFrm.method="post";
    gonggamFrm.submit();
   <%}%>
  });

//댓글삭제버튼(나중에 url 서블릿으로 바꿔주면댐)
  $(".btn-redelete").on("click",function(){
     var flag = confirm("정말로 삭제 하시겠습니까?");
       if(flag==false){
         return;
       }
     var num = $(this).val();
     replyDelete.replyNum.value=num;
     var url="<%=request.getContextPath()%>/infoReply/infoReplyDelete";
     replyDelete.action=url
     replyDelete.method="post";
     replyDelete.submit();
  });

});




//목록버튼
function fn_list(){
      <%if(searchType.equals("null")&&inputText.equals("null")&&sort.equals("null")){%>
         var url="<%=request.getContextPath()%>/freeBoard/boardList";
         listFrm2.action=url
         listFrm2.method="get";
         listFrm2.submit();
         
      <%}else{%>
      var url="<%=request.getContextPath()%>/freeBoard/search";
      listFrm.action=url
      listFrm.method="get";
      listFrm.submit();
      <%}%>
}

</script>

<div class="container row">
   <div class="col-sm-12">
      <table>
         <tbody>
            <tr>
               <td class="align-middle" style="width: 400px">
                  <%=infoBoard.getBoardTitle() %><font style="color: rgb(236, 111, 111);">[<%=replyList.size()%>]</font>
               </td>
            </tr>
            <tr>
               <td>    
                  <font id="BoardViewWriter"><%=infoBoard.getBoardId() %></font>
                  <font id="BoardViewLine">|</font>
                  <font id="BoardViewInquiry">조회 <%=infoBoard.getBoardHit() %></font>
                  <font id="BoardViewLine">|</font>
                  <font id="BoardViewDate"><%=infoBoard.getBoardDate() %></font>
               </td>
            </tr>
         </tbody>
      </table>
      <hr/>
      <div class="pt-2"></div>
      <p class = boardContent style="word-break:break-all;"><%=infoBoard.getBoardContent() %></p>
      <!-- 버튼 벨류값에 보드넘버 가지고있기-->  
      <div class="pt-5" align="center"><button class="btn btnRecommend" value="<%=infoBoard.getBoardNumber()%>">추천 <%=infoBoard.getBoardgood()%></button></div>       
   </div>
</div>
<!--수정삭제버튼 (관리자나 본인일때만)-->
<%if(userLoggedIn!=null){ %>
<%if(userLoggedIn!=null&&userLoggedIn.getUserId().equals("admin")||userLoggedIn.getUserNick().equals(infoBoard.getBoardId())) {%>
<div class="container pt-5">
   <div class="form-group">
      <a class="btn btn-revise">수정</a>
      <font id="BoardViewLine2">l</font>
      <a class="btn btn-delete">삭제</a>
   </div>
</div>
<%} 
}%>
<!--수정삭제 버튼 끝-->

<!--게시판읽기끝 코멘트갯수창-->
<div class="container  pb-3">
   <div class="float-left" style="border-bottom: 1px black solid" >
      <img src="<%=request.getContextPath() %>/images/fa-comment.png"  alt="댓글사진" id="gibon">
      <span class="custom-control-inline text-danger p-0" style="font-size: 20px"><%=replyList.size()%> <font class="p-0" style="font-size: 20px;color: black"> comment</font></span>
   </div>
</div>
<!--코멘트갯수창끝-->
<br><br>
<!--댓글시작-->
<%if(replyList!=null){ 
   for(InfoReply ir:replyList){
      if(ir.getReplyLevel()==1){
       
%>
<div class="container pl-4" >
   <%if(ir.getReName()!=null) {%>
   <div style="float: left!important;"class="pr-4">
   
          <img src="<%=request.getContextPath()%>/upload/user/<%=ir.getReName() %>" id="reply" class="rounded-circle">
     </div>
     <%}else{ %>
     <div style="float: left!important;" class="pr-4">
              <img src="<%=request.getContextPath()%>/upload/user/basicprofile.png" id="reply" class="rounded-circle">
     </div>
     <%} %>
   <div class="form-group">
      <span><b><%=ir.getReplyId() %></b></span> 
      <span style="font-size: 8px; color: rgb(153, 151, 147)" ><%=ir.getReplyDate() %></span>
      <!--댓글,답글의 삭제버튼(운영자,본인) 벨류값안에 코멘트넘버를 가지고 있기.-->
      <%if(userLoggedIn!=null){ %>
      <%if(userLoggedIn!=null&&userLoggedIn.getUserId().equals("admin")||userLoggedIn.getUserNick().equals(ir.getReplyId())) {%>
      <div class="pr-2" style="display: inline;"></div>
      <button class="btn btn-redelete" style="color: #e94a23; padding: 1px 4px; height: 18px;
      line-height: 14px; vertical-align: top;
      border: 1px solid #9f9f9f;
      font-size: 11px;
      background-color: #fff;
      
      font-family: dotum,sans-serif;
      cursor: pointer;" value="<%=ir.getReplyNum()%>">삭제</button>
      <%} 
      }%>
      
      <!-- 클래스이름을 지정하고 벨류값에 코멘트넘버를 넣어서 보내면 됌. function 안에선 벨류값을 $(this).val()로 불러옴.
            자세한건 맴버뷰의 삭제기능을 확인해봐 그럼기능구현가능할껄~?-->
      <button class="btn btn-gonggam" style="float: right; color: #5e23e9;
      padding: 1px 4px;
      
      height: 18px;
      line-height: 14px; vertical-align: top;
      border: 1px solid #9f9f9f;
      font-size: 11px;
      background-color: #fff;
      font-family: dotum,sans-serif;
      cursor: pointer;" value="<%=ir.getReplyNum() %>">공감 <%=ir.getReplyGood()%></button>
   </div>
   <div class="form-group mt-1 pl-5 " id='replyContent' style="word-break:break-all; margin-left: 40px;">
   <%=ir.getReplyContent() %>
      <%-- <p id='replyContent' style="word-break:break-all; padding-left: 40px;" ><%=ir.getReplyContent() %></p> --%>
         <!-- 답글버튼에도 벨류값에 부모댓글 번호를 가지고있어줘야해 그래야 함수에서 불러와서 쓸거니까!(레벨이1일때만 보여줄거야~) -->
      <div class="pb-3"> 
         <button class="btn btn-light btn-reply float-right" style="color: #a7a7a7;font-size: 12px; background:#00FF0000" value="<%=ir.getReplyNum() %>">[답글]</button>
      </div>
   </div>
</div>
<hr/>
<%}else{ %>
<!--댓글1개끝-->
<!--답글 형태 (레벨이 2일때만 보여주기)-->
<div class="container pl-5">
   <div class="form-group pl-4">
   <div style="float: left!important;" >
   
          <img src="<%=request.getContextPath()%>/images/ic_reply.png" id="gibon">
     </div>
      <%if(ir.getReName()!=null) {%>
   <div style="float: left!important;"class="pr-4" >
   
          <img src="<%=request.getContextPath()%>/upload/user/<%=ir.getReName() %>" id="reply" class="rounded-circle">
     </div>
     <%}else{ %>
     <div style="float: left!important;" class="pr-4">
              <img src="<%=request.getContextPath()%>/upload/user/basicprofile.png" id="reply" class="rounded-circle">
     </div>
     <%} %>
      <span><b><%=ir.getReplyId()%></b></span> 
      
      <span style="font-size: 8px; color: rgb(153, 151, 147)" ><%=ir.getReplyDate() %></span>
      
      
      <!--댓글,답글의 삭제버튼(운영자,본인) 벨류값안에 코멘트넘버를 가지고 있기.-->
      <%if(userLoggedIn!=null){ %>
      <%if(userLoggedIn!=null&&userLoggedIn.getUserId().equals("admin")||userLoggedIn.getUserNick().equals(ir.getReplyId())) {%>
      <div class="pr-2" style="display: inline;"></div>
      <button class="btn btn-redelete" style="color: #e94a23; padding: 1px 4px; height: 18px;
      line-height: 14px; vertical-align: top;
      border: 1px solid #9f9f9f;
      font-size: 11px;
      background-color: #fff;
      
      font-family: dotum,sans-serif;
      cursor: pointer;" value="<%=ir.getReplyNum()%>">삭제</button>
      <%} 
      }%>
      
      <!-- 클래스이름을 지정하고 벨류값에 코멘트넘버를 넣어서 보내면 됌. function 안에선 벨류값을 $(this).val()로 불러옴.
          자세한건 맴버뷰의 삭제기능을 확인해봐
      -->
      <button class="btn btn-gonggam" style="float: right; color: #5e23e9;
      padding: 1px 4px;
      float: right;
      height: 18px;
      line-height: 14px; vertical-align: top;
      border: 1px solid #9f9f9f;
      font-size: 11px;
      background-color: #fff;
      
      font-family: dotum,sans-serif;
      cursor: pointer;" value="<%=ir.getReplyNum()%>">공감 <%=ir.getReplyGood() %></button>
   </div>
   <div class="form-group mt-1" style="padding-left:130px; ">
      <p style="word-break:break-all;" id='replyContent'><%=ir.getReplyContent()%></p>             
   </div>
</div>
<hr/>
<%      }
   }
}
%>
<!--답글형태끝-->


<!--댓글입력창-->
    <%if(userLoggedIn==null){ %>
       <div class="text-center"><p><a href="<%=request.getContextPath()%>/user/login?no=<%=infoBoard.getBoardNumber()%>&cPage=<%=cPage%>&searchType=<%=searchType%>&inputText=<%=inputText%>&sort<%=sort%>">로그인</a>을 하시면 댓글을 등록할 수 있습니다.</p></div>
    <%}else{ %>
    <form action="<%=request.getContextPath()%>/infoReply/infoReplyInsert" method="POST" name="infoReplyFrm">
      <div class=" form-group pt-4 pl-4 "> 
        
          <!-- //글 내용이 많으므로 <textarea>태그를 쓴다 rows는 textarea의 높이 조절  -->
          <div class="pr-3">
             <textarea style= "width: 100%" rows="3" name="infoReplyContent" id="textAreaContent"></textarea>
          </div>
          <!--나중에 히든으로  넣어야함-->
          <input type="hidden" name="infoBoardRef" value="<%=infoBoard.getBoardNumber()%>"><!--나중에 헤더에서 가져올 세션값.id를 넣어야함-->
          <input type="hidden" name="infoReplyWriter" value="<%=userLoggedIn.getUserId()%>"/>
          <input type="hidden" name="infoReplyLevel" value="1"/>
        <input type="hidden" name="infoReplyRef" value="0"/><!-- 댓글의 번호 -->
        <input type='hidden' name='reName' value='<%=userLoggedIn.getUserImageRename()%>'/>
        <input type="hidden" name="cPage" value="<%=cPage %>">
        <input type="hidden" name="searchType" value="<%=searchType %>">
        <input type="hidden" name="inputText" value="<%=inputText %>">
        <input type="hidden" name="sort" value="<%=sort %>">
          <div class="pr-3">
             <input type="submit" class="btn pull-right float-right" style="width: 80px;" value="등록" onclick="submitContents()">
           </div>
      </div>
    </form>
   <br><br>
    <hr/>
    <%} %>
    <!--댓글입력창 끝-->
   <!-- 페이지이동버튼 -->
<div class="container pt-3">
   <div class="form-group">
      <input type="button" value="목록" class="btn" onclick="fn_list()">
      <%if(userLoggedIn!=null){ %>
      <form action="<%=request.getContextPath()%>/freeBoard/write" method="post">
            <button type="submit" class="btn float-right"><img src="<%=request.getContextPath() %>/images/pencil.png" alt="연필사진" id="gibon">글쓰기</button>
                <input type="hidden" name="cPage" value="<%=cPage %>">
              <input type="hidden" name="searchType" value="<%=searchType %>">
              <input type="hidden" name="inputText" value="<%=inputText %>">
              <input type="hidden" name="sort" value="<%=sort %>">
            </form>
            <%} %>
   </div>
</div>




      <!--버튼들을 적용시키기 위한 폼-->
      
      <!-- 목록버튼을 적용시키기 위한 폼 -->
      <form name="listFrm">
         <input type="hidden" name="cPage" value="<%=cPage%>">
         <input type="hidden" name="searchType" value="<%=searchType%>">
         <input type="hidden" name="inputText" value="<%=inputText%>">
         <input type="hidden" name="sort" value="<%=sort%>">
      </form>
      
      <form name="listFrm2">
         <input type="hidden" name="cPage" value="<%=cPage%>">
      </form>
      
      
      <!-- 수정버튼을 적용시키기 위한 폼 -->
        <form name="reviseFrm">
            <input type="hidden" name="boardNum">
            <input type="hidden" name="boardTitle" value='<%=infoBoard.getBoardTitle() %>'>
            <input type="hidden" name="boardContent" value='<%=infoBoard.getBoardContent() %>'>
            <input type="hidden" name="cPage" value="<%=cPage %>">
            <input type="hidden" name="searchType" value="<%=searchType %>">
            <input type="hidden" name="inputText" value="<%=inputText %>">
            <input type="hidden" name="sort" value="<%=sort %>">
        </form>

        <!--삭제버튼을 적용시키기 위한 폼-->
        <form name="deleteFrm">
            <input type="hidden" name="boardNum">
             <input type="hidden" name="cPage" value="<%=cPage %>">
        <input type="hidden" name="searchType" value="<%=searchType %>">
        <input type="hidden" name="inputText" value="<%=inputText %>">
        <input type="hidden" name="sort" value="<%=sort %>">
        </form>

        <!--추천버튼을 적용시키기 위한 폼-->
        <form name="recommendFrm">
          <input type="hidden" name="boardNum">
          <input type="hidden" name="cPage" value="<%=cPage %>">
        <input type="hidden" name="searchType" value="<%=searchType %>">
        <input type="hidden" name="inputText" value="<%=inputText %>">
        <input type="hidden" name="sort" value="<%=sort %>">
          <%if(userLoggedIn!=null){ %>
          <input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>">
          <%} %>
        </form>
        
        <!--공감버튼을 적용시키기 위한 폼-->
        <form name="gonggamFrm">
          <input type="hidden" name="replyNum">
          <input type="hidden" name="boardNum" value="<%=infoBoard.getBoardNumber()%>">
          <input type="hidden" name="cPage" value="<%=cPage %>">
        <input type="hidden" name="searchType" value="<%=searchType %>">
        <input type="hidden" name="inputText" value="<%=inputText %>">
        <input type="hidden" name="sort" value="<%=sort %>">
          <%if(userLoggedIn!=null){ %>
          <input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>">
          <%} %>
        </form>
        
        <!-- 댓글삭제를 적용시키기 위한 폼 -->
        <form name="replyDelete">
          <input type="hidden" name="replyNum">
          <input type="hidden" name="boardNum" value="<%=infoBoard.getBoardNumber()%>">
          <input type="hidden" name="cPage" value="<%=cPage %>">
        <input type="hidden" name="searchType" value="<%=searchType %>">
        <input type="hidden" name="inputText" value="<%=inputText %>">
        <input type="hidden" name="sort" value="<%=sort %>">
        </form>
         
    <!-- Smart Editor -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "textAreaContent",
    sSkinURI: "<%=request.getContextPath()%>/se2/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
 
//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", []);
 
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
  
    try {
        elClickedObj.form.submit();
    } catch(e) {
     
    }
}
 
// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/uploadFolder/'+filepath+'">';
    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
}
 
</script>

<%@ include file="/views/common/footer.jsp" %>