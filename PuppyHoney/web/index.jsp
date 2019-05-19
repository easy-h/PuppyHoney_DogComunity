<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="views/common/header.jsp" %>

<script>
	$(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/mainPage",
			type:"get",
			dataType:"html",
			success:function(data)
			{
				$("#main").html(data);
			}
			
		});
	});
</script> 	



	

<%@ include file="views/common/footer.jsp" %>