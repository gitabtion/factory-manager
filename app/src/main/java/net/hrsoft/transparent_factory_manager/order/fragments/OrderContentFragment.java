package net.hrsoft.transparent_factory_manager.order.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.order.models.OrderModel;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderContentAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author abtion.
 * @since 17/8/27 10:21.
 * email caiheng@hrsoft.net
 */

public class OrderContentFragment extends BaseFragment {
    @BindView(R.id.rec_order)
    RecyclerView orderRec;

    private int type;

    public final static int PROCESSING = 0;
    public final static int TO_BE_START = 1;
    public final static int ENDED = 2;

    public OrderContentFragment(int type) {
        super();
        this.type = type;
    }




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_content;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        switch (type){
            case OrderContentFragment.PROCESSING:
                for (int i = 0;i<10;i++){
                    orderModels.add(new OrderModel("processing "+i));
                }
                break;
            case OrderContentFragment.TO_BE_START:
                for (int i = 0;i<10;i++){
                    orderModels.add(new OrderModel("to be start "+i));
                }
                break;case OrderContentFragment.ENDED:
                for (int i = 0;i<10;i++){
                    orderModels.add(new OrderModel("ended "+i));
                }
                break;
            default:
                break;
        }

        OrderContentAdapter adapter = new OrderContentAdapter(getContext(),orderModels,type);
        orderRec.setAdapter(adapter);
        orderRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void loadData() {

    }
}
