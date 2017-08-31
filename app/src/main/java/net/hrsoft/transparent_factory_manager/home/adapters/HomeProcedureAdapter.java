package net.hrsoft.transparent_factory_manager.home.adapters;

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
 * @since 17/8/29 21:56.
 * email caiheng@hrsoft.net
 */

public class HomeProcedureAdapter extends BaseRecyclerViewAdapter<ProcedureModel> {

    public HomeProcedureAdapter(Context context, List<ProcedureModel> procedureModels) {
        super(context, procedureModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BaseRecyclerViewAdapter.DATA_ITEM:
                View dataItemView = inflater.inflate(R.layout.item_procedure_home, parent, false);
                return new ItemHolder(dataItemView);
            case BaseRecyclerViewAdapter.HEADER_ITEM:
                View headerItemView = inflater.inflate(R.layout.home_header, parent, false);
                return new HeaderItemHolder(headerItemView);
            default:
                break;
        }

        return null;
    }

    class ItemHolder extends ViewHolder<ProcedureModel> {
        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ProcedureModel procedureModel) {

        }
    }

    class HeaderItemHolder extends HeaderHolder {
        public HeaderItemHolder(View itemView) {
            super(itemView);
        }
    }
}
