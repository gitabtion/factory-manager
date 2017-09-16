package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.SubTimeStringUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/8/28 09:08.
 * email caiheng@hrsoft.net
 */

public class OrderContentAdapter extends BaseRecyclerViewAdapter<OrderModel> {




    public OrderContentAdapter(Context context, List<OrderModel> orderModels) {
        super(context, orderModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_order_content, parent, false);

        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<OrderModel> {
        @BindView(R.id.txt_item_order_custom_info)
        TextView txtItemOrderCustomInfo;
        @BindView(R.id.txt_order_number)
        TextView txtOrderNumber;
        @BindView(R.id.txt_item_order_title)
        TextView txtItemOrderTitle;
        @BindView(R.id.txt_order_start_time)
        TextView txtOrderStartTime;
        @BindView(R.id.txt_order_end_time)
        TextView txtOrderEndTime;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(OrderModel orderModel) {
            txtItemOrderTitle.setText(orderModel.getTitle()==null?"N/A":orderModel.getTitle());
            txtItemOrderCustomInfo.setText(orderModel.getCustomerInfo()==null?"N/A":orderModel.getCustomerInfo());
            txtOrderNumber.setText(orderModel.getOrderCode()==null?"N/A":orderModel.getOrderCode());
            txtOrderStartTime.setText(orderModel.getStartTime()==null?"N/A":SubTimeStringUtil.subTimeString(orderModel.getStartTime()));
            txtOrderEndTime.setText(orderModel.getEndTime()==null?"N/A": SubTimeStringUtil.subTimeString(orderModel.getEndTime()));
        }
    }
}
