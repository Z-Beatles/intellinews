package com.fintech.intellinews.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author waynechu
 * Created 2017-12-18 11:41
 */
@Component("shiroProperties")
public class ShiroProperties {

    @Value("${spring.shiro.algorithmName}")
    private String algorithmName;

    @Value("${spring.shiro.hashIterations}")
    private int hashIterations;

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
}