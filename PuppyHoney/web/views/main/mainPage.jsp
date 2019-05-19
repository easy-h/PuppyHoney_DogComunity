
<%@page import="java.util.*, com.ph.board.news.model.vo.NewsBoard, com.ph.infoBoard.model.vo.InfoBoard" %>
<%@page import="common.*, com.ph.board.image.model.vo.ImageBoard" %>
<%

List<NewsBoard> newsList=(List)request.getAttribute("newsList");
List<InfoBoard> infoHitsList = (List)request.getAttribute("infoHitsList");
List<InfoBoard> infoReplyList =(List)request.getAttribute("infoReplyList");
List<InfoBoard> infoList=(List)request.getAttribute("infoList");
List<ImageBoard> imageList =(List)request.getAttribute("imageList"); 
ImgExtract ie=new ImgExtract();
%>

<!-- 뉴스 carousel 감싸는 div -->
<div class="container" id="main">
<!-- 뉴스 carousel div -->
	<div id="main-news" class="carousel slide container pl-0 pr-0" data-ride="carousel">

		<!-- Indicators -->
		<ul class="carousel-indicators">
			<li data-target="#main-news" data-slide-to="0" class="active"></li>
			<li data-target="#main-news" data-slide-to="1"></li>
			<li data-target="#main-news" data-slide-to="2"></li>
		</ul>

		<!-- slideshow 내용 -->
		<div class="carousel-inner container pl-0 pr-0">
		<%for (int i=0;i<3;i++)
		{  if(i==0)
			{%>
			
			
				<div class="carousel-item active">
					<a href="<%=request.getContextPath() %>/board/newsBoardView?num=<%=newsList.get(i).getNewsBoardNum()%>">
						<img src="<%=ie.imgExtract(newsList.get(i).getNewsBoardContent()) %>">
						<div class="carousel-caption">
							<h3 ><%=newsList.get(i).getNewsBoardTitle() %></h3>
						</div>
					</a>
				</div>
			
			<%}else{ %>
			
			
				<div class="carousel-item">
					<a href="<%=request.getContextPath() %>/board/newsBoardView?num=<%=newsList.get(i).getNewsBoardNum()%>">
						<img src="<%=ie.imgExtract(newsList.get(i).getNewsBoardContent()) %>">
						<div class="carousel-caption ">
							<h3 ><%=newsList.get(i).getNewsBoardTitle() %></h3>
						</div>
					</a>
				</div>
			
		<%} 
		}%>
		</div>

		<!-- 이동버튼 -->
		<a class="carousel-control-prev" href="#main-news" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a class="carousel-control-next" href="#main-news" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
		
	<!-- 뉴스 carousel div 끝-->
	</div>
</div>


