package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/8/31 14:49.
 * email caiheng@hrsoft.net
 */

public class CreateProcedureActivity extends ToolBarActivity {


    @BindView(R.id.edit_order_name)
    TextInputEditText editOrderName;
    @BindView(R.id.txt_procedure_start_time)
    TextView txtProcedureStartTime;
    @BindView(R.id.txt_procedure_end_time)
    TextView txtProcedureEndTime;
    @BindView(R.id.txt_group_name)
    TextView txtGroupName;
    @BindView(R.id.edit_add_on)
    EditText editAddOn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_procedure_info;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("新增工序");
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_procedure_start_time_select)
    public void onBtnProcedureStartTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureStartTime.setText(+year+"."+(month+1)+"."+dayOfMonth);
            }
        },TimeUtil.getNowYear(),TimeUtil.getNowMonth(),TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_procedure_end_time_select)
    public void onBtnProcedureEndTimeSelectClicked() {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtProcedureEndTime.setText(+year+"."+(month+1)+"."+dayOfMonth);
            }
        },TimeUtil.getNowYear(),TimeUtil.getNowMonth(),TimeUtil.getDayOfMonth()).show();
    }

    @OnClick(R.id.btn_order_selector)
    public void onBtnOrderSelectorClicked() {
        Intent intent = new Intent(this,SelectGroupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_done_to_create_procedure)
    public void onBtnDoneToCreateProcedureClicked() {
    }
}
