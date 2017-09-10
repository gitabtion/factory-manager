package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/30 20:05.
 * email caiheng@hrsoft.net
 */

public class SplitProcedureActivity extends ToolBarActivity implements BaseRecyclerViewAdapter
        .OnItemClicked<ProcedureModel>{
    private OrderModel orderModel;
    @BindView(R.id.swipe_split_procedure)
    SwipeRefreshLayout swipeSplitProcedure;
    private ArrayList<ProcedureModel> procedureModels;
    public static final String PROCEDURES = "procedures";

    @BindView(R.id.rec_procedure)
    RecyclerView recProcedure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_split_procedure;
    }

    @Override
    protected void initVariable() {
        procedureModels = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        orderModel = (OrderModel) bundle.getSerializable(CreateOrderActivity.CREATE_ORDER);
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


    @OnClick(R.id.btn_create_procedure)
    public void onViewClicked() {
        Intent intent = new Intent(this, CreateProcedureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CreateOrderActivity.CREATE_ORDER,orderModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initRecSwipe() {
        swipeSplitProcedure.setRefreshing(true);
        swipeSplitProcedure.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeSplitProcedure.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedure();
            }
        });
    }

    private void getProcedure(){
        RestClient.getService().getOrderProcedure(orderModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call, Response<APIResponse<GetProcedureResponse>> response) {
                procedureModels = response.body().getData().getProcedures();
                ProcedureAdapter adapter = new ProcedureAdapter(SplitProcedureActivity.this, procedureModels,orderModel);
                adapter.setHasHeader(true);
                recProcedure.setAdapter(adapter);
                recProcedure.setLayoutManager(new LinearLayoutManager(SplitProcedureActivity.this, LinearLayoutManager
                        .VERTICAL, false));
            }

            @Override
            public void onDataFailure(Call<APIResponse<GetProcedureResponse>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(),"网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeSplitProcedure.isRefreshing()){
                    swipeSplitProcedure.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onItemClicked(ProcedureModel procedureModel, BaseRecyclerViewAdapter.ViewHolder holder) {

    }

}
