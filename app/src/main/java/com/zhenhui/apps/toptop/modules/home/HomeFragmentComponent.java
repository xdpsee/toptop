package com.zhenhui.apps.toptop.modules.home;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = HomeFragmentModule.class, dependencies = AppComponent.class)
public interface HomeFragmentComponent {

    HomeFragment inject(HomeFragment fragment);

    HomePresenter presenter();

}
