package com.ph.board.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.board.news.model.service.NewsBoardService;
import com.ph.board.news.model.vo.NewsBoard;
import com.ph.board.news.model.vo.NewsReply;
import com.ph.user.model.vo.User;

/**
 * Servlet implementation class NewsBoardViewServlet
 */
@WebServlet("/board/newsBoardView")
public class NewsBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public NewsBoardViewServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		
		
		User u=(User)request.getSession().getAttribute("userLoggedIn");
		
		if(u==null) {
			NewsBoard newsBoard=new NewsBoardService().selectNewsBoard(num);
			String view="";
			if(newsBoard!=null)
			{
				request.setAttribute("newsBoard", newsBoard);
				List<NewsReply> replyList = new NewsBoardService().selectNewsBoardReplyList(num);	//�뙎湲�遺덈윭�삤湲�...
				request.setAttribute("replyList", replyList);
				view="/views/board/board_news/newsBoardView.jsp";
			}
			else
			{
				request.setAttribute("msg", "조회한 게시물이 없습니다.");
				request.setAttribute("loc", "/freeBoard/boardList");
				view="/views/common/msg.jsp";
			
			}
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			
			//議고쉶�닔 媛숈�怨꾩젙 �븳踰덈쭔 利앷�
			//client媛� 蹂대궦 荑좏궎 �솗�씤
			Cookie[] cookie2=request.getCookies();
			String boardCookieVal2="";
			boolean hasRead2=false;
			if(cookie2!=null) {
				outer:
					for(Cookie c:cookie2) {
						String name = c.getName();
						String value=c.getValue();
						if("newsBoardCookie".equals(name)) {
							boardCookieVal2=value;
							if(value.contains("|"+num+"|")) { //�씫�뼵�뜕�쟻�씠�엳�뵲硫�?
								hasRead2=true;
								break outer;
							}
						}
					}
			}
			
			//�씫吏� �븡�븯�떎硫� 荑좏궎瑜� 留뚮뱾�뼱�꽌 ���옣
			if(!hasRead2) {
				Cookie boardCookie2=new Cookie("newsBoardCookie",boardCookieVal2+"|"+num+"|"); //10||20||30|
				//session�걡�뼱吏�硫� 吏��슦湲�!
				//釉뚮씪�슦�� �걡�뼱�졇�빞 �궘�젣
				boardCookie2.setMaxAge(-1);
				
				response.addCookie(boardCookie2);
			}
			
			NewsBoard NewsBoard=new NewsBoardService().selectOne(num, hasRead2);
			
			String view="";
			if(NewsBoard!=null)
			{
				request.setAttribute("newsBoard", NewsBoard);
				
				
				List<NewsReply> replyList = new NewsBoardService().selectNewsBoardReplyList(num);	//�뙎湲�遺덈윭�삤湲�...
				request.setAttribute("replyList", replyList);
				view="/views/board/board_news/newsBoardView.jsp";
			}else
			{
				request.setAttribute("msg", "조회한 게시물이 없습니다.");
				request.setAttribute("loc", "/board/newsBoardList");
				view="/views/common/msg.jsp";
			}
			
			request.getRequestDispatcher(view).forward(request, response);
			
			
		}
		
			
			
			
	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
