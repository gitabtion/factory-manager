package net.hrsoft.transparent_factory_manager.base.activities;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;
import net.hrsoft.transparent_factory_manager.common.exceptions.LogicErrorException;
import net.hrsoft.transparent_factory_manager.utils.Utility;

import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/25 17:35.
 * email caiheng@hrsoft.net
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 禁止APP横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 页面初始化操作.
     */
    protected void init() {
        ButterKnife.bind(this);
        preInit();
        initVariable();
        initView();
        loadData();
    }

    /**
     * 执行init 方法之前的处理
     */
    protected void preInit() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化变量.
     */
    protected abstract void initVariable();

    /**
     * 初始化View的状态，挂载各种监听事件.
     */
    protected abstract void initView();

    /**
     * 初始化加载页面数据.
     */
    protected abstract void loadData();

    /**
     * 添加Fragment
     *
     * @param fragment fragment
     * @param bundle   传递的数据
     */
    protected void addFragment(@IdRes int containerViewId, BaseFragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.add(containerViewId, fragment).commit();
    }

    /**
     * 替换Fragment
     *
     * @param fragment fragment
     * @param bundle   传递的数据
     */
    protected void replaceFragment(@IdRes int containerViewId, BaseFragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(containerViewId, fragment).addToBackStack(null).commit();
    }

    /**
     * 代码逻辑错误，在理论上不可达的位置调用此方法
     */
    protected void logicError() {
        try {
            throw new LogicErrorException();
        } catch (LogicErrorException e) {
            e.printStackTrace();
        }
    }


}
