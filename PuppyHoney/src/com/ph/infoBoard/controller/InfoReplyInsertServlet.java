package com.ph.infoBoard.controller;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.infoBoard.model.service.InfoBoardService;
import com.ph.infoBoard.model.vo.InfoReply;

/**
 * Servlet implementation class InfoReplyInsertServlet
 */
@WebServlet("/infoReply/infoReplyInsert")
public class InfoReplyInsertServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int infoBoardRef=Integer.parseInt(request.getParameter("infoBoardRef"));	//게시글번호
		String infoReplyWriter=request.getParameter("infoReplyWriter");	//작성자
		String infoReplyContent=request.getParameter("infoReplyContent");	//댓글내용
		int infoReplyLevel=Integer.parseInt(request.getParameter("infoReplyLevel"));	//답글구분 1은댓글 2는답글
		int infoReplyRef=Integer.parseInt(request.getParameter("infoReplyRef"));	//댓글번호
		String reName=request.getParameter("reName");//리네임
		int cPage=Integer.parseInt(request.getParameter("cPage"));
		String searchType=request.getParameter("searchType");
		String inputText=request.getParameter("inputText");
		String sort=request.getParameter("sort");
		
		
		InfoReply ir = new InfoReply();
		ir.setBoardRef(infoBoardRef);
		ir.setReplyId(infoReplyWriter);
		ir.setReplyContent(infoReplyContent);
		ir.setReplyLevel(infoReplyLevel);
		ir.setReplyRef(infoReplyRef);
		ir.setReName(reName);
		
		//비지니스로직 수행
		int result=new InfoBoardService().insertInfoReply(ir);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result>0)
		{
			msg="댓글이 등록되었습니다.";
		}
		else {
			msg="댓글등록이 실패했습니다.";
		}
		loc="/freeBoard/boardView?no="+infoBoardRef+"&cPage="+cPage+"&searchType="+searchType+"&inputText="+inputText+"&sort="+sort;
		
		request.setAttribute("no", infoBoardRef);
		request.setAttribute("cPage", cPage);
		request.setAttribute("searchType", searchType);
		request.setAttribute("inputText", inputText);
		request.setAttribute("sort", sort);
		
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
