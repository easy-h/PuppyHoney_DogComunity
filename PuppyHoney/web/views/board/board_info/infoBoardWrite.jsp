<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<!-- Smart Editor -->
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>

<style>
	img{max-width: 700px};
	img{max-height: 100%};
</style>
<!-- 로그인 안하고 주소창에 글작성주소 입력해서 들어올때 막아버리기. -->
<script type="text/javascript">
if(<%=userLoggedIn==null%>){
	alert("잘못된 경로로 접근했습니다.");
	location.href="/PuppyHoney/index.jsp";
}




</script>
<div class="panel panel-default"> 
            <!-- Default panel contents --> 
            <div class="panel-heading"><h2>Write</h2></div> 
            
            <div class="panel-body">
            
                <div class="container"> 
                    <!-- board테이블에 저장하기 위한 form.  -->
                    <form action='<%=request.getContextPath()%>/freeBoard/writeEnd' method="post" id="fr">
            			<!-- 원래는 벨류값에 세션아이디가 들어갈거임 -->
            			<input type="hidden" name="id" value="<%=userLoggedIn.getUserId() %>"/>
                         <!-- //글제목 입력 폼 -->
                         <div class="form-group"> 
                             <label for="title">Title</label> 
                             <input type="text" class="form-control" name ="title" id="title" placeholder="제목을 입력하세요."/> 
                         </div> 
                     
                         <!-- //글내용 입력 폼  -->
                         <div class="form-group"> 
                         	<label for="textAreaContent">Content</label>
                             <textarea style="width: 100%" rows="25" name="content" id="textAreaContent" cols="80"></textarea>
                         </div>
                         
                         <!--버튼능력-->
                         <div class="center-block ">      
                             <input type="reset" value="이전" class="btn btn-outline-secondary " style="width: 100px; height: 50px" onclick="history.back(1)"> 
                             <input type="button" value="등록" class="btn btn-outline-secondary float-right" style="width: 100px; height: 50px" onclick="submitContents(this);" >
                             <!-- //되돌아가기에서 onclick이벤트로 history.back(1) 왔던곳에서 1만큼 back (-1을 써도 된다)  -->
                             <!-- <br><br>
                             <input type="button" value="Back" onclick="history.back(1)"> -->
                         </div> 
                         
                     </form>
                 
             </div> 
         </div> <!--panel end--> 
     </div> 
    
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
		if(fr.title.value.trim()==''){
	    	alert("제목을 입력하세요.");
	    	return;
	    }
	   
	   
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