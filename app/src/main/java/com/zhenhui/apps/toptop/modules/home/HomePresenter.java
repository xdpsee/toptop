package com.zhenhui.apps.toptop.modules.home;

import javax.inject.Inject;


public class HomePresenter {

    private final HomeView mView;

    @Inject
    public HomePresenter(HomeView view) {
        this.mView = view;
    }

}
