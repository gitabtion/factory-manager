package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderInfoAdapter;
import net.hrsoft.transparent_factory_manager.order.fragments.OrderContentFragment;
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
 * @since 17/9/9 16:25.
 * email caiheng@hrsoft.net
 */

public class OrderInfoActivity extends ToolBarActivity implements BaseRecyclerViewAdapter
        .OnItemClicked<ProcedureModel> {

    @BindView(R.id.swipe_order_info)
    SwipeRefreshLayout swipeOrderInfo;
    private ArrayList<ProcedureModel> procedureModels;
    private OrderModel orderModel;

    @BindView(R.id.rec_procedure)
    RecyclerView recProcedure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getIntent().getExtras();
        orderModel = (OrderModel) bundle.getSerializable(OrderContentFragment.ORDER);
        procedureModels = new ArrayList<>();
    }

    @Override
    protected void initView() {
        setActivityTitle("订单详情");
        getProcedure(orderModel);
        setupSwipe();
        setupToolbar();
    }

    @Override
    protected void loadData() {

    }

    /**
     * 获取订单下的全部工序
     *
     * @param orderModel 订单
     */
    private void getProcedure(final OrderModel orderModel) {
        RestClient.getService().getOrderProcedure(orderModel.getId()).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse
                    (Call<APIResponse<GetProcedureResponse>> call, Response<APIResponse<GetProcedureResponse>>
                            response) {
                procedureModels.clear();
                procedureModels = response.body().getData().getProcedures();
                OrderInfoAdapter adapter = new
                        OrderInfoAdapter(OrderInfoActivity.this, procedureModels, orderModel);
                adapter.setHasHeader(true);
                adapter.setOnItemClickedListener(OrderInfoActivity.this);
                recProcedure.setAdapter(adapter);
                recProcedure.setLayoutManager(new LinearLayoutManager(OrderInfoActivity.this, LinearLayoutManager
                        .VERTICAL, false));
            }

            @Override
            public void onDataFailure
                    (Call<APIResponse<GetProcedureResponse>> call, Throwable t) {

                SnackbarUtil.showSnackbar
                        (getWindow().getDecorView(), "网络连接失败，请稍后失败");
            }

            @Override
            public void dismissDialog() {
                if (swipeOrderInfo.isRefreshing()) {
                    swipeOrderInfo.setRefreshing(false);
                }
            }
        });
    }


    @Override
    public void onItemClicked(ProcedureModel procedureModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.PROCEDURE, procedureModel);
        intent.putExtras(bundle);
        intent.setClass(this, ProcedureInfoActivity.class);
        startActivity(intent);
    }

    private void setupSwipe() {
        swipeOrderInfo.setRefreshing(true);
        swipeOrderInfo.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeOrderInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedure(orderModel);
            }
        });
    }

    private void setupToolbar() {
        getToolbar().inflateMenu(R.menu.base_toolbar_menu);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_edit) {
                    Intent intent = new Intent();
                    intent.setClass(OrderInfoActivity.this, UpdateOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Config.ORDER, orderModel);
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


    @OnClick(R.id.fab_add_procedure)
    public void onViewClicked() {
        Intent intent = new Intent(this,CreateProcedureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.ORDER,orderModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
