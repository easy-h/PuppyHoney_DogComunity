package com.ph.user.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Base64;
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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ph.user.model.service.UserService;
import com.ph.user.model.vo.User;

import common.Gmail;

import static common.Encrypt.*;

@WebServlet("/user/registerEnd")
public class UserRegisterEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    
    public UserRegisterEndServlet() {
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		
		if(!ServletFileUpload.isMultipartContent(request))
		{
			
			request.setAttribute("msg", "사전첨부오류!");
			request.setAttribute("loc", "/views/user/register.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		String root=getServletContext().getRealPath("/");
		String saveDir=root+"upload"+File.separator+"user";
		int maxSize=1024*1024*10;
		
		
		MultipartRequest mpreq=new MultipartRequest(request, saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		String userImageOrigin=mpreq.getOriginalFileName("userImage");
		user.setUserImageOrigin(userImageOrigin);
		
		String userImageRename=mpreq.getFilesystemName("userImage");
		user.setUserImageRename(userImageRename);
		
		String userId=mpreq.getParameter("userId");
		user.setUserId(userId);
		
		
		String pw=mpreq.getParameter("userPw");
		String userPw=getSha512(pw);
		user.setUserPw(userPw);
		String userNick=mpreq.getParameter("userNick");
		user.setUserNick(userNick);
		String userEmail=mpreq.getParameter("userEmail");
		user.setUserEmail(userEmail);
		String userEmailHash=getSha256(userEmail);
		user.setUserEmailHash(userEmailHash);
		String userName=mpreq.getParameter("userName");
		user.setUserName(userName);
		String userBirth1=mpreq.getParameter("userBirth");
		StringTokenizer st=new StringTokenizer(userBirth1,"-");
		int year=Integer.parseInt(st.nextToken());
		int month=Integer.parseInt(st.nextToken());
		int day=Integer.parseInt(st.nextToken());
		GregorianCalendar gc=new GregorianCalendar(year, month-1, day);
		Date userBirth2=new Date(gc.getTimeInMillis());
		user.setUserBirth(userBirth2);
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
		
		
		
		String host = "http://192.168.20.246:9090/PuppyHoney";

		String from = "semipuppyhoney@gmail.com";

		String to = user.getUserEmail();
		
		String subject = "PuppyHoney 회원가입 인증메일입니다.";
		
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

			script.println("alert('오류발생.');");

			script.println("history.back();");

			script.println("</script>");

			script.close();		

		    return;

		}


		
		int result=new UserService().insertUser(user);
		
		String msg="";
		String loc="/";
		String view="/views/common/msg.jsp";
		if(result>0)
		{
			msg="회원가입이 완료 되었습니다. E-Mail 인증 후에 로그인 해주세요.";
		}
		else
		{
			msg="회원가입 실패";
			loc="/views/user/register.jsp";
		}
		
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	

}
