package com.ph.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.message.model.service.MessageService;
import com.ph.message.model.vo.Message;
import com.ph.user.model.service.UserService;
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class UserMessageServlet
 */
@WebServlet("/message/receiveMessage")
public class UserReceiveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReceiveMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*HttpSession session=request.getSession();
		
		User user=(User)session.getAttribute("userLoggedIn");
		String userId=user.getUserId();*/
		String userId=request.getParameter("userId");
		
		
		
		
		
		
		
		int numPerPageReceiver=5;
		int cPageReceiver;
		try {
			cPageReceiver=Integer.parseInt(request.getParameter("cPageReceiver"));
		}
		catch(NumberFormatException e) {
			cPageReceiver=1;
		}
		
		List<Message> list=new MessageService().receiverMessageList(userId, cPageReceiver, numPerPageReceiver);
		String messageWriter="";
		User u1=null;
		if(list.size()>0)
		{
			messageWriter=list.get(0).getMessageWriter();
			u1=new UserService().selectUser(messageWriter);
		}
		
		
		int totalContentReceiver=new MessageService().selectReceiverMessageCount(userId);
		
		int totalPageReceiver=(int)Math.ceil((double)totalContentReceiver/numPerPageReceiver);
		int barSizeReceiver=5;
		String pageBarReceiver="";
		int pageNoReceiver=((cPageReceiver-1)/barSizeReceiver)*barSizeReceiver+1;
		int pageEndReceiver=pageNoReceiver+barSizeReceiver-1;
		
		
		
		if(pageNoReceiver==1) {
			pageBarReceiver+="<span> [이전] <span>";
		}
		else {
			pageBarReceiver+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageReceiver="+(pageNoReceiver-1)+"&userId="+userId+"'> [이전] </a";
		}
		
		
		while(!(pageNoReceiver>pageEndReceiver||pageNoReceiver>totalPageReceiver)) {
			if(cPageReceiver==pageNoReceiver) {
				pageBarReceiver+="<span>"+pageNoReceiver+"</span>";
			}
			else {
				pageBarReceiver+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageReceiver="+pageNoReceiver+"&userId="+userId+"'> ["+pageNoReceiver+"] </a>";
			}
			pageNoReceiver++;
		}
		
		
		if(pageNoReceiver>totalPageReceiver) {
			pageBarReceiver+="<span> [다음] </span>";
		}
		else {
			pageBarReceiver+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageReceiver="+pageNoReceiver+"&userId="+userId+"'> [다음] </a>";
		}
		
		
		
		
		
		
		
		userId=request.getParameter("userId");
		
		int numPerPageWriter=5;
		int cPageWriter;
		try {
			cPageWriter=Integer.parseInt(request.getParameter("cPageWriter"));
		}
		catch(NumberFormatException e) {
			cPageWriter=1;
		}
		
		List<Message> list2=new MessageService().writerMessageList(userId, cPageWriter, numPerPageWriter);
		String messageReceiver="";
		User u2=null;
		if(list2.size()>0)
		{
			messageReceiver=list2.get(0).getMessageReceiver();
			u2=new UserService().selectUser(messageReceiver);
		}
		
		
		int totalContentWriter=new MessageService().selectWriterMessageCount(userId);
		
		int totalPageWriter=(int)Math.ceil((double)totalContentWriter/numPerPageWriter);
		int barSizeWriter=5;
		String pageBarWriter="";
		int pageNoWriter=((cPageWriter-1)/barSizeWriter)*barSizeWriter+1;
		int pageEndWriter=pageNoWriter+barSizeWriter-1;
		
		
		if(pageNoWriter==1) {
			pageBarWriter+="<span> [이전] <span>";
		}
		else {
			pageBarWriter+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageWriter="+(pageNoWriter-1)+"&userId="+userId+"'> [이전] </a";
		}
		
		
		while(!(pageNoWriter>pageEndWriter||pageNoWriter>totalPageWriter)) {
			if(cPageWriter==pageNoWriter) {
				pageBarWriter+="<span>"+pageNoWriter+"</span>";
			}
			else {
				pageBarWriter+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageWriter="+pageNoWriter+"&userId="+userId+"'> ["+pageNoWriter+"] </a>";
			}
			pageNoWriter++;
		}
		
		
		if(pageNoWriter>totalPageWriter) {
			pageBarWriter+="<span> [다음] </span>";
		}
		else {
			pageBarWriter+="<a href='"+request.getContextPath()+"/message/receiveMessage?cPageWriter="+pageNoWriter+"&userId="+userId+"'> [다음] </a>";
		}
		
		request.setAttribute("pageBarReceiver", pageBarReceiver);
		request.setAttribute("cPageReceiver", cPageReceiver);
		request.setAttribute("list", list);
		request.setAttribute("u1", u1);
		request.setAttribute("pageBarWriter", pageBarWriter);
		request.setAttribute("cPageWriter", cPageWriter);
		request.setAttribute("list2", list2);
		request.setAttribute("u2", u2);
		
		
		
		request.getRequestDispatcher("/views/mypage/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
