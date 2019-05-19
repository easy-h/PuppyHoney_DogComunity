package com.ph.infoBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.infoBoard.model.service.InfoBoardService;

/**
 * Servlet implementation class InfoReplyDeleteServlet
 */
@WebServlet("/infoReply/infoReplyDelete")
public class InfoReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int cPage=Integer.parseInt(request.getParameter("cPage"));
		String searchType=request.getParameter("searchType");
		String inputText=request.getParameter("inputText");
		String sort=request.getParameter("sort");
		
		int result = new InfoBoardService().deleteInfoReply(replyNum);
		
		String msg="";
		String loc="/freeBoard/boardView?no="+boardNum+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort;
		if(result>0) {
			msg="댓글이 삭제되었습니다.";
		}else {
			msg="댓글 삭제에 실패했습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("no", boardNum);
		request.setAttribute("cPage", cPage);
		request.setAttribute("searchType", searchType);
		request.setAttribute("inputText", inputText);
		request.setAttribute("sort", sort);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
