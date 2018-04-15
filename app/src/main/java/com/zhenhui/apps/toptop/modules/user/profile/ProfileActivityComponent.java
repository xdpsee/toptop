package com.zhenhui.apps.toptop.modules.user.profile;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = ProfileActivityModule.class, dependencies = AppComponent.class)
public interface ProfileActivityComponent {

    void inject(ProfileActivity activity);

    ProfilePresenter presenter();

}


