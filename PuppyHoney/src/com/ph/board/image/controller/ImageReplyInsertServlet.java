package com.ph.board.image.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;
import com.ph.board.image.model.vo.ImageReply;

/**
 * Servlet implementation class imageReplyInsertServlet
 */
@WebServlet("/imageReply/imageReplyInsert")
public class ImageReplyInsertServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int imageBoardRef=Integer.parseInt(request.getParameter("imageBoardRef"));	//게시글번호
		String imageReplyWriter=request.getParameter("imageReplyWriter");	//작성자
		String imageReplyContent=request.getParameter("imageReplyContent");	//댓글내용
		int imageReplyLevel=Integer.parseInt(request.getParameter("imageReplyLevel"));	//답글구분 1은댓글 2는답글
		int imageReplyRef=Integer.parseInt(request.getParameter("imageReplyRef"));	//댓글번호
		String searchType=request.getParameter("searchType");
		String inputText=request.getParameter("inputText");
		
		
		ImageReply mr = new ImageReply();
		mr.setBoardRef(imageBoardRef);
		mr.setReplyId(imageReplyWriter);
		mr.setReplyContent(imageReplyContent);
		mr.setReplyLevel(imageReplyLevel);
		mr.setReplyRef(imageReplyRef);
		
		//비지니스로직 수행
		int result=new ImageBoardService().insertImageReply(mr);
		
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
		loc="/board/imageView?no="+imageBoardRef+"&searchType="+searchType+"&inputText="+inputText;
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
