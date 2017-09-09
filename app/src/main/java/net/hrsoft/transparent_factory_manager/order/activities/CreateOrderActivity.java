package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.sql.Time;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/8/29 18:04.
 * email caiheng@hrsoft.net
 */

public class CreateOrderActivity extends ToolBarActivity {
    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.edit_order_client_name)
    TextInputEditText editOrderClientName;
    @BindView(R.id.txt_order_start_time)
    TextView txtOrderStartTime;
    @BindView(R.id.txt_order_end_time)
    TextView txtOrderEndTime;
    @BindView(R.id.edit_add_on)
    EditText editAddOn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("创建订单");
    }

    @Override
    protected void loadData() {
    }

    @OnClick(R.id.btn_order_start_time_select)
    public void onBtnOrderStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderStartTime.setText(+year+"."+(month+1)+"."+dayOfMonth);
                SnackbarUtil.showSnackbar(getWindow().getDecorView(),"选择了"+txtOrderStartTime.getText());
            }
        },TimeUtil.getNowYear(),TimeUtil.getNowMonth(),TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_order_end_time_select)
    public void onBtnOrderEndTimeSelectClicked() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtOrderEndTime.setText(year+"."+(month+1)+"."+dayOfMonth);
            }
        },TimeUtil.getNowYear(),TimeUtil.getNowMonth(),TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_done_to_create_procedure)
    public void onBtnDoneToCreateProcedureClicked() {
        Intent intent = new Intent(this,SplitProcedureActivity.class);
        startActivity(intent);
    }
}
