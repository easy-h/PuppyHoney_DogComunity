package com.ph.board.lost.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.lost.model.vo.LostBoardComment;
import com.ph.board.lost.service.LostBoardService;

/**
 * Servlet implementation class LostBoardCommentServlet
 */
@WebServlet("/Reply/lostCommentInsert")
public class LostBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostBoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lostReplyWriter=request.getParameter("lostReplyWriter");
		String lostReplyContent=request.getParameter("lostReplyContent");
		int lostBoardRef=Integer.parseInt(request.getParameter("lostBoardRef"));
		int lostReplyLevel=Integer.parseInt(request.getParameter("lostReplyLevel"));
		int lostReplyRef=Integer.parseInt(request.getParameter("lostReplyRef"));
		
		
		
		
		LostBoardComment lbc=new LostBoardComment();
		lbc.setLostReplyId(lostReplyWriter);
		lbc.setLostReplyContent(lostReplyContent);
		lbc.setLostBoardRef(lostBoardRef);
		lbc.setLostReplyLevel(lostReplyLevel);
		lbc.setLostReplyRef(lostReplyRef);
		
		int result=new LostBoardService().insertLostBoardComment(lbc);
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result>0)
		{
			msg="댓글이 등록 되었습니다";
		}
		else 
		{
			msg="댓글 등록 실패";
		}
		loc="/board/lostBoardView?num="+lostBoardRef;
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
