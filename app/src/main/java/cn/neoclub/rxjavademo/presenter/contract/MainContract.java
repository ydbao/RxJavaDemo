package cn.neoclub.rxjavademo.presenter.contract;

import cn.neoclub.rxjavademo.base.BasePresenter;
import cn.neoclub.rxjavademo.base.BaseView;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;

/**
 * Created by renjialiang on 16/11/30.
 */
public interface MainContract {

    interface View extends BaseView {
        void showDate(String date);

        void showContent(DailyListBean dailyListBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getNetImage();
    }

}
