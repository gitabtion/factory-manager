package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.common.exceptions.ResultException;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

/**
 * @author abtion.
 * @since 17/8/25 18:00.
 * email caiheng@hrsoft.net
 */

public class GlobalAPIErrorHandler {
    public static void handler(int code){
        switch (code){
            case APICode.NEED_LOGIN:
                ToastUtil.showToast("身份信息过期，请重新登录");

                TFMApplication.getInstance().exitAccount();
                break;
            case APICode.PASSWORD_WRONG:
                ToastUtil.showToast("密码错误，请改正后重试");
                break;
            case APICode.USER_NOT_EXIST:
                ToastUtil.showToast("用户不存在，请检查手机号是否正确");
                break;
            default:
                ToastUtil.showToast("请求不被允许，请确定是否有权进行该操作");
                break;
        }
    }

    public static void handler(ResultException r){
        switch (r.getCode()){
            case APICode.NEED_LOGIN:
                ToastUtil.showToast("身份信息过期，请重新登录");
                TFMApplication.getInstance().exitAccount();
                break;
            case APICode.PASSWORD_WRONG:
                ToastUtil.showToast("密码错误，请改正后重试");
                break;
            case APICode.USER_NOT_EXIST:
                ToastUtil.showToast("用户不存在，请检查手机号是否正确");
                break;
            default:
                ToastUtil.showToast(r.getData());
                break;
        }
    }

}
