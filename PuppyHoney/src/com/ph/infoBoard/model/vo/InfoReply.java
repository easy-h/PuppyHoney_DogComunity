package com.ph.infoBoard.model.vo;

public class InfoReply {
	private int replyNum;
	private int boardRef;
	private String replyId;
	private String replyDate;
	private String replyContent;
	private int replyGood;
	private int replyLevel;
	private int replyRef;
	private String reName;
	
	public InfoReply() {}

	public InfoReply(int replyNum, int boardRef, String replyId, String replyDate, String replyContent, int replyGood,
			int replyLevel, int replyRef, String reName) {
		super();
		this.replyNum = replyNum;
		this.boardRef = boardRef;
		this.replyId = replyId;
		this.replyDate = replyDate;
		this.replyContent = replyContent;
		this.replyGood = replyGood;
		this.replyLevel = replyLevel;
		this.replyRef = replyRef;
		this.reName = reName;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getReplyGood() {
		return replyGood;
	}

	public void setReplyGood(int replyGood) {
		this.replyGood = replyGood;
	}

	public int getReplyLevel() {
		return replyLevel;
	}

	public void setReplyLevel(int replyLevel) {
		this.replyLevel = replyLevel;
	}

	public int getReplyRef() {
		return replyRef;
	}

	public void setReplyRef(int replyRef) {
		this.replyRef = replyRef;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	@Override
	public String toString() {
		return "InfoReply [replyNum=" + replyNum + ", boardRef=" + boardRef + ", replyId=" + replyId + ", replyDate="
				+ replyDate + ", replyContent=" + replyContent + ", replyGood=" + replyGood + ", replyLevel="
				+ replyLevel + ", replyRef=" + replyRef + ", reName=" + reName + "]";
	}
	
	
	
	
	
}
