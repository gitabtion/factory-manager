package net.hrsoft.transparent_factory_manager.mine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.mine.models.GroupModel;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/9/8 21:35.
 * email caiheng@hrsoft.net
 */

public class WorkGroupAdapter extends BaseRecyclerViewAdapter<GroupModel> {




    public WorkGroupAdapter(Context context, List<GroupModel> groupModels) {
        super(context, groupModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<GroupModel> {
        @BindView(R.id.txt_group_name)
        TextView txtGroupName;
        @BindView(R.id.txt_leader_name)
        TextView txtLeaderName;
        @BindView(R.id.txt_description)
        TextView txtDescription;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(GroupModel groupModel) {
            txtGroupName.setText(groupModel.getName()==null?"N/A":groupModel.getName());
            txtLeaderName.setText(groupModel.getLeaderName()==null?"N/A":groupModel.getLeaderName());
            txtDescription.setText(groupModel.getDescription()==null?"N/A":groupModel.getDescription());
        }
    }
}
