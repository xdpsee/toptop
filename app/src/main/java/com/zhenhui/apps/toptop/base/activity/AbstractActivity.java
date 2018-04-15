package com.zhenhui.apps.toptop.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.zhenhui.apps.toptop.modules.app.AppApplication;
import com.zhenhui.apps.toptop.modules.app.AppComponent;


public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.setStatusBarDarkMode(this);
        QMUIStatusBarHelper.translucent(this);
        setupActivityComponent(AppApplication.get(this).getAppComponent());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}
