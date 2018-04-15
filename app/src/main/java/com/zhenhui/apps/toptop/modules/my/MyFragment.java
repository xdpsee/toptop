package com.zhenhui.apps.toptop.modules.my;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.data.api.ApiServiceModule;
import com.zhenhui.apps.toptop.databinding.FragmentMyBinding;
import com.zhenhui.apps.toptop.model.User;
import com.zhenhui.apps.toptop.model.UserSetting;
import com.zhenhui.apps.toptop.modules.user.boot.AuthActivity;
import com.zhenhui.apps.toptop.modules.user.profile.ProfileActivity;
import com.zhenhui.apps.toptop.base.fragment.AbstractFragment;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends AbstractFragment implements MyView {

    private AppComponent appComponent;

    @Inject
    public MyPresenter mPresenter;

    private FragmentMyBinding mDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        this.appComponent = appComponent;

        DaggerMyFragmentComponent.builder()
                .appComponent(appComponent)
                .myFragmentModule(new MyFragmentModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataBinding = DataBindingUtil.bind(view);

        UserSetting setting = appComponent.getUserSetting();
        mDataBinding.setUser(setting.getUser());
        mDataBinding.setEvent(new EventListener() {
            @Override
            public void onAuthClick(View view) {
                UserSetting setting = appComponent.getUserSetting();
                User currUser = setting.getUser();
                if (currUser.getId() != null && currUser.getId() > 0) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), AuthActivity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @SuppressWarnings("unused")
    public interface EventListener {

        void onAuthClick(View view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            ApiServiceModule.TOKEN = data.getStringExtra("token");
            UserSetting setting = appComponent.getUserSetting();
            setting.setToken(ApiServiceModule.TOKEN);
            mPresenter.reloadUserInfo();
        }

    }

    @Override
    public void resetUserInfo(User user) {
        if (user != null) {
            appComponent.getUserSetting().setUser(user);
        }

        mDataBinding.setUser(user);
    }
}




