package com.fintech.intellinews;

/**
 * @author waynechu
 * Created 2017-10-20 13:49
 */
public class Constant {
    private Constant() {
    }

    public static final String ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    public static final String DEFAULT_USER_CHANNEL_CONFIG ="[{\"id\":1,\"name\":\"latest\",\"nameZh\":\"最新\"}," +
            "{\"id\":2,\"name\":\"policy\",\"nameZh\":\"政策\"},{\"id\":3,\"name\":\"industry\",\"nameZh\":\"行业\"}," +
            "{\"id\":4,\"name\":\"banking\",\"nameZh\":\"金融\"},{\"id\":7,\"name\":\"venture\",\"nameZh\":\"创投\"}]";

    /** 字符转Java失败 **/
    public static final int ERRORCODE_CONVERT_STR2JAVAERROR = 300001;
    /** Java转字符失败 **/
    public static final int ERRORCODE_CONVERT_JAVA2STRERROR = 300002;
    /** Json转字符失败 **/
    public static final int ERRORCODE_CONVERT_JSON2STRERROR = 300003;
    /** 字符转ObjectNode失败 **/
    public static final int ERRORCODE_CONVERT_STR2OBJECTNODEERROR = 300004;
    /** 字符转ArrayNode失败 **/
    public static final int ERRORCODE_CONVERT_STR2ARRAYNODEERROR = 300005;
    /** Json转Java失败 **/
    public static final int ERRORCODE_CONVERT_JSON2JAVAERROR = 300006;
    /** Java转Json失败 **/
    public static final int ERRORCODE_CONVERT_JAVA2JSONERROR = 300007;

}
