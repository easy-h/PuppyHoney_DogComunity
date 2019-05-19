package com.ph.board.place.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.place.model.service.PlaceBoardService;
import com.ph.board.place.model.vo.PlaceBoard;
import com.ph.board.place.model.vo.PlaceBoardComment;

/**
 * Servlet implementation class PlaceReplyInsertServlet
 */
@WebServlet("/Reply/placeReplyInsert")
public class PlaceReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int placeBoardRef=Integer.parseInt(request.getParameter("placeBoardRef"));
		String placeReplyWriter=request.getParameter("placeReplyWriter");
		String placeReplyContent=request.getParameter("placeReplyContent");
		int placeReplyLevel=Integer.parseInt(request.getParameter("placeReplyLevel"));
		int placeReplyRef=Integer.parseInt(request.getParameter("placeReplyRef"));	//댓글번호
		
		PlaceBoardComment placeReply = new PlaceBoardComment();
		placeReply.setPlBoardRef(placeBoardRef);				//게시물 번호
		placeReply.setPlBoardReplyId(placeReplyWriter);			//작성자
		placeReply.setPlBoardReplyContent(placeReplyContent);	//리플내용
		placeReply.setPlBoardReplyRef(placeReplyRef);			//부모댓글번호
		placeReply.setPlBoardReplyLevel(placeReplyLevel);		//댓글의 깊이(1,2)
		
		int result=new PlaceBoardService().insertPlaceReply(placeReply);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result>0)
		{
			msg="댓글 등록 완료";
		}
		else 
		{
			msg="댓글 등록 실패";
		}
		loc="/board/placeBoardView?plBoardNum="+placeBoardRef;
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
