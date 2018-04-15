package com.zhenhui.apps.toptop.modules.my;

import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.PerFragment;

import dagger.Component;


@PerFragment
@Component(modules = MyFragmentModule.class, dependencies = {AppComponent.class})
public interface MyFragmentComponent {

    MyFragment inject(MyFragment fragment);

    MyPresenter presenter();
}


