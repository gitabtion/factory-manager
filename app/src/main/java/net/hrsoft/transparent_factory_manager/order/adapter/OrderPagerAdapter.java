package net.hrsoft.transparent_factory_manager.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.hrsoft.transparent_factory_manager.order.fragments.OrderContentFragment;

/**
 * @author abtion.
 * @since 17/8/27 10:17.
 * email caiheng@hrsoft.net
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"进行中","未开始","已完成"};

    private OrderContentFragment processingOrderFragment;
    private OrderContentFragment toBeStartOrderFragment;
    private OrderContentFragment endedOrderFragment;
    private OrderContentFragment mCurrentFragment;

    public OrderPagerAdapter(FragmentManager fm) {
        super(fm);
        initVariable();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case OrderContentFragment.PROCESSING:
                mCurrentFragment = processingOrderFragment;
                break;
            case OrderContentFragment.TO_BE_START:
                mCurrentFragment = toBeStartOrderFragment;
                break;
            case OrderContentFragment.ENDED:
                mCurrentFragment = endedOrderFragment;
                break;
            default:
                break;
        }
        return mCurrentFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    /**
     * 初始化fragment页面
     */
    private void initVariable(){
        if (processingOrderFragment == null){
            processingOrderFragment = new OrderContentFragment();
        }
        if (toBeStartOrderFragment == null){
            toBeStartOrderFragment = new OrderContentFragment();
        }
        if (endedOrderFragment == null){
            endedOrderFragment = new  OrderContentFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
