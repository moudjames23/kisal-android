package com.moudjames23.coronanews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.moudjames23.coronanews.adapter.ViewPagerAdapter;
import com.moudjames23.coronanews.app.Constant;
import com.moudjames23.coronanews.app.Pref;
import com.moudjames23.coronanews.fragments.FragmentHome;
import com.moudjames23.coronanews.fragments.FragmentInfo;
import com.moudjames23.coronanews.fragments.FragmentVideo;
import com.moudjames23.coronanews.model.Home;
import com.moudjames23.coronanews.networkapi.NetworkCallBack;
import com.moudjames23.coronanews.networkapi.WebService;
import com.moudjames23.coronanews.views.NonSwipeableViewPager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NetworkCallBack<Home>{



    @Bind(R.id.bottom_navigation_bar)
    public BottomNavigationBar bottomNavigationBar;

    @Bind(R.id.viewPager)
    NonSwipeableViewPager viewPager;

    private FragmentManager manager;

    private FragmentHome home;
    public FragmentVideo stats;
    public FragmentInfo info;

    public static String TAG = "main_tag";

    private Pref pref;

    @Bind(R.id.loader)
    AVLoadingIndicatorView loader;

    private String[] titles = {"Accueil", "Sensibilisation", "Stats mondiales"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new Pref(this);

        if (pref.getBooleanValue(Constant.FIRST_LAUNCH, true)) {

            finish();
            Intent intent = new Intent(this, IntroActivity.class);

            startActivity(intent);

            return;
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //refreshLayout.setRefreshing(false);

        manager = getSupportFragmentManager();

        setupFragment();
        setupViewPager();
        setupBottomBar();


        init();

    }

    private void init() {

        WebService webService = new WebService(this, this);
        webService.home();
    }


    private void setupFragment() {
        home = (FragmentHome) manager.findFragmentByTag(FragmentHome.TAG);
        if (home == null)
        {
            home = new FragmentHome();
        }

        stats = (FragmentVideo) manager.findFragmentByTag(FragmentVideo.TAG);
        if(stats == null)
            stats = new FragmentVideo();

        info = (FragmentInfo) manager.findFragmentByTag(FragmentInfo.TAG);
        if(info == null)
            info = new FragmentInfo();

    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(manager);
        adapter.addFragment(home);
        adapter.addFragment(stats);
        adapter.addFragment(info);


        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }

    private void setupBottomBar() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home, getString(R.string.home)))
                .addItem(new BottomNavigationItem(R.drawable.ic_video, getString(R.string.video)))
                .addItem(new BottomNavigationItem(R.drawable.ic_world, getString(R.string.stats)))
                //.addItem(new BottomNavigationItem(R.drawable.ic_profile, getString(R.string.profile)))
                .setFirstSelectedPosition(0)
                .initialise();


        bottomNavigationBar.setBackgroundColor((getResources().getColor(android.R.color.white)));



        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position, false);

                setTitle(titles[position]);

            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });

    }


    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading: ");
        loader.show();
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading: ");
        loader.hide();

    }

    @Override
    public void noInternetConnexion() {
        Log.d(TAG, "noInternetConnexion: ");
    }

    @Override
    public void noItem() {

    }

    @Override
    public void onFailure(String errorMessage) {
        Log.d(TAG, "onFailure: " +errorMessage);
    }

    @Override
    public void onResponse(List<Home> data) {

    }

    @Override
    public void onResponse(Home data) {

        home.init(data.getLocal());
        home.setupGalerie(data.getGaleries(), this);

        stats.setupVideo(data.getVideos(), this);

        info.init(data.getGlobal(), data.getStats(), this);

    }
}
