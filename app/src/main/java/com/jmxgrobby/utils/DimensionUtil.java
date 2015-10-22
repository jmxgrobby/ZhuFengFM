package com.jmxgrobby.utils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/22.
 */

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 这个工具用于进行尺寸计算
 */
public class DimensionUtil {
    private  DimensionUtil(){}

    //根据当前手机的屏幕密度进行dp-》px单位的换算
    //上下文用于获取屏幕信息
    public static int dpTopx(Context context,int dp){
        int ret = -1;
        //获取屏幕的测量信息
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            ret = (int) (displayMetrics.density*dp);
        return ret;
    }
}
