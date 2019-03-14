package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.order.models.ProcedureDataModel;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/9/9 15:03.
 * email caiheng@hrsoft.net
 */

public class ProcedureDataAdapter extends BaseRecyclerViewAdapter<ProcedureDataModel> {


    public ProcedureDataAdapter(Context context, List<ProcedureDataModel> procedureDataModels) {
        super(context, procedureDataModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_procedure_data, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<ProcedureDataModel> {
        @BindView(R.id.txt_procedure_end_time)
        TextView txtProcedureEndTime;
        @BindView(R.id.txt_item_success_count)
        TextView txtItemSuccessCount;
        @BindView(R.id.txt_procedure_total_count)
        TextView txtProcedureTotalCount;
        @BindView(R.id.txt_procedure_leader_name)
        TextView txtProcedureLeaderName;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ProcedureDataModel procedureDataModel) {
            txtProcedureEndTime.setText(procedureDataModel.getUpdatedAt()==null?"N/A":procedureDataModel.getUpdatedAt());
            txtItemSuccessCount.setText(String.valueOf(procedureDataModel.getSuccessCount()));
            txtProcedureTotalCount.setText(String.valueOf(procedureDataModel.getTotalCount()));
            txtProcedureLeaderName.setText(procedureDataModel.getLeaderName()==null?"N/A":procedureDataModel.getLeaderName());
        }
    }
}
