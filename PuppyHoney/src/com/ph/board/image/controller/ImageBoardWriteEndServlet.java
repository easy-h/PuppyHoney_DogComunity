package com.ph.board.image.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;
import com.ph.board.image.model.vo.ImageBoard;

/**
 * Servlet implementation class InfoBoardWriteEndServlet
 */
@WebServlet("/board/imageWriteEnd")
public class ImageBoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageBoardWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		ImageBoard mb = new ImageBoard();
		mb.setBoardId(id);
		mb.setBoardTitle(title);
		mb.setBoardContent(content);
		
		int result = new ImageBoardService().insertImageBoard(mb);

		String msg="";
		String loc="/board/imageBoardList";
		if(result>0) {
			msg="게시물이 등록되었습니다.";
		}else {
			msg="게시물 등록에 실패했습니다.";
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
