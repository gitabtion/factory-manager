package net.hrsoft.transparent_factory_manager.account.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.account.models.LoginRequest;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.main.MainActivity;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.utils.RegexUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/30 15:56.
 * email caiheng@hrsoft.net
 */

public class LoginActivity extends ToolBarActivity {
    @BindView(R.id.edit_account)
    TextInputEditText editAccount;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    private LoginRequest loginRequest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("实干家定制版");
        getToolbar().setNavigationIcon(null);
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
//        if (isDataTrue()){
//            loginRequest = new LoginRequest(editAccount.getText().toString().trim(),editPassword.getText().toString()
//                    .trim());
//
//            RestClient.getService().login(loginRequest).enqueue(new DataCallback<APIResponse>() {
//
//                @Override
//                public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
//                }
//
//                @Override
//                public void onDataFailure(Call<APIResponse> call, Throwable t) {
//                    ToastUtil.showToast("error");
//                }
//            });
//        }

    }

    @OnClick(R.id.btn_forget_password)
    public void onBtnForgetPasswordClicked() {
    }

    /**
     * 用于TextInputEditText控件显示错误信息
     * @param textInputEditText 控件对象
     * @param error 错误信息
     */
    private void showError(TextInputEditText textInputEditText, String error) {
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    private boolean isDataTrue(){
        boolean flag = true;
        if (!RegexUtil.checkMobile(editAccount.getText().toString().trim())){
            showError(editAccount,"手机号不合法");
            flag = false;
        }else if (editPassword.getText().toString().trim().length()<6){
            showError(editPassword,"密码不得少于6位");
            flag = false;
        }
        return flag;
    }
}
