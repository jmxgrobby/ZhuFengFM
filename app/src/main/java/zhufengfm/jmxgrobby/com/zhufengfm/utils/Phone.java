package zhufengfm.jmxgrobby.com.zhufengfm.utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/23.
 */
public class Phone {
    //以宽度为基准的机型适配方法，返回值为高度
    public static int getSuitablePx(int w , int h, WindowManager manager){
        DisplayMetrics displayMetrics = new DisplayMetrics() ;
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels ;
        int height = displayMetrics.heightPixels ;
        MyLog.d("debug111","我的手机参数：width:"+width+"height:"+height) ;
        return (int)(width*1.0/w*h) ;
    }
}
