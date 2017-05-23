package entity;

//短信实体类
public class Message {
	private String serverUrl;// 服务器通讯地址
	private String userName, password;// 用户名和密码
	private String to;// 发送到
	private String content;// 短信内容

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
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

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [serverUrl=" + serverUrl + ", userName=" + userName
				+ ", password=" + password + ", to=" + to + ", content="
				+ content + "]";
	}

}
