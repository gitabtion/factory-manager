package net.hrsoft.transparent_factory_manager.order.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.order.adapter.GroupListAdapter;
import net.hrsoft.transparent_factory_manager.order.models.GroupModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/31 21:07.
 * email caiheng@hrsoft.net
 */

public class SelectGroupActivity extends ToolBarActivity {
    ArrayList<GroupModel> groupModels;
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
        for (int i = 0; i < 20; i++) {
            groupModels.add(new GroupModel());
        }
        GroupListAdapter adapter = new GroupListAdapter(this, groupModels);
        recGroup.setAdapter(adapter);
        recGroup.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void loadData() {

    }


}
