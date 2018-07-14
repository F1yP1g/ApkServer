package com.lu.info;

public class CodeFlag {
	/**
	 * 5位01字符串
	 * 通讯录、设备信息、已安装应用列表、位置、通话记录
	 * 0标志表示不更新数据
	 * 1标志表示更新数据
	 * 默认值00000
	 */
	public static String COMMEND_CODE = "00000";

	public static String getCOMMEND_CODE() {
		return COMMEND_CODE;
	}

	public static void setCOMMEND_CODE(String cOMMEND_CODE) {
		COMMEND_CODE = cOMMEND_CODE;
	}
}
