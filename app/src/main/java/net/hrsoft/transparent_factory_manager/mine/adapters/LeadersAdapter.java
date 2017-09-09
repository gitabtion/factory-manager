package net.hrsoft.transparent_factory_manager.mine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.base.models.BaseModel;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/9/9 22:33.
 * email caiheng@hrsoft.net
 */

public class LeadersAdapter extends BaseRecyclerViewAdapter<UserModel> {


    public LeadersAdapter(Context context, List<UserModel> userModels) {
        super(context, userModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_leaders, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<UserModel> {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_mobile)
        TextView txtMobile;
        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(UserModel userModel) {
            txtName.setText(userModel.getName()==null?"N/A":userModel.getName());
            txtMobile.setText(userModel.getMobile()==null?"N/A":userModel.getMobile());
        }
    }
}
