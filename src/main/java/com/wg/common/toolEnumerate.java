package com.wg.common;

public enum toolEnumerate {
	
	U("U", "处理中"), //
	B("B", "待补件"), //
	X("X", "聚信力授权"), //
	A("A", "通过"), //
	J("J", "放弃"), //
	R("R", "拒绝"), //
	N("N", "已签署");//

	private String code;
	private String desc;

	/**
	 * 长亮申请状态
	 * 
	 * @param code
	 *            编码
	 * @param desc
	 *            描述
	 */
	private toolEnumerate(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
