package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;

import java.util.List;

/**
 * @author abtion.
 * @since 17/8/30 22:07.
 * email caiheng@hrsoft.net
 */

public class ProcedureAdapter extends BaseRecyclerViewAdapter<ProcedureModel> {
    public ProcedureAdapter(Context context, List<ProcedureModel> procedureModels) {
        super(context, procedureModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case BaseRecyclerViewAdapter.HEADER_ITEM:
                View headerView = inflater.inflate(R.layout.procedure_header,parent,false);
                return new HeaderItemHolder(headerView);
            case BaseRecyclerViewAdapter.DATA_ITEM:
                View dataView = inflater.inflate(R.layout.procedure_item,parent,false);
                return new DataItemHolder(dataView);
            default:
                break;
        }

        return null;
    }

    static class HeaderItemHolder extends HeaderHolder {
        public HeaderItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Object o) {

        }
    }

    static class DataItemHolder extends ViewHolder {
        public DataItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Object o) {

        }
    }
}
