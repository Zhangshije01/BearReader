package com.llx.bear.network;


import com.llx.bear.BearReaderApplication;
import com.llx.bear.BuildConfig;
import com.llx.bear.network.OkhttpInterceptor.AddHeadHttpInterceptor;
import com.llx.bear.network.OkhttpInterceptor.CatchHttpInterceptor;
import com.llx.bear.network.converter.AESGsonConverterFactory;
import com.llx.bear.network.converter.AESGsonResponseBodyConverter;

import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Cteated by zhangshijie 2017/9/8
 * Retrofit 接口构建器
 *
 * @author fanlei
 */
public class RetrofitServiceGenerator {

    /**正式接口*/
//    public static final boolean isTest = false;
    /**测试接口*/
    public static final boolean isTest = true;//

    // token地址
    private static final String AUTH_TOKEN_URL = isTest ? "https://oauth.ericdress.com/" : "http://oauth.tbdress.com/";

    // 接口地址(Base)
    //    private static final String DEBUG_BASE_URL = "http://192.168.4.114:8081/";//本地
    private static final String DEBUG_BASE_URL = isTest ? "https://webapi1.tbdress.com/" : "http://webapi.tbdress.com/";

    /**
     * 接口地址(Base)
     */
    private static final String SERVICE_URL = DEBUG_BASE_URL;

    //定义一个信任所有证书的TrustManager
    private static final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    //创建普通请求的Client
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            //.sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//忽略https的证书
            .sslSocketFactory(new SSL(trustAllCert), trustAllCert)//忽略https的证书
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//忽略https的证书
//            .cache(new Cache(new File(TlzTbDressApplication.getInstance().getCacheDir(), "responses"), 30 * 1024 * 1024)) // 设置缓存的路径和大小(30M)
            .connectTimeout(15, TimeUnit.SECONDS)// 请求超时时间设置为 10 秒
            .readTimeout(15, TimeUnit.SECONDS)// 读取超时时间  10 秒
            .writeTimeout(15, TimeUnit.SECONDS)
//            .addNetworkInterceptor(new CatchHttpInterceptor()) // 缓存拦截器
            .addInterceptor(new AddHeadHttpInterceptor()); // 添加请求头
    //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));//加入过滤器，仅仅是为了打印请求头和返回的数据

    static {
        if (BuildConfig.DEBUG && null != httpClient) {
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));//加入过滤器，仅仅是为了打印请求头和返回的数据
        }
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(SERVICE_URL)
                    .addConverterFactory(AESGsonConverterFactory.create())// 自定义转换器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));
    private static AESGsonConverterFactory cusTomFactory = AESGsonConverterFactory.create();

    private static Retrofit.Builder customBuilder =
            new Retrofit.Builder()
                    .baseUrl(SERVICE_URL)
                    .addConverterFactory(cusTomFactory)// 自定义转换器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));

    // 创建接口
    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    // 创建接口
    public static <T> T createService(Class<T> serviceClass, AESGsonResponseBodyConverter.JsonBridge jsonBridge) {
        if (jsonBridge == null || !BuildConfig.DEBUG) {//debug模式才使用json调试工具
            return createService(serviceClass);
        }
        cusTomFactory.setJsonBridge(jsonBridge);
        Retrofit retrofit = customBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    //创建请求token的接口
    public static <T> T createTokenService(Class<T> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                //.sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//忽略https的证书
                .sslSocketFactory(new SSL(trustAllCert), trustAllCert)//忽略https的证书
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//忽略https的证书
                .cache(new Cache(new File(BearReaderApplication.getInstance().getCacheDir(), "responses"), 30 * 1024 * 1024)) // 设置缓存的路径和大小
                .connectTimeout(15, TimeUnit.SECONDS)// 请求超时时间设置为 15 秒
                .readTimeout(15, TimeUnit.SECONDS)// 读取超时时间  15 秒
                .writeTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new CatchHttpInterceptor()); // 缓存拦截器
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));//加入过滤器，仅仅是为了打印请求头和返回的数据
        }

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(AUTH_TOKEN_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                        .addConverterFactory(GsonConverterFactory.create());// 使用Gson的转换器
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


}