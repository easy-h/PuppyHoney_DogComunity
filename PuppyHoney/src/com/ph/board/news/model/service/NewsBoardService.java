
package com.ph.board.news.model.service;

import java.sql.Connection;
import java.util.List;

import com.ph.board.news.model.dao.NewsBoardDAO;
import com.ph.board.news.model.vo.NewsBoard;
import com.ph.board.news.model.vo.NewsReply;
import com.ph.infoBoard.model.dao.InfoBoardDAO;
import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.infoBoard.model.vo.InfoReply;

import static common.JDBCTemplate.*;
public class NewsBoardService {

   public List<NewsBoard> selectNewsBoardList()
   {
      Connection conn=getConnection();
      List<NewsBoard> newsList=new NewsBoardDAO().selectNewsBoardList(conn);
      close(conn);
      return newsList;
   }
   
   public int insertNews(NewsBoard nb)
   {
      Connection conn=getConnection();
      int result=new NewsBoardDAO().insertNews(conn, nb);
      if(result>0)commit(conn);
      else rollback(conn);
      close(conn);
      return result;
      
   }
   
   public NewsBoard selectNewsBoard(int num)
   {
      Connection conn=getConnection();
      NewsBoard nb=new NewsBoardDAO().selectNewsBoard(conn, num);
      close(conn);
      return nb;
   }

public List<NewsReply> selectNewsBoardReplyList(int num) {
   
      Connection conn = getConnection();
      List<NewsReply> list = new NewsBoardDAO().selectNewsBoardReplyList(conn,num);
      return list;
   }

public NewsBoard selectOne(int num, boolean hasRead2) {
   Connection conn = getConnection();
   NewsBoard nb = new NewsBoardDAO().selectOne(conn,num);
   int result=0; //조회수1증가처리
   if(nb!=null) {
      if(!hasRead2) {
         result = new NewsBoardDAO().insertBoardCount(conn, num);
         if(result>0) commit(conn);
         else rollback(conn);
      }
   }
   
   close(conn);
   return nb;
}

public int insertNewsReply(NewsReply ir) {
   Connection conn = getConnection();
   int result=new NewsBoardDAO().insertNewsReply(conn, ir);
   if(result>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result;
}

public int deleteNewsBoard(int boardNum) {
   Connection conn = getConnection();
   int result=new NewsBoardDAO().deleteNewsBoard(conn, boardNum);
   if(result>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result;
}

public int checkRecommend(int boardNum, String userId) {
   Connection conn = getConnection();
   int result=new NewsBoardDAO().checkRecommend(conn, boardNum, userId);
   close(conn);
   return result;
}

public int insertNewsBoardGood(int boardNum, String userId) {
   Connection conn = getConnection();
   int result2=new NewsBoardDAO().insertNewsBoardGood(conn, boardNum, userId);
   if(result2>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result2;
}

public int countNewsBoardRecommend(int boardNum) {
   Connection conn = getConnection();
   int result3=new NewsBoardDAO().countNewsBoardRecommend(conn, boardNum);
   if(result3>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result3;
}

public int checkGonggam(int replyNum, String userId) {
   Connection conn = getConnection();
   int result=new NewsBoardDAO().checkGonggam(conn, replyNum, userId);
   close(conn);
   return result;
}

public int insertNewsReplyGood(int replyNum, String userId) {
   Connection conn = getConnection();
   int result2=new NewsBoardDAO().insertNewsReplyGood(conn, replyNum, userId);
   if(result2>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result2;
}

public int countNewsReplyGonggam(int replyNum) {
   Connection conn = getConnection();
   int result3=new NewsBoardDAO().countNewsReplyGonggam(conn, replyNum);
   if(result3>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result3;
}

public int deleteNewsReply(int replyNum) {
   Connection conn = getConnection();
   int result=new NewsBoardDAO().deleteNewsReply(conn, replyNum);
   if(result>0) {
      commit(conn);
   }else {
      rollback(conn);
   }
   close(conn);
   return result;
}
}