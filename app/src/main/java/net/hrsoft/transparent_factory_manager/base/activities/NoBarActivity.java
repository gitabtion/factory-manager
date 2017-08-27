package net.hrsoft.transparent_factory_manager.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author abtion.
 * @since 17/8/25 17:47.
 * email caiheng@hrsoft.net
 */

public abstract class NoBarActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }
}
