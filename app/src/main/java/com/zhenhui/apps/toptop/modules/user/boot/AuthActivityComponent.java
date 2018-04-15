package com.zhenhui.apps.toptop.modules.user.boot;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = AuthActivityModule.class, dependencies = AppComponent.class)
public interface AuthActivityComponent {

    AuthActivity inject(AuthActivity activity);

    AuthPresenter presenter();

}
