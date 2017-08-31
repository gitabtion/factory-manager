package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.common.exceptions.ResultException;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/28 16:21.
 * email caiheng@hrsoft.net
 * 基于Callback封装，对data中带有error_message的失败请求做统一处理
 */

public abstract class DataCallback<T> implements Callback<T> {

    public abstract void onDataResponse(Call<T> call, Response<T> response);

    public abstract void onDataFailure(Call<T> call, Throwable t);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onDataResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ResultException) {
            GlobalAPIErrorHandler.handler((ResultException) t);
        } else {
            onDataFailure(call, t);
        }
    }
}
