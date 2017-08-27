package net.hrsoft.transparent_factory_manager.network;

import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.common.constants.CacheKey;
import net.hrsoft.transparent_factory_manager.network.convert.ResponseConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author abtion.
 * @since 17/8/25 18:00.
 * email caiheng@hrsoft.net
 */

public final class RestClient {
    private static Retrofit retrofit;
    private static APIService service;
    private static OkHttpClient okHttpClient;

    /**
     * API 请求生成器
     *
     * @return APIService
     */
    public static APIService getService() {
        if (service == null) {
            service = getRetrofit().create(APIService.class);
        }
        return service;
    }

    /**
     * 构造Retrofit
     *
     * @return retrofit
     */
    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.APP_SERVER_BASE_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    /**
     * 构造OKHttp客户端，相关参数设置
     *
     * @return OKHttp客户端
     */
    private static OkHttpClient getClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            String token = (TFMApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN)
                                    == null ? "token" : TFMApplication.getInstance().getCacheUtil().getString(CacheKey
                                    .TOKEN));
                            Request request = chain.request().newBuilder()
                                    .header("Token", token)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(Config.APP_SERVER_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return okHttpClient;
    }
}
