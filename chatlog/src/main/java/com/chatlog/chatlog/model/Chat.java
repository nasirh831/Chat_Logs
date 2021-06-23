package com.chatlog.chatlog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String user;
	@Column
	private String message;
	@Column
	private String issend;
	@Column
	private Long timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsSend() {
		return issend;
	}
	public void setIsSend(String isSend) {
		this.issend = isSend;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Chat [id=" + id + ", user=" + user + ", message=" + message + ", isSend=" + issend + ", timestamp="
				+ timestamp + "]";
	}
	
}
