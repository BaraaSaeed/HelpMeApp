package co.grandcircus.HelpMeApp.model;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;


@Component 
public class AutoEmail {

	@Value("${sendGrid_KEY}")
	private String SENDGRID_KEY;
	
	public void sendMail() throws Exception {
	
	    Email from = new Email("userName@HelpMeApp.com");
	    String subject = "Help Requested from userName from userCity";
	    Email to = new Email("gbreitenbeck@gmail.com");
	    Content content = new Content("text/plain", "Hello, I am in need of help with userIssue.");
	    Mail mail = new Mail(from, subject, to, content);

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
