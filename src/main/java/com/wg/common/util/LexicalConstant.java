/**
 * 
 */
package com.wg.common.util;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import org.apache.http.entity.ContentType;

/**
 * 词法常量
 * 
 * @author LiuJian
 *
 */
public class LexicalConstant {

	public final static String CR = "\r";
	public final static String LF = "\n";
	public final static String CRLF = CR + LF;

	public final static String DEFAULT_JSON_CONTENT_TYPE = "application/json";
	/**
	 * 系统默认字符集String
	 */
	public final static String DEFAULT_CHARSET_STR = "UTF-8";
	/**
	 * 系统默认Charset UTF-8
	 */
	public final static Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_STR);

	///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 日志域
	 */
	public final static String TOKEN_SPERATOR = CRLF + "Token:";

	// 默认UTF-8 JSON CONTENT_TYPE
	public static final ContentType JSON_CONTENT_TYPE = ContentType.create(LexicalConstant.DEFAULT_JSON_CONTENT_TYPE,
			LexicalConstant.DEFAULT_CHARSET);

	/**
	 * 0 2位精度文本
	 */
	public static final String ZERO_AMOUNT_SCALE_2 = "0.00";
	/**
	 * 0 2位精度 数字
	 */
	public static final BigDecimal ZERO_SCALE_2 = new BigDecimal(ZERO_AMOUNT_SCALE_2);
}
