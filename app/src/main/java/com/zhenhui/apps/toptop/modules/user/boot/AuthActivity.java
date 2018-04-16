package com.zhenhui.apps.toptop.modules.user.boot;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.databinding.ActivityAuthBinding;
import com.zhenhui.apps.toptop.model.Constants;
import com.zhenhui.apps.toptop.modules.user.login.LoginActivity;
import com.zhenhui.apps.toptop.base.activity.AbstractActivity;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

public class AuthActivity extends AbstractActivity implements AuthView {

    private SsoHandler mWeiboSsoHandler;

    private AuthInfo mWeiboAuthInfo;

    private ActivityAuthBinding mDataBinding;

    @Inject
    protected AuthPresenter mPresenter;

    private QMUITipDialog mProcessDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.setStatusBarLightMode(this);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        mWeiboAuthInfo = new AuthInfo(AuthActivity.this, Constants.WEIBO_APP_KEY, Constants.WEIBO_REDIRECT_URL, Constants.WEIBO_SCOPE);
        WbSdk.install(AuthActivity.this, mWeiboAuthInfo);
        mWeiboSsoHandler = new SsoHandler(AuthActivity.this);


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        mDataBinding.setPaddingTop(QMUIStatusBarHelper.getStatusbarHeight(this));
        mDataBinding.setEvent(new EventListener() {
            @Override
            public void onBack(View view) {
                finish();
            }

            @Override
            public void onTestPhone(View view) {
                String phone = mDataBinding.editTextPhone.getText().toString();
                mPresenter.testPhone(phone);
            }

            @Override
            public void onAttemptLoginWeibo(View view) {
                doAttemptLoginByWeibo();
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAuthActivityComponent.builder()
                .appComponent(appComponent)
                .authActivityModule(new AuthActivityModule(this))
                .build()
                .inject(this);
    }

    // Event
    private void doAttemptLoginByWeibo() {
        mWeiboSsoHandler.authorize(new WbAuthListener() {
            @Override
            public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                AuthActivity.this.runOnUiThread(() -> {
                    mPresenter.loginWithWeibo(Long.parseLong(oauth2AccessToken.getUid()), oauth2AccessToken.getToken());
                });
            }

            @Override
            public void cancel() {

            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                AuthActivity.this.runOnUiThread(() -> {
                    Toast.makeText(AuthActivity.this, wbConnectErrorMessage.getErrorMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @SuppressWarnings("unused")
    public interface EventListener {

        void onBack(View view);

        void onTestPhone(View view);

        void onAttemptLoginWeibo(View view);

    }


    ///////////////////////////////////////////

    @Override
    public void showProgressStatus() {

        if (null == mProcessDialog) {
            mProcessDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .create();
        }

        mProcessDialog.show();
    }

    @Override
    public void hideProgressStatus() {
        if (mProcessDialog != null) {
            mProcessDialog.dismiss();
        }
    }

    @Override
    public void showErrorMessage(String error) {
        Toasty.error(this, error, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void startLoginActivity(String phone) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("phone", phone);
        startActivityForResult(intent, 1);
    }

    @Override
    public void startRegistryActivity() {

    }

    @Override
    public void loginWeiboSuccess(String token) {

        Intent intent = new Intent();
        intent.putExtra("token", token);

        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mWeiboSsoHandler != null) {
            mWeiboSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }
    }
}






