package net.hrsoft.transparent_factory_manager.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.SubTimeStringUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/8/30 22:07.
 * email caiheng@hrsoft.net
 */

public class ProcedureAdapter extends BaseRecyclerViewAdapter<ProcedureModel> {


    private OrderModel orderModel;


    public ProcedureAdapter(Context context, List<ProcedureModel> procedureModels, OrderModel orderModel) {
        super(context, procedureModels);
        this.orderModel = orderModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BaseRecyclerViewAdapter.HEADER_ITEM:
                View headerView = inflater.inflate(R.layout.procedure_header, parent, false);
                return new HeaderItemHolder(headerView, orderModel);
            case BaseRecyclerViewAdapter.DATA_ITEM:
                View dataView = inflater.inflate(R.layout.procedure_item, parent, false);
                return new DataItemHolder(dataView);
            default:
                break;
        }

        return null;
    }

    static class HeaderItemHolder extends HeaderHolder<OrderModel> {
        @BindView(R.id.txt_home_order_description)
        TextView txtHomeOrderDescription;
        @BindView(R.id.txt_home_order_number)
        TextView txtHomeOrderNumber;
        @BindView(R.id.txt_home_order_name)
        TextView txtHomeOrderName;
        @BindView(R.id.txt_home_start_time)
        TextView txtHomeStartTime;
        @BindView(R.id.txt_home_end_time)
        TextView txtHomeEndTime;

        public HeaderItemHolder(View itemView, OrderModel orderModel) {
            super(itemView);
            onBind(orderModel);
        }

        @Override
        protected void onBind(OrderModel orderModel) {
            txtHomeEndTime.setText(SubTimeStringUtil.subTimeString(orderModel.getEndTime()));
            txtHomeOrderDescription.setText(orderModel.getDescription());
            txtHomeOrderName.setText(orderModel.getTitle());
            txtHomeOrderNumber.setText(orderModel.getOrderCode());
            txtHomeStartTime.setText(SubTimeStringUtil.subTimeString(orderModel.getStartTime()));
        }
    }

    static class DataItemHolder extends ViewHolder<ProcedureModel> {
        @BindView(R.id.txt_start_time)
        TextView txtStartTime;
        @BindView(R.id.txt_end_time)
        TextView txtEndTime;
        @BindView(R.id.group_name)
        TextView groupName;
        @BindView(R.id.procedure_count)
        TextView procedureCount;
        @BindView(R.id.procedure_count_unit)
        TextView procedureCountUnit;

        public DataItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ProcedureModel procedureModel) {
            groupName.setText(procedureModel.getWorkGroupName());
            procedureCount.setText(procedureModel.getTotalCount());
            procedureCountUnit.setText(procedureModel.getStandard());
            txtStartTime.setText(procedureModel.getStartTime());
            txtEndTime.setText(procedureModel.getEndTime());
        }
    }
}
