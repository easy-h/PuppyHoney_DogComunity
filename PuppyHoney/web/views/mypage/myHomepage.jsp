<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<style>
	.vBook>div{
		min-height: 50px;
	}
	.vBook{border: 5px solid #c6cace;}
	.balloon_left {  
	 position:absolute;
	 left:15%;	 
	 width: 40%; 
	 height:auto;
	 background:pink; 
	 border-radius: 10px;
	}
	.balloon_left:before { 
	 border-top: 10px solid transparent; 
	 border-left: 0px solid transparent; 
	 border-right: 10px solid pink; 
	 border-bottom: 10px solid transparent; 
	 content:""; 
	 position:absolute;
	 top:6px;
	 left:-9px;  
	}

	.balloon_right {  
	 position:absolute;
	 right:15%;
	 width:40%; 
	 height:auto;
	 background:burlywood; 
	 border-radius: 10px;
	}
	.balloon_right:after {
	 position:absolute; 
	 border-top: 10px solid transparent; 
	 border-left: 10px solid burlywood; 
	 border-right: 0px solid transparent; 
	 border-bottom: 10px solid transparent; 
	 content:""; 
	 position:absolute;
	 top:6px;
	 right:-9px;  
	}
</style>
	<div class="container col-sm-8">
        <h2>My Homepage</h2>
        <div>
			<img src="http://cfile239.uf.daum.net/image/0351F63750FD1275352FB8" class="img-thumbnail" alt="Cinque Terre" style="width: 100%; height: auto;">
        </div>
        <br>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#board">게시글</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#visitorBook">방명록</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div id="board" class="container tab-pane active"><br>
            <h3>게시글</h3>
            	<table class="table table-hover ">
					<tbody>
					<tr>
						<td class="align-middle">
							<a href="#">
								한국에서 더워 죽을수도 있나요?
							</a>
						</td>
						<!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
						<td class="align-middle">
							<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px">5
						</td>
						<td>
							<div class="block">
								오상현
							</div>
							<div style="color: #ccc; font-size: 12px;">
								2016.12.02 18:16:25
							</div>
						</td>
					</tr>
					<tr>
						<td class="align-middle">
							<a href="#">
								한국에서 더워 죽을수도 있나요?
							</a>
						</td>
						<!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
						<td class="align-middle">
							<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px">5
						</td>
						<td>
							<div class="block">
								오상현
							</div>
							<div style="color: #ccc; font-size: 12px;">
								2016.12.02 18:16:25
							</div>
						</td>
					</tr>
					<tr>
						<td class="align-middle">
							<a href="#">
								한국에서 더워 죽을수도 있나요?
							</a>
						</td>
						<!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
						<td class="align-middle">
							<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px">5
						</td>
						<td>
							<div class="block">
								오상현
							</div>
							<div style="color: #ccc; font-size: 12px;">
								2016.12.02 18:16:25
							</div>
						</td>
					</tr>
					<tr>
						<td class="align-middle">
							<a href="#">
								한국에서 더워 죽을수도 있나요?
							</a>
						</td>
						<!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
						<td class="align-middle">
							<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px">5
						</td>
						<td>
							<div class="block">
								오상현
							</div>
							<div style="color: #ccc; font-size: 12px;">
								2016.12.02 18:16:25
							</div>
						</td>
					</tr>
					<tr>
						<td class="align-middle">
							<a href="#">
								한국에서 더워 죽을수도 있나요?
							</a>
						</td>
						<!-- 브라우저 사이즈가 폰크기초과일때ㅐ만 나오게 -->
						<td class="align-middle">
							<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px">3&nbsp;
							<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px">5
						</td>
						<td>
							<div class="block">
								오상현
							</div>
							<div style="color: #ccc; font-size: 12px;">
								2016.12.02 18:16:25
							</div>
						</td>
					</tr>
					</tbody>
				</table>
            </div>
            <div id="visitorBook" class="container tab-pane fade">
            <div class="card mb-2 mt-2">
	            <h2 class="card-header alert-danger">방명록</h2>
	            <div class="card-body">
	              <form action="" method="post">
	                <div class="form-group">
	                  <textarea class="form-control" rows="3"></textarea>
	                </div>
	                <button type="submit" class="btn btn-primary" style="float: right;">등록</button>
	              </form>
	            </div>
	         </div>
            	
            	<div class="vBook">
            	<br>
            		<div>
            			<div class="balloon_left">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            		<div>
            			<div class="balloon_right">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            		<div>
            			<div class="balloon_right">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            		<div>
            			<div class="balloon_right">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            		<div>
            			<div class="balloon_right">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            		<div>
            			<div class="balloon_left">
            				<label>안녕하세요.</label>
            			</div>
            		</div>
            	</div>
            </div>
        </div>
    </div>
    
<%@ include file="/views/common/footer.jsp" %>