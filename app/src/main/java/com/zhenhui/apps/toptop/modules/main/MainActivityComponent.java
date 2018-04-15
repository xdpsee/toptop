package com.zhenhui.apps.toptop.modules.main;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = MainActivityModule.class,dependencies = AppComponent.class)
public interface MainActivityComponent {

    MainActivity inject(MainActivity mainActivity);

    MainPresenter presenter();


}
