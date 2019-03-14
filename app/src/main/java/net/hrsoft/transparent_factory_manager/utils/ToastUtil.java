package net.hrsoft.transparent_factory_manager.utils;

import androidx.annotation.StringRes;
import android.widget.Toast;

import net.hrsoft.transparent_factory_manager.TFMApplication;

/**
 * @author abtion.
 * @since 17/8/25 18:18.
 * email caiheng@hrsoft.net
 */

public final class ToastUtil {
    private static final int duration = Toast.LENGTH_SHORT;
    private static final boolean isShowErrorCode = true;

    public static void showToast(final String msg) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TFMApplication.getInstance(), msg, duration).show();
            }
        });
    }

    public static void showToast(@StringRes final int resId) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TFMApplication.getInstance(), resId, duration).show();
            }
        });
    }

    public static void showToast(String msg, int... errorCode) {
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }

    public static void showToast(@StringRes int resId, int... errorCode) {
        String msg = TFMApplication.getInstance().getResources().getString(resId);
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }
}