<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="com.ph.board.place.model.vo.PlaceBoard, java.util.*, common.ImgExtract"%>

 <%
 	PlaceBoard plBoard=(PlaceBoard)request.getAttribute("plBoard");
 	String addr=plBoard.getPlBoardAddr();
 	String Addr[]=addr.split(",");
 	String pAddr=Addr[1]+" "+Addr[2];
 %>
<style>

img{
	max-width:600px; max-height:100%; width:100%; height:100%;
}
   
.side-bar {
   position: fixed; 
   right: 390px;
   display: inline-block;
   width: 400px;
}
    
 /* 모바일로 바꿨을 때 */   
@media (max-width:450px) {
   .side-bar {
      position: relative;
      right: 0;
      width: 100%;
   }
}

   
</style>
<!-- 지도 api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29d28c77afa06b8d3797cd516b310f0f&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29d28c77afa06b8d3797cd516b310f0f"></script>

<script>
	$(function()
	{
		
		
		$('.btnAccept').on('click',function()
		{
			
			var url="<%=request.getContextPath()%>/admin/placeAccept";
			Frm.action=url;
			Frm.method="post";
			Frm.submit();
		});
		
		$('.btnDeny').on('click',function()
				{
					var url="<%=request.getContextPath()%>/admin/placeDeny";
					Frm.action=url;
					Frm.method="post";
					Frm.submit();
				});
		
		
		$('.btnCancel').on('click',function()
				{
					var url="<%=request.getContextPath()%>/admin/placeCancel";
					Frm.action=url;
					Frm.method="post";
					Frm.submit();
				});
		
		
	});
	
	
	function fn_list(){
		location.href="<%=request.getContextPath()%>/admin/AcceptBoardtList";
	}
			
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
                <div id="map" style="width:250px;height:180px;"></div>
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
                <th class="table-Light table-danger"> <%=plBoard.getPlBoardId()%></th>
                <th class="table-Light table-danger"> <%=plBoard.getPlBoardDate()%></th>
                <th class="table-Light table-danger"> <%=plBoard.getPlBoardHits() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
              </tr>
		 	</table>
          <blockquote align='center'class="blockquote">
          <p id='content2'><%=plBoard.getPlBoardContent()%></p>
          <br><br><br><br><br><br><br><br><br><br><br><br>
         <br><br>
          <%if(userLoggedIn!=null){ %>
			<%if(plBoard.getPlBoardId().equals(userLoggedIn.getUserNick())||userLoggedIn.getUserId().equals("admin")){ %>
          		<%if(plBoard.getPlBoardAcept_yn().equals("N")){ %>
          <button align='center' style="padding: 2px 4px;"class='btn btn-danger btn-sm btnAccept'>승인</button>&nbsp;
          <button alige='center'style="padding: 2px 4px;"class='btn btn-danger btn-sm btnDeny'>거부</button>
          		<%}else{ %>
          			<button alige='center'style="padding: 2px 4px;"class='btn btn-danger btn-sm btnCancel'>승인취소</button>
          		<%} %>
          <button style="padding: 2px 4px;"class='btn btn-danger btn-sm float-right' onclick='fn_list()'>목록</button>
          </blockquote>
          

          <hr>
          
          <%} 
          }
          }%>
          
          
        </div>			<!-- 본문내용 -->
      </div>			<!-- 전체컨테이너 -->
    
    <!-- 승인버튼 폼 -->
   <form name="Frm">
    	<input type="hidden" name="placeBoardNum" value="<%=plBoard.getPlBoardNum()%>">
    	<input type="hidden" name="placeBoardId" value="<%=plBoard.getPlBoardId()%>">
    	<input type="hidden" name="getPlBoardTitle" value="<%=plBoard.getPlBoardTitle()%>">
    </form> 
    
    
    
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
</script>



<%@ include file="/views/common/footer.jsp" %>