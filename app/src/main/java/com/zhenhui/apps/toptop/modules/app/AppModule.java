package com.zhenhui.apps.toptop.modules.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.zhenhui.apps.toptop.model.User;
import com.zhenhui.apps.toptop.model.UserSetting;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application=application;
    }

    @Provides
    @Singleton
    public Application application(){
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences sharedPreferences() {
        return application.getSharedPreferences("toptop", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    UserSetting provideUserSetting(SharedPreferences sharedPreferences) {
        return new UserSetting(sharedPreferences);
    }
}



