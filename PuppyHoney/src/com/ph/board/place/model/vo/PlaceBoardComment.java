package com.ph.board.place.model.vo;


public class PlaceBoardComment {
	private int plBoardReplyNo;	//댓글번호(시퀀스로 증가)
	private int plBoardRef;	//부모 게시물 번호
	private String plBoardReplyId; //작성자 아이디
	private String plBoardDate; //날짜(default)
	private String plBoardReplyContent; // 입력값(내용)
	private int plBoardReplyGood; //추천
	private int plBoardReplyLevel; //답글,댓글 구분을 위한 깊이 (댓글 1, 답글2)
	private int plBoardReplyRef;	//부모댓글의 번호 (답글이 따라가기위한 번호)
	
	
	public PlaceBoardComment() {}


	public PlaceBoardComment(int plBoardReplyNo, int plBoardRef, String plBoardReplyId, String plBoardDate,
			String plBoardReplyContent, int plBoardReplyGood, int plBoardReplyLevel, int plBoardReplyRef) {
		super();
		this.plBoardReplyNo = plBoardReplyNo;
		this.plBoardRef = plBoardRef;
		this.plBoardReplyId = plBoardReplyId;
		this.plBoardDate = plBoardDate;
		this.plBoardReplyContent = plBoardReplyContent;
		this.plBoardReplyGood = plBoardReplyGood;
		this.plBoardReplyLevel = plBoardReplyLevel;
		this.plBoardReplyRef = plBoardReplyRef;
	}


	public int getPlBoardReplyNo() {
		return plBoardReplyNo;
	}


	public void setPlBoardReplyNo(int plBoardReplyNo) {
		this.plBoardReplyNo = plBoardReplyNo;
	}


	public int getPlBoardRef() {
		return plBoardRef;
	}


	public void setPlBoardRef(int plBoardRef) {
		this.plBoardRef = plBoardRef;
	}


	public String getPlBoardReplyId() {
		return plBoardReplyId;
	}


	public void setPlBoardReplyId(String plBoardReplyId) {
		this.plBoardReplyId = plBoardReplyId;
	}


	public String getPlBoardDate() {
		return plBoardDate;
	}


	public void setPlBoardDate(String plBoardDate) {
		this.plBoardDate = plBoardDate;
	}


	public String getPlBoardReplyContent() {
		return plBoardReplyContent;
	}


	public void setPlBoardReplyContent(String plBoardReplyContent) {
		this.plBoardReplyContent = plBoardReplyContent;
	}


	public int getPlBoardReplyGood() {
		return plBoardReplyGood;
	}


	public void setPlBoardReplyGood(int plBoardReplyGood) {
		this.plBoardReplyGood = plBoardReplyGood;
	}


	public int getPlBoardReplyLevel() {
		return plBoardReplyLevel;
	}


	public void setPlBoardReplyLevel(int plBoardReplyLevel) {
		this.plBoardReplyLevel = plBoardReplyLevel;
	}


	public int getPlBoardReplyRef() {
		return plBoardReplyRef;
	}


	public void setPlBoardReplyRef(int plBoardReplyRef) {
		this.plBoardReplyRef = plBoardReplyRef;
	}


	

	

}
