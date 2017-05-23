package automation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import entity.Message;
import tool.ToolDebug;

public class MessageSender {
	private static MessageSender messageSender;
	private LinkedBlockingQueue<Message> linkedBlockingQueue;
	private Timer timer;
	private TimerTask timerTask;
	private int queueSize;

	// 私有的构造方法
	private MessageSender() {
		queueSize = 100;
		linkedBlockingQueue = new LinkedBlockingQueue<Message>(queueSize);
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
	private void send(final Message message) {
		// 创建会话
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(message.getServerUrl());
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
		// 提交短信
		NameValuePair[] data = { new NameValuePair("account", message.getUserName()),
				new NameValuePair("password", message.getPassword()), new NameValuePair("mobile", message.getTo()),
				new NameValuePair("content", message.getContent()), };
		method.setRequestBody(data);
		// 接收返回结果
		try {
			client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			String code = root.elementText("code");
			ToolDebug.printOut("短信提交" + ("2".equals(code) ? "成功" : "失败"));
		} catch (Exception e) {
			ToolDebug.printStackTrace(e);
		}
	}

	// 发送一封短信
	public boolean sendAMessage(Message message) {
		try {
			linkedBlockingQueue.put(message);
			return true;
		} catch (InterruptedException e) {
			ToolDebug.printStackTrace(e);
			return false;
		}
	}

	// 获得短信发送者
	public synchronized static MessageSender getMessageSender() {
		if (messageSender == null) {
			messageSender = new MessageSender();
		}
		return messageSender;
	}

}