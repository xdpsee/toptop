package com.zhenhui.apps.toptop.modules.user.login;


import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.data.api.request.Login;
import com.zhenhui.apps.toptop.model.Result;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter {

    private final LoginView mView;

    private final AppComponent appComponent;

    @Inject
    public LoginPresenter(LoginView view, AppComponent appComponent) {
        this.mView = view;
        this.appComponent = appComponent;
    }

    public void login(String phone, String secret) {

        mView.showProgressStatus();
        appComponent.getUserService()
                .login(new Login(phone, secret))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgressStatus();
                        e.printStackTrace();
                        mView.showLoginError("网络异常");
                    }

                    @Override
                    public void onNext(Result<String> result) {
                        mView.hideProgressStatus();

                        if (0 == result.getError()) {
                            mView.loginSuccess(result.getData());
                        } else {
                            mView.showLoginError(result.getMessage());
                        }
                    }
                });

    }

}
