package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.common.exceptions.ResultException;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

/**
 * @author abtion.
 * @since 17/8/25 18:00.
 * email caiheng@hrsoft.net
 */

public class GlobalAPIErrorHandler {
    public static void handler(ResultException r){
        switch (r.getCode()){
            default:
                ToastUtil.showToast(r.getData(),r.getCode());
                break;
        }
    }

}
