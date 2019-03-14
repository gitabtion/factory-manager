package net.hrsoft.transparent_factory_manager.mine.activities;

import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.mine.models.CreateLeaderRequest;
import net.hrsoft.transparent_factory_manager.mine.models.LeaderAccountModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.utils.RegexUtil;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/9 23:06.
 * email caiheng@hrsoft.net
 */

public class AddLeaderActivity extends ToolBarActivity {
    @BindView(R2.id.edit_name)
    TextInputEditText editName;
    @BindView(R2.id.edit_mobile)
    TextInputEditText editMobile;
    @BindView(R2.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R2.id.btn_edit_done)
    TextView btnEditDone;

    @Override
    protected int getLayoutId() {
        return R2.layout.activity_update_user_info;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        btnEditDone.setText("确认创建");

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R2.id.btn_edit_done)
    public void onViewClicked() {
        if (isDataTrue()) {
            progressDialog.setMessage("请稍候");
            progressDialog.show();

            LeaderAccountModel leaderAccountModel = new LeaderAccountModel(editName.getText().toString().trim(),
                    editMobile.getText().toString().trim(), editPassword.getText().toString().trim());
            CreateLeaderRequest request = new CreateLeaderRequest();
            request.add(leaderAccountModel);

            RestClient.getService().createLeader(request).enqueue(new DataCallback<APIResponse>() {
                @Override
                public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    ToastUtil.showToast("创建成功");
                    finish();
                }

                @Override
                public void onDataFailure(Call<APIResponse> call, Throwable t) {
                    SnackbarUtil.showSnackbar(getWindow().getDecorView(),"网络连接失败，请稍候再试");
                }

                @Override
                public void dismissDialog() {
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }
            });
        }
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
        } else if (editName.getText().length()==0) {
            showError(editName, "姓名不能为空");
            flag = false;
        }


        return flag;
    }

}
