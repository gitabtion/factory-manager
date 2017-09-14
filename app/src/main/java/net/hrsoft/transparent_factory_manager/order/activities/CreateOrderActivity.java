package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import net.hrsoft.transparent_factory_manager.order.models.CreateOrderResponse;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/29 18:04.
 * email caiheng@hrsoft.net
 */

public class CreateOrderActivity extends ToolBarActivity {


    @BindView(R.id.edit_total_count)
    TextInputEditText editTotalCount;

    @BindView(R.id.radio_in)
    RadioButton radioIn;
    @BindView(R.id.radio_out)
    RadioButton radioOut;
    @BindView(R.id.radio_group_type)
    RadioGroup radioGroupType;

    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.edit_order_client_name)
    TextInputEditText editOrderClientName;
    @BindView(R.id.txt_order_start_time)
    TextView txtOrderStartTime;
    @BindView(R.id.txt_order_end_time)
    TextView txtOrderEndTime;
    @BindView(R.id.edit_description)
    EditText editAddOn;
    @BindView(R.id.edit_order_number)
    TextInputEditText editOrderNumber;

    private CreateOrderRequest createOrderRequest;
    private OrderModel orderModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initVariable() {
        createOrderRequest = new CreateOrderRequest();
        orderModel = new OrderModel();
    }

    @Override
    protected void initView() {
        setActivityTitle("创建订单");
        radioIn.setChecked(true);
    }

    @Override
    protected void loadData() {
    }

    /**
     * 选择订单开始时间按钮点击事件
     */
    @OnClick(R.id.btn_order_start_time_select)
    public void onBtnOrderStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderStartTime.setText(+year + "-" + (month + 1) + "-" + dayOfMonth);
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "选择了" + txtOrderStartTime.getText());
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    /**
     * 结束订单开始时间按钮点击事件
     */
    @OnClick(R.id.btn_order_end_time_select)
    public void onBtnOrderEndTimeSelectClicked() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderEndTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, TimeUtil.getNowYear(), TimeUtil.getNowMonth(), TimeUtil.getDayOfMonth()).show();
    }

    /**
     * 确认按钮点击事件
     */
    @OnClick(R.id.btn_update_order_done)
    public void onBtnDoneToCreateProcedureClicked() {
        if (isDataTrue()) {
            createOrder();
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
        if (editOrderName.getText().length() == 0) {
            showError(editOrderName, "订单名称不能为空");
            flag = false;
        } else if (editOrderNumber.getText().length() == 0) {
            showError(editOrderNumber, "订单号不能为空");
            flag = false;
        } else if (editTotalCount.getText().length() == 0) {
            showError(editTotalCount, "订单数量不能为空");
            flag = false;
        }
        return flag;
    }

    private void bindData() {
        createOrderRequest.setOrderCode(editOrderNumber.getText().toString().trim());
        createOrderRequest.setTotalCount(editTotalCount.getText().toString().trim());
        createOrderRequest.setCustomerInfo(editOrderClientName.getText().toString().trim());
        createOrderRequest.setDescription(editAddOn.getText().toString().trim());
        createOrderRequest.setEndTime(txtOrderEndTime.getText().toString().trim());
        createOrderRequest.setStartTime(txtOrderStartTime.getText().toString().trim());
        createOrderRequest.setTitle(editOrderName.getText().toString().trim());
        if (radioIn.isChecked()) {
            createOrderRequest.setType(0);
        } else {
            createOrderRequest.setType(1);
        }

        orderModel.setCustomerInfo(createOrderRequest.getCustomerInfo());
        orderModel.setDescription(createOrderRequest.getDescription());
        orderModel.setEndTime(createOrderRequest.getEndTime());
        orderModel.setStartTime(createOrderRequest.getStartTime());
        orderModel.setTitle(createOrderRequest.getTitle());
        orderModel.setType(createOrderRequest.getType());
        orderModel.setTotalCount(createOrderRequest.getTotalCount());
    }

    private void createOrder() {
        bindData();
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        RestClient.getService().createOrder(createOrderRequest).enqueue(new DataCallback<APIResponse<CreateOrderResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<CreateOrderResponse>> call,
                                       Response<APIResponse<CreateOrderResponse>> response) {
                orderModel.setId(response.body().getData().getOrderId());
                orderModel.setOrderCode(response.body().getData().getOrderCode());
                Intent intent = new Intent(CreateOrderActivity.this, OrderInfoActivity   .class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Config.ORDER, orderModel);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }

            @Override
            public void onDataFailure(Call<APIResponse<CreateOrderResponse>> call, Throwable t) {
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
