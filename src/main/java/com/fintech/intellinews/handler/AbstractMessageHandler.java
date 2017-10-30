package com.fintech.intellinews.handler;

import java.util.Map;

/**
 * @author wanghao
 * Create 2017-10-20 16:44
 **/
public interface AbstractMessageHandler {
    String messageHandler(Map<String,String> context);

}
