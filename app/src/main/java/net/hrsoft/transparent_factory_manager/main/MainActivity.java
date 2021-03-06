package net.hrsoft.transparent_factory_manager.main;


import androidx.fragment.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R2;
import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.base.activities.NoBarActivity;
import net.hrsoft.transparent_factory_manager.home.fragments.HomeFragment;
import net.hrsoft.transparent_factory_manager.mine.fragments.MineFragment;
import net.hrsoft.transparent_factory_manager.order.fragments.OrderFragment;
import net.hrsoft.transparent_factory_manager.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends NoBarActivity {
    private long firstTime;

    @BindView(R2.id.frame_main_container)
    FrameLayout frameMainContainer;
    @BindView(R2.id.img_tab_menu_home)
    ImageView imgTabMenuHome;
    @BindView(R2.id.txt_tab_menu_home)
    TextView txtTabMenuHome;
    @BindView(R2.id.img_tab_menu_order)
    ImageView imgTabMenuOrder;
    @BindView(R2.id.txt_tab_menu_order)
    TextView txtTabMenuOrder;
    @BindView(R2.id.img_tab_menu_mine)
    ImageView imgTabMenuMine;
    @BindView(R2.id.txt_tab_menu_mine)
    TextView txtTabMenuMine;
    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;


    @Override
    protected int getLayoutId() {
        return R2.layout.activity_main;
    }

    @Override
    protected void initVariable() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
    }

    @Override
    protected void initView() {
        addFragment(R2.id.frame_main_container, homeFragment, null);
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
        getSupportFragmentManager().beginTransaction().add(R2.id.frame_main_container, fragment).commit();
    }


    @OnClick(R2.id.ly_tab_menu_home)
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

    @OnClick(R2.id.ly_tab_menu_order)
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

    @OnClick(R2.id.ly_tab_menu_mine)
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
        txtTabMenuHome.setTextColor(getResources().getColor(R2.color.subtitle));
        txtTabMenuOrder.setTextColor(getResources().getColor(R2.color.subtitle));
        txtTabMenuMine.setTextColor(getResources().getColor(R2.color.subtitle));

        imgTabMenuHome.setImageResource(R2.drawable.img_home_primary);
        imgTabMenuOrder.setImageResource(R2.drawable.img_order_primary);
        imgTabMenuMine.setImageResource(R2.drawable.img_mine_primary);

    }

    private void changeHomeMenuStatus(){
        clearChoiceStatus();
        imgTabMenuHome.setImageResource(R2.drawable.img_home_selected);
        txtTabMenuHome.setTextColor(getResources().getColor(R2.color.white));
    }
    private void changeOrderMenuStatus(){
        clearChoiceStatus();
        imgTabMenuOrder.setImageResource(R2.drawable.img_order_selected);
        txtTabMenuOrder.setTextColor(getResources().getColor(R2.color.white));
    }
    private void changMineMenuStatus(){
        clearChoiceStatus();
        imgTabMenuMine.setImageResource(R2.drawable.img_mine_selected);
        txtTabMenuMine.setTextColor(getResources().getColor(R2.color.white));
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showToast("再按一次退出");
            firstTime = secondTime;
        } else {
            TFMApplication.getInstance().exitApp();
        }
    }
}
