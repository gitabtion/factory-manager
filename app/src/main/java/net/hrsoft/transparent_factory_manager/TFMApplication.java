package net.hrsoft.transparent_factory_manager;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import net.hrsoft.transparent_factory_manager.account.activities.LoginActivity;
import net.hrsoft.transparent_factory_manager.utils.CacheUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abtion.
 * @since 17/8/25 18:08.
 * email caiheng@hrsoft.net
 */

public class TFMApplication extends Application {
    private static TFMApplication instance;//实例对象
    private static List<Activity> activityList = new ArrayList<>();
    private static CacheUtil cacheUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //RichText.initCacheDir(this);
    }

    /**
     * 缓存初始化
     */
    public CacheUtil getCacheUtil() {
        if (cacheUtil == null) {
            cacheUtil = CacheUtil.get(instance.getFilesDir());
        }
        return cacheUtil;
    }

    /**
     * 外部获取实例对象
     *
     * @return net.hrsoft.transparent_factory_manager.TFMApplication
     */
    public static TFMApplication getInstance() {
        return instance;
    }

    private static android.app.Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new android.app.Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityList.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityList.remove(activity);
        }
    };

    /**
     * 移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 清除所有Activity
     */
    public void removeAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing())
                activity.finish();
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        removeAllActivity();
        // TODO: 2017/7/25 退出的后续操作
        System.exit(0);
    }

    /**
     * 退出账户
     */
    public void exitAccount() {
        removeAllActivity();
        getCacheUtil().clear();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
