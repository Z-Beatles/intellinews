package com.fintech.intellinews.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author waynechu
 * Created 2017-10-20 13:50
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 发起get请求
     *
     * @param url 请求地址
     * @return json对象
     */
    public static ObjectNode doGetUrl(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpGet httpGet = new HttpGet(url);
        ObjectNode objectNode = null;
        try {
            HttpResponse response = httpClientBuilder.build().execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, DEFAULT_CHARSET);
                objectNode = JacksonUtil.toObjectNodeFromString(new ObjectMapper(), result);
            }
        } catch (IOException e) {
            logger.error("发起GET请求失败，URL:{}", url, e);
        }
        return objectNode;
    }

    /**
     * 发起post请求
     *
     * @param url 请求地址
     * @return json对象
     */
    public static ObjectNode doPostUrl(String url, String outStr) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost httpPost = new HttpPost(url);
        ObjectNode objectNode = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, DEFAULT_CHARSET));
            HttpResponse response = httpClientBuilder.build().execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, DEFAULT_CHARSET);
                objectNode = JacksonUtil.toObjectNodeFromString(new ObjectMapper(), result);
            }
        } catch (IOException e) {
            logger.error("发起POST请求失败，URL:{}，请求内容:{}", url, outStr, e);
        }
        return objectNode;
    }
}
