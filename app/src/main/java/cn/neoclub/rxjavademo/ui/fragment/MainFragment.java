package cn.neoclub.rxjavademo.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.base.SimpleFragment;
import cn.neoclub.rxjavademo.ui.adapter.FragmentAdapter;

/**
 * Created by renjialiang on 16/12/5.
 */
public class MainFragment extends SimpleFragment {

    @BindView(R.id.tab_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_main)
    ViewPager viewPager;

    String[] tabTitle = new String[]{"FFFF","SSSS"};
    List<Fragment> fragments = new ArrayList<Fragment>();

    FragmentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        mAdapter = new FragmentAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(mAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.setupWithViewPager(viewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
    }

}
