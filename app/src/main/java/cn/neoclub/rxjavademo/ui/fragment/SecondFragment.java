package cn.neoclub.rxjavademo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.base.SimpleFragment;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;
import cn.neoclub.rxjavademo.ui.adapter.DailyAdapter;

/**
 * Created by renjialiang on 16/12/5.
 */
public class SecondFragment extends SimpleFragment {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    DailyAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initEventAndData() {
        List<DailyListBean.StoriesBean> list = new ArrayList<>();
        for (int i = 0; i<20;i++) {
            DailyListBean.StoriesBean storiesBean = new DailyListBean.StoriesBean();
            storiesBean.setTitle("ITEM " + i);
            list.add(storiesBean);
        }
        mAdapter = new DailyAdapter(mContext, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
    }

}
