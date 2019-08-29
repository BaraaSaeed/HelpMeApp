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

	@Value("${HOST}")
	private String host;

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private ApiService apiService;

	public void sendMailFromUserToOrg(User user, Org org, String userContent) throws Exception {

		Mail mail = new Mail();
		mail.setFrom(new Email(getUserEmailAddress(user)));
		mail.setTemplateId(TEMPLATE_ID);

		Personalization personalization = new Personalization();
		personalization.addDynamicTemplateData("subject", getUserSubject(user));
		personalization.addDynamicTemplateData("name", getUserFullName(user));
		personalization.addDynamicTemplateData("orgLink", getOrgToUserLink(org, user));
		personalization.addDynamicTemplateData("message", userContent);
		personalization.addTo(new Email(EMAIL_ADDRESS));
		mail.addPersonalization(personalization);

		saveMessageAndOrg(user, org, userContent);

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

	public String getUserEmailAddress(User user) {
		String userEmail = user.getFirstName().toLowerCase().substring(0, 1) + user.getLastName().toLowerCase()
				+ "@HelpMeApp.com";
		return userEmail;
	}

	public String getUserSubject(User user) {
		String subject = "Help Requested from " + getUserFullName(user) + "of" + user.getCity() + "for help with "
				+ user.getServiceSelection() + "service(s)" + ".";

		return subject;
	}

	public String getUserFullName(User user) {
		String fullName = user.getFirstName() + " " + user.getLastName();
		return fullName;
	}

	public String getOrgToUserLink(Org org, User user) {
		String link = host + "/org-message-detail?orgId=" + getOrgIdFromApiId(org.getApiId()) + "&userId=" + user.getId()
				+ "&secret=" + generateSecretKey(org);
		return link;
	}

	private String getOrgIdFromApiId(String apiId) {
		String id[] = apiId.split(":::");
		for (String each : id) {
			System.out.println(each);
		}
		String orgId = id[2];
		return orgId;
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
		Message message = new Message(user.getId(), getUserFullName(user), null, org.getOrgId(), org.getName(),
				org.getApiId(), user.getServiceSelection(), getDate(), getUserFullName(user), org.getName(),
				getUserSubject(user), getUserContentTemplate(user, userContent));
		return message;
	}

	private String getUserContentTemplate(User user, String userContent) {
		String template = "Help Me App user" + getUserFullName(user) + " is reaching out for help with "
				+ user.getServiceSelection() + "./n" + userContent;
		return template;
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

	public void sendMailFromOrgToUser(Long messageId, String content) {
		Message orgMessage = createOrgMessage(messageId, content);
		messageDao.save(orgMessage);
	}

	public Message createOrgMessage(Long messageId, String content) {
		Message userMessage = messageDao.findByMessageId(messageId);
		System.out.println("User Message: " + userMessage);
		String subject = "Re: " + userMessage.getIssue();
		String trimmedContent = content.trim();
		Message message = new Message(userMessage.getUserId(), userMessage.getUserName(), null, userMessage.getOrgId(),
				userMessage.getOrgName(), userMessage.getApiId(), userMessage.getIssue(), getDate(),
				userMessage.getTo(), userMessage.getFrom(), subject, trimmedContent);
		return message;
	}

	public Map<Long, String> getOrgMessageHistory(String orgId) {
		List<Message> messageHistory = messageDao.findAllByOrgId(orgId);
		System.out.println(messageHistory);
		Map<Long, String> users = new HashMap<>();
		for (Message each : messageHistory) {
			users.put(each.getUserId(), each.getUserName());
			System.out.println(each.getUserName());
		}
		System.out.println(users.values());

		return users;
	}
//
	public String getLastMessageIssue(List<Message> messages) {
		Message lastMessage = messages.get(messages.size() - 1);
		String issue = lastMessage.getIssue();
		return issue;
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
	
	
//	public Long calcOrgResponseTime(Message message) {
//		List<Message> messageHistory = messageDao.findAllByApiId(message.getApiId());
//		List<Long> diffs = new ArrayList<>();
//		LocalDateTime lastUserMessageTime = null;
//		for (Message each : messageHistory) {
//			if (each.getFromUser() && lastUserMessageTime == null) {
//				lastUserMessageTime = each.getDate();
//			} else if (!each.getFromUser() && lastUserMessageTime != null) {
//				diffs.add(ChronoUnit.MINUTES.between(each.getDate(), lastUserMessageTime));
//				lastUserMessageTime = null;
//			}
//		}
//		Long total = 0L;
//		for (Long each : diffs) {
//			total += each;
//		}
//		return total / diffs.size();
//	}
}
