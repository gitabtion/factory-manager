package net.hrsoft.transparent_factory_manager.home.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.adapters.BaseListViewAdapter;
import net.hrsoft.transparent_factory_manager.order.models.CurrentOrderModel;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;

import java.util.List;


/**
 * @author abtion.
 * @since 17/9/1 19:07.
 * email caiheng@hrsoft.net
 */

public class HomeSpinnerAdapter extends BaseListViewAdapter<CurrentOrderModel>{


    public HomeSpinnerAdapter(Context context, List<CurrentOrderModel> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_home_spinner,parent,false);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtOrderName = (TextView) convertView.findViewById(R.id.txt_order_name);
        viewHolder.txtOrderName.setText(dataList.get(position).getTitle() + " "+ dataList.get(position).getOrderCode
                ());
        return convertView;
    }
    static class ViewHolder{
        TextView txtOrderName;
    }
}
