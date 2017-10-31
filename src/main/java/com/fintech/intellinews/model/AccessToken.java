package com.fintech.intellinews.model;

/**
 * @author waynechu
 * Created 2017-10-20 12:05
 */
public class AccessToken {
    /** 获取到的凭证 **/
    private String token;
    /** 凭证有效时间 **/
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