<!-- 인기게시물+최신게시물 탭  감싸는 div -->
<div class="container col-sm-11 pl-0 pr-0">
<!-- 인기게시물+최신게시물 탭 div -->
<div class="container row  mt-3 pl-0 pr-0">
	<!-- 인기게시물 div -->
	<div class="container col-lg-6 mt-5 mr-auto ">
		
		
		<br>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<div class="h4 ml-4 mr-5">
				<h4>인기게시물</h4>
			</div>
			<li class="nav-item float-right ml-3" >
				<a class="nav-link active" data-toggle="tab" href="#hitsHot">조회순</a>
			</li>
			<li class="nav-item float-right">
				<a class="nav-link" data-toggle="tab" href="#replyHot">댓글순</a>
			</li>
		</ul>
		
		<!-- Tab 내용 -->
		<div class="tab-content">
			<div id="hitsHot" class="container tab-pane active"><br>
				<ul class="list-group">
				<%for(int i=0; i<5; i++)
				{ %>
				    <li class="list-group-item">
				    	<a href="<%=request.getContextPath()%>/freeBoard/boardView?no=<%=infoHitsList.get(i).getBoardNumber() %>&cPage=1&searchType=null&inputText=null&sort=INFO_BOARD_HITS">
				    		<%=infoHitsList.get(i).getBoardTitle() %>&nbsp;[<%=infoHitsList.get(i).getBoardReplyNum() %>]
				    	</a>
				    </li>
				<%} %>
			  	</ul>
			</div>
			<div id="replyHot" class="container tab-pane fade"><br>
				<ul class="list-group">
				    <%for(int i=0; i<5; i++)
					{ %>
				    <li class="list-group-item">
				    	<a href="<%=request.getContextPath()%>/freeBoard/boardView?no=<%=infoReplyList.get(i).getBoardNumber() %>&cPage=1&searchType=null&inputText=null&sort=REPLYNUM">
				    		<%=infoReplyList.get(i).getBoardTitle() %>&nbsp;[<%=infoReplyList.get(i).getBoardReplyNum() %>]
				    	</a>
				    </li>
					<%} %>
			  	</ul>
			</div>
			
		</div>
		
	
	<!-- 인기게시물 div 끝-->	
	</div>
	
	<!-- 최신게시물 div -->
	<div class="container col-lg-6 mt-5 ">
	
		
		<br>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<div class="h4 ml-4 mr-4">
				<h4 class="">최신 게시물</h4>
			</div>
			
		</ul>
		
		<!-- Tab 내용 -->
		<div class="tab-content">
			<div id="infoNew" class="container tab-pane active"><br>
				<ul class="list-group">
				     <%for(int i=0; i<5; i++)
				     { %>
				     <li class="list-group-item">
				    	 <a href="<%=request.getContextPath()%>/freeBoard/boardView?no=<%=infoList.get(i).getBoardNumber() %>&cPage=1&searchType=null&inputText=null&sort=null">
				    		 <%=infoList.get(i).getBoardTitle() %>&nbsp;[<%=infoList.get(i).getBoardReplyNum() %>]
				    	 </a>
				    </li>
					<%} %>
			  	</ul>
			</div>
		</div>
		
	<!-- 최신게시물 div 끝-->	
	</div>
	
	
<!-- 인기게시물+최신게시물 탭 div 끝-->
</div>


<!-- 인기게시물+최신게시물 탭  감싸는 div 끝-->
</div>


 <div class="container mt-5 col-sm-11">
<!-- Page Heading -->
	<div class="h4">
		<h4 >사진 게시판</h4>
	</div>
	<hr>
	<%for(int i=0;i<4;i++)
		{ %>
		<div class="row">
	
		<div class="col-md-6 boardList_image"  style="max-height: 400px;">
			<a href="<%=request.getContextPath()%>/board/imageView?no=<%=imageList.get(i).getBoardNum()%>&searchType=null&inputText=null">	
				<img class="rounded mb-3 mb-md-0" src="<%=ie.imgExtract(imageList.get(i).getBoardContent()) %>" alt="">
			</a>
		</div>
		
		<div class="col-md-6 align-self-center">
			<div class="boardList_image_title">
				<h1><a href="<%=request.getContextPath()%>/board/imageView?no=<%=imageList.get(i).getBoardNum()%>&searchType=null&inputText=null"><%=imageList.get(i).getBoardTitle() %></a></h1>
			</div>
			<br>
			<div class="mt-4 mr-2">
				<span align="right"><i></i><%=imageList.get(i).getBoardId()%></span>
				<div class="float-right">
					<span ><i></i>작성일 : <%=imageList.get(i).getBoardDate()%>&nbsp;</span>
					<span><img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px"><%=imageList.get(i).getBoardHits() %>&nbsp;</span>
					<span><img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px"><%=imageList.get(i).getBoardReplyNum()%>&nbsp;</span>
					<span><img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px"><%=imageList.get(i).getBoardGood() %></span>
				</div>
			</div>
			
		</div>
		
	</div>
	<hr>
		<%}%>
	</div>  
		
	
	


