package com.fintech.intellinews.service.wechat;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.config.AppProperties;
import com.fintech.intellinews.dao.cache.RedisDao;
import com.fintech.intellinews.model.AccessToken;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.util.ProtoStuffSerializerUtil;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author waynechu
 * Created 2017-10-19 12:12
 */
@Service
public class SecurityService extends BaseService {

    private AppProperties appProperties;

    private RedisDao redisDao;

    /**
     * 检测微信服务器签名
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 签名正确返回true
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{appProperties.getToken(), timestamp, nonce};
        // 排序
        Arrays.sort(arr);
        // 生产字符串
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        // sha1加密
        String temp = getSha1(content.toString());
        return signature.equals(temp);
    }

    /**
     * 获取accessToken
     *
     * @return accessToken
     */
    public AccessToken getAccessToken() {
        AccessToken accessToken = null;
        // token在redis中的有效期，2小时
        long expire = 7200L;
        try {
            byte[] bytes = redisDao.get("accessToken");
            accessToken = ProtoStuffSerializerUtil.deserialize(bytes, AccessToken.class);
            logger.info("redis中的accessToken有效");
        } catch (Exception e) {
            logger.info("redis中的accessToken已过期，将重新从微信服务器获取");
        }
        if (accessToken == null) {
            String url = Constant.ACCESS_TOKEN_URL.replace("APPID", appProperties.getAppId()).replace("APPSECRET",
                    appProperties.getAppSecret());
            ObjectNode objectNode = HttpUtil.doGetUrl(url);
            if (objectNode != null) {
                accessToken = new AccessToken();
                accessToken.setToken(JacksonUtil.getChildAsString(objectNode, "access_token"));
                accessToken.setExpiresIn(JacksonUtil.getChildAsInteger(objectNode, "expires_in"));
                redisDao.setEx("accessToken", ProtoStuffSerializerUtil.serialize(accessToken), expire);
            }
        }
        return accessToken;
    }

    /**
     * sha1加密
     *
     * @param str 加密前的字符
     * @return 加密处理后
     */
    private static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }


    @Autowired
    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Autowired
    public void setRedisDao(RedisDao redisDao) {
        this.redisDao = redisDao;
    }
}
