<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.*,com.ph.board.place.model.vo.PlaceBoard, common.ImgExtract" %>

<%
	 List<PlaceBoard> pList =(List<PlaceBoard>)request.getAttribute("pList");
%>




<script>

        $(function(){
              $(".pp").slice(0, 10).show(); // select the first ten
              $("#load").click(function(e){ // click event for load more
                  e.preventDefault();
    
                  $(".pp:hidden").slice(0, 9).show(); // select next 10 hidden divs and show them
                  if($(".pp:hidden").length == 0){ // check if any hidden divs still exist
                      //alert("더 보여줄 게시물이 없습니다"); // alert if there are none left
              }
        });
    });
    </script>
    
    <style>
    
        .pp{
            display: none;
        }
    
    
        div table tr td span{
            display: block;
        }
        a{
            color: rgb(114, 109, 109)
        }
    
        .form-group{
            position: relative;
        }
        .btna{
            position: absolute; top:0; right: 0;
        }
        @media(max-width:950px){
            .btn{
                position: relative;
                display: block;
            }
        }
    </style>
   
     <div class="container col-sm-12 p-4 pb-5 mt-5" style="background:white;">
        <h1 class="mt-4 mb-3 "><a href="placeBoard.html">승인게시판</a></h1>
        <table class="table table-striped " style="table-layout:fixed; word-break:break-all;">
            
            <hr>
            <tbody>
                <tr class="pp">
                    <td class="align-middle pp" align='center'><b>타이틀(게시물 제목)</b></td>
                    
                    <td class="align-middle pp" align='center'>
                       	<b> 작성자 아이디</b>
                    </td>
                    <td class="align-middle pp" align='center'>
                      	<b>신청날짜</b>
                    </td>
                    <td class="align-middle pp" align='center' >
                       	<b>게시물 승인처리 (Y/N)</b>
                     </td>
                </tr>
			<%for(PlaceBoard pb: pList){%>
				<tr class="pp">
                        <td class="align-middle" align='center'><a href="<%=request.getContextPath()%>/admin/acceptBoardView?plBoardNum=<%=pb.getPlBoardNum()%>"><%=pb.getPlBoardTitle() %></a></td>
                   
                        <td class="align-middle" align='center'>
                         	<%=pb.getPlBoardId() %>
                        </td>
                        <td class="align-middle" align='center'>
                          	<%=pb.getPlBoardDate() %>
                        </td>
                        <td class="align-middle" align='center' >
                        	<%if(pb.getPlBoardAcept_yn().equals("Y")){ %>
                           		<span style="color: blue;"><%=pb.getPlBoardAcept_yn() %></span>
                           	<%}else if(pb.getPlBoardAcept_yn().equals("N")){ %>
                           		<span style="color: red;"><%=pb.getPlBoardAcept_yn() %></span>
                           	<%} %>
                         </td>
                    </tr>
				
			<%}%>
                

                    
                    
            </tbody>
        </table>        
        <hr/>
        <div align='center'>
            <button id='load'type="button" class="btn btn-dark btn-lg ">더보기</button>
        </div>



<%@ include file="/views/common/footer.jsp" %>