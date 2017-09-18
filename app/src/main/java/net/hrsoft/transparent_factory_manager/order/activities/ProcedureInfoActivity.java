package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
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
import net.hrsoft.transparent_factory_manager.utils.MPAndroidChartUtil;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;
import net.hrsoft.transparent_factory_manager.utils.SubTimeStringUtil;
import net.hrsoft.transparent_factory_manager.utils.TimeUtil;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
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
    @BindView(R.id.pie_time)
    PieChart pieTime;
    @BindView(R.id.rec_procedure_data)
    RecyclerView recProcedureData;
    @BindView(R.id.swipe_procedure_info)
    SwipeRefreshLayout swipeProcedureInfo;
    @BindView(R.id.line_procedure_data)
    LineChart lineProcedureData;

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


        float allPercent = ((float) procedureModel.getSuccessCount()) / ((float) procedureModel.getTotalCount
                ());
        MPAndroidChartUtil.setPieChart(pieDone, allPercent, "完成率");
        float timeData = ((float) (TimeUtil.getCurrentTimeStamp() - TimeUtil.setStringToStamp
                (procedureModel
                        .getStartTime()))) / ((float) (TimeUtil.setStringToStamp(procedureModel.getEndTime()) - TimeUtil
                .setStringToStamp
                        (procedureModel
                                .getStartTime())));
        MPAndroidChartUtil.setPieChart(pieTime, timeData, "时间进度");

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onStart() {
        getProcedureData();
        getProcedureInfo();
        super.onStart();
    }

    /**
     * 获取工序记录数据
     */
    private void getProcedureData() {
        RestClient.getService().getProcedureData(procedureModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureDataResponse>>() {
            @Override
            public void onDataResponse
                    (Call<APIResponse<GetProcedureDataResponse>> call,
                     Response<APIResponse<GetProcedureDataResponse>> response) {
                procedureDataModels
                        = response.body().getData().getLogs();
                ProcedureDataAdapter adapter = new ProcedureDataAdapter(ProcedureInfoActivity.this,
                        procedureDataModels);
                recProcedureData.setAdapter(adapter);
                recProcedureData.setNestedScrollingEnabled(false);
                recProcedureData.setLayoutManager(new LinearLayoutManager(ProcedureInfoActivity.this,
                        LinearLayoutManager
                        .VERTICAL, false));
                MPAndroidChartUtil.setLineChart(response.body().getData(),lineProcedureData);
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
                if (swipeProcedureInfo.isRefreshing()) {
                    swipeProcedureInfo.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 设置toolbar的menu
     */
    private void setupToolbar() {
        getToolbar().inflateMenu(R.menu.procedure_info_menu);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProcedureInfoActivity.this);
                        builder.setTitle("提示")
                                .setMessage("删除工序后将不可恢复，确认删除？")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteProcedure();
                                    }
                                }).show();

                        break;
                    case R.id.action_edit:
                        Intent intent = new Intent(ProcedureInfoActivity.this, UpdateProcedureActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Config.PROCEDURE, procedureModel);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    default:
                        logicError();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 显示toolbar的menu
     *
     * @param menu menu
     * @return menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.procedure_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 绑定视图
     */
    private void bindView() {
        txtEndTime.setText(SubTimeStringUtil.subTimeString(procedureModel.getEndTime()));
        txtStartTime.setText(SubTimeStringUtil.subTimeString(procedureModel.getStartTime()));
        txtLeaderName.setText(procedureModel.getWorkGroupName());
        txtProcedureName.setText(procedureModel.getName());
        txtTotalCount.setText(String.valueOf(procedureModel.getTotalCount()));
    }

    /**
     * 获取工序信息
     */
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
                if (swipeProcedureInfo.isRefreshing()) {
                    swipeProcedureInfo.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initSwipe() {
        swipeProcedureInfo.setColorSchemeColors(Color.parseColor("#FF3955"));
        swipeProcedureInfo.setRefreshing(true);
        swipeProcedureInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedureData();
                getProcedureInfo();
            }
        });
    }


    private void deleteProcedure() {
        progressDialog.setMessage("请稍候");
        progressDialog.show();

        RestClient.getService().deleteProcedure(procedureModel.getId()).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast("删除成功");
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
