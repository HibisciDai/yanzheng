package execute;

import automation.MailSender;
import automation.MessageSender;
import entity.Mail;
import entity.Message;
import entity.ToolEntity;
import tool.ToolVerification;

public class Main {
	// 获得一个默认的邮件发送者
	private static MailSender mailSender = MailSender.getMailSender();

	// 获得一个默认的短信发送者
	private static MessageSender messageSender = MessageSender.getMessageSender();

	public static void main(String[] args) {
		// 构造默认实体对象
		Mail mail = ToolEntity.getMail();
		// 补充相应的信息
		mail.setTo("751152833@qq.com");
		mail.setTitle("测试");
		mail.setContent("这是一个测试邮件，请勿回复");
		// 发送实体对象
//		 mailSender.sendAMail(mail);

		// 构造默认实体对象
		Message message = ToolEntity.getMessage();
		// 补充相应的信息
//		message.setTo("18838252833");
		message.setTo("18838947335");
		message.setContent("您的验证码是：" + ToolVerification.getVerificationCode() + "。请不要把验证码泄露给其他人。");
		// 发送实体对象
		messageSender.sendAMessage(message);
	}

}
