package com.llx.suandroidbase.network;

import com.llx.suandroidbase.SuApplication;
import com.llx.suandroidbase.network.OkhttpInterceptor.AddHeadHttpInterceptor;
import com.llx.suandroidbase.network.converter.AESGsonConverterFactory;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

/**
 * Retrofit 接口构建器
 *
 * @author zhangshijie
 */
public class RetrofitServiceGenerator {

    /**
     * 定义一个信任所有证书的TrustManager
     */
    private static final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    };

    /**
     * 创建接口
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T createService(String baseApiUrl, Class<T> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        /**忽略https的证书*/
        httpClient.sslSocketFactory(new SSL(trustAllCert), trustAllCert);
        /**忽略https的证书*/
        httpClient.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        /**请求超时时间设置为 15 秒*/
        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        /**读取超时时间  15 秒*/
        httpClient.readTimeout(15, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);
        /**添加请求头*/
        httpClient.addInterceptor(new AddHeadHttpInterceptor());
        if (SuApplication.issIsDebug()) {
            /**加入过滤器，仅仅是为了打印请求头和返回的数据*/
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseApiUrl)
                /**自定义转换器*/
                .addConverterFactory(AESGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }


    /**
     * 创建接口
     *
     * @param serviceClass
     * @param jsonBridge
     * @param <T>
     * @return
     */
//    public static <T> T createService(Class<T> serviceClass, AESGsonResponseBodyConverter.JsonBridge jsonBridge) {
//        /***
//         * debug模式才使用json调试工具
//         */
//        if (jsonBridge == null || !BuildConfig.DEBUG) {
//            return createService(serviceClass);
//        }
//        cusTomFactory.setJsonBridge(jsonBridge);
//        Retrofit retrofit = customBuilder.client(httpClient.build()).build();
//        return retrofit.create(serviceClass);
//    }


}