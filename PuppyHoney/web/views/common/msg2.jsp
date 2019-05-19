<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% 
	String msg=(String)request.getAttribute("msg");
	String loc=request.getContextPath()+(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
	
	int no =0;
	int cPage=0;
	String searchType ="";
	String inputText ="";
	String sort ="";
	
	if(request.getParameter("searchType")!=null){
		searchType = (String)request.getAttribute("searchType");
		inputText = (String)request.getAttribute("inputText");
		sort = (String)request.getAttribute("sort");
		cPage = Integer.parseInt(request.getAttribute("cPage").toString()); 
		no = Integer.parseInt(request.getAttribute("no").toString());
		loc = request.getContextPath()+"/freeBoard/boardView?no="+no+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort;
	}
%>
<body>
	<script>
		alert("<%= msg %>");
		<%= script!=null?script:""%>;
		location.href="<%=loc%>";
	</script>
</body>
</html>