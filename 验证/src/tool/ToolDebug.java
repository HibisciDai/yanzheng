package tool;

import configuration.Configuration;

public class ToolDebug {

	// 打印堆栈信息
	public static void printStackTrace(Exception e) {
		if (Configuration.isInDebug) {
			e.printStackTrace();
		}
	}

	// 打印提示信息
	public static void printOut(String content) {
		if (Configuration.isInDebug) {
			System.out.println(content);
		}
	}

	// 打印错误信息
	public static void printErr(String content) {
		if (Configuration.isInDebug) {
			System.err.println(content);
		}
	}

}
