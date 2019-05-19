<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.ph.infoBoard.model.vo.InfoBoard"%>
<%@ include file="/views/common/header.jsp" %>
<%
	List<InfoBoard> list=(List<InfoBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String inputText = (String)request.getAttribute("inputText");
	String sort = (String)request.getAttribute("sort");
	int cPage = Integer.parseInt(request.getAttribute("cPage").toString()); 
	System.out.println("써치타입 :"+searchType);
	System.out.println("인풋텍스트 :"+inputText);
	System.out.println("쏘트 :"+sort);
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

function check2(){
	<%if(userLoggedIn==null){%>
		alert("로그인후 이용해 주세요.");
		return false;
	<%}%>
}

$(function(){
    $('.btnCheck').click(function(){
    	var option = $('select#sel1 option:selected').val();
    	if(option=="no"){
        	alert("분류를 선택해 주세요");
        	return false;
    	}
    });
    
  //조회순 정렬 의 반응
    $('.sortHits').on('click',function(){
      var sort = "INFO_BOARD_HITS";
      var searchType = "<%=searchType%>";
      var inputText = "<%=inputText%>";
      sortFrm.sort.value=sort;
      sortFrm.searchType.value=searchType;
      sortFrm.inputText.value=inputText;
      var url="<%=request.getContextPath()%>/freeBoard/search";
      sortFrm.action=url
      sortFrm.method="get";
      sortFrm.submit();
    });
  
  //최신순 정렬 의 반응
    $('.sortDate').on('click',function(){
      var sort = "INFO_BOARD_DATE";
      var searchType = "<%=searchType%>";
      var inputText = "<%=inputText%>";
      sortFrm.sort.value=sort;
      sortFrm.searchType.value=searchType;
      sortFrm.inputText.value=inputText;
      var url="<%=request.getContextPath()%>/freeBoard/search";
      sortFrm.action=url
      sortFrm.method="get";
      sortFrm.submit();
    });
  
  // 댓글순 정렬 의 반응
    $('.sortReply').on('click',function(){
      var sort = "REPLYNUM";
      var searchType = "<%=searchType%>";
      var inputText = "<%=inputText%>";
      sortFrm.sort.value=sort;
      sortFrm.searchType.value=searchType;
      sortFrm.inputText.value=inputText;
      var url="<%=request.getContextPath()%>/freeBoard/search";
      sortFrm.action=url
      sortFrm.method="get";
      sortFrm.submit();
    });
  
 // 추찬순 정렬 의 반응
    $('.sortGood').on('click',function(){
      var sort = "INFO_BOARD_GOOD";
      var searchType = "<%=searchType%>";
      var inputText = "<%=inputText%>";
      sortFrm.sort.value=sort;
      sortFrm.searchType.value=searchType;
      sortFrm.inputText.value=inputText;
      var url="<%=request.getContextPath()%>/freeBoard/search";
      sortFrm.action=url
      sortFrm.method="get";
      sortFrm.submit();
    });
  
});
</script>
<style>
@media(max-width:450px){
        #info{
            display: none;
        }
    }
</style>
<div class="container">
	<div class="mt-4 form-group">
		<label><h3>자유게시판</h3></label>
        <div class="float-right pt-2 ml-4">
        	<form action="<%=request.getContextPath()%>/freeBoard/write" method="post" onsubmit="return check2()">
            <button type="submit" class="btn">글쓰기</button>
		     	  <input type="hidden" name="cPage" value="<%=cPage %>">
				  <input type="hidden" name="searchType" value="<%=searchType %>">
				  <input type="hidden" name="inputText" value="<%=inputText %>">
				  <input type="hidden" name="sort" value="<%=sort %>">
            </form>
        </div>
        <div class="float-right pt-2">
             <form name="fr" action="<%=request.getContextPath()%>/freeBoard/search" onsubmit="return check()">
             	<input type="hidden" name="sort" value="<%=sort%>">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <select class="form-control" id="sel1" name="searchType">
                            <option value=no>선택</option>
                            <option value="INFO_BOARD_CONTENT" >내용</option>
                            <option value="INFO_BOARD_TITLE" >제목</option>
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
<script>

