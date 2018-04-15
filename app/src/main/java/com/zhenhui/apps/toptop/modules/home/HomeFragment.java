package com.zhenhui.apps.toptop.modules.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.databinding.FragmentHomeBinding;
import com.zhenhui.apps.toptop.base.fragment.BlankFragment;


public class HomeFragment extends Fragment implements HomeView {

    private ContentFragmentAdapter contentFragmentAdapter;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        FragmentHomeBinding binding = DataBindingUtil.bind(view);

        contentFragmentAdapter = new ContentFragmentAdapter(getChildFragmentManager());

        binding.setPaddingTop(QMUIStatusBarHelper.getStatusbarHeight(getActivity()));
        binding.viewPager.setAdapter(contentFragmentAdapter);
        binding.tabLayout.setViewPager(binding.viewPager);

    }

    private class ContentFragmentAdapter extends FragmentStatePagerAdapter {

        public ContentFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            BlankFragment fragment = new BlankFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        private String[] titles = new String[] {
                "关注",
                "精选",
                "视频",
                "GIF",
                "美图",
                "漫画",
                "段子"
        };
    }
}
