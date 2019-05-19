<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.ph.board.lost.model.vo.LostBoard, common.*"%>
     <%
    	List<LostBoard> list=(List)request.getAttribute("list");
     	ImgExtract ie=new ImgExtract();
    %>
    
<%@ include file="/views/common/header.jsp" %>

<script>
	$(function(){

		$('#write').click(function(){
			location.href="<%=request.getContextPath()%>/board/LostBoardWrite"
		});
		function checkInput() {
			alert("버튼이 눌렸습니다.");
		}
		
	});	

	
	$(function(){
		
		 $('.pp').slice(0, 12).show();  // select the first ten
		 $('#load').click(function(e){ // click event for load more
			
			 if($('.pp:hidden').length == 0){ // check if any hidden divs still exist
					alert("더 보여줄 게시물이 없습니다."); // alert if there are none left
				}
			$('.pp:hidden').slice(0, 12).show(); // select next 10 hidden divs and show them
			
			
		}); 
		 
		 
	});
	
    
</script>
<style>
	 div.pp{display: none;} 
</style>

	<div class="container mb-5 col-sm-11">
        <div class="mt-4 form-group">
            <label><h3>분실게시판</h3></label>
            <div class="float-right pt-2 ml-4">
            <button type="button" class="btn btn-dark" data-toggle="collapse" data-target="#demo" aria-expanded="false" aria-controls="collapseExample">
                      	필터
            </button>
            <%if(userLoggedIn!=null){ %>
                <button id="write" class="btn">글쓰기</button>
                <%} %>
            </div>
            
            
        </div>      

        <div>
             
            <div id="demo" class="collapse mb-0">
                
                    <div class="m-2">
                        <form action="<%=request.getContextPath() %>/board/lostBoardFilter" method="post">
			                <span class="badge badge-secondary col-md-2"><h4>지역</h4></span>
			                <br><br>
			                <label for='seoul' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='seoul' value="서울"> 서울
			                </label>
			                <label for='kyeong' class="btn btn-dark">
			                  <input type="checkbox" name="addr" id='kyeong' value="경기도"> 경기도
			                </label>
			                <label for='gangwon' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='gangwon' value="강원도"> 강원도
			                </label>
			                <label for='kyeongsang' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='kyeongsang' value="경상도"> 경상도
			                </label>
			                <label for='jeonra' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='jeonra' value="전라도"> 전라도
			                </label>
			                <label for='chungcheong' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='chungcheong' value="충청도"> 충청도
			                </label>
			                <label for='jeju' class="btn btn-dark ">
			                  <input type="checkbox" name="addr" id='jeju' value="제주도"> 제주도
			                </label>
			                <input type="submit" value='검색' class="btn btn-dark col-md-2 " style="float:right;">
              			</form>
                           
                </div>
            </div>

        </div>
        <hr>
        <div class="container">
            	<div class="row" >
		  <%if(!list.isEmpty()){
      for(int i=0;i<list.size();i++)
      {%>
      
         <div class="col-sm-6 boardList_image pp pb-4 pt-4" style="border-bottom:1px dashed">
         
    
            <a href="<%=request.getContextPath()%>/board/lostBoardView?num=<%=list.get(i).getLostBoardNum()%>">   
               <img class="rounded mb-3 mb-md-0" src="<%=ie.imgExtract(list.get(i).getLostBoardContent())%>" >
            </a>
         </div>
         <div class="col-sm-6 mt-5 pp pb-4 pt-4" align="center" style="border-bottom:1px dashed">
            <div class="boardList_image_title pp" >
               <h2><a href="<%=request.getContextPath()%>/board/lostBoardView?num=<%=list.get(i).getLostBoardNum()%>"><%=list.get(i).getLostBoardTitle() %></a></h2>
            </div>
            <br>
            <span class="float-right">
            작성일: <%=list.get(i).getLostBoardDate() %><br>
            지역 : <%=list.get(i).getLostBoardArea() %><br>
            견종 : <%=list.get(i).getLostBoardType() %>
            </span>
            <span class="float-left">
            작성자: <%=list.get(i).getLostBoardId() %>
            

            
            </span>
            
         
         </div>
     
      
      
      
      <%}
   }%>
         		 </div>
        </div>
        <div class="form-group mt-5" align="center">
			<button id="load" type="button" class="btn w-50">더보기</button>
		</div>
    </div>
	
<%@ include file="/views/common/footer.jsp" %>