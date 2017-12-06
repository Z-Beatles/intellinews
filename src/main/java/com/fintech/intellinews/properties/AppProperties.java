package com.fintech.intellinews.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author waynechu
 * Created 2017-10-20 13:04
 */
@Component("appProperties")
public class AppProperties {

    @Value("${spring.shiro.algorithmName}")
    private String algorithmName;

    @Value("${spring.shiro.hashIterations}")
    private int hashIterations;

    @Value("${fintech.wechat.appId}")
    private String appId;

    @Value("${fintech.wechat.appSecret}")
    private String appSecret;

    @Value("${fintech.wechat.hostURL}")
    private String hostURL;

    @Value("${fintech.wechat.token}")
    private String token;

    @Value("${fintech.wechat.encodingAESKey}")
    private String encodingAESKey;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getHostURL() {
        return hostURL;
    }

    public void setHostURL(String hostURL) {
        this.hostURL = hostURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("appId", appId)
                .append("appSecret", appSecret)
                .append("hostURL", hostURL)
                .append("token", token)
                .append("encodingAESKey", encodingAESKey)
                .toString();
    }
}
