package com.ph.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.message.model.service.MessageService;

/**
 * Servlet implementation class DeleteSendMessageServlet
 */
@WebServlet("/message/deleteSendMessage")
public class DeleteSendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String[] arr=request.getParameterValues("val")[0].split(",");
		int su=0;
		
		for(String s:arr) {
			
			int messageNum=Integer.parseInt(s);
			int result=new MessageService().deleteReceiveMessage(messageNum);
			if(result==1) {
				su++;
			}
		}
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(su==arr.length) {
			msg="쪽지가 삭제 되었습니다.";
			loc="/message/receiveMessage?userId="+userId;
		}
		else {
			msg="쪽지 삭제 실패.";
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
