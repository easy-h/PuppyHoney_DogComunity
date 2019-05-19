package com.ph.user.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.close;
import com.ph.user.model.vo.User;

public class UserDAO {
 
	private Properties prop;
	
	public UserDAO()
	{
		prop=new Properties();
		try
		{
			prop.load(new FileReader(UserDAO.class.getResource("/sql/user/user_query.properties").getPath()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int insertUser(Connection conn, User user)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertUser");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserNick());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getUserName());
			pstmt.setDate(6, user.getUserBirth());
			pstmt.setString(7, user.getUserDogName());
			pstmt.setDate(8, user.getUserDogBirth());
			pstmt.setString(9, user.getUserImageOrigin());
			pstmt.setString(10, user.getUserImageRename());
			pstmt.setString(11, user.getUserBookmark());
			pstmt.setString(12, user.getUserEmailHash());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public User selectUser(Connection conn,String id)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectUser");
		User user=null;
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				user=new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserNick(rs.getString("user_nick"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getDate("user_birth"));
				user.setUserDogName(rs.getString("user_dog_name"));
				user.setUserDogBirth(rs.getDate("user_dog_birth"));
				user.setUserImageOrigin(rs.getString("user_image_origin"));
				user.setUserImageRename(rs.getString("user_image_rename"));
				user.setUserBookmark(rs.getString("user_bookmark"));
				user.setUserJoinDate(rs.getDate("user_join_date"));
				user.setUserLeave(rs.getString("user_leave"));
				user.setUserEmailHash(rs.getString("user_email_hash"));
				user.setUserEmailChecked(rs.getInt("user_email_checked"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return user;
	}
	
	public int checkUserId(Connection conn, String id)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("checkUserId");
		int result=0;
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("CNT");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	public int checkUserNick(Connection conn, String nick)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("checkUserNick");
		int result=0;
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("CNT");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	public int checkUserEmail(Connection conn, String email)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("checkUserEmail");
		int result=0;
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("CNT");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	public int updateEmailChecked(Connection conn, String userId)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateEmailChecked");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	

	public int updateUser(Connection conn, User user)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateUser");
		try
		{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserNick());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserName());
			pstmt.setDate(5, user.getUserBirth());
			pstmt.setString(6, user.getUserImageOrigin());
			pstmt.setString(7, user.getUserImageRename());
			pstmt.setString(8, user.getUserBookmark());
			pstmt.setString(9, user.getUserEmailHash());
			pstmt.setString(10, user.getUserId());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		
		
		return result;
	}
	
	public User selectUserNick(Connection conn, String id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectUserNick");
		User user=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				user=new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserNick(rs.getString("user_nick"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getDate("user_birth"));
				user.setUserDogName(rs.getString("user_dog_name"));
				user.setUserDogBirth(rs.getDate("user_dog_birth"));
				user.setUserImageOrigin(rs.getString("user_image_origin"));
				user.setUserImageRename(rs.getString("user_image_rename"));
				user.setUserBookmark(rs.getString("user_bookmark"));
				user.setUserJoinDate(rs.getDate("user_join_date"));
				user.setUserLeave(rs.getString("user_leave"));
				user.setUserEmailHash(rs.getString("user_email_hash"));
				user.setUserEmailChecked(rs.getInt("user_email_checked"));
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		close(pstmt);
		
		return user;
	}
}
