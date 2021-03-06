package cn.neoclub.rxjavademo.base;

/**
 * Created by renjialiang on 16/11/30.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
