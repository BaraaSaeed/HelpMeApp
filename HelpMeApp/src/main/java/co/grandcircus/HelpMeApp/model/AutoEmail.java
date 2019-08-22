package co.grandcircus.HelpMeApp.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@Component
public class AutoEmail {

	@Value("${sendGrid_KEY}")
	private String SENDGRID_KEY;
	@Value("${email_ADDRESS}")
	private String EMAIL_ADDRESS;
	
	@Autowired
	MessageDao messageDao;

	public void sendMail(User user, Long orgId, String issue, String name, String userContent) throws Exception {

		String link = "http://localhost:8080/org-message-detail?orgId=" + orgId + "&userId=" + user.getId();
		Email from = new Email(user.getFirstName() + "@HelpMeApp.com");
		String fromString = (user.getFirstName() + "@HelpMeApp.com");
		System.out.println(issue);
		String subject = "Help Requested from " + user.getFirstName() + " from " + user.getCity();
		Email to = new Email(EMAIL_ADDRESS);
		String toString = EMAIL_ADDRESS;
		String bodyContent;
//		System.out.println(userContent);
//		if (userContent.equals(",")) {
			bodyContent = "Hello, I'm currently living in " + user.getCity() + " and am interested in more information on " + issue  
					+ ". To reply, please follow this link: " + link;
//		} else {
//		bodyContent = userContent + link;
//		System.out.println(bodyContent);
//		}
		Content content = new Content("text/plain", bodyContent);
		String contentString = bodyContent;
		Mail mail = new Mail(from, subject, to, content);

		Message message = new Message(user.getId(), orgId, issue, getDate(), fromString, name, subject,
				contentString);
		messageDao.save(message);
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
}