<%if(searchType!=null){%>
var type = '<%=searchType%>';
var text = '<%=inputText%>';
document.getElementById('sel1').value=type;
document.getElementById('inputText2').value=text;

<%}%>
</script>
	
	
<div class="container mt-5 col-sm-11">
<!-- Page Heading -->
	<div class="pb-4">                                                                                  <!-- 선택이 되있으면 #555, text-decoration: underline; -->
		<%if(sort!=null){ %>
			<%if(sort.equals("INFO_BOARD_DATE")){ %>
				<b><a href="#"><font class="sortMenu sortDate" style="color: #555; text-decoration: underline;">최신순</font></a></b>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortHits" style="color: #bbb;">조회순</font></a>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortReply" style="color: #bbb;">댓글순</font></a>
				<font class="sortMenuBar" >|</font>
				<a href="#"><font class="sortMenu sortGood" style="color: #bbb;">추천순</font></a>
			<%}else if(sort.equals("INFO_BOARD_HITS")){ %>
				<a href="#"><font class="sortMenu sortDate" style="color: #bbb;">최신순</font></a>
				<font class="sortMenuBar" >|</font>
				<b><a  href="#"><font class="sortMenu sortHits" style="color: #555; text-decoration: underline;">조회순</font></a></b>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortReply" style="color: #bbb;">댓글순</font></a>
				<font class="sortMenuBar" >|</font>
				<a href="#"><font class="sortMenu sortGood" style="color: #bbb;">추천순</font></a>
			<%}else if(sort.equals("REPLYNUM")){ %>
				<a href="#"><font class="sortMenu sortDate" style="color: #bbb;">최신순</font></a>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortHits" style="color: #bbb;">조회순</font></a>
				<font class="sortMenuBar" >|</font>
				<b><a  href="#"><font class="sortMenu sortReply" style="color: #555; text-decoration: underline;">댓글순</font></a></b>
				<font class="sortMenuBar" >|</font>
				<a href="#"><font class="sortMenu sortGood" style="color: #bbb;">추천순</font></a>
			<%}else if(sort.equals("INFO_BOARD_GOOD")){ %>
				<a href="#"><font class="sortMenu sortDate" style="color: #bbb;">최신순</font></a>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortHits" style="color: #bbb;">조회순</font></a>
				<font class="sortMenuBar" >|</font>
				<a  href="#"><font class="sortMenu sortReply" style="color: #bbb;">댓글순</font></a>
				<font class="sortMenuBar" >|</font>
				<b><a href="#"><font class="sortMenu sortGood" style="color: #555; text-decoration: underline;">추천순</font></a></b>
			<%} %>
		<%}else{ %>
			<b><a href="#"><font class="sortMenu sortDate" style="color: #555; text-decoration: underline;">최신순</font></a></b>
			<font class="sortMenuBar" >|</font>
			<a  href="#"><font class="sortMenu sortHits" style="color: #bbb;">조회순</font></a>
			<font class="sortMenuBar" >|</font>
			<a  href="#"><font class="sortMenu sortReply" style="color: #bbb;">댓글순</font></a>
			<font class="sortMenuBar" >|</font>
			<a href="#"><font class="sortMenu sortGood" style="color: #bbb;">추천순</font></a>
		<%} %>
		
		
	
	</div>
	<table class="table table-hover" style="table-layout:fixed; word-break:break-all;">
		<tbody>
		<%for(InfoBoard ib : list){
			%>
		<tr>
			<td class="align-middle">
				<a href='<%=request.getContextPath()%>/freeBoard/boardView?no=<%=ib.getBoardNumber()%>&cPage=<%=cPage%>&searchType=<%=searchType%>&inputText=<%=inputText%>&sort=<%=sort%>'>
					<%=ib.getBoardTitle() %>
				</a>
			</td>
			<td class="align-middle" id="info">
				<img src="<%=request.getContextPath() %>/images/fa-comment.png" alt="댓글이미지" width="20px"><%=ib.getBoardReplyNum() %>&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="<%=request.getContextPath() %>/images/thumb-up.png" alt="따봉이미지" width="20px"><%=ib.getBoardgood()%>&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="<%=request.getContextPath() %>/images/eye50.png" alt="조회이미지" width="20px"><%=ib.getBoardHit()%>
			</td>
			<td>
				<div class="block">
					<%=ib.getBoardId()%>
				</div>
				<div style="color: #ccc; font-size: 12px;">
					<%=ib.getBoardDate() %>
				</div>
			</td>
		</tr>
		<%} %>
		</tbody>
	</table>        
	<hr>
	<div class="container">

		<div class="form-group">
			<nav class="align-content-center">
				<ul class="pagination justify-content-center">
					<%=pageBar%>
				</ul>     
			</nav>
		</div>
	</div>
</div>
<!-- 댓글삭제를 적용시키기 위한 폼 -->
     	<form name="sortFrm">
     	  <input type="hidden" name="sort">
     	  <input type="hidden" name="searchType">
     	  <input type="hidden" name="inputText">
     	</form>
<%@ include file="/views/common/footer.jsp" %>