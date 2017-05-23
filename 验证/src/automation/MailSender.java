package automation;

import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import tool.ToolDebug;

import com.sun.mail.util.MailSSLSocketFactory;

import configuration.Configuration;
import entity.Mail;

public class MailSender {
	private static MailSender mailSender;
	private LinkedBlockingQueue<Mail> linkedBlockingQueue;
	private Timer timer;
	private TimerTask timerTask;
	private int queueSize;

	// 私有的构造方法
	private MailSender() {
		queueSize = 100;
		linkedBlockingQueue = new LinkedBlockingQueue<Mail>(queueSize);
		timerTask = new TimerTask() {
			@Override
			public void run() {
				handleTask();
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 0);
	}

	// 发送者执行的任务
	private void handleTask() {
		while (true) {
			try {
				send(linkedBlockingQueue.take());
			} catch (InterruptedException e) {
				ToolDebug.printStackTrace(e);
			}
		}
	}

	// 发送
	private void send(final Mail mail) {
		try {
			// 得到一个系统配置
			Properties properties = new Properties();
			MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
			mailSSLSocketFactory.setTrustAllHosts(true);
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mail.getUserName(),
							mail.getPassword());
				}
			};
			properties.put("mail.smtp.host", mail.getMailHost());
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);
			properties.setProperty("mail.smtp.port", mail.getMailHostPort());
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(properties, authenticator);
			session.setDebug(Configuration.isInDebug);
			// 构建一封邮件
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					mail.getTo()));
			message.setSubject(mail.getTitle());
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(mail.getContent(), "text/html;charset=UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
			message.setSentDate(new Date());
			// 发送一封邮件
			Transport.send(message);
		} catch (Exception e) {
			ToolDebug.printStackTrace(e);
		}
	}

	// 发送一封邮件
	public boolean sendAMail(Mail mail) {
		try {
			linkedBlockingQueue.put(mail);
			return true;
		} catch (InterruptedException e) {
			ToolDebug.printStackTrace(e);
			return false;
		}
	}

	// 获得邮件发送者
	public synchronized static MailSender getMailSender() {
		if (mailSender == null) {
			mailSender = new MailSender();
		}
		return mailSender;
	}

}
