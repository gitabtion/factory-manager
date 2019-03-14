package net.hrsoft.transparent_factory_manager.mine.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.account.activities.LoginActivity;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.common.constants.CacheKey;
import net.hrsoft.transparent_factory_manager.mine.activities.WorkGroupManageActivity;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateMobileModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdatePasswordModel;
import net.hrsoft.transparent_factory_manager.mine.models.UpdateUserNameModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.utils.RegexUtil;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/26 21:10.
 * email caiheng@hrsoft.net
 */

public class MineFragment extends BaseFragment {
    @BindView(R2.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R2.id.txt_username)
    TextView txtUsername;
    @BindView(R2.id.txt_mobile)
    TextView txtMobile;

    private UserModel user;
    private ViewGroup container;


    @Override
    protected int getLayoutId() {
        return R2.layout.fragment_mine;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.container = container;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initVariable() {
        user = (UserModel) TFMApplication.getInstance().getCacheUtil().getSerializableObj(CacheKey.USER);

    }

    @Override
    protected void initView() {
        if (user==null){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
            TFMApplication.getInstance().removeActivity(getActivity());
        }
        txtUsername.setText(user.getName());
        txtMobile.setText(user.getMobile());

    }

    @Override
    protected void loadData() {

    }


    /**
     * 修改姓名
     * 应产品要求取消该需求
     */
    @OnClick(R2.id.rl_personal_info)
    public void onRlPersonalInfoClicked() {
//        AlertDialog.Builder editBuilder = new AlertDialog.Builder(getActivity());
//        final EditText editText = new EditText(getContext());
//        editBuilder.setView(editText).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface
//                .OnClickListener() {
//
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                UpdateUserNameModel updateUserNameModel = new UpdateUserNameModel(editText.getText().toString().trim());
//                updateName(user.getId(), updateUserNameModel);
//            }
//        }).setTitle("修改姓名").show();
    }

    /**
     * 修改密码
     */
    @OnClick(R2.id.rl_update_password)
    public void onRlUpdatePasswordClicked() {
        final AlertDialog.Builder editBuilder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R2.layout.dialog_edit_text, container, false);
        final TextInputEditText inputEditText = (TextInputEditText) view.findViewById(R2.id.edit_dialog_input);
        editBuilder.setView(view).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UpdatePasswordModel passwordModel = new UpdatePasswordModel(inputEditText.getText().toString().trim());
                if (passwordModel.getPassword().length()<6){
                    SnackbarUtil.showSnackbar(getView(),"密码不得少于6位");
                }else {
                    updatePassword(user.getId(), passwordModel);
                }
            }
        }).setTitle("修改密码").show();
    }

    /**
     * 修改手机号
     */
    @OnClick(R2.id.rl_edit_mobile)
    public void onRlEditMobileClicked() {
        AlertDialog.Builder editBuilder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R2.layout.dialog_edit_text, container, false);
        final TextInputEditText inputEditText = (TextInputEditText) view.findViewById(R2.id.edit_dialog_input);
        editBuilder.setView(view).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface
                .OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                UpdateMobileModel mobileModel = new UpdateMobileModel(inputEditText.getText().toString().trim());
                if (RegexUtil.checkMobile(mobileModel.getMobile())) {
                    updateMobile(user.getId(), mobileModel);
                } else {
                    SnackbarUtil.showSnackbar(getView(), "请输入正确的手机号码");
                }
            }
        }).setTitle("修改手机号").show();
    }


    /**
     * 员工管理
     */
    @OnClick(R2.id.rl_employ_manager)
    public void onRlEmployManagerClicked() {
        Intent intent = new Intent(getActivity(), WorkGroupManageActivity.class);
        startActivity(intent);
    }

    @OnClick(R2.id.rl_exit_account)
    public void onRlExitAccountClicked() {
        new AlertDialog.Builder(getContext()).setMessage("确定退出账号并清除缓存信息？").setPositiveButton("确定", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TFMApplication.getInstance().exitAccount();
                        getActivity().finish();
                    }
                }).setNegativeButton("取消", null).setCancelable(false).show();
    }

    /**
     * 更改姓名网络请求
     * 应产品要求，取消该功能
     *
     * @param userId              id
     * @param updateUserNameModel request
     */

    private void updateName(final int userId, final UpdateUserNameModel updateUserNameModel) {
//        final ProgressDialog.Builder builder = new ProgressDialog.Builder(getContext());
//        builder.setMessage("请稍候");
//        final AlertDialog dialog = builder.show();
//        RestClient.getService().updateName(userId, updateUserNameModel).enqueue(new DataCallback<APIResponse>() {
//            @Override
//            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
//                user.setName(updateUserNameModel.getName());
//                txtUsername.setText(updateUserNameModel.getName());
//                TFMApplication.getInstance().getCacheUtil().putSerializableObj(CacheKey.USER, user);
//            }
//
//            @Override
//            public void onDataFailure(Call<APIResponse> call, Throwable t) {
//                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
//            }
//
//            @Override
//            public void dismissDialog() {
//                dialog.dismiss();
//            }
//        });
    }

    /**
     * 更改密码网络请求
     *
     * @param userId   id
     * @param password request
     */
    private void updatePassword(final int userId, final UpdatePasswordModel password) {
        progressDialog.setMessage("请稍候");
        progressDialog.show();
        RestClient.getService().updatePassword(userId, password).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("修改成功，请重新登录");
                TFMApplication.getInstance().exitAccount();
                getActivity().finish();
//                TFMApplication.getInstance().getCacheUtil().putSerializableObj(CacheKey.USER, user);
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {
                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                progressDialog.dismiss();
            }
        });
    }

    /**
     * 更改手机号网络请求
     *
     * @param userId id
     * @param mobile request
     */
    private void updateMobile(final int userId, final UpdateMobileModel mobile) {
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        RestClient.getService().updateMobile(userId, mobile).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                user.setMobile(mobile.getMobile());
                txtMobile.setText(mobile.getMobile());
                ToastUtil.showToast("修改成功，请重新登录");
                TFMApplication.getInstance().exitAccount();
                getActivity().finish();
//                TFMApplication.getInstance().getCacheUtil().putSerializableObj(CacheKey.USER, user);
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {
                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                progressDialog.dismiss();
            }
        });
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


}
