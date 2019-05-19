package com.ph.message.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.message.model.vo.Message;
import com.ph.user.model.dao.UserDAO;

public class MessageDAO {
	
	private Properties prop;
	
	public MessageDAO()
	{
		prop=new Properties();
		try
		{
			prop.load(new FileReader(MessageDAO.class.getResource("/sql/message/message.properties").getPath()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Message> receiverMessageList(Connection conn, String userId, int cPage, int numPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Message m=null;
		String sql=prop.getProperty("receiverMessageList");
		ArrayList<Message> list=new ArrayList();
		System.out.println("유저 아이디"+userId);
		System.out.println("초기 페이지"+cPage);
		System.out.println("페이지개수"+numPerPage);
		
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			System.out.println("sql문"+sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				m=new Message();
				m.setMessageNum(rs.getInt("message_num"));
				m.setMessageTitle(rs.getString("message_title"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageReceiver(rs.getString("message_receiver"));
				m.setMessageWriter(rs.getString("message_writer"));
				m.setMessageDate(rs.getDate("message_date"));
				m.setMessageRead(rs.getString("message_read"));
				list.add(m);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		System.out.println("다오"+list);
		return list;
	}
	
	public List<Message> writerMessageList(Connection conn, String userId, int cPage, int numPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Message m=null;
		String sql=prop.getProperty("writerMessageList");
		ArrayList<Message> list=new ArrayList();
		System.out.println("유저 아이디"+userId);
		System.out.println("초기 페이지"+cPage);
		System.out.println("페이지개수"+numPerPage);
		
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			System.out.println("sql문"+sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				m=new Message();
				m.setMessageNum(rs.getInt("message_num"));
				m.setMessageTitle(rs.getString("message_title"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageReceiver(rs.getString("message_receiver"));
				m.setMessageWriter(rs.getString("message_writer"));
				m.setMessageDate(rs.getDate("message_date"));
				m.setMessageRead(rs.getString("message_read"));
				list.add(m);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		System.out.println("다오2"+list);
		return list;
	}
	
	public int selectReceiverMessageCount(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectReceiverMessageCount");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		return result;
	}
	
	public int selectWriterMessageCount(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectWriterMessageCount");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		return result;
	}
	
	public int insertMessage(Connection conn, Message m) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertMessage");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMessageTitle());
			pstmt.setString(2, m.getMessageContent());
			pstmt.setString(3, m.getMessageReceiver());
			pstmt.setString(4, m.getMessageWriter());
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		
		return result;
	}
	
	public int deleteReceiveMessage(Connection conn, int s) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteReceiveMessage");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, s);
			result=pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public Message selectMessage(Connection conn, int messageNum) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectMessage");
		Message m=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, messageNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				m=new Message();
				m.setMessageNum(rs.getInt("message_num"));
				m.setMessageTitle(rs.getString("message_title"));
				m.setMessageContent(rs.getString("message_content"));
				m.setMessageWriter(rs.getString("message_writer"));
				m.setMessageDate(rs.getDate("message_date"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		close(rs);
		close(pstmt);
		
		return m;
	}

}
