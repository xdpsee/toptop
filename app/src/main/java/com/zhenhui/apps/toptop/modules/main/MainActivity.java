package com.zhenhui.apps.toptop.modules.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.databinding.ActivityMainBinding;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.activity.AbstractActivity;
import com.zhenhui.apps.toptop.base.fragment.BaseFragment;
import com.zhenhui.apps.toptop.modules.home.HomeFragment;
import com.zhenhui.apps.toptop.modules.my.MyFragment;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AbstractActivity implements MainView {


    private final List<Fragment> fragments = new LinkedList<>();

    private FragmentsAdapter fragmentsAdapter;

    @Inject
    protected MainPresenter mPresenter;

    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        fragments.add(homeFragment);

        BaseFragment fragment = new BaseFragment();
        fragments.add(fragment);

        fragment = new BaseFragment();
        fragments.add(fragment);

        Fragment myFragment = new MyFragment();
        fragments.add(myFragment);

        fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(), fragments);

        mDataBinding.viewPager.setAdapter(fragmentsAdapter);
        mDataBinding.viewPager.setSlidingEnable(false);
        mDataBinding.navigationView.setupWithViewPager(mDataBinding.viewPager, true);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDataBinding.navigationView.setSoundEffectsEnabled(true);
        mDataBinding.navigationView.enableShiftingMode(false);
        mDataBinding.navigationView.enableItemShiftingMode(false);
    }

    private static class FragmentsAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public FragmentsAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            this.data = fragments;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}
