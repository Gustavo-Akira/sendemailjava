package gustavoakira.sendemail.sendemail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SentEmail {
	
	private final String email = "akirauekita2002@gmail.com";
	private final String password = "Kadeira2.0";
	
	private String toList = "";
	private String remetent = "";
	private String subjectEmail = "";
	private String textEmail = "";
	
	
	
	public SentEmail(String toList, String remetent, String subjectEmail, String textEmail) {
		super();
		this.toList = toList;
		this.remetent = remetent;
		this.subjectEmail = subjectEmail;
		this.textEmail = textEmail;
	}



	public void sent(boolean isHtml) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email,password);
				}
			});
			Address[] toUsers = InternetAddress.parse(toList);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email, remetent));
			message.setRecipients(Message.RecipientType.TO, toUsers);
			message.setSubject(subjectEmail);
			if(isHtml) {
				message.setContent(textEmail, "text/html;charset=utf-8");
			}else {
				message.setText(textEmail);
			}
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sentwhitAnexed(boolean isHtml) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email,password);
				}
			});
			Address[] toUsers = InternetAddress.parse(toList);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email, remetent));
			message.setRecipients(Message.RecipientType.TO, toUsers);
			message.setSubject(subjectEmail);
			
			MimeBodyPart bodyEmail = new MimeBodyPart();
			
			
			if(isHtml) {
				bodyEmail.setContent(textEmail, "text/html;charset=utf-8");
			}else {
				bodyEmail.setText(textEmail);
			}
			MimeBodyPart anex = new MimeBodyPart();
			anex.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfSimulator(), "application/pdf")));
			anex.setFileName("anex.pdf");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyEmail);
			multipart.addBodyPart(anex);
			
			message.setContent(multipart);
			
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private FileInputStream pdfSimulator() throws Exception{
		Document document = new Document();
		File file = new File("anexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Content of pdf anexed wiht java mail"));
		document.close();
		return new FileInputStream(file);
	}
}
