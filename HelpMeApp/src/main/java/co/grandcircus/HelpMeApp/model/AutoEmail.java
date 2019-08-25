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

	private HelpMeMethods methods;

	public void sendMail(User user, Org org, String issue, String userContent) throws Exception {
		String wrappedApi = wrapApiIdToHtml(org.getApiId());
		String link = "http://localhost:8080/org-message-detail?apiId=" + wrappedApi + "&userId=" + user.getId();
		System.out.println(link);
		Email from = new Email(user.getFirstName() + "@HelpMeApp.com");
		String fromString = (user.getFirstName() + "@HelpMeApp.com");
		String subject = "Help Requested from " + user.getFirstName() + " from " + user.getCity();

		Email to = new Email(EMAIL_ADDRESS);
		String toString = EMAIL_ADDRESS;
		String linkBase = "To reply, please follow this link: "; 
		String bodyContent;
//		System.out.println(userContent);
//		if (userContent.equals(",")) {
			bodyContent = "Hello, I'm currently living in " + user.getCity() + 
					" and I'm reaching out to " + org.getName() + 
					" because I'm interested in more information on " + issue.toLowerCase()  + ". ";
//		} else {
//		bodyContent = userContent + link;
//		System.out.println(bodyContent);
//		}
		Content content = new Content("text/plain", bodyContent + linkBase + link);
		String contentString = bodyContent;
		Mail mail = new Mail(from, subject, to, content);
					//		Long messageId, Long userId, String apiId, String issue, Date date, String from, String to,
					//		String subject, String content
		Message message = new Message(user.getId(), user.getFirstName() + " " + user.getLastName(), org.getOrgId(), org.getName(), org.getApiId(), issue, getDate(), user.getFirstName() + " " + user.getLastName(), org.getName(), subject,
				contentString);
		messageDao.save(message);
		orgDao.save(org);
		
		SendGrid sg = new SendGrid(SENDGRID_KEY);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
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
	
}
