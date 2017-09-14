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
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.order.models.UpdateProcedureRequest;
import net.hrsoft.transparent_factory_manager.order.models.WeightModel;
import net.hrsoft.transparent_factory_manager.utils.RxBus;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/31 14:49.
 * email caiheng@hrsoft.net
 */

public class CreateProcedureActivity extends ToolBarActivity {
    @BindView(R.id.edit_procedure_total_count)
    TextInputEditText editTotalCount;
    @BindView(R.id.edit_standard)
    TextInputEditText editStandard;
    @BindView(R.id.seek_weight)
    AppCompatSeekBar seekWeight;
    @BindView(R.id.txt_procedure_weight)
    TextView txtProcedureWeight;

    private CompositeDisposable compositeDisposable;
    private GroupModel groupModel;
    private UpdateProcedureRequest updateProcedureRequest;
    private OrderModel orderModel;
    private ArrayList<ProcedureModel> procedureModels;
//    private ArrayList<WeightModel> weightModels;

    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.txt_procedure_start_time)
    TextView txtProcedureStartTime;
    @BindView(R.id.txt_procedure_end_time)
    TextView txtProcedureEndTime;
    @BindView(R.id.txt_group_name)
    TextView txtGroupName;
    @BindView(R.id.edit_description)
    EditText editDescription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_procedure_info;
    }

    @Override
    protected void initVariable() {
        updateProcedureRequest = new UpdateProcedureRequest();
        Bundle bundle = getIntent().getExtras();
        orderModel = (OrderModel) bundle.getSerializable(Config.ORDER);
//        weightModels = new ArrayList<>();
//        getProcedure();
    }

    @Override
    protected void initView() {
        setActivityTitle("新增工序");
//        initSeekBar();
    }

    @Override
    protected void loadData() {
//        getProcedure();
    }


    /**
     * 选择工序开始时间按钮点击事件
     */
    @OnClick(R.id.btn_procedure_start_time_select)
    public void onBtnProcedureStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureStartTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    /**
     * 选择工序结束时间按钮点击事件
     */
    @OnClick(R.id.btn_procedure_end_time_select)
    public void onBtnProcedureEndTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureEndTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }


    /**
     * 选择班组按钮点击事件
     */
    @OnClick(R.id.btn_group_selector)
    public void onBtnOrderSelectorClicked() {
        compositeDisposable = new CompositeDisposable();
        RxBus.getInstance().toObservable(GroupModel.class).subscribe(new Observer<GroupModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(GroupModel groupModel) {
                CreateProcedureActivity.this.groupModel = groupModel;
                txtGroupName.setText(groupModel.getName());
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
        Intent intent = new Intent(this,SelectGroupActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_update_done)
    public void onBtnDoneToCreateProcedureClicked() {
//        if (procedureModels != null && isDataTrue()) {
//            if (procedureModels.size() != 0) {
//
//                for (ProcedureModel procedureModel : procedureModels) {
//                    weightModels.add(new WeightModel(procedureModel.getId(), procedureModel.getWeight() * (1 - (((double) seekWeight.getProgress()) / 100))));
//                }
//                updateProcedureRequest.setWeights(weightModels);
//                createProcedure();
//            } else {
//                updateProcedureRequest.setWeights(weightModels);
//                updateProcedureRequest.setWeight(1);
//                createProcedure();
//            }
//        } else if (procedureModels == null) {
//            updateProcedureRequest.setWeights(weightModels);
//            SnackbarUtil.showSnackbar(getWindow().getDecorView(), "获取信息中，请稍后再试");
//            getProcedure();
//        }

        if (isDataTrue()){
            createProcedure();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    private void bindData() {
        updateProcedureRequest.setName(editOrderName.getText().toString().trim());
        updateProcedureRequest.setDescription(editDescription.getText().toString().trim());
        updateProcedureRequest.setStartTime(txtProcedureStartTime.getText().toString().trim());
        updateProcedureRequest.setEndTime(txtProcedureEndTime.getText().toString().trim());
        updateProcedureRequest.setStandard(editStandard.getText().toString().trim());
        updateProcedureRequest.setTotalCount(Integer.valueOf(editTotalCount.getText().toString().trim()));
        updateProcedureRequest.setOrderId(orderModel.getId());
        updateProcedureRequest.setWorkGroupId(groupModel.getId());
//        if (procedureModels!=null&&procedureModels.size()!=0){
//            updateProcedureRequest.setWeight(((double) seekWeight.getProgress())/100);
//        }else if (procedureModels!=null){
//            updateProcedureRequest.setWeight(1);
//        }
    }

    /**
     * 创建工序网络请求
     */
    private void createProcedure() {
        bindData();
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        RestClient.getService().createProcedure(updateProcedureRequest).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("添加成功，请刷新页面");
                finish();
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {
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
        if (editOrderName.getText().length() == 0) {
            showError(editOrderName, "工序名称不能为空");
            flag = false;
        } else if (editTotalCount.getText().length() == 0) {
            showError(editTotalCount, "数量不可为空");
            flag = false;
        } else if (editStandard.getText().length() == 0) {
            showError(editStandard, "单位不可为空");
            flag = false;
        }else if (groupModel==null){
            SnackbarUtil.showSnackbar(getWindow().getDecorView(),"请选择选择班组");
            flag = false;
        }
        return flag;
    }

    /**
     * 权重设置依赖
     */
    private void getProcedure() {
        progressDialog.setMessage("请稍后");
        progressDialog.show();

        RestClient.getService().getOrderProcedure(orderModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call, Response<APIResponse<GetProcedureResponse>> response) {
                procedureModels = response.body().getData().getProcedures();
//                updateProcedureRequest.setWeight(((double) seekWeight.getProgress())/100);
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
     * 应产品要求，取消权重自由选择
     */
    private void initSeekBar() {
        seekWeight.setProgress(100);
        txtProcedureWeight.setText("100%");
        seekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtProcedureWeight.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (procedureModels!=null && procedureModels.size()==0){
                    seekBar.setProgress(100);

                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (procedureModels!=null && procedureModels.size()==0){
                    seekBar.setProgress(100);
                    SnackbarUtil.showSnackbar(getWindow().getDecorView(),"第一道工序权重必须为100%");
                }
            }
        });
    }

}
