package net.hrsoft.transparent_factory_manager.order.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.order.adapter.OrderPagerAdapter;

import butterknife.BindView;

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
}
