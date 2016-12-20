package cn.neoclub.rxjavademo.ui.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.base.BaseFragment;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;
import cn.neoclub.rxjavademo.model.event.RxBus;
import cn.neoclub.rxjavademo.presenter.MainPresenter;
import cn.neoclub.rxjavademo.presenter.contract.MainContract;
import cn.neoclub.rxjavademo.ui.CalendarActivity;
import cn.neoclub.rxjavademo.ui.adapter.DailyAdapter;
import cn.neoclub.rxjavademo.util.CircularAnimUtil;
import cn.neoclub.rxjavademo.util.DateUtil;
import cn.neoclub.rxjavademo.widget.ProgressImageView;

/**
 * Created by renjialiang on 16/12/5.
 */
public class FirstFragment extends BaseFragment<MainPresenter> implements MainContract.View {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rv_daily_list)
    RecyclerView rvDailyList;
    @BindView(R.id.iv_progress)
    ProgressImageView ivProgress;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    DailyAdapter mAdapter;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initEventAndData() {
        mAdapter = new DailyAdapter(mContext, mList);
        rvDailyList.setLayoutManager(new LinearLayoutManager(mContext));
        rvDailyList.setAdapter(mAdapter);

        ivProgress.start();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String currentDate = DateUtil.getCurrentDate();
                int year = Integer.valueOf(currentDate.substring(0, 4));
                int month = Integer.valueOf(currentDate.substring(4, 6));
                int day = Integer.valueOf(currentDate.substring(6, 8));
                CalendarDay date = CalendarDay.from(year, month, day);
                RxBus.getDefault().post(date);
            }
        });
    }

    @OnClick(R.id.fab)
    void OnClickFb() {
        Intent intent = new Intent(mContext, CalendarActivity.class);
        CircularAnimUtil.startActivity(mActivity, intent, fab, R.color.colorAccent, 500);
    }

    @Override
    public void showDate(String date) {
    }

    @Override
    public void showContent(DailyListBean dailyListBean) {
        swipeRefresh.setRefreshing(false);
        ivProgress.stop();
        mAdapter.addDaily(dailyListBean);
    }

    @Override
    public void showError(String msg) {
        swipeRefresh.setRefreshing(false);
        ivProgress.stop();
    }
}
