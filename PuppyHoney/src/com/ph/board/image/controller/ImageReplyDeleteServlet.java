package com.ph.board.image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;

/**
 * Servlet implementation class imageReplyDeleteServlet
 */
@WebServlet("/imageReply/imageReplyDelete")
public class ImageReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String searchType=request.getParameter("searchType");
		String inputText=request.getParameter("inputText");
		
		int result = new ImageBoardService().deleteImageReply(replyNum);
		
		String msg="";
		String loc="/board/imageView?no="+boardNum+"&searchType="+searchType+"&inputText="+inputText;
		if(result>0) {
			msg="댓글이 삭제되었습니다.";
		}else {
			msg="댓글 삭제에 실패했습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
