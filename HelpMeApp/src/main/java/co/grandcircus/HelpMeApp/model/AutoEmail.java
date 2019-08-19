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
	
	@Autowired
	MessageDao userMessageDao;
	
	public void sendMail(User user, Long orgId, String issue) throws Exception {
	
		String link = "http://localhost:8080/orgpro?orgId=" + orgId + "&userId=" + user.getId();
	    Email from = new Email(user.getFirstName() + "@HelpMeApp.com");
	    String fromString = (user.getFirstName() + "@HelpMeApp.com");
	    
	    String subject = "Help Requested from " + user.getFirstName() + " from " + user.getCity();
	    Email to = new Email("gbreitenbeck@gmail.com");
	    String toString = "Help Requested from " + user.getFirstName() + " from " + user.getCity();
	    
	    Content content = new Content("text/plain", "Hello, I am in need of help with" + issue + "." + link);
	    String contentString = "Hello, I am in need of help with" + issue + ". To reply to this, please click this link: " + link;
	    Mail mail = new Mail(from, subject, to, content);
	    
	    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Date dateObj = new Date();
	    System.out.println(df.format(dateObj));
	    Message message = new Message(user.getId(), orgId, issue, dateObj, fromString, toString, subject, contentString);
	    userMessageDao.save(message);
	    System.out.println(message);
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
}	
