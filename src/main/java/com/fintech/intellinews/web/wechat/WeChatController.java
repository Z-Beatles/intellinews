package com.fintech.intellinews.web.wechat;

import com.fintech.intellinews.service.wechat.WeChatService;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.service.wechat.SecurityService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-19 11:51
 */
@Controller
@Api(value = "/v1/wechat/portal", tags = "公众号服务器请求接口")
@RequestMapping("/v1/wechat/portal")
public class WeChatController extends BaseController {

    private  SecurityService securityService;

    private WeChatService weChatService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "微信服务器GET请求", notes = "处理微信服务器发来的GET请求")
    public String getProcess(
            @ApiParam(required = true, name = "signature", value = "微信加密签名")
            @RequestParam(name = "signature") String signature,
            @ApiParam(required = true, name = "timestamp", value = "时间戳")
            @RequestParam(name = "timestamp") String timestamp,
            @ApiParam(required = true, name = "nonce", value = "随机数")
            @RequestParam(name = "nonce") String nonce,
            @ApiParam(required = true, name = "echostr", value = "随机字符串")
            @RequestParam(name = "echostr") String echostr) {
        logger.info("接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }
        // 通过检验signature对请求进行校验
        if (securityService.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "非法请求";
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "微信服务器POST请求", notes = "处理微信服务器发来的POST请求")
    public String postProcess(
            @ApiParam(required = true, name = "requestBody", value = "请求体")
            @RequestBody String requestBody,
            @ApiParam(required = true, name = "signature", value = "微信加密签名")
            @RequestParam("signature") String signature,
            @ApiParam(required = true, name = "timestamp", value = "时间戳")
            @RequestParam("timestamp") String timestamp,
            @ApiParam(required = true, name = "nonce", value = "随机数")
            @RequestParam("nonce") String nonce,
            @ApiParam(name = "encrypt_type", value = "加密方式")
            @RequestParam(name = "encrypt_type", required = false) String encType,
            @ApiParam(name = "msg_signature", value = "消息签名")
            @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        logger.info("接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}], timestamp=[{}], " +
                "nonce=[{}],requestBody=[{}] ", signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!securityService.checkSignature(signature, timestamp, nonce)) {
            throw new IllegalArgumentException("非法请求，可能是伪造的请求！");
        }
        String response = null;
        if (encType == null) {
            response = weChatService.fromXml(requestBody);
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            response = weChatService.fromEncryptedXml(requestBody);
        }
        return response;
    }


    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setWeChatService(WeChatService weChatService) {
        this.weChatService = weChatService;
    }

}
