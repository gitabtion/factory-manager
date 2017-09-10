package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/9/10 15:14.
 * email caiheng@hrsoft.net
 */

public class WorkGroupProcedureAdapter extends BaseRecyclerViewAdapter<ProcedureModel> {


    public WorkGroupProcedureAdapter(Context context, List<ProcedureModel> procedureModels) {
        super(context, procedureModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_procedure_home, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<ProcedureModel> {
        @BindView(R.id.txt_procedure_name)
        TextView txtProcedureName;
        @BindView(R.id.txt_group_name)
        TextView txtGroupName;
        @BindView(R.id.txt_start_time)
        TextView txtStartTime;
        @BindView(R.id.txt_end_time)
        TextView txtEndTime;
        @BindView(R.id.pie_all)
        PieChart pieAll;
        @BindView(R.id.pie_time)
        PieChart pieTime;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ProcedureModel procedureModel) {
            pieAll.setVisibility(View.GONE);
            pieTime.setVisibility(View.GONE);

            txtGroupName.setText(procedureModel.getWorkGroupName());
            txtProcedureName.setText(procedureModel.getName());
            txtStartTime.setText(procedureModel.getStartTime());
            txtEndTime.setText(procedureModel.getEndTime());
        }
    }
}
