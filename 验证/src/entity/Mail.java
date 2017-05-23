package entity;

//电子邮件实体类
public class Mail {
	private String mailHost, mailHostPort;
	private String userName, password;
	private String from, to;
	private String title, content;

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getMailHostPort() {
		return mailHostPort;
	}

	public void setMailHostPort(String mailHostPort) {
		this.mailHostPort = mailHostPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Mail [from=" + from + ", to=" + to + ", title=" + title
				+ ", content=" + content + "]";
	}

}
