package net.hrsoft.transparent_factory_manager.common;

/**
 * @author abtion.
 * @since 17/8/25 18:11.
 * email caiheng@hrsoft.net
 */
public final class Config {
    /**
     * 验证手机号正则
     */
    public static final String MOBILE_REGEX = "[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 验证邮箱正则
     */
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * APP Server 网络请求BaseUrl
     */
    public static final String APP_SERVER_BASE_URL = "http://factory.hrsoft.net/";

    /**
     * APP Server 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;

    /**
     * 启动页面到首页启动延时，单位：毫秒
     */
    public static final int SPLASH_GO_MAIN_DELAY = 1500;
    public static final int CLIENT_ID = 1;

    /**
     * activity间信息传递
     */
    public static final String ORDER = "order";
    public static final String PROCEDURE = "procedure";
    public static final String PROCEDURES = "procedures";
    public static final String ORDER_FRAGMENT_CONTENT_TYPE = "type";

}