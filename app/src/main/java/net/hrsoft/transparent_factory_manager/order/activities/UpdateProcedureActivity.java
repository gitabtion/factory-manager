package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.models.UpdateProcedureRequest;
import net.hrsoft.transparent_factory_manager.order.models.WeightModel;
import net.hrsoft.transparent_factory_manager.utils.RxBus;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/11 11:31.
 * email caiheng@hrsoft.net
 */

public class UpdateProcedureActivity extends ToolBarActivity {
    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.edit_procedure_total_count)
    TextInputEditText editProcedureTotalCount;
    @BindView(R.id.edit_standard)
    TextInputEditText editStandard;
    @BindView(R.id.txt_procedure_start_time)
    TextView txtProcedureStartTime;
    @BindView(R.id.txt_procedure_end_time)
    TextView txtProcedureEndTime;
    @BindView(R.id.txt_group_name)
    TextView txtGroupName;
    @BindView(R.id.seek_weight)
    AppCompatSeekBar seekWeight;
    @BindView(R.id.txt_procedure_weight)
    TextView txtProcedureWeight;
    @BindView(R.id.edit_description)
    EditText editDescription;
    @BindView(R.id.btn_update_done)
    TextView btnDoneToCreateProcedure;

//    private CompositeDisposable compositeDisposable;
//    private GroupModel groupModel;
    private UpdateProcedureRequest updateProcedureRequest;
//    private ArrayList<WeightModel> weightModels;
    private ProcedureModel procedureModel;
//    private ArrayList<ProcedureModel> procedureModels;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_procedure_info;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getIntent().getExtras();
        procedureModel = (ProcedureModel) bundle.getSerializable(Config.PROCEDURE);
        updateProcedureRequest = new UpdateProcedureRequest();
//        weightModels = new ArrayList<>();
    }

    @Override
    protected void initView() {
        btnDoneToCreateProcedure.setText("确认修改");
        setActivityTitle("修改工序信息");
//        initSeekBar();
        bindView();
    }

    @Override
    protected void loadData() {
//        getProcedure();
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

    /**
     * 验证用户填充的数据是否符合规范
     * @return true为规范
     */
    private boolean isDataTrue() {
        boolean flag = true;
        if (editOrderName.getText().length() == 0) {
            showError(editOrderName, "工序名称不能为空");
            flag = false;
        } else if (editProcedureTotalCount.getText().length() == 0) {
            showError(editProcedureTotalCount, "数量不可为空");
            flag = false;
        } else if (editStandard.getText().length() == 0) {
            showError(editStandard, "单位不可为空");
            flag = false;
        }
        return flag;
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        updateProcedureRequest.setName(editOrderName.getText().toString().trim());
        updateProcedureRequest.setDescription(editDescription.getText().toString().trim());
        updateProcedureRequest.setStartTime(txtProcedureStartTime.getText().toString().trim());
        updateProcedureRequest.setEndTime(txtProcedureEndTime.getText().toString().trim());
        updateProcedureRequest.setStandard(editStandard.getText().toString().trim());
        updateProcedureRequest.setTotalCount(Integer.valueOf(editProcedureTotalCount.getText().toString().trim()));
        updateProcedureRequest.setOrderId(procedureModel.getOrderId());
        updateProcedureRequest.setWorkGroupId(procedureModel.getWorkGroupId());
//        updateProcedureRequest.setWeight(((double) seekWeight.getProgress()) / 100);
//        updateProcedureRequest.setWeights(weightModels);
    }

    /**
     * 获取同订单下所有的工序,权重修改依赖
     */
    private void getProcedure() {
        progressDialog.setMessage("请稍后");
        progressDialog.show();

        RestClient.getService().getOrderProcedure(procedureModel.getOrderId()).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call,
                                       Response<APIResponse<GetProcedureResponse>> response) {
//                procedureModels = response.body().getData().getProcedures();
            }

            @Override
            public void onDataFailure(Call<APIResponse<GetProcedureResponse>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    /**
     * 更新工序信息网络请求
     */
    private void updateProcedure() {
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        bindData();
        RestClient.getService().updateProcedure(procedureModel.getId(), updateProcedureRequest).enqueue(new DataCallback<APIResponse>() {


            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("更新成功，请刷新页面");
                finish();
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍候再试");
            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void bindView() {
        editOrderName.setText(procedureModel.getName());
        editProcedureTotalCount.setText(String.valueOf(procedureModel.getTotalCount()));
        editDescription.setText(procedureModel.getDescription());
        editStandard.setText(procedureModel.getStandard());
        txtGroupName.setText(procedureModel.getWorkGroupName());
        txtProcedureEndTime.setText(procedureModel.getEndTime());
        txtProcedureStartTime.setText(procedureModel.getStartTime());
//        txtProcedureWeight.setText(String.valueOf((int) (procedureModel.getWeight() * 100)));
//        seekWeight.setProgress((int) (procedureModel.getWeight() * 100));
    }

    private void initSeekBar() {
        seekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtProcedureWeight.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.btn_procedure_start_time_select)
    public void onBtnProcedureStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureStartTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_procedure_end_time_select)
    public void onBtnProcedureEndTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureEndTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_group_selector)
    public void onBtnGroupSelectorClicked() {
//        compositeDisposable = new CompositeDisposable();
//        RxBus.getInstance().toObservable(GroupModel.class).subscribe(new Observer<GroupModel>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                compositeDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(GroupModel groupModel) {
//                UpdateProcedureActivity.this.groupModel = groupModel;
//                txtGroupName.setText(groupModel.getName());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                ToastUtil.showToast(e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//        Intent intent = new Intent(this, SelectGroupActivity.class);
//        startActivity(intent);
        SnackbarUtil.showSnackbar(getWindow().getDecorView(),"工序已经创建，不能修改班组");
    }

    @OnClick(R.id.btn_update_done)
    public void onBtnDoneToCreateProcedureClicked() {
//        if (procedureModels != null && isDataTrue()) {
//            if (procedureModels.size() != 0) {
//
//                for (ProcedureModel procedureModel : procedureModels) {
//                    weightModels.add(new WeightModel(procedureModel.getId(), procedureModel.getWeight() * (1 - ((
//                            (double) seekWeight.getProgress()) / 100))));
//                }
//                weightModels.add(new WeightModel(procedureModel.getId(), ((double) seekWeight.getProgress())/100));
//                updateProcedureRequest.setWeights(weightModels);
//                updateProcedure();
//            } else {
//                updateProcedureRequest.setWeight(1);
//                updateProcedure();
//            }
//        } else if (procedureModels == null) {
//            SnackbarUtil.showSnackbar(getWindow().getDecorView(), "获取信息中，请稍后再试");
//            getProcedure();
//        }
        if (isDataTrue()){
            updateProcedure();
        }
    }

}
