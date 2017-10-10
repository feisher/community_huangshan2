package com.yusong.club.api.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yusong.club.MyApplication;
import com.yusong.club.utils.Constants;
import com.yusong.club.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by quaner on 16/12/30.
 */
public class ServiceFactory {

    private final Gson mGsonDateFormat;

    private volatile Object mS;
    private volatile Object s;

    public ServiceFactory() {
        mGsonDateFormat = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * create a service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass, boolean agent) {

        if (s == null) {
            synchronized (ServiceFactory.class) {

                if (s == null) {
                    Retrofit mRetrofit = new Retrofit.Builder()
                            .baseUrl(Constants.KDG_URL)
                            .client(getOkHttpClient(agent))
                            .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    s = mRetrofit.create(serviceClass);
                    return (S) s;
                }
            }
        }

        return (S) s;

    }


    /**
     * create a service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
        if (mS == null) {
            synchronized (ServiceFactory.class) {

                if (mS == null) {
                    Retrofit mRetrofit = new Retrofit.Builder()
                            .baseUrl(Constants.getNetUrl())
                            .client(getOkHttpClient(false))//false
                            .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    mS = mRetrofit.create(serviceClass);
                    return (S) mS;
                }
            }
        }

        return (S) mS;
    }

    private final static long DEFAULT_TIMEOUT = 5;


    private OkHttpClient getOkHttpClient(boolean agent) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(

                        new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                //输出访问地址
                                LogUtils.e("OkHttp: " + message);
                            }
                        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
        MyApplication instance = MyApplication.getInstance();
        File cacheDir = instance.getCacheDir();
        File httpCacheDirectory = new File(cacheDir, "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
//        //设置拦截器
//        httpClientBuilder.addInterceptor(new TokenInterceptor());

        if (!agent) {
            // 添加证书
            try {
                setCertificates(httpClientBuilder,
                        MyApplication.getContext().getAssets().open("122.224.164.50.tomcat.cer"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } else {

            httpClientBuilder.addNetworkInterceptor(new CommonHeaderInterceptor());
        }

        return httpClientBuilder.build();
    }
//
//    public class TokenInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(final Chain chain) throws IOException {
//            final Request request = chain.request();
//
//            final TokenInfo info = CacheUtils.getTokenInfo(MyApplication.getContext());
//            if (info != null) {
//                long saveTime = info.getSaveTime();
//                long millis = System.currentTimeMillis();
//                if ((millis - saveTime) > 1000000) {
//
//                    Call<HttpResult<LoginResult>> call = HttpUtil.getInstance().refreshToken(info.getName(), info.getPwd(), Constants.AGENTID);
//                    HttpResult<LoginResult> body = call.execute().body();
//                    if (body.code == 0){
//                        AppUtils.saveLoginInfo(body, info.getName(), info.getPwd());
//                        AppUtils.loginIm(info.getName(), info.getPwd());
//                        AppUtils.updateBaiduPushBind();//刷新绑定推送channel
//                        String token = CacheUtils.getToken(MyApplication.getContext());
//                        request.url().newBuilder().setQueryParameter("Token",token);
//                        LogUtils.e("替换了新的token");
//                        return chain.proceed(request);
//                    }
//                }
//            }
//
//            return chain.proceed(request);
//        }
//    }


    class CommonHeaderInterceptor implements Interceptor {

        public CommonHeaderInterceptor() {
        }


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request()
                    .newBuilder();

//            //添加token
//            if (addToken) {
//                TokenInfo info = CacheUtils.getTokenInfo(MyApplication.getInstance());
//                String token = info.getToken();
//                if (!StringUtils.isEmpty(token)) {
//                    builder.header(Constants.HEADER_TOKEN, token).build();
//                }
//                return chain.proceed(builder.build());
//            }

            builder.header("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            return chain.proceed(builder.build());
        }
    }

    /**
     * 配置https证书
     *
     * @param builder
     * @param certificates
     */
    public void setCertificates(OkHttpClient.Builder builder, InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
