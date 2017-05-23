package entity;

import configuration.Configuration;

//实体工具类
public class ToolEntity {

	// 获取默认的Message配置
	public static Message getMessage() {
		Message message = new Message();
		message.setServerUrl(Configuration.messageHost);
		message.setUserName(Configuration.messageAccount);
		message.setPassword(Configuration.messagePassword);
		return message;
	}

	// 获取默认的Mail配置
	public static Mail getMail() {
		Mail mail = new Mail();
		mail.setMailHost(Configuration.mailHost);
		mail.setMailHostPort(Configuration.mailHostPort);
		mail.setUserName(Configuration.mailAccount);
		mail.setPassword(Configuration.mailPassword);
		mail.setFrom(Configuration.mailFrom);
		return mail;
	}

}
