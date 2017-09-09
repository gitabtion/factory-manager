package net.hrsoft.transparent_factory_manager.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseRecyclerViewAdapter;
import net.hrsoft.transparent_factory_manager.home.models.ProcedureModel;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/8/29 21:56.
 * email caiheng@hrsoft.net
 */

public class HomeProcedureAdapter extends BaseRecyclerViewAdapter<ProcedureModel> {

    private CurrentOrderModel orderModel;


    public HomeProcedureAdapter(Context context, List<ProcedureModel> procedureModels, CurrentOrderModel orderModel) {
        super(context, procedureModels);
        this.orderModel = orderModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BaseRecyclerViewAdapter.DATA_ITEM:
                View dataItemView = inflater.inflate(R.layout.item_procedure_home, parent, false);
                return new ItemHolder(dataItemView);
            case BaseRecyclerViewAdapter.HEADER_ITEM:
                View headerItemView = inflater.inflate(R.layout.home_header, parent, false);
                return new HeaderItemHolder(headerItemView, orderModel);
            default:
                break;
        }
        return null;
    }

    static class ItemHolder extends ViewHolder<ProcedureModel> {
        @BindView(R.id.txt_procedure_name)
        TextView txtProcedureName;
        @BindView(R.id.txt_group_name)
        TextView txtGroupName;
        @BindView(R.id.pie_all)
        PieChart pieAll;
        @BindView(R.id.pie_time)
        PieChart pieTime;

        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ProcedureModel procedureModel) {
            txtProcedureName.setText(procedureModel.getName());
            txtGroupName.setText(procedureModel.getWorkGroupName());
            double allPercent = ((double)procedureModel.getSuccessCount())/((double)procedureModel.getTotalCount())*100;
            pieAll.setCenterText(allPercent+"%");
            pieAll.setHoleRadius((float) allPercent);
        }
    }

    class HeaderItemHolder extends HeaderHolder<CurrentOrderModel> {

        private CurrentOrderModel order;

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
        @BindView(R.id.pie_all)
        PieChart pieAll;
        @BindView(R.id.pie_time)
        PieChart pieTime;

        HeaderItemHolder(View itemView, CurrentOrderModel orderModel) {
            super(itemView);
            this.order = orderModel;
            onBind(orderModel);
        }

        @Override
        protected void onBind(CurrentOrderModel orderModel) {
            txtHomeEndTime.setText(order.getEndTime()==null?"N/A":orderModel.getEndTime());
            txtHomeStartTime.setText(order.getStartTime()==null?"N/A":orderModel.getStartTime());
            txtHomeOrderName.setText(order.getTitle()==null?"N/A":orderModel.getTitle());
            txtHomeOrderNumber.setText(order.getOrderCode()==null?"N/A":orderModel.getOrderCode());
            txtHomeOrderDescription.setText(order.getCustomerInfo()==null?"N/A":orderModel.getCustomerInfo());
        }
    }

    public void setHeader(CurrentOrderModel orderModel){
        this.orderModel = orderModel;
        refresh();
    }
}
