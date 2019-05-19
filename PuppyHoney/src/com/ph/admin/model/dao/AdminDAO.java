

package com.ph.admin.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ph.board.place.model.vo.PlaceBoard;
import com.ph.infoBoard.model.dao.InfoBoardDAO;
import com.ph.infoBoard.model.vo.InfoBoard;
import com.ph.user.model.vo.User;

public class AdminDAO {

private Properties prop;
   
   public AdminDAO() {
      prop=new Properties();
      try {
          String file=AdminDAO.class.getResource("/sql/admin/admin-sql.properties").getPath();
          prop.load(new FileReader(file));
       }
       catch(IOException e)
       {
          e.printStackTrace();
       }
   }
   
   public List<User> allMemberList(Connection conn, int cPage, int numPerPage) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("allMemberList");
      ArrayList<User> list=new ArrayList();
      User u=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, ((cPage-1)*numPerPage+1));
         pstmt.setInt(2, cPage*numPerPage);         
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            u=new User();
            u.setUserId(rs.getString("USER_ID"));
            u.setUserPw(rs.getString("USER_PW"));
            u.setUserNick(rs.getString("USER_NICK"));
            u.setUserEmail(rs.getString("USER_EMAIL"));
            u.setUserName(rs.getString("USER_NAME"));
            u.setUserBirth(rs.getDate("USER_BIRTH"));
            u.setUserDogName(rs.getString("USER_DOG_NAME"));
            u.setUserDogBirth(rs.getDate("USER_DOG_BIRTH"));
            u.setUserImageOrigin(rs.getString("USER_IMAGE_ORIGIN"));
            u.setUserImageRename(rs.getString("USER_IMAGE_RENAME"));
            u.setUserBookmark(rs.getString("USER_BOOKMARK"));
            u.setUserJoinDate(rs.getDate("USER_JOIN_DATE"));
            u.setUserLeave(rs.getString("USER_LEAVE"));
            u.setUserEmailChecked(rs.getInt("USER_EMAIL_CHECKED"));
            u.setUserEmailHash(rs.getString("USER_EMAIL_HASH"));
            list.add(u);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
   }

   public int allUserCount(Connection conn) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("allUserCount");
      int result=0;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            result=rs.getInt("cnt");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return result;
   }

   public User selectUser(Connection conn, String userId) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("selectUser");
      User user=null;
      try
      {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, userId);
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

   public int deleteMember(Connection conn, String userId) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql =prop.getProperty("deleteMember");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, userId);
         result=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;

   }

   public List<User> searchMemberList(Connection conn, int cPage, int numPerPage, String searchType,
         String inputText) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      
      String sql="SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT * FROM PH_USER WHERE "+searchType+" LIKE "+"'%"+inputText+"%' ORDER BY USER_JOIN_DATE DESC)A)WHERE RNUM BETWEEN "+((cPage-1)*numPerPage+1)+" AND "+cPage*numPerPage;
      ArrayList<User> list=new ArrayList();
      User u=null;
      
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            
            u=new User();
            u.setUserId(rs.getString("user_id"));
            u.setUserPw(rs.getString("user_pw"));
            u.setUserNick(rs.getString("user_nick"));
            u.setUserEmail(rs.getString("user_email"));
            u.setUserName(rs.getString("user_name"));
            u.setUserBirth(rs.getDate("user_birth"));
            u.setUserDogName(rs.getString("user_dog_name"));
            u.setUserDogBirth(rs.getDate("user_dog_birth"));
            u.setUserImageOrigin(rs.getString("user_image_origin"));
            u.setUserImageRename(rs.getString("user_image_rename"));
            u.setUserBookmark(rs.getString("user_bookmark"));
            u.setUserJoinDate(rs.getDate("user_join_date"));
            u.setUserLeave(rs.getString("user_leave"));
            u.setUserEmailHash(rs.getString("user_email_hash"));
            u.setUserEmailChecked(rs.getInt("user_email_checked"));
            list.add(u);
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return list;
   }

   public int countMemberList(Connection conn, String searchType, String inputText) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql="SELECT COUNT(*) AS CNT FROM PH_USER WHERE "+searchType+" LIKE "+"'%"+inputText+"%'";
      int result=0;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            result=rs.getInt("cnt");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return result;
   }

   public String countUserId(Connection conn, int i) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String userId ="";
      String sql =prop.getProperty("countUserId");
      try
      {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, i);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            userId=rs.getString("USER_ID");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return userId;
      
   }

   public int allMessageSend(Connection conn, String userId, String title, String content, String admin) {
      
      PreparedStatement pstmt = null;
      int sendCount = 0;
      String sql =prop.getProperty("allMessageSend");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, title);
         pstmt.setString(2, content);
         pstmt.setString(3, userId);
         pstmt.setString(4, admin);
         sendCount=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return sendCount;
   }

   public List<PlaceBoard> acceptBoardList(Connection conn) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql=prop.getProperty("acceptBoardList");
      ArrayList<PlaceBoard> pList=new ArrayList();
      PlaceBoard plBoard=null;
      try
      {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next())
         {
            plBoard=new PlaceBoard();
            plBoard.setPlBoardNum(rs.getInt("place_board_num"));
            plBoard.setPlBoardTitle(rs.getString("place_board_title"));
            plBoard.setPlBoardId(rs.getString("user_nick"));
            plBoard.setPlBoardContent(rs.getString("place_board_content"));
            plBoard.setPlBoardDate(rs.getDate("place_board_date"));
            plBoard.setPlBoardHits(rs.getInt("place_board_hits"));
            plBoard.setPlBoardGood(rs.getInt("place_board_good"));
            plBoard.setPlBoardArea(rs.getString("place_board_area"));
            plBoard.setPlBoardDogSize(rs.getString("place_board_dogsize"));
            plBoard.setPlBoardBusinessType(rs.getString("place_board_business_type"));
            plBoard.setPlBoardStoreName(rs.getString("place_board_name"));
            plBoard.setPlBoardTime(rs.getString("place_board_time"));
            plBoard.setPlBoardPhone(rs.getString("place_board_phone"));
            plBoard.setPlBoardAddr(rs.getString("place_board_name"));
            plBoard.setPlBoardAcept_yn(rs.getString("place_board_accept_yn"));
            pList.add(plBoard);
         }
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      return pList;
   }

   public int boardAccept(Connection conn, int placeBoardNum) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql =prop.getProperty("boardAccept");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, placeBoardNum);
         result=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;

   }
   
   
   
   public String sameUserId(Connection conn, String placeBoardId) {
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String userId ="";
      String sql =prop.getProperty("sameUserId");
      try
      {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, placeBoardId);
         rs=pstmt.executeQuery();
         if(rs.next())
         {
            userId=rs.getString("USER_ID");
         }
         
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      
      close(rs);
      close(pstmt);
      
      return userId;
   }
   
   

   public int messageSend(Connection conn, String userId, String getPlBoardTitle) {
      PreparedStatement pstmt = null;
      int result2 = 0;
      String sql =prop.getProperty("messageSend");
      
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, "게시물이 승인되었습니다.");
         pstmt.setString(2, "요청한 게시물 '"+getPlBoardTitle+"'(가)이 승인되었습니다. 장소게시판을 확인해 주세요.");
         pstmt.setString(3, userId);
         result2=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result2;
   }

   public int messageSendDeny(Connection conn, String userId, String getPlBoardTitle) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql =prop.getProperty("messageSend");
      
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, "게시물이 거부되었습니다.");
         pstmt.setString(2, "요청한 게시물 '"+getPlBoardTitle+"'(가)이 거부되었습니다.");
         pstmt.setString(3, userId);
         result=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;
   }

   public int boardCancel(Connection conn, int placeBoardNum) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql =prop.getProperty("boardCancel");
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, placeBoardNum);
         result=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;
   }

   public int messageSendCancel(Connection conn, String userId, String getPlBoardTitle) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql =prop.getProperty("messageSend");
      
      
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, "승인된 게시물이 취소되었습니다.");
         pstmt.setString(2, "승인된 게시물 '"+getPlBoardTitle+"'(가)이 취승인소되었습니다.");
         pstmt.setString(3, userId);
         result=pstmt.executeUpdate();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      close(pstmt);
      return result;
   }

   

   

   

}