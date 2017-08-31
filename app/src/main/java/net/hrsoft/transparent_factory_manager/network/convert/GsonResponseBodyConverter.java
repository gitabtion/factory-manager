package net.hrsoft.transparent_factory_manager.network.convert;

import com.google.gson.Gson;

import net.hrsoft.transparent_factory_manager.common.exceptions.ResultException;
import net.hrsoft.transparent_factory_manager.network.APIResponse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author abtion.
 * @since 17/8/25 18:04.
 * email caiheng@hrsoft.net
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        //将返回的json数据储存在String类型的response中
        String response = value.string();
        //将外层的数据解析到APIResponse类型的httpResult中
        APIResponse httpResult = gson.fromJson(response,APIResponse.class);
        //服务端设定0为正确的请求，故在此为判断标准
        if (httpResult.getCode()==0){
            //直接解析，正确请求不会导致json解析异常
            return gson.fromJson(response,type);
        }else {
            //定义错误响应体，并通过抛出自定义异常传递错误码及错误信息
            ResultException errorResult = gson.fromJson(response,ResultException.class);
            throw errorResult;
        }
    }
}
