package co.grandcircus.HelpMeApp.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import co.grandcircus.HelpMeApp.ApiService;
import co.grandcircus.HelpMeApp.Dao.MessageDao;
import co.grandcircus.HelpMeApp.Dao.OrgDao;

@Component
public class AutoEmail {

	@Value("${sendGrid_KEY}")
	private String SENDGRID_KEY;
	@Value("${email_ADDRESS}")
	private String EMAIL_ADDRESS;
	@Value("${TEMPLATE_ID}")
	private String TEMPLATE_ID;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private ApiService apiService;

	public void sendMailFromUserToOrg(User user, Org org, String userContent) throws Exception {

		Mail mail = new Mail();
	    mail.setFrom(new Email("helpmeapp@example.com"));
	    mail.setTemplateId(TEMPLATE_ID);

	    Personalization personalization = new Personalization();
	    personalization.addDynamicTemplateData("subject",getUserSubject(user) );
	    personalization.addDynamicTemplateData("name", getUserFullName(user));
	    personalization.addDynamicTemplateData("orgLink", getOrgToUserLink(org,user));
	    personalization.addDynamicTemplateData("message",getUserTextContent(user, org, userContent) );
	    personalization.addTo(new Email(EMAIL_ADDRESS));
	    mail.addPersonalization(personalization);
		/*
		 * Mail mail = getMail(user, org, userContent); saveMessageAndOrg(user, org,
		 * userContent); mail.personalization.get(0).addSubstitution("fullname",
		 * getUserFullName(user));
		 * mail.personalization.get(0).addSubstitution("subject", getUserSubject(user));
		 * mail.personalization.get(0).addSubstitution("orgLink",
		 * getOrgToUserLink(org,user));
		 * mail.personalization.get(0).addSubstitution("message",
		 * getUserTextContent(user, org, userContent) );
		 * 
		 * 
		 * mail.setTemplateId(TEMPLATE_ID);
		 */

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

	public Mail getMail(User user, Org org, String userContent) {
		Email from = new Email(getUserEmail(user));
		String subject = getUserSubject(user);
		Email to = new Email(EMAIL_ADDRESS);
		Content content = new Content("text/plain", getUserTotalContent(user, org, userContent));
		return new Mail(from, subject, to, content);
	}

	public String getUserEmail(User user) {
		String userEmail = user.getFirstName().toLowerCase().substring(0, 1) + user.getLastName().toLowerCase()
				+ "@HelpMeApp.com";
		return userEmail;
	}

	public String getUserSubject(User user) {
		String subject = "Help Requested with " + user.getSelection() + " from " + getUserFullName(user) + " from "
				+ user.getCity();
		return subject;
	}

	public String getUserFullName(User user) {
		String fullName = user.getFirstName() + " " + user.getLastName();
		return fullName;
	}

	public String getUserTotalContent(User user, Org org, String userContent) {
		String content = getUserTextContent(user, org, userContent) + getOrgToUserLink(org, user);
		return content;
	}

	public String getUserTextContent(User user, Org org, String userContent) {
		String content = "";
		if (userContent.equals("")) {
			content = "Hello, I'm currently living in " + user.getCity() + " and I'm reaching out to " + org.getName()
					+ " because I'm looking for help with " + user.getSelection().toLowerCase() + ". ";
		} else {

			content = userContent;
		}

		return content;
	}

	public String getOrgToUserLink(Org org, User user) {
		String link = "http://localhost:8080/org-message-detail?apiId="
				+ wrapApiIdToHtml(org.getApiId()) + "&userId=" + user.getId();
		return link;
	}

	public String wrapApiIdToHtml(String apiId) {
		String wrappedApi = apiId.replaceAll(" ", "_");
		return wrappedApi;
	}

	public void saveMessageAndOrg(User user, Org org, String userContent) {
		Message message = getMessage(user, org, userContent);
		messageDao.save(message);
		orgDao.save(org);
	}

	public Message getMessage(User user, Org org, String userContent) {

		// Long userId, String userName, String orgId, String orgName, String apiId,
		// String issue, Date date,
		// String from, String to, String subject, String content

		Message message = new Message(user.getId(), getUserFullName(user), org.getOrgId(), org.getName(),
				org.getApiId(), user.getSelection(), getDate(), getUserFullName(user), org.getName(),
				getUserSubject(user), getUserTextContent(user, org, userContent));
		return message;
	}

	public LocalDateTime getDate() {
		LocalDateTime dateObj = LocalDateTime.now();
		return dateObj;
	}

	public Map<String, String> getUserMessageHistory(User user) {
		List<Message> messageHistory = messageDao.findAllByUserId(user.getId());
		Map<String, String> orgs = new HashMap<>();
		for (Message each : messageHistory) {
			orgs.put(each.getApiId(), each.getOrgName());
		}
		return orgs;
	}

	public void sendMailFromOrgToUser(Message orgMessage) {
		Org org = apiService.findByApiId(orgMessage.getApiId());
//		org.setAvgResponseTimeInMinutes(calcOrgResponseTime(orgMessage));
		orgDao.save(org);
		messageDao.save(orgMessage);
	}

	public Message createOrgMessage(Long messageId, String content) {
		Message userMessage = messageDao.findByMessageId(messageId);
		String subject = "Re: " + userMessage.getIssue();
		String trimmedContent = content.trim();
		Message message = new Message(userMessage.getUserId(), userMessage.getUserName(), userMessage.getOrgId(),
				userMessage.getOrgName(), userMessage.getApiId(), userMessage.getIssue(), getDate(),
				userMessage.getTo(), userMessage.getFrom(), subject, trimmedContent);
		return message;
	}

	public Map<Long, String> getOrgMessageHistory(String apiId) {
		List<Message> messageHistory = messageDao.findAllByApiId(apiId);
		Map<Long, String> users = new HashMap<>();
		for (Message each : messageHistory) {
			users.put(each.getUserId(), each.getUserName());
		}
		return users;
	}

	public String getLastMessageIssue(List<Message> messages) {
		Message lastMessage = messages.get(messages.size() - 1);
		String issue = lastMessage.getIssue();
		return issue;
	}


	public Long calcOrgResponseTime(Message message) {
		List<Message> messageHistory = messageDao.findAllByApiId(message.getApiId());
		long diffTotal = 0L;
		LocalDateTime now = LocalDateTime.now();
		for (Message each : messageHistory) {
			LocalDateTime messageSent = each.getDate();
			long diff = ChronoUnit.MINUTES.between(now, messageSent);
			diffTotal += diff;
		}
		long averageResponseInMinutes = (diffTotal / messageHistory.size());
		System.out.println(averageResponseInMinutes);
		return averageResponseInMinutes;
	}
	/* This method creates and returns a UUID if an org does not have one */
	public String generateSecretKey(Org org) {

		if (org.getSecret() == null) {
			UUID uuid = UUID.randomUUID();
			String[] uuidString = uuid.toString().split("-");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < uuidString.length; i++) {
				sb.append(uuidString[i]);
			}
			org.setSecret(sb.toString());
			orgDao.save(org);
			return sb.toString();
		} else {
			return org.getSecret();
		}

	}
}
