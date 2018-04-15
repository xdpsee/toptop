package com.zhenhui.apps.toptop.modules.user.profile;

import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModule {

    private final ProfileView mView;

    public ProfileActivityModule(ProfileView view) {
        this.mView = view;
    }

    @Provides
    @PerActivity
    ProfileView profileView() {
        return mView;
    }


}
