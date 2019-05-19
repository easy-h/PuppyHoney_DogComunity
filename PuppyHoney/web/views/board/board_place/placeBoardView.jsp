<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="com.ph.board.place.model.vo.PlaceBoard, java.util.*, common.ImgExtract,com.ph.board.place.model.vo.PlaceBoardComment"%>

 <%
 	PlaceBoard plBoard=(PlaceBoard)request.getAttribute("plBoard");
 	List<PlaceBoardComment> pbReply=(List)request.getAttribute("pReplyList");
 	String addr=plBoard.getPlBoardAddr();
 	String Addr[]=addr.split(",");
 	String pAddr=Addr[1]+" "+Addr[2];	//주소 1은 기본주소 주소2는 상세주소
 	
 	
 %>
<style>


#replyImg{
	max-width:50px;
   max-height:50px;
}
   
.side-bar {
   position: absolute; 
   right: 410px;
   width: 380px;
}
    

img{
	max-width:600px; max-height:500px; width:100%; height:100%;
}

#gibon{
width:20px;}


   
</style>
<!-- 지도 api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29d28c77afa06b8d3797cd516b310f0f&libraries=services"></script>
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29d28c77afa06b8d3797cd516b310f0f"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
<script>
	$(function()
	{
		//답글버튼 반응
		$('.btn-reply').on('click',function(){
			<%if(userLoggedIn==null){%>
				alert("로그인 후 이용 가능합니다.");
				location.href="/PuppyHoney/user/login";
			<%}else {%>
			if(<%=userLoggedIn!=null%>)
				{
					var form=$("<form action='<%=request.getContextPath()%>/Reply/placeReplyInsert' method='post'></form>");
					var html="<div class='row form-group pl-5'>";
					html+="<input type='hidden' name='placeBoardRef' value='<%=plBoard.getPlBoardNum()%>'/>";
					html+="<textarea rows='2' cols='70' name='placeReplyContent' id='placeReplyContent' style='resize: none;' ></textarea>"
					html+="<input type='hidden' name='placeReplyWriter' value='<%=userLoggedIn.getUserId()%>'/>";
					html+="<input type='hidden' name='placeReplyLevel' value='2'/>";
				    html+="<input type='hidden' name='placeReplyRef' value='"+$(this).val()+"'/>";
					html+="&nbsp;&nbsp;<input type='submit' class='btn float-rigt btn-sm' value='등록'>";
					form.html(html);
					form.insertAfter($(this).parent().parent().parent()).slideDown(800);
					$(this).off('click');
				}
			
			<%}%>
		});
		
		$('.btnModi').on('click',function()
		{
			var num=<%=plBoard.getPlBoardNum()%>; 
			modifyFrm.placeBoardNum.value=num;
			var url="<%=request.getContextPath()%>/board/placeBoardModify";
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
		    var num=<%=plBoard.getPlBoardNum()%>; 
		    deleFrm.boardNum.value=num;
		    var url="<%=request.getContextPath()%>/board/placeBoardDelete";
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
		    var url="<%=request.getContextPath()%>/Reply/placeReplyDelete";
		    replyDelete.action=url;
		    replyDelete.method='post';
		    replyDelete.submit();
		});
	});
	
