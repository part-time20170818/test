package com.wg.common;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将传入的实体类对象转换为JSON格式字符串对象。对需要转换的属性字段要提供get方法，否则该字段不被转换。如果转换过程出错，则返回""。
     * @param o
     * @return
     */
    public static String json2String(Object o){
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(o);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 将传入的JSON字符串对象转换为org.codehaus.jackson.JsonNode对象。如果传入字符串格式不正确则返回null。
     * @param s
     * @return
     */
    public static JsonNode string2Json(String s){
        try {
            ObjectReader or = mapper.reader();
            return  or.readTree(s);

        } catch (IOException e) {
            return null;
        }
    }
}
