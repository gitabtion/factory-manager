package net.hrsoft.transparent_factory_manager.order.fragments;

import android.content.Intent;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.order.activities.CreateOrderActivity;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/8/26 21:14.
 * email caiheng@hrsoft.net
 */

public class OrderFragment extends BaseFragment {
    @BindView(R2.id.vp_order)
    ViewPager orderVp;
    @BindView(R2.id.tab_order)
    TabLayout orderTab;


    @Override
    protected int getLayoutId() {
        return R2.layout.fragment_order;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        OrderPagerAdapter adapter = new OrderPagerAdapter(getFragmentManager());
        orderVp.setAdapter(adapter);
        orderTab.setupWithViewPager(orderVp);
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R2.id.fab_add_order)
    public void onAddOrderFabClicked() {
        Intent intent = new Intent(getContext(), CreateOrderActivity.class);
        startActivity(intent);
    }


}
