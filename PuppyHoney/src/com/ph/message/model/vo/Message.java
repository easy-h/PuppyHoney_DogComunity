package com.ph.message.model.vo;

import java.sql.Date;

public class Message {
	
	private int messageNum;
	private String messageTitle;
	private String messageContent;
	private String messageReceiver;
	private String messageWriter;
	private Date messageDate;
	private String messageRead;
	
	public Message() {}

	public Message(int messageNum, String messageTitle, String messageContent, String messageReceiver,
			String messageWriter, Date messageDate, String messageRead) {
		super();
		this.messageNum = messageNum;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.messageReceiver = messageReceiver;
		this.messageWriter = messageWriter;
		this.messageDate = messageDate;
		this.messageRead = messageRead;
	}

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public String getMessageWriter() {
		return messageWriter;
	}

	public void setMessageWriter(String messageWriter) {
		this.messageWriter = messageWriter;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageRead() {
		return messageRead;
	}

	public void setMessageRead(String messageRead) {
		this.messageRead = messageRead;
	}

	@Override
	public String toString() {
		return "Message [messageNum=" + messageNum + ", messageTitle=" + messageTitle + ", messageContent="
				+ messageContent + ", messageReceiver=" + messageReceiver + ", messageWriter=" + messageWriter
				+ ", messageDate=" + messageDate + ", messageRead=" + messageRead + "]";
	}
	
	

}
