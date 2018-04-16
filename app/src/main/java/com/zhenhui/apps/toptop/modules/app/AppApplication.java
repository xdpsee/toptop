package com.zhenhui.apps.toptop.modules.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.zhenhui.apps.toptop.data.api.ApiServiceModule;
import com.zhenhui.apps.toptop.model.UserSetting;

import es.dmoral.toasty.Toasty;

public class AppApplication extends Application {

    private AppComponent appComponent;


    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();

        UserSetting setting = appComponent.getUserSetting();
        ApiServiceModule.TOKEN = setting.currToken();

        Toasty.Config.getInstance()
                .setTextColor(Color.WHITE)
                .setErrorColor(Color.argb(80, 20,20,20))
                .apply();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
