package com.zhenhui.apps.toptop.modules.app;

import android.app.Application;

import com.zhenhui.apps.toptop.data.api.ApiServiceModule;
import com.zhenhui.apps.toptop.data.api.UserService;
import com.zhenhui.apps.toptop.model.UserSetting;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    Application getApplication();

    UserService getUserService();

    UserSetting getUserSetting();

}


