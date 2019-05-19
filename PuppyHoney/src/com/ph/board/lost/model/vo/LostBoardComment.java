package com.ph.board.lost.model.vo;

import java.sql.Date;

public class LostBoardComment {
	
	private int lostReplyNum;
	private int lostBoardRef;
	private String lostReplyId;
	private Date lostReplyDate;
	private String lostReplyContent;
	private int lostReplyGood;
	private int lostReplyLevel;
	private int lostReplyRef;
	
	public LostBoardComment() {}

	public LostBoardComment(int lostReplyNum, int lostBoardRef, String lostReplyId, Date lostReplyDate,
			String lostReplyContent, int lostReplyGood, int lostReplyLevel, int lostReplyRef) {
		super();
		this.lostReplyNum = lostReplyNum;
		this.lostBoardRef = lostBoardRef;
		this.lostReplyId = lostReplyId;
		this.lostReplyDate = lostReplyDate;
		this.lostReplyContent = lostReplyContent;
		this.lostReplyGood = lostReplyGood;
		this.lostReplyLevel = lostReplyLevel;
		this.lostReplyRef = lostReplyRef;
	}

	public int getLostReplyNum() {
		return lostReplyNum;
	}

	public void setLostReplyNum(int lostReplyNum) {
		this.lostReplyNum = lostReplyNum;
	}

	public int getLostBoardRef() {
		return lostBoardRef;
	}

	public void setLostBoardRef(int lostBoardRef) {
		this.lostBoardRef = lostBoardRef;
	}

	public String getLostReplyId() {
		return lostReplyId;
	}

	public void setLostReplyId(String lostReplyId) {
		this.lostReplyId = lostReplyId;
	}

	public Date getLostReplyDate() {
		return lostReplyDate;
	}

	public void setLostReplyDate(Date lostReplyDate) {
		this.lostReplyDate = lostReplyDate;
	}

	public String getLostReplyContent() {
		return lostReplyContent;
	}

	public void setLostReplyContent(String lostReplyContent) {
		this.lostReplyContent = lostReplyContent;
	}

	public int getLostReplyGood() {
		return lostReplyGood;
	}

	public void setLostReplyGood(int lostReplyGood) {
		this.lostReplyGood = lostReplyGood;
	}

	public int getLostReplyLevel() {
		return lostReplyLevel;
	}

	public void setLostReplyLevel(int lostReplyLevel) {
		this.lostReplyLevel = lostReplyLevel;
	}

	public int getLostReplyRef() {
		return lostReplyRef;
	}

	public void setLostReplyRef(int lostReplyRef) {
		this.lostReplyRef = lostReplyRef;
	}

	@Override
	public String toString() {
		return "LostBoardComment [lostReplyNum=" + lostReplyNum + ", lostBoardRef=" + lostBoardRef + ", lostReplyId="
				+ lostReplyId + ", lostReplyDate=" + lostReplyDate + ", lostReplyContent=" + lostReplyContent
				+ ", lostReplyGood=" + lostReplyGood + ", lostReplyLevel=" + lostReplyLevel + ", lostReplyRef="
				+ lostReplyRef + "]";
	}
	

}
