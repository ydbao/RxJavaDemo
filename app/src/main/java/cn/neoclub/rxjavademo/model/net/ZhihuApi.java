package cn.neoclub.rxjavademo.model.net;

import cn.neoclub.rxjavademo.model.bean.DailyListBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by renjialiang on 16/12/2.
 */
public interface ZhihuApi {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyListBean> getDailyList();

    /**
     * 往期日报
     */
    @GET("news/before/{date}")
    Observable<DailyListBean> getDailyBeforeList(@Path("date") String date);

}
