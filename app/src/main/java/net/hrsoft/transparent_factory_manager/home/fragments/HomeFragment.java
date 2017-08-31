package net.hrsoft.transparent_factory_manager.home.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.home.adapters.HomeProcedureAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/8/26 21:13.
 * email caiheng@hrsoft.net
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rec_home_procedure)
    RecyclerView recHomeProcedure;
    ArrayList<ProcedureModel> procedureModels;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {
        procedureModels = new ArrayList<>();
        for (int i= 0;i<10;i++){
            procedureModels.add(new ProcedureModel());
        }
    }

    @Override
    protected void initView() {
        HomeProcedureAdapter adapter = new HomeProcedureAdapter(getContext(),procedureModels);
        adapter.setHasHeader(true);
        recHomeProcedure.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        recHomeProcedure.setLayoutManager(layoutManager);
        recHomeProcedure.setHasFixedSize(true);
        recHomeProcedure.setNestedScrollingEnabled(false);

    }

    @Override
    protected void loadData() {

    }

}
