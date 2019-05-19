

package com.ph.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ph.user.model.service.UserService;
import com.ph.user.model.vo.User;

import static common.Encrypt.*;

@WebServlet("/user/loginEnd")
public class UserLoginEndServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   
    public UserLoginEndServlet() {
    
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
      
            String id=request.getParameter("userId");
            String pw_=request.getParameter("userPw");
            String pw=getSha512(pw_);
            
            User user=new UserService().selectUser(id);
            
            String msg="";
            String loc="";
            String view="/views/common/msg2.jsp";
            
            int no = 0;
            int cPage = 1;
            String searchType = "";
            String inputText = "";
            String sort = "";
            
            if(request.getParameter("searchType")!=null) {
               no=Integer.parseInt(request.getParameter("no"));
               cPage = Integer.parseInt(request.getParameter("cPage"));
               searchType = request.getParameter("searchType");
               inputText = request.getParameter("inputText");
               sort = request.getParameter("sort");
               
               request.setAttribute("no", no);
               request.setAttribute("cPage", cPage);
               request.setAttribute("searchType", searchType);
               request.setAttribute("inputText", inputText);
               request.setAttribute("sort", sort);
               
               
            }
            
            //�α���üũ
            if(user!=null)
            {
               //�α��μ���
               if(user.getUserPw().equals(pw))
               {
                  if(user.getUserLeave().equals("N"))
                  {
                     if(user.getUserEmailChecked()==1)
                     {
                        msg=user.getUserId()+"님 환영합니다";
                        loc="/";
                        //���ǻ���
                        HttpSession session=request.getSession();
                        session.setAttribute("userLoggedIn",user);
                        //���̵����� üũ���� �޾ƿ���
                        String saveId=request.getParameter("saveId");
                        //üũ�� �� ���
                        if(saveId!=null)
                        {
                           Cookie c= new Cookie("saveId",id);
                           c.setMaxAge(30*24*60*60);
                           c.setPath("/");
                           response.addCookie(c);
                        }
                        //üũ�ȵ� ���
                        else
                        {
                           Cookie c=new Cookie("saveId",id);
                           c.setMaxAge(0);
                           c.setPath("/");
                           response.addCookie(c);
                        }
                     }
                     else
                     {
                        msg="E-Mail 인증 후에 로그인해주세요";
                        loc="/";
                     }
                     
                  }
                  else
                  {
                     msg="탈퇴한 회원입니다";
                     loc="/";
                  }
                  
               }
               //��й�ȣ ����
               else
               {
                  msg="비밀번호를 다시 확인해주세요";
                  loc="/";
               }
            }
            //ID����
            else
            {
               msg="ID를 다시 확인해주세요";
               loc="/";
            }
            request.setAttribute("msg", msg);
            request.setAttribute("loc", loc);
            request.getRequestDispatcher(view).forward(request, response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}