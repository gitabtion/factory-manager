package net.hrsoft.transparent_factory_manager.order.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.common.constants.CacheKey;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.activities.OrderInfoActivity;
import net.hrsoft.transparent_factory_manager.order.adapter.CurrentOrderAdapter;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderContentAdapter;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderResponse;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/27 10:21.
 * email caiheng@hrsoft.net
 */

public class OrderContentFragment extends BaseFragment implements BaseRecyclerViewAdapter.OnItemClicked<OrderModel>{
    public static final String ORDER = "order";
    public static final String CURRENT_ORDER = "currentOrder";

    @BindView(R.id.rec_order)
    RecyclerView orderRec;
    @BindView(R.id.swipe_order_content)
    SwipeRefreshLayout swipeOrderContent;

    private ArrayList<OrderModel> orderModels;
    private ArrayList<CurrentOrderModel> currentOrderModels;

    private int type;
    private int page;

    public final static int PROCESSING = 0;
    public final static int TO_BE_START = 1;
    public final static int ENDED = 2;

    private static final int REFRESH_STATUS = 0;
    private static final int ADD_DATA_STATUS = 1;


    public OrderContentFragment(int type) {
        super();
        this.type = type;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_content;
    }

    @Override
    protected void initVariable() {
        orderModels = new ArrayList<>();
        currentOrderModels = new ArrayList<>();
        page = 1;
    }

    @Override
    protected void initView() {
        initSwipe();
        switch (type) {
            case OrderContentFragment.PROCESSING:
                getCurrentList(page, 20, REFRESH_STATUS);
                break;
            case OrderContentFragment.TO_BE_START:
                getUnstartList(page, 20, REFRESH_STATUS);
                break;
            case OrderContentFragment.ENDED:
                getEndedList(page, 20, REFRESH_STATUS);
                break;
            default:
                break;
        }
    }

    @Override
    protected void loadData() {
    }

    /**
     * 获取未开始订单
     *
     * @param page page
     * @param size size
     */
    private void getUnstartList(int page, int size, final int status) {
        RestClient.getService().getUnstarOrders(page, size).enqueue(new DataCallback<APIResponse<OrderResponse<OrderModel[]>>>() {


            @Override
            public void onDataResponse(Call<APIResponse<OrderResponse<OrderModel[]>>> call,
                                       Response<APIResponse<OrderResponse<OrderModel[]>>> response) {
                if (status == 0) {
                    orderModels.clear();
                }
                Collections.addAll(orderModels, response.body().getData().getOrders());

                if (orderRec.getAdapter() == null) {
                    OrderContentAdapter adapter = new OrderContentAdapter(getContext(), orderModels);
                    adapter.setOnItemClickedListener(OrderContentFragment.this);
                    orderRec.setAdapter(adapter);
                    orderRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                            false));
                } else {
                    orderRec.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<OrderResponse<OrderModel[]>>> call, Throwable t) {

                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeOrderContent.isRefreshing()) {
                    swipeOrderContent.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 获取已结束的订单
     *
     * @param page page
     * @param size size
     */
    private void getEndedList(int page, int size, final int status) {
        RestClient.getService().getPastOrders(page, size).enqueue(new DataCallback<APIResponse<OrderResponse<OrderModel[]>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<OrderResponse<OrderModel[]>>> call,
                                       Response<APIResponse<OrderResponse<OrderModel[]>>> response) {
                if (status == 0) {
                    orderModels.clear();
                }

                Collections.addAll(orderModels, response.body().getData().getOrders());
                if (orderRec.getAdapter() == null) {
                    OrderContentAdapter adapter = new OrderContentAdapter(getContext(), orderModels);
                    adapter.setOnItemClickedListener(OrderContentFragment.this);
                    orderRec.setAdapter(adapter);
                    orderRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                            false));
                } else {
                    orderRec.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onDataFailure(Call<APIResponse<OrderResponse<OrderModel[]>>> call, Throwable t) {

                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeOrderContent.isRefreshing()) {
                    swipeOrderContent.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 获取正在进行中的订单
     *
     * @param page page
     * @param size size
     */
    private void getCurrentList(int page, int size, final int status) {
        RestClient.getService().getCurrentOrders(page, size).enqueue(new DataCallback<APIResponse<OrderResponse<CurrentOrderModel[]>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> call,
                                       Response<APIResponse<OrderResponse<CurrentOrderModel[]>>> response) {
                if (status == 0) {
                    currentOrderModels.clear();
                }
                Collections.addAll(currentOrderModels, response.body().getData().getOrders());


                if (orderRec.getAdapter() == null) {
                    CurrentOrderAdapter adapter = new CurrentOrderAdapter(getContext(), currentOrderModels);
                    adapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<CurrentOrderModel>() {
                        @Override
                        public void onItemClicked(CurrentOrderModel orderModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                            Intent intent = new Intent(getActivity(),OrderInfoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(ORDER,orderModel);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    orderRec.setAdapter(adapter);
                    orderRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                            false));
                } else {
                    orderRec.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onDataFailure(Call<APIResponse<OrderResponse<CurrentOrderModel[]>>> call, Throwable t) {

                SnackbarUtil.showSnackbar(getView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeOrderContent.isRefreshing()) {
                    swipeOrderContent.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 初始化下拉刷新控件
     */
    private void initSwipe() {
        swipeOrderContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeOrderContent.setRefreshing(true);
                switch (type) {
                    case OrderContentFragment.PROCESSING:
                        getCurrentList(page, 20, REFRESH_STATUS);
                        break;
                    case OrderContentFragment.TO_BE_START:
                        getUnstartList(page, 20, REFRESH_STATUS);
                        break;
                    case OrderContentFragment.ENDED:
                        getEndedList(page, 20, REFRESH_STATUS);
                        break;
                    default:
                        break;
                }
            }
        });
        swipeOrderContent.setRefreshing(true);
        swipeOrderContent.setColorSchemeColors(getResources().getColor(R.color.colorAccent));


    }

    @Override
    public void onItemClicked(OrderModel orderModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ORDER,orderModel);
        intent.putExtras(bundle);
        intent.setClass(getContext(), OrderInfoActivity.class);
        startActivity(intent);
    }
}
