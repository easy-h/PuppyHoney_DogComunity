<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.ph.board.lost.model.vo.LostBoard, common.*, com.ph.board.lost.model.vo.LostBoardComment, java.util.*"%>
<%@ include file="/views/common/header.jsp" %>
    <%
		LostBoard lb=(LostBoard)request.getAttribute("lb");
    	List<LostBoardComment> list=(List)request.getAttribute("list");
    	ImgExtract ie=new ImgExtract();
    	if(userLoggedIn==null)
    	{
    		System.out.println("null");
    	}else
    	{
    		System.out.println("not null");
    	}
    	System.out.println(lb.getLostBoardNum());
    %>
    
    
<style>
 
   
/* .side-bar {
	position: fixed; 
	right: 220px;
	display: inline-block;
	width: 360px;
}
	
 	
@media (max-width:450px) {
	.side-bar {
		position: relative;
		right: 0;
		width: 100%;
	}
} */

.side-bar {
   position: fixed; 
   right: 600px;
   display: inline-block;
   width: 500px;
}
    
 /* 모바일로 바꿨을 때 */   
@media (max-width:1930px) {
   .side-bar {
      position: relative;
      right: 0;
      width: 100%;
      height:70%;
   }
}
img{
	max-width:600px; max-height:100%; width:100%; height:100%;
}

	
</style>
<script>

$(function()
		{
		 	//답글버튼 반응
		$('.btn-reply').on('click',function(){
			
			<%if(userLoggedIn==null){%>
			alert("로그인 후 이용 가능합니다.");
			location.href="/PuppyHoney/user/login";
		<%}
		else {%>
		if(<%=userLoggedIn!=null%>)
			{
					var form=$("<form action='<%=request.getContextPath()%>/Reply/lostReplyInsert' method='post'></form>");
					var html="<div class='row form-group pl-5'>";
					html+="<input type='hidden' name='lostBoardRef' value='<%=lb.getLostBoardNum()%>'/>";
					html+="<textarea rows='2' cols='70' name='lostReplyContent' id='placeReplyContent' style='resize: none;' ></textarea>"
					html+="<input type='hidden' name='lostReplyWriter' value='<%=userLoggedIn.getUserId()%>'/>";
					html+="<input type='hidden' name='lostReplyLevel' value='2'/>";
				    html+="<input type='hidden' name='lostReplyRef' value='"+$(this).val()+"'/>";
					html+="&nbsp;&nbsp;<input type='submit' class='btn float-rigt btn-sm' value='등록'></div>";
					form.html(html);
					form.insertAfter($(this).parent().parent().parent()).slideDown(800);
					$(this).off('click');
			}
		<%}%>
		
		}); 
			
			$('.btnModi').on('click',function()
			{
				
				var url="<%=request.getContextPath()%>/board/lostBoardModify";
				modifyFrm.action=url;
				modifyFrm.method="post";
				modifyFrm.submit();
			});
			
			$('.btnDele').on('click',function()
			{
			 var flag = confirm("삭제 하시겠습니까?");
			    if(flag==false){
			      return;
			    }
			    
			    var url="<%=request.getContextPath()%>/board/lostBoardDelete";
			    deleFrm.action=url;
			    deleFrm.method="post";
			    deleFrm.submit();
			});
			
			$('.replyDele').on('click',function()
				{
					var flag = confirm("삭제 하시겠습니까?");
				    if(flag==false){
				      return;
				    }
				    var num = $(this).val();
				    replyDelete.replyNum.value=num;
				    var url="<%=request.getContextPath()%>/Reply/lostReplyDelete";
				    replyDelete.action=url;
				    replyDelete.method='post';
				    replyDelete.submit();
				});
		
		});
</script>

