package com.jmxgrobby.utils;

import android.os.Build;
import android.util.Log;
import zhufengfm.jmxgrobby.com.zhufengfm.BuildConfig;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public final class MyLog {
    private static final boolean LOG_ON = BuildConfig.DEBUG;

    public static void d(String tag,String msg){
        if(LOG_ON){
            Log.d(tag,msg);
        }
    }
}
