package com.zhenhui.apps.toptop.modules.my;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class MyFragmentModule {

    private final MyView mView;

    @Inject
    public MyFragmentModule(MyView view) {
        this.mView = view;
    }


    @Provides
    public MyView myView() {
        return mView;
    }

}