</script>

	<%if(plBoard!=null){ %>
   <div class="container">
   	  <div class='row'>
      
      <br>
     </div>
     <div class="row">
      <!-- Sidebar Widgets Column -->
        <div class="side-bar col-lg-3">
          <!-- Search Widget -->
          <div id="side" class="card mb-4">
            <h6 class="card-header alert-danger">상세 정보</h6>
            <div class="card-body">
            <div>
	            <h6 class="alert alert-danger">입장가능</h6>
	            <ul>
	              <li><span class="badge badge-light"><%=plBoard.getPlBoardDogSize()%></span></li>
	            </ul>
            </div> 
          
            <div>
              <h6 class="alert alert-danger">연락처</h6>
              <ul>
                <li><h6><%=plBoard.getPlBoardPhone()%></h6></li>
              </ul>
            </div>
              <div>
              <h6 class="alert alert-danger">영업시간 (Open - Close)</h6>
              <ul>
                <li>
               		<h6><%=plBoard.getPlBoardTime()%></h6>
                </li>
              </ul>
            </div>
           
            <h6  class="alert alert-danger">주소</h6>
            <div align='center'>
                <ul>
                  <li><h6><%=pAddr%></h6></li>
                </ul>
                <div id="map" style="width:200px;height:180px;"></div>
              </div>
                   
            </div>
          </div>
        </div>
		</div>
        <!-- ./Sidebar -->
        
             
        
        <div class="col-lg-8">
          <img class="img-fluid rounded mx-auto d-block" src="<%=new ImgExtract().imgExtract(plBoard.getPlBoardContent())%>"  >
    		
    		<br><br>
           <table class="table text-center" >
           	  <tr>
           	  	<th class="table-active" colspan='3'><%=plBoard.getPlBoardTitle()%></th>
           	  </tr>
              <tr>
                <th class="table-Light table-danger"> <%=plBoard.getPlBoardId()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th class="table-Light table-danger"> <%=plBoard.getPlBoardDate()%></th>
              </tr>
		 	</table>
          <blockquote align='center'class="blockquote">
          <p><%=plBoard.getPlBoardContent()%></p>
          <br><br><br><br><br><br><br><br>
          <%if(userLoggedIn!=null){ %>
			<%if(plBoard.getPlBoardId().equals(userLoggedIn.getUserNick())||userLoggedIn.getUserId().equals("admin")){ %>
          <button alige='center' style="padding: 2px 4px;"class='btn btn-danger btn-sm btnModi'>수정</button>&nbsp;<button alige='center'style="padding: 2px 4px;"class='btn btn-danger btn-sm btnDele'>삭제</button>
          </blockquote>
          

          <hr>
          
          <%} 
          }
          }%>
          
          
       
		  
        
          <!-- 댓글 쓰기 Form -->
          <%if(userLoggedIn==null){%>
          <div class="text-center"><p>댓글은 <a href="<%=request.getContextPath()%>/user/login">&nbsp;로그인</a>후 이용 가능합니다.</p></div>
          <%}else{ %>
          <div class="card my-4">
            <h5 class="card-header alert-danger">댓글</h5>
            <div class="card-body">
              <form action="<%=request.getContextPath()%>/Reply/placeReplyInsert" method="post" name="placeReplyFrm">
                <div class="form-group">
                  <textarea class="form-control" style="width: 100%"  rows="2" name="placeReplyContent" id="textAreaContent"></textarea>		<!-- 댓글 적는 부분 -->
                </div>
                <button type="submit" class="btn btn-primary" style="float: right;" onclick="submitContents()">등록</button>
		          <input type="hidden" name="placeBoardRef" value='<%=plBoard.getPlBoardNum()%>'/>
		          <input type="hidden" name="placeReplyWriter" value="<%=userLoggedIn.getUserId()%>"/>
		          <input type="hidden" name="placeReplyLevel" value='1'/>
				  <input type="hidden" name="placeReplyRef" value='0'/><!-- 부모 댓글의 번호 -->
              </form>
            </div>
          </div>
		  <hr>
		  <%} %>
		  
		  
		  
		  
          <!-- 댓글 list Comment -->

          
        <%if(pbReply!=null){
        	  for(PlaceBoardComment pr:pbReply){
				if(pr.getPlBoardReplyLevel()==1){  %>
         	<div class="media mb-4">
            <!-- 답글부분(운영자,본인) 밸류값안에 코멘트 넘버를 가지고 있기 -->
            <div class="media-body">
              <h5 class="mt-0"><b><%=pr.getPlBoardReplyId() %></b>&nbsp;<small style="font-size: 10px;"><%=pr.getPlBoardDate() %></small>&nbsp;
              <%if(userLoggedIn!=null){%>
              <button style="float:right;  padding: 2px 4px;"class='btn btn-danger btn-sm replyDele' value="<%=pr.getPlBoardReplyNo()%>">삭제</button>&nbsp;
              <%} %>
              </h5>
	              <div>
		              <span class="pl-2" style="font-size: 14px">
		              	<%=pr.getPlBoardReplyContent() %>
		              </span>
		              <%if(userLoggedIn!=null){%>
						<button style="float:right;"class='badge badge-light btn btn-sm btn-reply'value=<%=pr.getPlBoardReplyNo() %>>답글</button> 
						<%} %>             
	              </div>
	           </div>
          </div>
	     	<hr>
	            <%}else{ %>
	          <!-- 답글 -->
              <div class="media mt-4 pl-3">
                <div class="media-body">
                
                  <h5 class="mt-0" style='font-size:16px;'><img src="<%=request.getContextPath() %>/images/ic_reply.png" alt="답글표시" id='gibon'><b><%=pr.getPlBoardReplyId()%></b>&nbsp;<small style="font-size: 10px;"><%=pr.getPlBoardDate() %></small>
                  <%if(userLoggedIn!=null){
                  	if(plBoard.getPlBoardId().equals(userLoggedIn.getUserNick())||userLoggedIn.getUserId().equals("admin")){ %>
                  <button style="float:right;  padding: 2px 4px;"class='btn btn-danger btn-sm replyDele' value="<%=pr.getPlBoardReplyNo()%>">삭제</button><%} }%></h5>
                  <div>
                  <span class="pl-2" style="font-size: 14px">
                		  <%=pr.getPlBoardReplyContent()%>
                  </span>	
                  </div>
                </div>
             
                </div>
                <hr>
        		<%} %>
          
			
	   <%	} 	
		  }%> 
		  
		  
         
          
        </div>			<!-- 본문내용 -->
      </div>			<!-- 전체컨테이너 -->
    
    <!-- 수정버튼 폼 -->
   <form name="modifyFrm">
    	<input type="hidden" name="placeBoardNum">
    	<input type="hidden" name="placeBoardTitle" value='<%=plBoard.getPlBoardTitle() %>'> 
    	<input type="hidden" name="placeBoardContent" value='<%=plBoard.getPlBoardContent() %>'>
    	<input type="hidden" name="placeBoardArea" value='<%=plBoard.getPlBoardArea() %>'>
    	<input type="hidden" name="placeBoardDogSize" value='<%=plBoard.getPlBoardDogSize() %>'>
    	<input type="hidden" name="placeBoardBusinessType" value='<%=plBoard.getPlBoardBusinessType() %>'>
    	<input type="hidden" name="placeBoardStoreName" value='<%=plBoard.getPlBoardStoreName() %>'>
    	<input type="hidden" name="placeBoardTime" value='<%=plBoard.getPlBoardTime() %>'>
    	<input type="hidden" name="placeBoardPhone" value='<%=plBoard.getPlBoardPhone() %>'>
    	<input type="hidden" name="placeBoardAddr" value='<%=plBoard.getPlBoardAddr() %>'>
    	
    </form> 
    	<!-- 댓글 삭제 폼 -->
    <form name="replyDelete">
     	  <input type="hidden" name="replyNum">
     	  <input type="hidden" name="boardNum" value="<%=plBoard.getPlBoardNum()%>">
     	  
     	</form>
    	<!-- 게시물 삭제 폼 -->
    <form name="deleFrm">
		 <input type="hidden" name="boardNum">
		 <%if(userLoggedIn!=null){%>
		<input type="hidden" name="userId" value="<%=userLoggedIn.getUserId()%>">
		 <%}%>
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

    
<script>
 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
 mapOption = {
     center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
     level: 3 // 지도의 확대 레벨
 };  

//지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

//주소로 좌표를 검색합니다

geocoder.addressSearch('<%=Addr[1]%>', function(result, status) {		//주소값에 저장된 주소 넣기(name ="address" id="address2" placeholder="주소" 값으로)

 // 정상적으로 검색이 완료됐으면 
  if (status === daum.maps.services.Status.OK) {

     var coords = new daum.maps.LatLng(result[0].y, result[0].x);

     console.log("1.다음지도====="+result[0].y); /// 위도
     console.log("2.다음지도====="+result[0].x); /// 경도



     // 결과값으로 받은 위치를 마커로 표시합니다
     var marker = new daum.maps.Marker({
         map: map,
         position: coords
     });

     // 인포윈도우로 장소에 대한 설명을 표시합니다
     var infowindow = new daum.maps.InfoWindow({
         content: '<div style="width:150px;text-align:center;padding:6px 0;">"<%=plBoard.getPlBoardStoreName()%>"</div>'
     });
     infowindow.open(map, marker);

     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
     map.setCenter(coords);
     
 } 
});    
function relayout() {    
    
    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
    map.relayout();
}
</script>




<%@ include file="/views/common/footer.jsp" %>