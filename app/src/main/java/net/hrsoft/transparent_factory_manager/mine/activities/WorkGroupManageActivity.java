package net.hrsoft.transparent_factory_manager.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.mine.adapters.LeadersAdapter;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/8 21:12.
 * email caiheng@hrsoft.net
 */

public class WorkGroupManageActivity extends ToolBarActivity implements BaseRecyclerViewAdapter
        .OnItemClicked<UserModel> {
    public static final String LEADER = "leader";

    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.rec_work_group)
    RecyclerView recWorkGroup;
    @BindView(R.id.swipe_work_group)
    SwipeRefreshLayout swipeWorkGroup;
    ArrayList<GroupModel> groupModels;
    ArrayList<UserModel> userModels;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_group_manage;
    }

    @Override
    protected void initVariable() {
        groupModels = new ArrayList<>();
        userModels = new ArrayList<>();

    }

    @Override
    protected void initView() {
        setActivityTitle("员工管理");
        setupSwipe();
        getLeaderList();
        if (userModels.size()==0){
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadData() {

    }

    /**
     * 初始化下拉刷新控件
     */
    private void setupSwipe() {
        swipeWorkGroup.setRefreshing(true);
        swipeWorkGroup.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeWorkGroup.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLeaderList();

            }
        });
    }


    private void getLeaderList() {
        RestClient.getService().getLeaders().enqueue(new DataCallback<APIResponse<UserModel[]>>() {
            @Override
            public void onDataResponse(Call<APIResponse<UserModel[]>> call, Response<APIResponse<UserModel[]>>
                    response) {
                userModels.clear();
                Collections.addAll(userModels, response.body().getData());
                LeadersAdapter adapter = new LeadersAdapter(WorkGroupManageActivity.this, userModels);
                adapter.setOnItemClickedListener(WorkGroupManageActivity.this);
                recWorkGroup.setAdapter(adapter);
                recWorkGroup.setLayoutManager(new LinearLayoutManager(WorkGroupManageActivity.this,
                        LinearLayoutManager.VERTICAL, false));
                if (userModels.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<UserModel[]>> call, Throwable t) {
                if (userModels.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setVisibility(View.GONE);
                }
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍候再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeWorkGroup.isRefreshing()) {
                    swipeWorkGroup.setRefreshing(false);
                }
            }
        });
    }


    @Override
    public void onItemClicked(UserModel userModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent(this, UpdateUserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(LEADER, userModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @OnClick(R.id.fat_add_leader)
    public void onViewClicked() {
        Intent intent = new Intent(this,AddLeaderActivity.class);
        startActivity(intent);
    }
}
