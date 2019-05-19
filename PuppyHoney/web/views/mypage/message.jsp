<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ph.message.model.vo.Message, java.util.List, com.ph.user.model.vo.User"%>
    <%
    	User userLoggedIn=(User)session.getAttribute("userLoggedIn");
    	
    	List<Message> list=(List)request.getAttribute("list");
    	int cPageReceiver=(int)request.getAttribute("cPageReceiver");
    	String pageBarReceiver=(String)request.getAttribute("pageBarReceiver");
    	User u1=(User)request.getAttribute("u1");
    	
    	List<Message> list2=(List)request.getAttribute("list2");
 	    int cPageWriter=(int)request.getAttribute("cPageWriter");
    	String pageBarWriter=(String)request.getAttribute("pageBarWriter");
    	User u2=(User)request.getAttribute("u2");
    	
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>Document</title>
    <style>
        tr:first-child{width:150px;}
        .inlineb{display: inline-block;}
        .pageBar{
        	text-align: center;
        }
    </style>
</head>
<body>
        <!-- 체크박스 전체선택 펑션 -->
<script>
    $(function()
    {
       $('#customCheck').click(function()
       {
            if($('#customCheck').prop("checked"))
            {
                $('input[type=checkbox]').prop('checked',true);
            }
            else
            {
                $('input[type=checkbox]').prop('checked',false);
            }
       });
       
       $('#deleteReceiveMessage').on('click',function(){
    	   var chkval=[];
    	   $(".chk").each(function(){
    		   if($(this).prop('checked')){;
    		 	  chkval.push($(this).val());
    		 	  
    		   }
    	   });
    	   
    	   location.href="<%=request.getContextPath()%>/message/deleteReceiveMessage?val="+chkval+"&userId=<%=userLoggedIn.getUserId()%>";
    	   
       });
       
       $('#deleteSendMessage').on('click',function(){
    	   var chkval=[];
    	   $(".chk").each(function(){
    		   if($(this).prop('checked')){;
    		 	  chkval.push($(this).val());
    		 	  
    		   }
    	   });
    	   
    	   location.href="<%=request.getContextPath()%>/message/deleteReceiveMessage?val="+chkval+"&userId=<%=userLoggedIn.getUserId()%>";
    	   
       });   
        
    });
    
