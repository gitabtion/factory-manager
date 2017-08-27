package net.hrsoft.transparent_factory_manager.utils;

import net.hrsoft.transparent_factory_manager.common.Config;

import java.util.regex.Pattern;

/**
 * @author abtion.
 * @since 17/8/25 18:12.
 * email caiheng@hrsoft.net
 */

public class RegexUtil {
    //手机号正则
    public static boolean checkMobile(String phone) {
        return Pattern.matches(Config.MOBILE_REGEX, phone);
    }

    //验证邮箱
    public static boolean checkEmail(String email){
        return Pattern.matches(Config.EMAIL_REGEX,email);
    }
}
