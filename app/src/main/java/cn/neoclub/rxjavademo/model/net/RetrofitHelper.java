package cn.neoclub.rxjavademo.model.net;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.neoclub.rxjavademo.app.Constants;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;
import cn.neoclub.rxjavademo.util.SystemUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by renjialiang on 16/12/2.
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static  ZhihuApi zhihuApi = null;

    public RetrofitHelper() {
        init();
    }

    private void init() {
        initOkHttpClient();
        zhihuApi = getZhihuApi();
    }

    private void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private ZhihuApi getZhihuApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ZhihuApi.class);
    }

    public Observable<DailyListBean> fetchDailyNews() {
        return zhihuApi.getDailyList();
    }

    public Observable<DailyListBean> fetchDailyBeforeNews(String date) {
        return zhihuApi.getDailyBeforeList(date);
    }
}
