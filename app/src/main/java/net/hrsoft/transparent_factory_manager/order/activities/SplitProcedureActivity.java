package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.adapter.ProcedureAdapter;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/30 20:05.
 * email caiheng@hrsoft.net
 */

public class SplitProcedureActivity extends ToolBarActivity implements BaseRecyclerViewAdapter
        .OnItemClicked<ProcedureModel> {
    @BindView(R2.id.swipe_split_procedure)
    SwipeRefreshLayout swipeSplitProcedure;
    @BindView(R2.id.rec_procedure)
    RecyclerView recProcedure;
    private ArrayList<ProcedureModel> procedureModels;
    private OrderModel orderModel;

    @Override
    protected int getLayoutId() {
        return R2.layout.activity_split_procedure;
    }

    @Override
    protected void initVariable() {
        procedureModels = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        orderModel = (OrderModel) bundle.getSerializable(Config.ORDER);
    }

    @Override
    protected void initView() {
        setActivityTitle("工序拆分");
        getProcedure();
        initRecSwipe();
    }

    @Override
    protected void loadData() {

    }

    /**
     * 确认创建工序按钮点击事件
     */
    @OnClick(R2.id.btn_create_procedure)
    public void onViewClicked() {
        Intent intent = new Intent(this, CreateProcedureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.ORDER, orderModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initRecSwipe() {
        swipeSplitProcedure.setRefreshing(true);
        swipeSplitProcedure.setColorSchemeColors(getResources().getColor(R2.color.colorAccent));
        swipeSplitProcedure.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedure();
            }
        });
    }

    /**
     * 获取当前订单下的工序列表
     */
    private void getProcedure() {
        RestClient.getService().getOrderProcedure(orderModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call,
                                       Response<APIResponse<GetProcedureResponse>> response) {
                procedureModels = response.body().getData().getProcedures();
                ProcedureAdapter adapter = new ProcedureAdapter(SplitProcedureActivity.this, procedureModels,
                        orderModel);
                adapter.setHasHeader(true);
                recProcedure.setAdapter(adapter);
                recProcedure.setLayoutManager(new LinearLayoutManager(SplitProcedureActivity.this, LinearLayoutManager
                        .VERTICAL, false));
            }

            @Override
            public void onDataFailure(Call<APIResponse<GetProcedureResponse>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeSplitProcedure.isRefreshing()) {
                    swipeSplitProcedure.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onItemClicked(ProcedureModel procedureModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent(this,UpdateProcedureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.PROCEDURES,procedureModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
