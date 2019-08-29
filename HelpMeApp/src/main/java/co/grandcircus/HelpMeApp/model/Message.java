package co.grandcircus.HelpMeApp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;
	private Long userId;
	private Boolean fromUser;
	private String userName;
	private String orgId;
	private String orgName;
	private String apiId;
	private String issue;
	private LocalDateTime date;
	@Column(name="`from`")
	private String from;
	@Column(name="`to`")
	private String to;
	private String subject;
	private String content;
	
	public Message() {
		super();
	}

	public Message(Long messageId, Long userId, Boolean fromUser, String userName, String orgId, String orgName, String apiId,
			String issue, LocalDateTime date, String from, String to, String subject, String content) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.fromUser = fromUser;
		this.userName = userName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.apiId = apiId;
		this.issue = issue;
		this.date = date;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public Message(Long userId, String userName, Boolean fromUser, String orgId, String orgName, String apiId, String issue,
			LocalDateTime date, String from, String to, String subject, String content) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.fromUser = fromUser;
		this.orgId = orgId;
		this.orgName = orgName;
		this.apiId = apiId;
		this.issue = issue;
		this.date = date;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getFromUser() {
		return fromUser;
	}

	public void setFromUser(Boolean fromUser) {
		this.fromUser = fromUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userId=" + userId + ", fromUser=" + fromUser + ", userName="
				+ userName + ", orgId=" + orgId + ", orgName=" + orgName + ", apiId=" + apiId + ", issue=" + issue
				+ ", date=" + date + ", from=" + from + ", to=" + to + ", subject=" + subject + ", content=" + content
				+ "]";
	}

}