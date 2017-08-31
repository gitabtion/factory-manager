package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/28 09:08.
 * email caiheng@hrsoft.net
 */

public class OrderContentAdapter extends BaseRecyclerViewAdapter<OrderModel> {



    private int type;

    public OrderContentAdapter(Context context, List<OrderModel> orderModels, int type) {
        super(context, orderModels);
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (type) {
//            case OrderContentFragment.PROCESSING:
//                View view = inflater.inflate(R.layout.item_order_processing, parent, false);
//                return new ItemHolder(view);
//            case OrderContentFragment.TO_BE_START:
//                View viewToBeStart = inflater.inflate(R.layout.item_order_to_be_start, parent, false);
//                return new ItemHolder(viewToBeStart);
//            case OrderContentFragment.ENDED:
//                View viewEnded = inflater.inflate(R.layout.item_order_ended, parent, false);
//                return new ItemHolder(viewEnded);
//            default:
//                break;
//        }

        View view = inflater.inflate(R.layout.item_order_content, parent, false);
        return new ItemHolder(view);
    }

    class ItemHolder extends ViewHolder<OrderModel> {
        @BindView(R.id.txt_item_order_title)
        TextView txtItemOrderTitle;
        @BindView(R.id.txt_item_order_description)
        TextView txtItemOrderDescription;
        @BindView(R.id.txt_order_number)
        TextView txtOrderNumber;
        @BindView(R.id.txt_item_progress_percent)
        TextView txtItemProgressPercent;
        @BindView(R.id.progress_item_order)
        ProgressBar progressItemOrder;
        @BindView(R.id.txt_order_start_time)
        TextView txtOrderStartTime;
        @BindView(R.id.txt_order_end_time)
        TextView txtOrderEndTime;

//        private View itemView;
//        private TextView orderTitle;

        ItemHolder(View itemView) {
            super(itemView);
//            this.itemView = itemView;
            ButterKnife.bind(itemView);
        }

        @Override
        protected void onBind(OrderModel orderModel) {
//            switch (type) {
//                case OrderContentFragment.PROCESSING:
//                    bindProcessingOrderView();
//                    orderTitle.setText(orderModel.getName());
//                    break;
//                case OrderContentFragment.TO_BE_START:
//                    bindToBeStartOrderView();
//                    orderTitle.setText(orderModel.getName());
//                    break;
//                case OrderContentFragment.ENDED:
//                    bindEndedOrderView();
//                    orderTitle.setText(orderModel.getName());
//                    break;
//                default:
//                    break;
//            }
            txtItemOrderTitle.setText(orderModel.getTitle());

        }

//        private void bindProcessingOrderView() {
//            orderTitle = (TextView) itemView.findViewById(R.id.txt_item_processing_title);
//        }
//
//        private void bindToBeStartOrderView() {
//            orderTitle = (TextView) itemView.findViewById(R.id.txt_item_to_be_start_title);
//        }
//
//        private void bindEndedOrderView() {
//            orderTitle = (TextView) itemView.findViewById(R.id.txt_item_ended_title);
//        }
    }
}
