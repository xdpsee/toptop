package com.zhenhui.apps.toptop.modules.user.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.databinding.ActivityLoginBinding;
import com.zhenhui.apps.toptop.modules.app.AppComponent;
import com.zhenhui.apps.toptop.base.activity.AbstractActivity;

import javax.inject.Inject;

public class LoginActivity extends AbstractActivity implements LoginView {

    @Inject
    public LoginPresenter mPresenter;

    private QMUITipDialog mProcessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        QMUIStatusBarHelper.setStatusBarLightMode(this);

        final String phone = getIntent().getStringExtra("phone");

        binding.setEvent(new EventListener() {
            @Override
            public void onBack(View view) {
                finish();
            }

            @Override
            public void onLoginClick(View v) {
                String secret = binding.editTextSecret.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(secret)) {
                    mPresenter.login(phone, secret);
                } else {

                }
            }
        });

        binding.setPaddingTop(QMUIStatusBarHelper.getStatusbarHeight(this));
        binding.setTip(Html.fromHtml("请输入账号<font color='red'>" + phone + "</font>的密码"));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);

    }

    public interface EventListener {

        void onBack(View view);

        void onLoginClick(View v);

    }


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
    public void loginSuccess(String token) {
        Intent intent = new Intent();
        intent.putExtra("token", token);
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void showLoginError(String message) {
        QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord(message)
                .create();
        dialog.show();
    }
}


