package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;


import java.util.List;

import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/31 21:13.
 * email caiheng@hrsoft.net
 */

public class GroupListAdapter extends BaseRecyclerViewAdapter<GroupModel> {

    public GroupListAdapter(Context context, List<GroupModel> groupModels) {
        super(context, groupModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group,parent,false);
        return new ItemHolder(view);
    }
    static class ItemHolder extends ViewHolder<GroupModel> {
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void onBind(GroupModel groupModel) {

        }
    }
}
