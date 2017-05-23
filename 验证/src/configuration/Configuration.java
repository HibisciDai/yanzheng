package configuration;

//默认工程信息配置类
public class Configuration {

	// ///////////////// 工程信息配置信息////////////////////////////
	public static boolean isInDebug = true;// 工程是否处于调试状态

	// ///////////////// 短信及电子邮件配置信息///////////////////////////
	// 短信服务
	public static String messageHost = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// 短信服务主机
	public static String messageAccount = "C44667516";// 短信发送帐号
//	public static String messageAccount = "C98844587";// 短信发送帐号
//	public static String messagePassword = "be068912abf3f8a8c60e473b01d27ecc";// 短信发送密码
	public static String messagePassword = "b7de39e02bed2e868c367ad72c22325d";// 短信发送密码
	// 邮件服务
	public static String mailAccount = "18838947335@139.com";// 邮件发送帐号
	public static String mailPassword = "1qazxsw2";// 邮件服务器密码
	public static String mailHost = "smtp.139.com";// 邮件服务器主机地址
	public static String mailHostPort = "465";// 邮件服务器端口
	public static String mailFrom = "18838947335@139.com";// 发件人


}
