package net.hrsoft.transparent_factory_manager.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.common.Config;
import net.hrsoft.transparent_factory_manager.home.adapters.HomeProcedureAdapter;
import net.hrsoft.transparent_factory_manager.home.adapters.HomeSpinnerAdapter;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.activities.ProcedureInfoActivity;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderResponse;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/26 21:13.
 * email caiheng@hrsoft.net
 */

public class HomeFragment extends BaseFragment implements BaseRecyclerViewAdapter.OnItemClicked<ProcedureModel>{
    private ArrayList<ProcedureModel> procedureModels;
    private ArrayList<CurrentOrderModel> orderModels;

    private int position;


    @BindView(R2.id.swipe_home)
    SwipeRefreshLayout swipeHome;
    @BindView(R2.id.rec_home_procedure)
    RecyclerView recHomeProcedure;
    @BindView(R2.id.spinner_home_order_list)
    Spinner spinnerHomeOrderList;
    @BindView(R2.id.empty_view)
     View emptyView;

    @Override
    protected int getLayoutId() {
        return R2.layout.fragment_home;
    }

    @Override
    protected void initVariable() {
        procedureModels = new ArrayList<>();
        orderModels = new ArrayList<>();
    }

    @Override
    protected void initView() {
        if (orderModels.size()==0){
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
        }
        getCurrentOrderList(1,100);
        swipeHome.setRefreshing(true);
        swipeHome.setColorSchemeColors(getResources().getColor(R2.color.colorAccent));
    }

    @Override
    protected void loadData() {

    }


    /**
     * 获取正在进行中的订单列表
     * @param page 页
     * @param size 一页的数据量
     */
    private void getCurrentOrderList(final int page, final int size){
        RestClient.getService().getCurrentOrders(page,size).enqueue(new DataCallback<APIResponse<OrderResponse<CurrentOrderModel[]>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> call, Response<APIResponse<OrderResponse<CurrentOrderModel[]>>> response) {
                Collections.addAll(orderModels,response.body().getData().getOrders());
                HomeSpinnerAdapter spinnerAdapter= new HomeSpinnerAdapter(getContext(),orderModels);
                spinnerHomeOrderList.setAdapter(spinnerAdapter);
                if (response.body().getData().getOrders().length==20){
                    getCurrentOrderList(page+1,size);
                }
                if(page==1&&orderModels.size()!=0){
                    HomeProcedureAdapter adapter = new HomeProcedureAdapter(getContext(), procedureModels,orderModels.get(0));
                    adapter.setHasHeader(true);
                    recHomeProcedure.setAdapter(adapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recHomeProcedure.setLayoutManager(layoutManager);
                    getProcedure(orderModels.get(0));
                    setOnSpinnerItemClick();
                    initSwipeHome();
                }
                if (orderModels.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getView(),"网络连接失败，请稍后再试");
                if (orderModels.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void dismissDialog() {
                if (swipeHome.isRefreshing()){
                    swipeHome.setRefreshing(false);
                }
            }
        });

    }

    /**
     * 获取订单下的全部工序
     * @param orderModel 订单
     */
    private void getProcedure(final CurrentOrderModel orderModel){
        RestClient.getService().getOrderProcedure(orderModel.getId()).enqueue(new
                                                                               DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call, Response<APIResponse<GetProcedureResponse>> response) {
                procedureModels.clear();
                procedureModels = response.body().getData().getProcedures();
                HomeProcedureAdapter adapter = new HomeProcedureAdapter(getContext(),procedureModels,orderModel);
                adapter.setOnItemClickedListener(HomeFragment.this);
                adapter.setHasHeader(true);
                recHomeProcedure.setAdapter(adapter);

            }

            @Override
            public void onDataFailure(Call<APIResponse<GetProcedureResponse>> call, Throwable t) {

                SnackbarUtil.showSnackbar(getView(),"网络连接失败，请稍后失败");
            }

            @Override
            public void dismissDialog() {
                if (swipeHome.isRefreshing()){
                    swipeHome.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 设置spinner的item的点击事件
     */
    private void setOnSpinnerItemClick(){
        spinnerHomeOrderList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                swipeHome.setRefreshing(true);
                getProcedure(orderModels.get(position));
                HomeFragment.this.position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initSwipeHome(){
        swipeHome.setRefreshing(true);
        swipeHome.setColorSchemeColors(getResources().getColor(R2.color.colorAccent));
        swipeHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getProcedure(orderModels.get(position));
            }
        });
    }

    @Override
    public void onItemClicked(ProcedureModel procedureModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent(getActivity(), ProcedureInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.PROCEDURE,procedureModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
