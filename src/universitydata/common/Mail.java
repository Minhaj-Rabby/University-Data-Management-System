package universitydata.common;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
			
		Session newSession = null;
		MimeMessage mimeMessage = null;
		public Mail(String email,String name ) throws AddressException, MessagingException, IOException {
			setupServerProperties();
			draftEmail(email,name);
			System.out.println(email);
			sendEmail();
		}
		private void sendEmail() throws MessagingException {
			String fromUser = "minhaj12113@gmail.com";
			String fromUserPassword = "arxrrjfdnritjadp";
			String emailHost = "smtp.gmail.com";
			Transport transport = newSession.getTransport("smtp");
			transport.connect(emailHost, fromUser, fromUserPassword);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			System.out.println("Email successfully sent!!!");
		}
		private MimeMessage draftEmail(String email,String name) throws AddressException, MessagingException, IOException {
			String emailReceipients = email;
			String emailSubject = "Profile Approval!";
			String emailBody = "Welcome," +name +"Your MBSTU account has been Approved By admin.Please Follow the rules.";
			mimeMessage = new MimeMessage(newSession);
			
			
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients));
			
			mimeMessage.setSubject(emailSubject);
	
			 mimeMessage.setContent(emailBody,"text/html");
			 return mimeMessage;
		}

		private void setupServerProperties() {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			newSession = Session.getDefaultInstance(properties,null);
		}


}
