package net.hrsoft.transparent_factory_manager.order.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.home.models.GetProcedureResponse;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;
import net.hrsoft.transparent_factory_manager.network.APIResponse;
import net.hrsoft.transparent_factory_manager.network.DataCallback;
import net.hrsoft.transparent_factory_manager.network.RestClient;
import net.hrsoft.transparent_factory_manager.order.adapter.WorkGroupProcedureAdapter;
import net.hrsoft.transparent_factory_manager.utils.RxBus;
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
 * @since 17/9/10 14:33.
 * email caiheng@hrsoft.net
 */

public class WorkGroupInfoActivity extends ToolBarActivity {
    private ArrayList<ProcedureModel> procedureModels;

    private static final int REFRESH = 0;
    private static final int ADD = 1;

    @BindView(R.id.txt_leader_name)
    TextView txtLeaderName;
    @BindView(R.id.txt_group_name)
    TextView txtGroupName;
    @BindView(R.id.txt_description)
    TextView txtDescription;
    @BindView(R.id.rec_work_group_procedure)
    RecyclerView recWorkGroupProcedure;
    @BindView(R.id.swipe_work_group_info)
    SwipeRefreshLayout swipeWorkGroupInfo;
    private GroupModel groupModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_group_info;
    }

    @Override
    protected void initVariable() {
        procedureModels = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        groupModel = (GroupModel) bundle.getSerializable(SelectGroupActivity.WORK_GROUP);
    }

    @Override
    protected void initView() {
        setActivityTitle("班组详情");
        swipeWorkGroupInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcedure(1, 20, REFRESH);
            }
        });
        swipeWorkGroupInfo.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeWorkGroupInfo.setRefreshing(true);
        getProcedure(1, 20, REFRESH);

        txtGroupName.setText(groupModel.getName());
        txtLeaderName.setText(groupModel.getLeaderName());
        txtDescription.setText(groupModel.getDescription());
    }

    @Override
    protected void loadData() {

    }

    private void getProcedure(final int page, int size, final int type) {
        RestClient.getService().getGroupProcedure(groupModel.getId(), page, size).enqueue(new DataCallback<APIResponse<GetProcedureResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<GetProcedureResponse>> call,
                                       Response<APIResponse<GetProcedureResponse>> response) {
                if (type == REFRESH) {
                    procedureModels = response.body().getData().getProcedures();
                    WorkGroupProcedureAdapter adapter = new WorkGroupProcedureAdapter(WorkGroupInfoActivity.this,
                            procedureModels);
                    recWorkGroupProcedure.setAdapter(adapter);
                    recWorkGroupProcedure.setLayoutManager(new LinearLayoutManager(WorkGroupInfoActivity.this,
                            LinearLayoutManager.VERTICAL, false));
                } else {
                    procedureModels.addAll(response.body().getData().getProcedures());
                    recWorkGroupProcedure.getAdapter().notifyDataSetChanged();
                }
                // TODO: 17/9/10 上拉加载
            }

            @Override
            public void onDataFailure(Call<APIResponse<GetProcedureResponse>> call, Throwable t) {
                SnackbarUtil.showSnackbar(getWindow().getDecorView(), "网络连接失败，请稍后再试");
            }

            @Override
            public void dismissDialog() {
                if (swipeWorkGroupInfo.isRefreshing()) {
                    swipeWorkGroupInfo.setRefreshing(false);
                }
            }
        });
    }


    @OnClick(R.id.btn_select)
    public void onViewClicked() {
        RxBus.getInstance().post(groupModel);
        finish();
    }
}
