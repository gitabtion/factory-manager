package net.hrsoft.transparent_factory_manager.mine.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.account.models.UserModel;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;

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
        View view = inflater.inflate(R2.layout.item_leaders, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<UserModel> {
        @BindView(R2.id.txt_name)
        TextView txtName;
        @BindView(R2.id.txt_mobile)
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
