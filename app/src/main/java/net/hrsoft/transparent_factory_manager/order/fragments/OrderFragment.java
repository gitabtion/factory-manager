package net.hrsoft.transparent_factory_manager.order.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.order.activities.CreateOrderActivity;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author abtion.
 * @since 17/8/26 21:14.
 * email caiheng@hrsoft.net
 */

public class OrderFragment extends BaseFragment {
    @BindView(R.id.vp_order)
    ViewPager orderVp;
    @BindView(R.id.tab_order)
    TabLayout orderTab;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
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

    @OnClick(R.id.fab_add_order)
    public void onAddOrderFabClicked() {
        Intent intent = new Intent(getContext(), CreateOrderActivity.class);
        startActivity(intent);
    }


}