</script>

    <div class="container mt-3">
        <h2 style="text-align: center;">OOO님의 쪽지함</h2>
        <br>
        
        <ul class="nav nav-tabs" id="myTab">
            <li class="nav-item">
            <a id="receiveMessage" class="nav-link active" data-toggle="tab" href="#home">받은 쪽지함</a>
            </li>
            <li class="nav-item">
            <a id="sendMessage" class="nav-link" data-toggle="tab" href="#menu1">보낸 쪽지함</a>
            </li>
            <li class="nav-item">
            <a id="messageWrite" class="nav-link" data-toggle="tab" href="#menu2">쪽지 보내기</a>
            </li>
        </ul>

        <div class="tab-content">
            <div id="home" class="container tab-pane active">
            <h3 class="mt-3" style="display: inline-block;">받은 쪽지함</h3>
            
            <table class="table table-hover">
               <tr style="background: rgb(200, 152, 152)">
                  <th class="wideth" style="display: inline-block;">
	                  <div class="input-group inlineb">
	                  	<div class="custom-control custom-checkbox inlineb">
		                    <input type="checkbox" class="custom-control-input inlineb" id="customCheck" name="example1">
		                    <label class="custom-control-label ilineb" for="customCheck"></label>
	                     </div>
                		
	                     <div class="inlineb">
			           	 	<input id="deleteReceiveMessage" type="button" class='btn btn-danger' value="삭제">
			             </div>
	                  </div> 
                  </th>
                  <th>제목</th>
                  <th>보낸 사람 닉네임</th>
                  <th>날짜</th>
               </tr>
               
               <%if(list!=null&&!list.isEmpty()){
            	   for(Message m : list){ %>
               
                <tr>
                   <th> 
	                   <div class="custom-control custom-checkbox inlineb">
		                   <input type="checkbox" class="custom-control-input chk" id="<%=m.getMessageNum() %>" name="checkMessage" value="<%=m.getMessageNum() %>">
		                   <label class="custom-control-label" for="<%=m.getMessageNum() %>"></label>
	                   </div>
                   </th>
                   <td><a href="<%=request.getContextPath()%>/message/detailMessage?messageNum=<%=m.getMessageNum()%>"><%=m.getMessageTitle() %></a></td>
                   <td><%=u1.getUserNick() %></td>
                   <td><%=m.getMessageDate() %></td>
                </tr>
                
                <% }
            		} %>	
            </table>
				<div class='pageBar'>
					<%=request.getAttribute("pageBarReceiver") %>
				</div> 
            </div>


            
            <div id="menu1" class="container tab-pane fade">
            <h3 class="mt-3" style="display: inline-block;">보낸 쪽지함</h3>
            <table class="table table-hover">
               <tr style="background: rgb(200, 152, 152)">
                  <th class="wideth" style="display: inline-block;">
	                  <div class="input-group inlineb">
	                  	<div class="custom-control custom-checkbox inlineb">
		                    <input type="checkbox" class="custom-control-input inlineb" id="customCheck" name="example1">
		                    <label class="custom-control-label ilineb" for="customCheck"></label>
	                     </div>
                		
	                     <div class="inlineb">
			           	 	<input id="deleteSendMessage" type="button" class='btn btn-danger' value="삭제">
			             </div>
	                  </div> 
                  </th>
                  <th>제목</th>
                  <th>받는 사람 닉네임</th>
                  <th>날짜</th>
               </tr>
                <%if(list2!=null){
            	   for(Message m : list2){ %>
               
                <tr>
                   <th> 
	                   <div class="custom-control custom-checkbox inlineb">
		                   <input type="checkbox" class="custom-control-input chk" id="<%=m.getMessageNum() %>" name="checkMessage" value="<%=m.getMessageNum() %>">
		                   <label class="custom-control-label" for="<%=m.getMessageNum() %>"></label>
	                   </div>
                   </th>
                   <td><a href="<%=request.getContextPath()%>/message/detailMessage?messageNum=<%=m.getMessageNum()%>"><%=m.getMessageTitle() %></a></td>
                   <td><%=u2.getUserNick() %></td>
                   <td><%=m.getMessageDate() %></td>
                </tr>
                <%}
            	   } %> 
                
            </table>
	            <div class='pageBar'>
					<%=request.getAttribute("pageBarWriter") %>
				</div>
            </div>
            
            
            <div id="menu2" class="container tab-pane fade"><br>
            <h3>쪽지 보내기</h3>
            <form action="<%=request.getContextPath() %>/message/messageWrite?userId=<%=userLoggedIn.getUserId() %>" method="post">
	            <table class="table table-hover ">
	                <tr>
	                    <th colspan="1">
		                    <h6>받는 사람</h6>
		                    <input class="form-control col-md-2"type="text" name="receiverId" placeholder="닉네임">
	                    </th>
	                </tr>
	                <tr>
	                    <th colspan="3">
		                    <h6>제목</h6>
		                    <input class="form-control  col-md-6" type="text" name="messageTitle" placeholder="제목">
	                    </th>
	                </tr>
	                <tr>
	                    <th colspan="1">
		                    <h6>내용</h6>
		                    <textarea class="form-control" name='messageTextarea' cols="150" rows="11" style='resize: none;'>
		                    </textarea>
	                    </th>
	                </tr>
	            </table>
	            <input type="hidden" name="sendId" value="<%=userLoggedIn.getUserId() %>"/>  
	            <button type="submit" class="btn m-2 alert-danger">보내기</button>
	            <button type="reset" class="btn alert-danger">취소</button>
			</form>
            </div>
        </div>
    </div>
</body>
</html>