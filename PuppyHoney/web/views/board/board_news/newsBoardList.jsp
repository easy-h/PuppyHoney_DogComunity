<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.ph.board.news.model.vo.NewsBoard,common.*" %>
<%@ include file="/views/common/header.jsp" %>
<%
   List<NewsBoard> newsList=(List<NewsBoard>)request.getAttribute("newsList");
   ImgExtract ie=new ImgExtract();
   
%>

<script>
         $(function(){
          $(".pp").slice(0, 15).show(); // select the first ten
          $("#load").click(function(e){ // click event for load more
              
        	  if($(".pp:hidden").length == 0){ // check if any hidden divs still exist
                  alert("더보기 할 게시물이 없습니다"); // alert if there are none left
          }
              $(".pp:hidden").slice(0, 15).show(); // select next 10 hidden divs and show them
              
    });
});
</script>
<style>
	.pp{display:none}
</style>
<div class="container mb-5">
   <div class="mt-4">
   <label><h3>News</h3></label>
   </div>
   <%if(userLoggedIn!=null)
   { 
      if(userLoggedIn.getUserId().equals("admin"))
      { %>
      <div class="float-right pt-2 ml-4">
           <button class="btn " onclick="location.href='<%=request.getContextPath()%>/board/newsBoardWrite'">글쓰기</button>
       </div>
       <%} 
    }%>

</div> 

   
   
<div class="container mt-5 col-sm-11">

   
   <hr>
   
   <div class="row">
   <%if(!newsList.isEmpty()){
      for(int i=0;i<newsList.size();i++)
      {%>
      
         <div class="col-sm-6 boardList_image pp pb-4 pt-4" style="border-bottom:1px dashed">
         
    
            <a href="<%=request.getContextPath()%>/board/newsBoardView?num=<%=newsList.get(i).getNewsBoardNum()%>">   
               <img class="rounded mb-3 mb-md-0" src="<%=ie.imgExtract(newsList.get(i).getNewsBoardContent())%>" >
            </a>
         </div>
         <div class="col-sm-6 mt-5 pp pb-4 pt-4" align="center" style="border-bottom:1px dashed">
            <div class="boardList_image_title pp" >
               <h2><a href="<%=request.getContextPath()%>/board/newsBoardView?num=<%=newsList.get(i).getNewsBoardNum()%>"><%=newsList.get(i).getNewsBoardTitle() %></a></h2>
            </div>
            <div class="mt-4 mr-2 pp" >
            
               <div class="float-right pp">
               	작성일: <%=newsList.get(i).getNewsBoardDate() %>
                  <img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px"><%=newsList.get(i).getNewsBoardReplynum() %>&nbsp;&nbsp;&nbsp;&nbsp;
                  <img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px"><%=newsList.get(i).getNewsBoardGood()%>&nbsp;&nbsp;&nbsp;&nbsp;
                  <img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px"><%=newsList.get(i).getNewsBoardHits()%>
               </div>
            </div>
         
         </div>
     
      
      
      
      <%}
   }%>
   
    </div>
   <div class="form-group mt-5" align="center">
      <button  id='load' type="button" class="btn w-50">더보기</button>
   </div>
   
</div>

<%@ include file="/views/common/footer.jsp" %>