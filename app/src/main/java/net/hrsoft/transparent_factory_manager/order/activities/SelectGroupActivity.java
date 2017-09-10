package net.hrsoft.transparent_factory_manager.order.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.mine.models.GetGroupLIstResponse;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.adapter.GroupListAdapter;
import net.hrsoft.transparent_factory_manager.utils.SnackbarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/8/31 21:07.
 * email caiheng@hrsoft.net
 */

public class SelectGroupActivity extends ToolBarActivity implements
        BaseRecyclerViewAdapter.OnItemClicked<GroupModel> {
    public static final String WORK_GROUP = "workGroup";

    @BindView(R.id.swipe_group)
    SwipeRefreshLayout swipeGroup;
    private ArrayList<GroupModel> groupModels;
    private GroupModel groupModel;
    @BindView(R.id.rec_group)
    RecyclerView recGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_group;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("选择班组");
        groupModels = new ArrayList<>();

        swipeGroup.setRefreshing(true);
        swipeGroup.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeGroup.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWorkGroup();
            }
        });
        getWorkGroup();
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onItemClicked(GroupModel groupModel, BaseRecyclerViewAdapter.ViewHolder holder) {
        this.groupModel = groupModel;
        Intent intent = new Intent(this,WorkGroupInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(WORK_GROUP,groupModel);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    private void getWorkGroup() {

        RestClient.getService().getGroupList().enqueue(new DataCallback<APIResponse<GetGroupLIstResponse>>() {
            @Override
            public void onDataResponse(Call<APIResponse<GetGroupLIstResponse>> call,
                                       Response<APIResponse<GetGroupLIstResponse>> response) {
                groupModels = response.body().getData().getRecords();
                GroupListAdapter adapter = new GroupListAdapter(SelectGroupActivity.this, groupModels);
                recGroup.setAdapter(adapter);
                adapter.setOnItemClickedListener(SelectGroupActivity.this);
                recGroup.setLayoutManager(new LinearLayoutManager(SelectGroupActivity.this, LinearLayoutManager
                        .VERTICAL, false));
            }

            @Override
            public void onDataFailure(Call<APIResponse<GetGroupLIstResponse>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeGroup.isRefreshing()){
                    swipeGroup.setRefreshing(false);
                }
            }
        });
    }

}
