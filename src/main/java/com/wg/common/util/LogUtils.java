/**
 * 
 */
package com.wg.common.util;

/**
 * 日志输出格式化工具类
 * 
 * @author LiuJian
 *
 */
public class LogUtils {
	public static String logTag(Long currSeq) {
		return System.nanoTime() + "," + currSeq + ":";
	}
}
