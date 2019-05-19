package com.ph.mypage.controller;

import static common.Encrypt.getSha256;
import static common.Encrypt.getSha512;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ph.user.model.service.UserService;
import com.ph.user.model.vo.User;

import common.Gmail;

/**
 * Servlet implementation class UpdateMemberEndServlet
 */
@WebServlet("/updateMemberPageEnd")
public class UpdateMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		HttpSession session=request.getSession();
		User userLoggedIn=(User)session.getAttribute("userLoggedIn");
		if(!ServletFileUpload.isMultipartContent(request))
		{
			
			request.setAttribute("msg", "사진첨부오류");
			request.setAttribute("loc", "/views/user/register.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"upload"+File.separator+"user";
		int maxSize=1024*1024*10;
		
		
		MultipartRequest mpreq=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		String userImageOrigin="";
		if(mpreq.getOriginalFileName("userImage")!=null) userImageOrigin=mpreq.getOriginalFileName("userImage");
		else userImageOrigin=userLoggedIn.getUserImageOrigin();
		user.setUserImageOrigin(userImageOrigin);
		System.out.println("origin : "+userImageOrigin);
		
		String userImageRename=null;
	
		if(mpreq.getFilesystemName("userImage")!=null)userImageRename=mpreq.getFilesystemName("userImage");
		else userImageRename=userLoggedIn.getUserImageRename();
		System.out.println(userImageRename);
		user.setUserImageRename(userImageRename);
		
		
		user.setUserId(userLoggedIn.getUserId());
		
		
		String pw=mpreq.getParameter("userPw");
		String userPw=getSha512(pw);
		user.setUserPw(userPw);
		String userNick=mpreq.getParameter("userNick");
		user.setUserNick(userNick);
		
		String userName=mpreq.getParameter("userName");
		user.setUserName(userName);
		user.setUserBirth(userLoggedIn.getUserBirth());
		
		String userDogName="";
		if(!mpreq.getParameter("userDogName").isEmpty())
		{
			userDogName=mpreq.getParameter("userDogName");
		}
		else
		{
			userDogName=null;
		}
		user.setUserDogName(userDogName);
		Date userDogBirth2=null;
		if(!mpreq.getParameter("userDogBirth").isEmpty())
		{
			String userDogBirth1=mpreq.getParameter("userDogBirth");
			StringTokenizer st2=new StringTokenizer(userDogBirth1,"-");
			int year2=Integer.parseInt(st2.nextToken());
			int month2=Integer.parseInt(st2.nextToken());
			int day2=Integer.parseInt(st2.nextToken());
			GregorianCalendar gc2=new GregorianCalendar(year2, month2-1, day2);
			userDogBirth2=new Date(gc2.getTimeInMillis());
			user.setUserDogBirth(userDogBirth2);
		}
		else
		{
			user.setUserDogBirth(userDogBirth2);
		}
		
		if(mpreq.getParameter("userEmail").equals(userLoggedIn.getUserEmail()))
		{
			user.setUserEmail(userLoggedIn.getUserEmail());
			user.setUserEmailHash(userLoggedIn.getUserEmailHash());
			user.setUserEmailChecked(1);
		}
		else
		{
			String userEmail=mpreq.getParameter("userEmail");
			user.setUserEmail(userEmail);
			String userEmailHash=getSha256(userEmail);
			user.setUserEmailHash(userEmailHash);
			user.setUserEmailChecked(0);
			String userId=mpreq.getParameter("userId");
			
			String host = "http://localhost:9090/PuppyHoney";

			String from = "semipuppyhoney@gmail.com";

			String to = user.getUserEmail();

			String subject = "PuppyHoney 인증메일입니다.";

			String content = "아래의 버튼을 누르시면 인증이 완료됩니다. <br>" +

				"<form action='" + host + "/user/emailValidate' method='post'>"+
				"<input type='hidden' value='"+userId+"' name='userId'>" +
				"<input type='hidden' value='"+getSha256(to)+"' name='code'>" + 
				"<button type='submit'>이메일인증</button>"+
				"</form>";

			

			

			Properties p = new Properties();

			p.put("mail.smtp.user", from);

			p.put("mail.smtp.host", "smtp.googlemail.com");

			p.put("mail.smtp.port", "465");

			p.put("mail.smtp.starttls.enable", "true");

			p.put("mail.smtp.auth", "true");

			p.put("mail.smtp.debug", "true");

			p.put("mail.smtp.socketFactory.port", "465");

			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			p.put("mail.smtp.socketFactory.fallback", "false");

			 

			try{

			    Authenticator auth = new Gmail();

			    Session ses = Session.getInstance(p, auth);

			    ses.setDebug(true);

			    MimeMessage msg = new MimeMessage(ses); 

			    msg.setSubject(subject);

			    Address fromAddr = new InternetAddress(from);

			    msg.setFrom(fromAddr);

			    Address toAddr = new InternetAddress(to);

			    msg.addRecipient(Message.RecipientType.TO, toAddr);

			    msg.setContent(content, "text/html;charset=UTF-8");

			    Transport.send(msg);

			} catch(Exception e){

			    e.printStackTrace();

				PrintWriter script = response.getWriter();

				script.println("<script>");

				script.println("alert('오류가 발생했습니다.');");

				script.println("history.back();");

				script.println("</script>");

				script.close();		

			    return;

			}
		}
		
		
		
		
		
		
		


		
		int result=new UserService().updateUser(user);
		
		String msg="";
		String loc="/";
		String view="/views/common/msg.jsp";
		if(result>0)
		{
			msg="회원정보 수정 완료. E-Mail을 변경한 경우 인증 후 로그인 해주세요.";
		}
		else
		{
			msg="회원정보 수정 실패";
			
		}
		
		
		
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
