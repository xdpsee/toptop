package com.zhenhui.apps.toptop.modules.home;


import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    private final HomeView mView;

    @Inject
    public HomeFragmentModule(HomeView view) {
        this.mView = view;
    }


    @Provides
    public HomeView homeView() {
        return mView;
    }

}