<div class="container">     
	

    <div class="row">
		<div class="col-lg-8">
			<h2><%=lb.getLostBoardTitle() %></h2>
			<hr>
			<p><%=lb.getLostBoardDate() %></p>
			<hr>
			<blockquote class="blockquote">
				<p><%=lb.getLostBoardContent() %></p>
			
				<%if(userLoggedIn!=null){ %>
					<%if(lb.getLostBoardId().equals(userLoggedIn.getUserId())||userLoggedIn.getUserId().equals("admin")){ %>
					<button class='btn btn-danger btn-sm btnModi'>수정</button>&nbsp;<button class='btn btn-danger btn-sm btnDele'>삭제</button>
					<%} 
				}%>
			</blockquote>
			<hr>			
		</div>
          
		<div class="side-bar col-lg-4" style="height:880px;">
			<div id="side" class="card mb-4" style="height:70%;">
			<h5 class="card-header alert-danger">상세 정보</h5>
				<div class="card-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>상세정보</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>분실지역</td>
								<td><%=lb.getLostBoardArea() %></td>
							</tr>
							<tr>
								<td>연락가능번호</td>
								<td><%=lb.getLostBoardPhone() %></td>
							</tr>
							<tr>
								<td>견종</td>
								<td><%=lb.getLostBoardType() %></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div> 
		</div> 
	</div>
	
	<%if(userLoggedIn==null){%>
          <div class="text-center"><p><a href="<%=request.getContextPath()%>/user/login">로그인</a>을 하시면 댓글을 등록할 수 있습니다.</p></div>
          <%}
	else{ %>
          <div class="card my-4">
          <h5 class="card-header alert-danger">댓글</h5>
          	<div class="card-body">
             <form action="<%=request.getContextPath()%>/Reply/lostCommentInsert" method="post" name="lostReplyFrm">
               <div class="form-group">
                 <textarea class="form-control" style="width: 100%"  rows="2" name="lostReplyContent" id="textAreaContent"></textarea>
               </div>
               <button type="submit" class="btn btn-primary" style="float: right;" onclick="submitContents()">등록</button>
	          <input type="hidden" name="lostBoardRef" value='<%=lb.getLostBoardNum()%>'/>
	          <input type="hidden" name="lostReplyWriter" value="<%=userLoggedIn.getUserId()%>"/>
	          <input type="hidden" name="lostReplyLevel" value='1'/>
			  <input type="hidden" name="lostReplyRef" value='0'/><!-- 부모 댓글의 번호 -->
             </form>
           </div>
         </div>
	  <hr>
	  <%} %>
		  
		   <!-- 댓글 list Comment -->
          <%if(list!=null){
        	  for(LostBoardComment lbc: list){
				if(lbc.getLostReplyLevel()==1){        		  
          %>
          <div class="media mb-4"> 
            <!-- 답글부분(운영자,본인) 밸류값안에 코멘트 넘버를 가지고 있기 -->
            <div class="media-body">
              <h5 class="mt-0"><b><%=lbc.getLostReplyId() %></b>&nbsp;<small style="font-size: 10px;"><%=lbc.getLostReplyDate() %></small>&nbsp;
              <%if(userLoggedIn!=null){%>
              <button style="float:right;  padding: 2px 4px;"class='btn btn-danger btn-sm replyDele' value="<%=lbc.getLostReplyNum() %>">삭제</button>&nbsp; </h5>
	           <%} %>   
	              <div>
		              <span class="pl-2" style="font-size: 14px">
		              	<%=lbc.getLostReplyContent() %>
		              </span>
		              <%if(userLoggedIn!=null){%>
						<button style="float:right;"class='badge badge-light btn btn-sm btn-reply' value="<%=lbc.getLostReplyNum() %>">답글</button>              
	                  <%} %>
	              </div>
              
            </div>
          </div>
		  <hr>
	
		 <% }else{%> 
		 	<!-- 답글 -->
              <div class="media mt-4 pl-5">
                
                <div class="media-body">
                  <h5 class="mt-0"><b><%=lbc.getLostReplyId()%></b>&nbsp;<small style="font-size: 10px;"><%=lbc.getLostReplyDate() %></small>
                  <%if(lbc.getLostReplyId().equals(userLoggedIn.getUserNick())||userLoggedIn.getUserId().equals("admin")){ %>
                  <button style="float:right;  padding: 2px 4px;"class='btn btn-danger btn-sm replyDele' value="<%=lbc.getLostReplyNum()%>">삭제</button></h5><%} %>
                  <div>
                  <span class="pl-2" style="font-size: 14px">
                		  <%=lbc.getLostReplyContent()%>
                  </span>	
                  </div>
                </div>
             
                </div>
                <hr>
		 
		 
		 <% }
				}
        	  }%>			
</div>
           
	   <form name="modifyFrm">
	    	<input type="hidden" name="lostBoardNum" value='<%=lb.getLostBoardNum() %>'>
	    	<input type="hidden" name="lostBoardTitle" value='<%=lb.getLostBoardTitle() %>'> 
	    	<input type="hidden" name="lostBoardContent" value='<%=lb.getLostBoardContent() %>'>
	    	<input type="hidden" name="lostBoardArea" value='<%=lb.getLostBoardArea() %>'>
	    	<input type="hidden" name="lostBoardPhone" value='<%=lb.getLostBoardPhone() %>'>
	    	<input type="hidden" name="lostBoardType" value='<%=lb.getLostBoardType() %>'>	    	
	    </form> 
	    
	    <form name="deleFrm">
			 <input type="hidden" name="lostBoardNum" value="<%=lb.getLostBoardNum()%>">
			 <%if(userLoggedIn!=null) {%>
				<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>"/>
			 <%} %>
	    </form>
	    
	    <!-- 댓글 삭제 폼 -->
    	<form name="replyDelete">
     	  <input type="hidden" name="replyNum">
     	  <input type="hidden" name="lostBoardNum" value=<%=lb.getLostBoardNum() %>>
     	  
     	</form>
		 

<%@ include file="/views/common/footer.jsp" %>