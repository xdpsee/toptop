package com.zhenhui.apps.toptop.modules.user.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.zhenhui.apps.toptop.R;
import com.zhenhui.apps.toptop.base.activity.AbstractActivity;
import com.zhenhui.apps.toptop.databinding.ActivityProfileBinding;
import com.zhenhui.apps.toptop.modules.app.AppComponent;

import javax.inject.Inject;

public class ProfileActivity extends AbstractActivity implements ProfileView {

    @Inject
    public ProfilePresenter mPresenter;

    private ActivityProfileBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        QMUIStatusBarHelper.setStatusBarDarkMode(this);

        mDataBinding.setPaddingTop(QMUIStatusBarHelper.getStatusbarHeight(this));
        mDataBinding.setEvent(new EventListener() {
            @Override
            public void onBack(View view) {
                finish();
            }

            @Override
            public void onLogout(View view) {
                mPresenter.logout();

                setResult(RESULT_OK);

                finish();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        QMUIGroupListView mGroupListView = mDataBinding.groupListView;

        QMUICommonListItemView avatarItem = mGroupListView.createItemView("头像");
        avatarItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);

        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(80, 80);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_default_avatar));

        avatarItem.addAccessoryCustomView(imageView);
        avatarItem.setMinimumHeight(200);


        QMUICommonListItemView nicknameItem = mGroupListView.createItemView("昵称");
        nicknameItem.setOrientation(QMUICommonListItemView.HORIZONTAL);
        nicknameItem.setDetailText("zhcen");
        nicknameItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);


        QMUICommonListItemView ganderItem = mGroupListView.createItemView("性别");
        ganderItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        ganderItem.setOrientation(QMUICommonListItemView.HORIZONTAL);
        ganderItem.setDetailText("未知");

        QMUICommonListItemView wechatItem = mGroupListView.createItemView("微信");
        wechatItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        wechatItem.setOrientation(QMUICommonListItemView.HORIZONTAL);
        wechatItem.setDetailText("立即绑定");

        QMUICommonListItemView qqItem = mGroupListView.createItemView("QQ");
        qqItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        qqItem.setOrientation(QMUICommonListItemView.HORIZONTAL);
        qqItem.setDetailText("立即绑定");

        QMUICommonListItemView weiboItem = mGroupListView.createItemView("微博");
        weiboItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        weiboItem.setOrientation(QMUICommonListItemView.HORIZONTAL);
        weiboItem.setDetailText("解除绑定");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    Toast.makeText(ProfileActivity.this, text + " is Clicked", Toast.LENGTH_SHORT).show();
                }
            }
        };

        QMUIGroupListView.newSection(this)
                .addItemView(avatarItem, onClickListener)
                .addTo(mGroupListView);

        QMUIGroupListView.newSection(this)
                .addItemView(nicknameItem, onClickListener)
                .addItemView(ganderItem, onClickListener)
                .addTo(mGroupListView);

        QMUIGroupListView.newSection(this)
                .addItemView(wechatItem, onClickListener)
                .addItemView(qqItem, onClickListener)
                .addItemView(weiboItem, onClickListener)
                .setTitle("社交账号绑定")
                .addTo(mGroupListView);


    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerProfileActivityComponent.builder()
                .appComponent(appComponent)
                .profileActivityModule(new ProfileActivityModule(this))
                .build()
                .inject(this);

    }

    public interface EventListener {

        void onBack(View view);

        void onLogout(View view);

    }

}
