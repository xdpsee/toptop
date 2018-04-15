package com.zhenhui.apps.toptop.modules.user.profile;


import com.zhenhui.apps.toptop.modules.app.AppComponent;

import javax.inject.Inject;

public class ProfilePresenter {

    private final ProfileView mView;

    private final AppComponent appComponent;

    @Inject
    public ProfilePresenter(ProfileView mView, AppComponent appComponent) {
        this.mView = mView;
        this.appComponent = appComponent;
    }

    public void logout() {
        appComponent.getUserSetting().clearUser();



    }

}


