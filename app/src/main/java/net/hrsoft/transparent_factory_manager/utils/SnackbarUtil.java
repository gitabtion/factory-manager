package net.hrsoft.transparent_factory_manager.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import net.hrsoft.transparent_factory_manager.TFMApplication;

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
