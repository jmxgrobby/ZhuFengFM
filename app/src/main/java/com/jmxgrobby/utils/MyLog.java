package com.jmxgrobby.utils;

import android.util.Log;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public final class MyLog {
    public static void d(String tag,String msg){
        if(Configs.ISDEBUG){
            Log.d(tag,msg);
        }
    }
}
