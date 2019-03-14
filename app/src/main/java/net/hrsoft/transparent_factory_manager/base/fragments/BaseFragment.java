package net.hrsoft.transparent_factory_manager.base.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/25 17:39.
 * email caiheng@hrsoft.net
 */
public abstract class BaseFragment extends Fragment {
    /** 进度窗 */
    protected ProgressDialog progressDialog;
    /** 当前Fragment RootView */
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    /**
     * 页面初始化操作.
     */
    protected void init() {
        preInit();
        initVariable();
        initView();
        loadData();
    }

    /**
     * 执行init 方法之前的处理
     */
    private void preInit() {
        // progressDialog 统一定义
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
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
     * 获取当前Fragment的RootView
     *
     * @return RootView
     */
    protected View getRootView() {
        return view;
    }
}
