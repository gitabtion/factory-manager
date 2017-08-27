package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/8/25 17:59.
 * email caiheng@hrsoft.net
 */

public class APIResponse<T> extends BaseModel {
    private int code = -2;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
