package com.zhenhui.apps.toptop.modules.user.login;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = LoginActivityModule.class, dependencies = AppComponent.class)
public interface LoginActivityComponent {

    LoginActivity inject(LoginActivity activity);

    LoginPresenter presenter();

}

