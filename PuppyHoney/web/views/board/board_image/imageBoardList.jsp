<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.ph.board.image.model.vo.ImageBoard, java.util.*,common.*"
    %>
<%@ include file="/views/common/header.jsp" %>
<%	
	List<ImageBoard> list=(List<ImageBoard>)request.getAttribute("list");
	String searchType = (String)request.getAttribute("searchType");
	String inputText = (String)request.getAttribute("inputText");
	ImgExtract ie=new ImgExtract();

%>

<script>

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
	.pp{display:none;}
</style>
<div class="container">
	<div class="mt-4 form-group">
		<label><h3>사진게시판</h3></label>
        <div class="float-right pt-2 ml-4">
            <button class="btn " onclick="fn_write()">글쓰기</button>
            <script>
	            function fn_write(){
	         	   
	            		location.href="<%=request.getContextPath()%>/board/imageWrite";
	            }
            </script>
        </div>
        <div class="float-right pt-2">
            <form name="fr" action="<%=request.getContextPath()%>/imageBoard/search" onsubmit="return check()">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <select class="form-control" id="sel1" name="searchType">
                            <option value=no>선택</option>
                            <option value="IMAGE_BOARD_CONTENT">내용</option>
                            <option value="IMAGE_BOARD_TITLE">제목</option>
                            <option value="USER_NICK">닉네임</option>
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
	
</div>

	
	
<div class="container mt-5 col-sm-11">
<div class="row">
	<!-- Project One -->
	<%for(ImageBoard mb : list) {%>
	
	
		<div class="col-md-6 boardList_image pp pb-4 pt-4" style="border-bottom:1px dashed">
			<a href="<%=request.getContextPath()%>/board/imageView?no=<%=mb.getBoardNum()%>&searchType=null&inputText=null">	
				<img class="rounded mb-3 mb-md-0" src="<%=ie.imgExtract(mb.getBoardContent()) %>" alt="">
			</a>
		</div>
		
		<div class="col-md-6 mt-5 pp pb-4 pt-4 "  style="border-bottom:1px dashed">
			<div class="boardList_image_title pp">
				<h1><a href="<%=request.getContextPath()%>/board/imageView?no=<%=mb.getBoardNum()%>&searchType=null&inputText=null"><%=mb.getBoardTitle() %></a></h1>
			</div>
			<br>
			<div class="mt-4 mr-2 pp">
				<span align="right"><i></i><%=mb.getBoardId()%></span>
				<div class="float-right pp">
					<span ><i></i>작성일 : <%=mb.getBoardDate()%>&nbsp;</span>
					<br>
					<span><img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px"><%=mb.getBoardHits() %>&nbsp;</span>
					<span><img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px"><%=mb.getBoardReplyNum()%>&nbsp;</span>
					<span><img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px"><%=mb.getBoardGood() %></span>
				</div>
			</div>
			
		</div>
		
	
	
	<%} %>
	</div>
	<!-- /.row -->
	<div class="form-group mt-5" align="center">
      <button  id='load' type="button" class="btn w-50">더보기</button>
   </div>
	
</div>
	
	
<%@ include file="/views/common/footer.jsp" %>