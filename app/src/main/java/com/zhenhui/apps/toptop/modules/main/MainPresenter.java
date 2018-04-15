package com.zhenhui.apps.toptop.modules.main;

import javax.inject.Inject;

public class MainPresenter {

    private final MainView mView;

    @Inject
    public MainPresenter(MainView view) {
        this.mView = view;
    }

}

