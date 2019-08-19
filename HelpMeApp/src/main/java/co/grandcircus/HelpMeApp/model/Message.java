package co.grandcircus.HelpMeApp.model;

import java.io.Serializable;
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
	private Long orgId;
	private String issue;
	private Date date;
	@Column(name="`from`")
	private String from;
	@Column(name="`to`")
	private String to;
	private String subject;
	private String content;
	
	
	public Message() {
		super();
	}

	public Message(Long userId, Long orgId, String issue, Date date, String from, String to, String subject,
			String content) {
		super();
		this.userId = userId;
		this.orgId = orgId;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		return "Message [messageId=" + messageId + ", userId=" + userId + ", orgId=" + orgId + ", issue=" + issue
				+ ", date=" + date + ", from=" + from + ", to=" + to + ", subject=" + subject + ", content=" + content
				+ "]";
	}
	
	
	
}