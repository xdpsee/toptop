package com.zhenhui.apps.toptop.modules.user.boot;

import android.content.Intent;

import com.zhenhui.apps.toptop.data.api.request.SocialSignin;
import com.zhenhui.apps.toptop.model.SocialType;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.model.Result;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthPresenter {

    private final AuthView mView;

    private final AppComponent appComponent;

    @Inject
    public AuthPresenter(AuthView view, AppComponent appComponent) {
        this.mView = view;
        this.appComponent = appComponent;

    }

    public void testPhone(final String phone) {
        mView.showProgressStatus();
        appComponent.getUserService().isUserExist(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Result<Boolean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressStatus();
                mView.showErrorMessage("网络异常");
            }

            @Override
            public void onNext(Result<Boolean> result) {
                mView.hideProgressStatus();
                if (result.getError() == 0) {
                    if (result.getData()) {
                        mView.startLoginActivity(phone);
                    } else {
                        mView.startRegistryActivity();
                    }
                } else {
                    mView.showErrorMessage(result.getMessage());
                }
            }
        });
    }

    public void loginWithWeibo(long uid, String accessToken) {

        mView.showProgressStatus();
        appComponent.getUserService().loginSocial(new SocialSignin(SocialType.WEIBO, uid, accessToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Result<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgressStatus();
                mView.showErrorMessage("网络异常");
            }

            @Override
            public void onNext(Result<String> result) {
                mView.hideProgressStatus();
                if (result.getError() == 0) {
                    final String token = result.getData();
                    mView.loginWeiboSuccess(token);

                } else {
                    mView.showErrorMessage(result.getMessage());
                }
            }
        });

    }


}

