package com.zhenhui.apps.toptop.modules.my;

import com.zhenhui.apps.toptop.data.api.ApiServiceModule;
import com.zhenhui.apps.toptop.model.UserSetting;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.model.Result;
import com.zhenhui.apps.toptop.model.User;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MyPresenter {

    private final MyView mView;

    private final AppComponent appComponent;

    @Inject
    public MyPresenter(MyView view, AppComponent appComponent) {
        this.mView = view;
        this.appComponent = appComponent;
    }

    public void reloadUserInfo(final String token) {
        ApiServiceModule.TOKEN = token;
        appComponent.getUserService().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<User> result) {
                        appComponent.getUserSetting().saveUser(token, result.getData());
                        mView.renderUserInfo(result.getData());
                    }
                });

    }
}
