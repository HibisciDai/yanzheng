package tool;

public class ToolVerification {

	// 获取用户验证码
	public static String getVerificationCode() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
	}

	// 验证用户账户是邮箱还是手机号-1错误、0是手机、1是邮箱
	public static int verificationAccount(String account) {
		if (account != null) {
			account = account.replaceAll("\\s*", "");
			if (account.matches("^1\\d{10}$")) {
				return 0;
			}
			if (account.matches("^\\w+@\\w+(\\.\\w+)+$")) {
				return 1;
			}
		}
		return -1;
	}

	// 验证用户密码格式
	public static boolean verificationPassword(String password) {
		if (password != null) {
			password = password.replaceAll("\\s*", "");
			return password.matches("^\\w{6,12}$");
		}
		return false;
	}

}
