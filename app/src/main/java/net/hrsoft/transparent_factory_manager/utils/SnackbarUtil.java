package net.hrsoft.transparent_factory_manager.utils;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;

/**
 * @author abtion.
 * @since 17/9/3 20:24.
 * email caiheng@hrsoft.net
 */

public class SnackbarUtil {
    private static final int duration = Snackbar.LENGTH_SHORT;

    public static void showSnackbar(View view, String msg){
        Snackbar.make(view,msg,duration).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }

}
