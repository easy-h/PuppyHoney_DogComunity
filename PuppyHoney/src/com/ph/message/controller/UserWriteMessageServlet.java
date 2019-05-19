package com.ph.message.controller;

import java.io.IOException;

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
 * Servlet implementation class UserWriteMessageServlet
 */
@WebServlet("/message/messageWrite")
public class UserWriteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserWriteMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String id=request.getParameter("receiverId");
		String messageTitle=request.getParameter("messageTitle");
		String messageTextarea=request.getParameter("messageTextarea");
		String sendId=request.getParameter("sendId");
		
		User user=new UserService().selectUserNick(id);
		String receiverId=user.getUserId();
		
		
		
		
		Message m=new Message();
		m.setMessageReceiver(receiverId);
		m.setMessageTitle(messageTitle);
		m.setMessageContent(messageTextarea);
		m.setMessageWriter(sendId);
		
		int result=new MessageService().insertMessage(m);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result>0) {
			msg="쪽지가 전송 되었습니다.";
			loc="/message/receiveMessage?userId="+userId;
		}
		else {
			msg="쪽지 전송 실패.";
			loc="/message/receiveMessage?userId="+userId;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
