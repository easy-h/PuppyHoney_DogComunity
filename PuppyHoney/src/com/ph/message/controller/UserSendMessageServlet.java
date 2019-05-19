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

/**
 * Servlet implementation class UserSendMessageServlet
 */
@WebServlet("/message/sendMessage")
public class UserSendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		
		int numPerPageWriter=5;
		int cPageWriter;
		try {
			cPageWriter=Integer.parseInt(request.getParameter("cPageWriter"));
		}
		catch(NumberFormatException e) {
			cPageWriter=1;
		}
		
		List<Message> list=new MessageService().writerMessageList(userId, cPageWriter, numPerPageWriter);
		
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
			pageBarWriter+="<a href='"+request.getContextPath()+"/message?cPageWriter="+(pageNoWriter-1)+"&userId="+userId+"'> [이전] </a";
		}
		
		
		while(!(pageNoWriter>pageEndWriter||pageNoWriter>totalPageWriter)) {
			if(cPageWriter==pageNoWriter) {
				pageBarWriter+="<span>"+pageNoWriter+"</span>";
			}
			else {
				pageBarWriter+="<a href='"+request.getContextPath()+"/message?cPageWriter="+pageNoWriter+"&userId="+userId+"'> ["+pageNoWriter+"] </a>";
			}
			pageNoWriter++;
		}
		
		
		if(pageNoWriter>totalPageWriter) {
			pageBarWriter+="<span> [다음] </span>";
		}
		else {
			pageBarWriter+="<a href='"+request.getContextPath()+"/message?cPageWriter="+pageNoWriter+"&userId="+userId+"'> [다음] </a>";
		}
		
		request.setAttribute("pageBarWriter", pageBarWriter);
		request.setAttribute("cPageWriter", cPageWriter);
		request.setAttribute("list", list);
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
