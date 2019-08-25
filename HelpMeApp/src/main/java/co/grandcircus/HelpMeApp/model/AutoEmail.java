package co.grandcircus.HelpMeApp.model;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import co.grandcircus.HelpMeApp.Dao.MessageDao;
import co.grandcircus.HelpMeApp.Dao.OrgDao;

@Component
public class AutoEmail {

	@Value("${sendGrid_KEY}")
	private String SENDGRID_KEY;
	@Value("${email_ADDRESS}")
	private String EMAIL_ADDRESS;	
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private OrgDao orgDao;

	public void sendMailUserToOrg(User user, Org org, String userContent) throws Exception {
		
		Mail mail = getMail(user, org);
		saveMessageAndOrg(user, org);
		SendGrid sg = new SendGrid(SENDGRID_KEY);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
		} catch (IOException ex) {
			throw ex;
		}
	}

	public Date getDate() {
		Date dateObj = new Date();
		return dateObj;
	}
	
	public String wrapApiIdToHtml(String apiId) {
		String wrappedApi = apiId.replaceAll(" ", "_");
		return wrappedApi;
	}
	
	public String getOrgToUserLink(Org org, User user) {
		String link = "http://localhost:8080/org-message-detail?apiId=" + 
			wrapApiIdToHtml(org.getApiId()) + "&userId=" + user.getId();
		return link;
	}
	
	public String getUserEmail(User user) {
		String userEmail = 
				user.getFirstName().toLowerCase().substring(0, 1) + 
				user.getLastName().toLowerCase() + 
				"@HelpMeApp.com";
		return userEmail;
	}
	
	public String getUserSubject(User user) {
		String subject = 
				"Help Requested with " + user.getSelection() + 
				" from " + getUserFullName(user) + 
				" from " + user.getCity();
		return subject;
	}
	
	public String getUserTextContent(User user, Org org) {
		String content = 
				"Hello, I'm currently living in " + user.getCity() + 
				" and I'm reaching out to " + org.getName() + 
				" because I'm looking for help with " + 
				user.getSelection().toLowerCase()  +
				". To reply, please follow this link: ";
		return content;
	}
	
	public String getUserTotalContent(User user, Org org) {
		String content = getUserTextContent(user, org) + getOrgToUserLink(org, user);
		return content;
	}
	
	public Mail getMail(User user, Org org) {
		Email from = new Email(getUserEmail(user));
		String subject = getUserSubject(user);
		Email to = new Email(EMAIL_ADDRESS);
		Content content = new Content("text/plain", getUserTotalContent(user, org));
		return new Mail(from, subject, to, content);
	}
	
	public String getUserFullName(User user) {
		String fullName = user.getFirstName() + " " + user.getLastName();
		return fullName;
	}
	
	public Message getMessage(User user, Org org) {
		Message message = new Message(
				user.getId(), getUserFullName(user), 
				org.getOrgId(), org.getName(), org.getApiId(), 
				user.getSelection(), getDate(), getUserFullName(user), 
				org.getName(), getUserSubject(user), getUserTextContent(user, org));
		return message;
	}
	
	public void saveMessageAndOrg(User user, Org org) {
		Message message = getMessage(user, org);
		messageDao.save(message);
		orgDao.save(org);
	}
	
	
}
