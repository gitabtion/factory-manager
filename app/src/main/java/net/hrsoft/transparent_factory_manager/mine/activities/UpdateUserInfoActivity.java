package net.hrsoft.transparent_factory_manager.mine.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.utils.RegexUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author abtion.
 * @since 17/9/9 21:58.
 * email caiheng@hrsoft.net
 */

public class UpdateUserInfoActivity extends ToolBarActivity {
    @BindView(R.id.edit_name)
    TextInputEditText editName;
    @BindView(R.id.edit_mobile)
    TextInputEditText editMobile;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    private UserModel userModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_user_info;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getIntent().getExtras();
        userModel = (UserModel) bundle.getSerializable(WorkGroupManageActivity.LEADER);
    }

    @Override
    protected void initView() {
        setActivityTitle("修改班组长信息");
        editName.setText(userModel.getName());
        editMobile.setText(userModel.getMobile());
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_edit_done)
    public void onViewClicked() {
    }

    /**
     * 用于TextInputEditText控件显示错误信息
     *
     * @param textInputEditText 控件对象
     * @param error             错误信息
     */
    private void showError(TextInputEditText textInputEditText, String error) {
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    private boolean isDataTrue() {
        boolean flag = true;
        if (!RegexUtil.checkMobile(editMobile.getText().toString().trim())) {
            showError(editMobile, "手机号不合法");
            flag = false;
        } else if (editPassword.getText().toString().trim().length() < 6) {
            showError(editPassword, "密码不得少于6位");
            flag = false;
        } else if (editName.getText().equals("")) {
            showError(editName, "姓名不能为空");
            flag = false;
        }


        return flag;
    }
}
