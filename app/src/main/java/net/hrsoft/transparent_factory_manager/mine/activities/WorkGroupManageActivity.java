package net.hrsoft.transparent_factory_manager.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import net.hrsoft.transparent_factory_manager.R2;
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

    @BindView(R2.id.empty_view)
    View emptyView;
    @BindView(R2.id.rec_work_group)
    RecyclerView recWorkGroup;
    @BindView(R2.id.swipe_work_group)
    SwipeRefreshLayout swipeWorkGroup;
    ArrayList<GroupModel> groupModels;
    ArrayList<UserModel> userModels;


    @Override
    protected int getLayoutId() {
        return R2.layout.activity_work_group_manage;
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
        if (userModels.size()==0){
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onStart() {
        getLeaderList();
        super.onStart();
    }

    /**
     * 初始化下拉刷新控件
     */
    private void setupSwipe() {
        swipeWorkGroup.setRefreshing(true);
        swipeWorkGroup.setColorSchemeColors(getResources().getColor(R2.color.colorAccent));
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
                if (userModels.size()==0){
                    emptyView.setVisibility(View.VISIBLE);
                }else {
                    emptyView.setVisibility(View.GONE);
                }
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


    @OnClick(R2.id.fat_add_leader)
    public void onViewClicked() {
        Intent intent = new Intent(this,AddLeaderActivity.class);
        startActivity(intent);
    }
}
