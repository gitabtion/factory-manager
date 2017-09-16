package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.models.CreateOrderRequest;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.SubTimeStringUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/11 17:42.
 * email caiheng@hrsoft.net
 */

public class UpdateOrderActivity extends ToolBarActivity {
    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.edit_order_client_name)
    TextInputEditText editOrderClientName;
    @BindView(R.id.edit_total_count)
    TextInputEditText editTotalCount;
    @BindView(R.id.txt_order_start_time)
    TextView txtOrderStartTime;
    @BindView(R.id.txt_order_end_time)
    TextView txtOrderEndTime;
    @BindView(R.id.radio_in)
    RadioButton radioIn;
    @BindView(R.id.radio_out)
    RadioButton radioOut;
    @BindView(R.id.radio_group_type)
    RadioGroup radioGroupType;
    @BindView(R.id.edit_description)
    EditText editDescription;
    @BindView(R.id.btn_update_order_done)
    TextView btnDoneToCreateProcedure;

    private CreateOrderRequest createOrderRequest;
    private OrderModel orderModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initVariable() {
        createOrderRequest = new CreateOrderRequest();
        Bundle bundle = getIntent().getExtras();
        orderModel = (OrderModel) bundle.getSerializable(Config.ORDER);
    }

    @Override
    protected void initView() {
        btnDoneToCreateProcedure.setText("确认修改");
        setActivityTitle("修改订单信息");
        bindView();
    }

    @Override
    protected void loadData() {

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
            showError(editOrderName, "订单名称不能为空");
            flag = false;
        } else if (editTotalCount.getText().length() == 0) {
            showError(editTotalCount, "订单数量不可为空");
            flag = false;
        }
        return flag;
    }

    private void bindData() {
        createOrderRequest.setTotalCount(editTotalCount.getText().toString());
        createOrderRequest.setCustomerInfo(editOrderClientName.getText().toString().trim());
        createOrderRequest.setDescription(editDescription.getText().toString().trim());
        createOrderRequest.setEndTime(txtOrderEndTime.getText().toString().trim());
        createOrderRequest.setStartTime(txtOrderStartTime.getText().toString().trim());
        createOrderRequest.setTitle(editOrderName.getText().toString().trim());
        if (radioIn.isChecked()) {
            createOrderRequest.setType(0);
        } else {
            createOrderRequest.setType(1);
        }

    }

    private void bindView() {
        txtOrderEndTime.setText(SubTimeStringUtil.subTimeString(orderModel.getEndTime()));
        txtOrderStartTime.setText(SubTimeStringUtil.subTimeString(orderModel.getStartTime()));
        editDescription.setText(orderModel.getDescription());
        editOrderClientName.setText(orderModel.getCustomerInfo());
        editOrderName.setText(orderModel.getTitle());
        editTotalCount.setText(orderModel.getTotalCount());
        if (orderModel.getType() == 0) {
            radioIn.setChecked(true);
        } else {
            radioOut.setChecked(true);
        }
    }


    @OnClick(R.id.btn_order_start_time_select)
    public void onBtnOrderStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderStartTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_order_end_time_select)
    public void onBtnOrderEndTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderEndTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_update_order_done)
    public void onBtnDoneToCreateProcedureClicked() {
        if (isDataTrue()) {
            updateOrder();
        }
    }

    private void updateOrder() {
        progressDialog.setMessage("请稍候");
        progressDialog.show();
        bindData();
        RestClient.getService().updateOrder(orderModel.getId(), createOrderRequest).enqueue(new DataCallback<APIResponse>() {

            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("修改成功，请刷新页面");
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

}
