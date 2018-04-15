package com.zhenhui.apps.toptop.modules.user.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.databinding.ActivityProfileBinding;
import com.zhenhui.apps.toptop.base.activity.AbstractActivity;

public class ProfileActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        QMUIStatusBarHelper.setStatusBarDarkMode(this);

        binding.setPaddingTop(QMUIStatusBarHelper.getStatusbarHeight(this));
        binding.setEvent(new EventListener() {
            @Override
            public void onBack(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerProfileActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    public interface EventListener {
        void onBack(View view);
    }

}
