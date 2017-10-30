package com.fintech.intellinews.service.wechat;

import com.fintech.intellinews.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-10-20 14:30
 */
@Service
public class WeChatService extends BaseService {
    /**
     * 处理微信服务器发过来的请求
     *
     * @param xml 微信服务器请求
     * @return 处理结果
     */
    public String fromXml(String xml) {
        return null;
    }

    public String fromEncryptedXml(String requestBody) {
        return null;
    }
}
