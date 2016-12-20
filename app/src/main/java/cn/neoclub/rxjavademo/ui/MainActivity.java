package cn.neoclub.rxjavademo.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.base.SimpleActivity;
import cn.neoclub.rxjavademo.ui.fragment.FirstFragment;
import cn.neoclub.rxjavademo.ui.fragment.MainFragment;
import cn.neoclub.rxjavademo.ui.fragment.SecondFragment;
import cn.neoclub.rxjavademo.util.ToastUtil;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SimpleActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "TTTT");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mainFragment = new MainFragment();
        loadRootFragment(R.id.layout_fragment, mainFragment);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            ToastUtil.shortShow("Camera");
        } else if (id == R.id.nav_gallery) {
            ToastUtil.shortShow("Gallery");
        } else if (id == R.id.nav_slideshow) {
            ToastUtil.shortShow("SlideShow");
        } else if (id == R.id.nav_manage) {
            ToastUtil.shortShow("Manage");
        } else if (id == R.id.nav_share) {
            ToastUtil.shortShow("Share");
        } else if (id == R.id.nav_send) {
            ToastUtil.shortShow("Send");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}