package com.wg.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;

public class WebUtil {
    
    /**
     * 对参数url字符串以utf-8编码将除字母和数字以及*字符外的都编码成%xx形式。
     * @param url
     * @return
     */
    public static String urlEncode(String url){
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // should not happen
            return "/";
        }
    }
    
    /**
     * 将经过urlEncode处理的%xx形式的编码还原成普通字符串。
     * @param s
     * @return
     */
    public static String urlDecode(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
    
    /**
     * 获取发出请求客户的ip地址。如果是多级代理，那么取第一个ip为客户ip。
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 调用远程的 RESTful HTTP Service
     * @param url 服务地址
     * @param method 访问方法 GET|POST
     * @param formparams 以FormData方式提交的参数
     * @return 服务调用结果（JsonNode）
     */
    public static JsonNode callHttpService(String url, String method, List<NameValuePair> formparams){
        CloseableHttpResponse response;
        JsonNode json = null;

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try{
            if (method.equalsIgnoreCase("POST")){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
                
                HttpPost httppost = new HttpPost(url);
//                httppost.setHeader("Content-Type", "application/json;charset=utf-8");
                httppost.setEntity(entity);
                
                try {
                    response = httpclient.execute(httppost);
                } catch (IOException e) {
                    return null;
                }
            } else {
                HttpGet httpget = new HttpGet(url);
                
                try {
                    response = httpclient.execute(httpget);
                } catch (IOException e) {
                    return null;
                }
            }
            
            try {
                HttpEntity respEntity = response.getEntity();
                if (respEntity != null) {
                    //某些服务端（如新浪微博的rest api）不返回正确的length
                    //long len = respEntity.getContentLength();
                    //if (len != -1 && len < 2048) {
                        json = JsonUtil.string2Json(EntityUtils.toString(respEntity));
                    //}
                }
            } catch(Exception e){
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }

        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            }
        }
        
        return json;
    }
}
