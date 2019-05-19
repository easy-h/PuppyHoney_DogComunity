package com.ph.message.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.ph.message.model.dao.MessageDAO;
import com.ph.message.model.vo.Message;

public class MessageService {
	
	public List<Message> receiverMessageList(String userId, int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Message> list=new MessageDAO().receiverMessageList(conn, userId, cPage, numPerPage);
		close(conn);
		System.out.println("서비스"+list);
		return list;
		
	}
	
	public List<Message> writerMessageList(String userId, int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Message> list=new MessageDAO().writerMessageList(conn, userId, cPage, numPerPage);
		close(conn);
		System.out.println("서비스2"+list);
		return list;
	}
	
	public int selectReceiverMessageCount(String userId) {
		Connection conn=getConnection();
		int result=new MessageDAO().selectReceiverMessageCount(conn, userId);
		close(conn);
		return result;
	}
	
	public int selectWriterMessageCount(String userId) {
		Connection conn=getConnection();
		int result=new MessageDAO().selectWriterMessageCount(conn, userId);
		close(conn);
		return result;
	}
	
	public int insertMessage(Message m) {
		Connection conn=getConnection();
		int result=new MessageDAO().insertMessage(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteReceiveMessage(int s) {
		Connection conn=getConnection();
		int result=new MessageDAO().deleteReceiveMessage(conn, s);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public Message selectMessage(int messageNum) {
		Connection conn=getConnection();
		Message m=new MessageDAO().selectMessage(conn, messageNum);
		return m;
		
	}

}
