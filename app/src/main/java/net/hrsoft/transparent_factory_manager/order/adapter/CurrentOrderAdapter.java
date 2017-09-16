package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.utils.SubTimeStringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/9/7 20:55.
 * email caiheng@hrsoft.net
 */

public class CurrentOrderAdapter extends BaseRecyclerViewAdapter<CurrentOrderModel> {


    public CurrentOrderAdapter(Context context, List<CurrentOrderModel> currentOrderModels) {
        super(context, currentOrderModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_order_content, parent, false);
        return new ItemHolder(view);
    }


    static class ItemHolder extends ViewHolder<CurrentOrderModel> {
        @BindView(R.id.txt_item_order_custom_info)
        TextView txtItemOrderCustomInfo;
        @BindView(R.id.txt_order_number)
        TextView txtOrderNumber;
        @BindView(R.id.txt_item_order_title)
        TextView txtItemOrderTitle;
        @BindView(R.id.progress_item_order)
        ProgressBar progressItemOrder;
        @BindView(R.id.txt_item_progress_percent)
        TextView txtItemProgressPercent;
        @BindView(R.id.txt_order_start_time)
        TextView txtOrderStartTime;
        @BindView(R.id.txt_order_end_time)
        TextView txtOrderEndTime;
        @BindView(R.id.btn_order_done)
        TextView btnOrderDone;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(CurrentOrderModel currentOrderModel) {
            txtItemProgressPercent.setVisibility(View.VISIBLE);
            progressItemOrder.setVisibility(View.VISIBLE);


            txtItemProgressPercent.setText(currentOrderModel.getCapacity() * 100 + "%");
            progressItemOrder.setProgress((int) (currentOrderModel.getCapacity() * 100));
            txtItemOrderTitle.setText(currentOrderModel.getTitle() == null ? "N/A" : currentOrderModel.getTitle());
            txtItemOrderCustomInfo.setText(currentOrderModel.getCustomerInfo() == null ? "N/A" : currentOrderModel
                    .getCustomerInfo());
            txtOrderNumber.setText(currentOrderModel.getOrderCode() == null ? "N/A" : currentOrderModel.getOrderCode());
            txtOrderStartTime.setText(currentOrderModel.getStartTime() == null ? "N/A" : SubTimeStringUtil.subTimeString(currentOrderModel
                    .getStartTime()));
            txtOrderEndTime.setText(currentOrderModel.getEndTime() == null ? "N/A" : SubTimeStringUtil.subTimeString(currentOrderModel.getEndTime()));
        }


        /**
         * 修改订单状态，模糊需求，后端无接口，留下以迭代
         */
        @OnClick(R.id.rl_order_done)
        public void onViewClicked() {

        }
    }
}
