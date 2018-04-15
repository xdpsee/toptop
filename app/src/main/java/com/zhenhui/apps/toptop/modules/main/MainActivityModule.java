package com.zhenhui.apps.toptop.modules.main;

import com.zhenhui.apps.toptop.base.PerActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainView mView;

    @Inject
    public MainActivityModule(MainView mView) {
        this.mView = mView;
    }

    @Provides
    @PerActivity
    MainView mainView() {
        return mView;
    }
}
