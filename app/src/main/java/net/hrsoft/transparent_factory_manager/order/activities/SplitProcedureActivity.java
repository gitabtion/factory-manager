package net.hrsoft.transparent_factory_manager.order.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.ToolBarActivity;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.order.adapter.ProcedureAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/8/30 20:05.
 * email caiheng@hrsoft.net
 */

public class SplitProcedureActivity extends ToolBarActivity {
    private ArrayList<ProcedureModel> procedureModels;

    @BindView(R.id.rec_procedure)
    RecyclerView recProcedure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_split_procedure;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("工序拆分");
        procedureModels = new ArrayList<>();
        ProcedureAdapter adapter = new ProcedureAdapter(this, procedureModels);
        adapter.setHasHeader(true);
        recProcedure.setAdapter(adapter);
        recProcedure.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_create_procedure)
    public void onViewClicked() {
        Intent intent = new Intent(this,CreateProcedureActivity.class);
        startActivity(intent);
    }

    private void initRecSwipe(){
    }
}
