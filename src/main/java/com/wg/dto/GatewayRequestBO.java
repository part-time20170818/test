package com.wg.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xiewenge on 2017/7/21.
 */
public class GatewayRequestBO {
    /**
     * 签名参数名称
     */
    public static final String SIGN_NAME = "sign";
    /**
     * 服务参数名称
     */
    public static final String SERVICE_NAME = "service";

    public static final String REQID_NAME = "reqId";
    /**
     * 服务版本号
     */
    @NotBlank
    private String ver;

    /**
     * 请求数据
     */
    @NotBlank
    private String data;

    /**
     * AES秘钥
     */
    @NotBlank
    private String tm;

    /**
     *   请求方式  H5 或者  sdk
     */
    private String type;


    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
