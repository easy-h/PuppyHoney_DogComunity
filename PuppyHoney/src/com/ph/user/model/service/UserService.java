package com.ph.user.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.ph.user.model.dao.UserDAO;
import com.ph.user.model.vo.User;

public class UserService {
 
	public int insertUser(User user)
	{
		Connection conn=getConnection();
		int result=new UserDAO().insertUser(conn,user);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public User selectUser(String id)
	{
		Connection conn=getConnection();
		User user=new UserDAO().selectUser(conn,id);
		close(conn);
		return user;
	}
	
	public int checkUserId(String id)
	{
		Connection conn=getConnection();
		int result=new UserDAO().checkUserId(conn,id);
		close(conn);
		return result;
	}
	
	public int checkUserNick(String nick)
	{
		Connection conn=getConnection();
		int result=new UserDAO().checkUserNick(conn,nick);
		close(conn);
		return result;
	}
	public int checkUserEmail(String email)
	{
		Connection conn=getConnection();
		int result=new UserDAO().checkUserEmail(conn,email);
		close(conn);
		return result;
	}
	
	public int updateEmailChecked(String userId)
	{
		Connection conn=getConnection();
		int result=new UserDAO().updateEmailChecked(conn,userId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	

	public int updateUser(User user)
	{
		Connection conn=getConnection();
		int result=new UserDAO().updateUser(conn,user);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteMember(String userId) {
		Connection conn=getConnection();
		int result=new UserDAO().deleteMember(conn,userId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		System.out.println("¼­ºñ½º"+result);
		return result;
	}
	
	public User selectUserNick(String id) {
		Connection conn=getConnection();
		User user=new UserDAO().selectUserNick(conn, id);
		close(conn);
		return user;
		
	}
}
