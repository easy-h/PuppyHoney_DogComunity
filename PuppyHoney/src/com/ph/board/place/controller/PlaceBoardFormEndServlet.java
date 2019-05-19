package com.ph.board.place.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ph.board.place.model.service.PlaceBoardService;
import com.ph.board.place.model.vo.PlaceBoard;
/**
 * Servlet implementation class PlaceBoardFormEndServlet
 */
@WebServlet("/board/placeBoardFormEnd") 


public class PlaceBoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBoardFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			 

				
				String userId=request.getParameter("userId");
				String title=request.getParameter("title");
				String storeName=request.getParameter("storeName");
				String businessType=request.getParameter("businessType");
				String location=request.getParameter("location");
				String size=request.getParameter("size");
				String phone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
				String time=request.getParameter("open")+" - "+request.getParameter("close");
				String addr=request.getParameter("address1")+","+request.getParameter("address2")+","+request.getParameter("address3");
				String content=request.getParameter("content");
				
				PlaceBoard plBoard=new PlaceBoard();
				
				plBoard.setPlBoardId(userId);
				plBoard.setPlBoardTitle(title);
				plBoard.setPlBoardStoreName(storeName);
				plBoard.setPlBoardBusinessType(businessType);
				plBoard.setPlBoardArea(location);
				plBoard.setPlBoardDogSize(size);
				plBoard.setPlBoardPhone(phone);
				plBoard.setPlBoardTime(time);
				plBoard.setPlBoardAddr(addr);
				plBoard.setPlBoardContent(content);
				
				int result=new PlaceBoardService().insertPlaceBoard(plBoard);
				
				String msg="";
				String loc="/board/placeBoardList";
				if(result>0)
				{
					msg="게시물 등록 완료";
				}else
				{
					msg="게시물 등록 실패";
				}
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//				System.out.println("userId : "+userId);
//				System.out.println("content : "+content);
//				System.out.println("addr : "+addr);
//				System.out.println("time : "+time);
//				System.out.println("phone : "+phone);
//				System.out.println("size : "+size);
//				System.out.println("location : "+location);
//				System.out.println("placeCate : "+placeCate);
//				System.out.println("storeName : "+storeName);
				
				
				
				
				
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
