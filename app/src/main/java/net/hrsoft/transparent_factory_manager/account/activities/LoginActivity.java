package net.hrsoft.transparent_factory_manager.account.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.account.models.LoginRequest;
import net.hrsoft.transparent_factory_manager.account.models.LoginResponse;
import net.hrsoft.transparent_factory_manager.base.activities.NoBarActivity;
import net.hrsoft.transparent_factory_manager.common.constants.CacheKey;
import net.hrsoft.transparent_factory_manager.main.MainActivity;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.utils.RegexUtil;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/30 15:56.
 * email caiheng@hrsoft.net
 */

public class LoginActivity extends NoBarActivity {
    @BindView(R2.id.edit_account)
    TextInputEditText editAccount;
    @BindView(R2.id.edit_password)
    TextInputEditText editPassword;
    private LoginRequest loginRequest;

    @Override
    protected int getLayoutId() {
        return R2.layout.activity_login;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        if (TFMApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN)!=null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R2.id.btn_login)
    public void onBtnLoginClicked() {
        if (isDataTrue()){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("请稍候");
            progressDialog.show();
            loginRequest = new LoginRequest(editAccount.getText().toString().trim(),editPassword.getText().toString()
                    .trim());

            RestClient.getService().login(loginRequest).enqueue(new DataCallback<APIResponse<LoginResponse>>() {
                @Override
                public void onDataResponse(Call<APIResponse<LoginResponse>> call, Response<APIResponse<LoginResponse
                                        >> response) {

                    if (response.body().getData().getUser().getRole().equals("manager")){
                        TFMApplication.getInstance().getCacheUtil().putString(CacheKey.TOKEN,response.body().getData().getToken());
                        TFMApplication.getInstance().getCacheUtil().putSerializableObj(CacheKey.USER,response.body()
                                .getData().getUser());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        SnackbarUtil.showSnackbar(getWindow().getDecorView(),"您的账号无权登录此客户端，请确认您的账号是否为经理账号");
                    }
                }

                @Override
                public void onDataFailure(Call<APIResponse<LoginResponse>> call, Throwable t) {
                    SnackbarUtil.showSnackbar(getWindow().getDecorView(),"网络连接失败，请稍后再试");
                }

                @Override
                public void dismissDialog() {
                    progressDialog.dismiss();
                }
            });
        }

    }

    @OnClick(R2.id.btn_forget_password)
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
