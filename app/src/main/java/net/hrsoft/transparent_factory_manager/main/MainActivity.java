package net.hrsoft.transparent_factory_manager.main;


import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.activities.NoBarActivity;
import net.hrsoft.transparent_factory_manager.home.fragments.HomeFragment;
import net.hrsoft.transparent_factory_manager.mine.fragments.MineFragment;
import net.hrsoft.transparent_factory_manager.order.fragments.OrderFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends NoBarActivity {

    @BindView(R.id.frame_main_container)
    FrameLayout frameMainContainer;
    @BindView(R.id.img_tab_menu_home)
    ImageView imgTabMenuHome;
    @BindView(R.id.txt_tab_menu_home)
    TextView txtTabMenuHome;
    @BindView(R.id.img_tab_menu_order)
    ImageView imgTabMenuOrder;
    @BindView(R.id.txt_tab_menu_order)
    TextView txtTabMenuOrder;
    @BindView(R.id.img_tab_menu_mine)
    ImageView imgTabMenuMine;
    @BindView(R.id.txt_tab_menu_mine)
    TextView txtTabMenuMine;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
    }

    @Override
    protected void initView() {
        addFragment(R.id.frame_main_container, homeFragment, null);
        changeHomeMenuStatus();
    }

    @Override
    protected void loadData() {

    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    private void showFragment(Fragment fragment) {
        hideAllFragment();
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container, fragment).commit();
    }


    @OnClick(R.id.ly_tab_menu_home)
    public void onLyTabMenuHomeClicked() {
        changeHomeMenuStatus();
        hideAllFragment();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            addFragment(homeFragment);
        } else {
            showFragment(homeFragment);
        }
    }

    @OnClick(R.id.ly_tab_menu_order)
    public void onLyTabMenuOrderClicked() {
        changeOrderMenuStatus();
        hideAllFragment();
        if (orderFragment == null) {
            orderFragment = new OrderFragment();
            addFragment(orderFragment);
        } else {
            showFragment(orderFragment);
        }
    }

    @OnClick(R.id.ly_tab_menu_mine)
    public void onLyTabMenuMineClicked() {
        changMineMenuStatus();
        hideAllFragment();
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            addFragment(mineFragment);
        } else {
            showFragment(mineFragment);
        }
    }

    private void hideAllFragment() {
        if (homeFragment != null) {
            hideFragment(homeFragment);
        }
        if (orderFragment != null) {
            hideFragment(orderFragment);
        }
        if (mineFragment != null) {
            hideFragment(mineFragment);
        }
    }


    private void clearChoiceStatus(){
        txtTabMenuHome.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuOrder.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuMine.setTextColor(getResources().getColor(R.color.subtitle));

        imgTabMenuHome.setImageResource(R.drawable.img_home_primary);
        imgTabMenuOrder.setImageResource(R.drawable.img_order_primary);
        imgTabMenuMine.setImageResource(R.drawable.img_mine_primary);

    }

    private void changeHomeMenuStatus(){
        clearChoiceStatus();
        imgTabMenuHome.setImageResource(R.drawable.img_home_selected);
        txtTabMenuHome.setTextColor(getResources().getColor(R.color.white));
    }
    private void changeOrderMenuStatus(){
        clearChoiceStatus();
        imgTabMenuOrder.setImageResource(R.drawable.img_order_selected);
        txtTabMenuOrder.setTextColor(getResources().getColor(R.color.white));
    }
    private void changMineMenuStatus(){
        clearChoiceStatus();
        imgTabMenuMine.setImageResource(R.drawable.img_mine_selected);
        txtTabMenuMine.setTextColor(getResources().getColor(R.color.white));
    }
}
