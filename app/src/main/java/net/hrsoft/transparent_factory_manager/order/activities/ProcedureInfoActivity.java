package net.hrsoft.transparent_factory_manager.order.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.order.adapter.ProcedureAdapter;
import net.hrsoft.transparent_factory_manager.order.adapter.ProcedureDataAdapter;
import net.hrsoft.transparent_factory_manager.order.models.ProcedureDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/9/4 16:30.
 * email caiheng@hrsoft.net
 */

public class ProcedureInfoActivity extends ToolBarActivity {
    private ArrayList<ProcedureDataModel> procedureDataModels;

    @BindView(R.id.txt_total_count)
    TextView txtTotalCount;
    @BindView(R.id.txt_leader_name)
    TextView txtLeaderName;
    @BindView(R.id.txt_procedure_name)
    TextView txtProcedureName;
    @BindView(R.id.txt_start_time)
    TextView txtStartTime;
    @BindView(R.id.txt_end_time)
    TextView txtEndTime;
    @BindView(R.id.pie_done)
    PieChart pieDone;
    @BindView(R.id.pie_success)
    PieChart pieSuccess;
    @BindView(R.id.rec_procedure_data)
    RecyclerView recProcedureData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_procedure_info;
    }

    @Override
    protected void initVariable() {
        procedureDataModels = new ArrayList<>();
    }

    @Override
    protected void initView() {
        setActivityTitle("工序详情");
        for (int i=0;i<20;i++){
            procedureDataModels.add(new ProcedureDataModel());
        }

        ProcedureDataAdapter adapter = new ProcedureDataAdapter(this,procedureDataModels);
        recProcedureData.setAdapter(adapter);
        recProcedureData.setNestedScrollingEnabled(false);
        recProcedureData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void loadData() {

    }

}
