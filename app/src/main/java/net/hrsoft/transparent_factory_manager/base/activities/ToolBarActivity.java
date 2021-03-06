package net.hrsoft.transparent_factory_manager.base.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import net.hrsoft.transparent_factory_manager.R2;

/**
 * @author abtion.
 * @since 17/8/25 17:47.
 * email caiheng@hrsoft.net
 */

public abstract class ToolBarActivity extends BaseActivity {
    /** activity 页面Toolbar */
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getToolbarView());
        init();
    }

    /**
     * 获取带toolbar的基类页面View
     *
     * @return View
     */
    private View getToolbarView() {
        LayoutInflater inflater = getLayoutInflater();
        RelativeLayout viewRoot = (RelativeLayout) inflater.inflate(R2.layout.view_toolbar_base, null);
        FrameLayout viewContainer = (FrameLayout) viewRoot.findViewById(R2.id.view_container);
        viewContainer.addView(inflater.inflate(getLayoutId(), null));
        initToolbar(viewRoot);
        return viewRoot;
    }

    /**
     * 初始化设置toolbar.
     *
     * @param root 页面rootView
     */
    private void initToolbar(View root) {
        toolbar = (Toolbar) root.findViewById(R2.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackBtnOnclick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 获取当前页面的toolbar.
     *
     * @return toolbar
     */
    protected Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 设置activity 页面标题
     *
     * @param charSequence 页面标题
     */
    protected void setActivityTitle(CharSequence charSequence) {
        if (toolbar != null) {
            toolbar.setTitle(charSequence);
            toolbar.setTitleTextColor(getResources().getColor(R2.color.black_primary));
        }
    }

    /**
     * Toolbar返回按钮的监听事件
     */
    protected void onBackBtnOnclick() {
        this.finish();
    }

}
