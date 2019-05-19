package com.ph.board.image.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.image.model.service.ImageBoardService;
import com.ph.board.image.model.vo.ImageBoard;
import com.ph.board.image.model.vo.ImageReply;
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class ImageBoardViewServlet
 */
@WebServlet("/board/imageView")
public class ImageBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNum = Integer.parseInt(request.getParameter("no"));
		
		String searchType = request.getParameter("searchType");
		String inputText = request.getParameter("inputText");
		
		
		User u=(User)request.getSession().getAttribute("userLoggedIn");
		if(u==null) {
		
			ImageBoard imageBoard = new ImageBoardService().notLoginSelectOne(boardNum);
			String view="";
			if(imageBoard!=null)
			{
				request.setAttribute("imageBoard", imageBoard);
				request.setAttribute("searchType", searchType);
				request.setAttribute("inputText", inputText);
				List<ImageReply> replyList = new ImageBoardService().selectImageBoardReplyList(boardNum);	//댓글불러오기...
				request.setAttribute("replyList", replyList);
				view="/views/board/board_image/imageBoardView.jsp";
			}
			else
			{
				request.setAttribute("msg", "조회한 게시물이 없습니다.");
				request.setAttribute("loc", "/board/imageBoardList");
				view="/views/common/msg.jsp";
			}
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			
			//조회수 같은계정 한번만 증가
			//client가 보낸 쿠키 확인
			Cookie[] cookie=request.getCookies();
			String boardCookieVal="";
			boolean hasRead=false;
			if(cookie!=null) {
				outer:
					for(Cookie c:cookie) {
						String name = c.getName();
						String value=c.getValue();
						if("boardCookie".equals(name)) {
							boardCookieVal=value;
							if(value.contains("|"+boardNum+"|")) { //읽언던적이있따면?
								hasRead=true;
								break outer;
							}
						}
					}
			}
			
			//읽지 않았다면 쿠키를 만들어서 저장
			if(!hasRead) {
				Cookie boardCookie=new Cookie("boardCookie",boardCookieVal+"|"+boardNum+"|"); //10||20||30|
				//session끊어지면 지우기!
				//브라우저 끊어져야 삭제
				boardCookie.setMaxAge(-1);
				
				response.addCookie(boardCookie);
			}
			
			ImageBoard imageBoard=new ImageBoardService().selectOne(boardNum, hasRead);
			
			String view="";
			if(imageBoard!=null)
			{
				request.setAttribute("imageBoard", imageBoard);
				request.setAttribute("searchType", searchType);
				request.setAttribute("inputText", inputText);
				
				List<ImageReply> replyList = new ImageBoardService().selectImageBoardReplyList(boardNum);	//댓글불러오기...
				request.setAttribute("replyList", replyList);
				view="/views/board/board_image/imageBoardView.jsp";
			}
			else
			{
				request.setAttribute("msg", "조회한 게시물이 없습니다.");
				request.setAttribute("loc", "/board/imageBoardList");
				view="/views/common/msg.jsp";
			}
			request.getRequestDispatcher(view).forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
