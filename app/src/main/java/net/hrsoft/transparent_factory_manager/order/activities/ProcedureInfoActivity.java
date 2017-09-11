package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.adapter.ProcedureDataAdapter;
import net.hrsoft.transparent_factory_manager.order.models.GetProcedureDataResponse;
import net.hrsoft.transparent_factory_manager.order.models.ProcedureDataModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/4 16:30.
 * email caiheng@hrsoft.net
 */

public class ProcedureInfoActivity extends ToolBarActivity {

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
    @BindView(R.id.swipe_procedure_info)
    SwipeRefreshLayout swipeProcedureInfo;

    private ArrayList<ProcedureDataModel> procedureDataModels;
    private ProcedureModel procedureModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_procedure_info;
    }

    @Override
    protected void initVariable() {
        procedureDataModels = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        procedureModel = (ProcedureModel) bundle.getSerializable(Config.PROCEDURE);
    }

    @Override
    protected void initView() {
        setActivityTitle("工序详情");
        setupToolbar();
        bindView();
        initSwipe();
    }

    @Override
    protected void loadData() {
        getProcedureData();
        getProcedureInfo();
    }

    private void getProcedureData() {
        RestClient.getService().getProcedureData(procedureModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureDataResponse>>() {
            @Override
            public void onDataResponse
                    (Call<APIResponse<GetProcedureDataResponse>> call,
                     Response<APIResponse<GetProcedureDataResponse>> response) {
                procedureDataModels
                        = response.body().getData().getLogs();
                ProcedureDataAdapter adapter = new ProcedureDataAdapter(ProcedureInfoActivity.this, procedureDataModels);
                recProcedureData.setAdapter(adapter);
                recProcedureData.setNestedScrollingEnabled(false);
                recProcedureData.setLayoutManager(new LinearLayoutManager(ProcedureInfoActivity.this, LinearLayoutManager
                                .VERTICAL,false));
            }

            @Override
            public void
            onDataFailure
                    (Call<APIResponse<GetProcedureDataResponse>> call, Throwable t) {
                ToastUtil.showToast
                        ("网络连接失败，请稍后再试");
            }

            @Override
            public void
            dismissDialog() {
                if (swipeProcedureInfo.isRefreshing()){
                    swipeProcedureInfo.setRefreshing(false);
                }
            }
        });
    }

    private void setupToolbar() {
        getToolbar().inflateMenu(R.menu.base_toolbar_menu);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_edit) {
                    Intent intent = new Intent(ProcedureInfoActivity.this, UpdateProcedureActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Config.PROCEDURE, procedureModel);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void bindView() {
        txtEndTime.setText(procedureModel.getEndTime());
        txtStartTime.setText(procedureModel.getStartTime());
        txtLeaderName.setText(procedureModel.getWorkGroupName());
        txtProcedureName.setText(procedureModel.getName());
        txtTotalCount.setText(String.valueOf(procedureModel.getTotalCount()));
    }

    private void getProcedureInfo() {
        RestClient.getService().getProcedureInfo(procedureModel.getId()).enqueue(new DataCallback<APIResponse<ProcedureModel>>() {
            @Override
            public void onDataResponse(Call<APIResponse<ProcedureModel>> call, Response<APIResponse<ProcedureModel>>
                    response) {
                procedureModel = response.body().getData();
                bindView();
            }

            @Override
            public void onDataFailure(Call<APIResponse<ProcedureModel>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeProcedureInfo.isRefreshing()){
                    swipeProcedureInfo.setRefreshing(false);
                }
            }
        });
    }

    private void initSwipe(){
        swipeProcedureInfo.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeProcedureInfo.setRefreshing(true);
        swipeProcedureInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedureData();
                getProcedureInfo();
            }
        });
    }
}
