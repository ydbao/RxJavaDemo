package cn.neoclub.rxjavademo.presenter;

import com.orhanobut.logger.Logger;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

import javax.inject.Inject;

import cn.neoclub.rxjavademo.base.RxPresenter;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;
import cn.neoclub.rxjavademo.model.event.RxBus;
import cn.neoclub.rxjavademo.model.net.RetrofitHelper;
import cn.neoclub.rxjavademo.presenter.contract.MainContract;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by renjialiang on 16/11/30.
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper retrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
        registerEvent();
    }

    private void registerEvent() {
        Subscription subscription = RxBus.getDefault().toObservable(CalendarDay.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CalendarDay, String>() {
                    @Override
                    public String call(CalendarDay calendarDay) {
                        StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth());
                        String day = String.valueOf(calendarDay.getDay());
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        mView.showDate(s);
                        return true;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<DailyListBean>>() {
                    @Override
                    public Observable<DailyListBean> call(String s) {
                        Logger.e(s);
                        return retrofitHelper.fetchDailyBeforeNews(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean call(DailyListBean dailyListBean) {
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                        return dailyListBean;
                    }
                })
                .subscribe(new Action1<DailyListBean>() {
                    @Override
                    public void call(DailyListBean dailyListBean) {
                        mView.showContent(dailyListBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        registerEvent();
                        mView.showError("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
                addSubscrebe(subscription);
    }

    @Override
    public void getNetImage() {
    }
}
