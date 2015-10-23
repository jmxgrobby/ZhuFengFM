package com.jmxgrobby.View;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
/**
 * 所有的容器在显示内容之前 需要进行排版，排版的时候都需要获取内部子空间的尺寸
 * 所有的View内部都有一个回调方法 onMeasure方法，是控件自身测量自己尺寸的方法
 *onMeasure实际上就是控件算自己的尺寸
 * 当ListView被ScrollView保函的时候，那么onMeasure方法，接受的参数有一些不同：
 * 普通wrap_content 会对应一个参数形式：AT_MOST
 *         match_parent 对应EXACTLY
 *  ScrollView内部的控件进行尺寸计算onMeasure（）的时候
 *      参数模式时UNSPECIFIED
 *      ListView onMeasure方法，检测模式 如果是UNSPECIFIED 永远只有一行
 * listview里面嵌套listview，子listview高度的设定
 * @author Administrator
 *
 */
public class Utility {    
    public static void setListViewHeightBasedOnChildren(ListView listView) {    
        ListAdapter listAdapter = listView.getAdapter();     
        if (listAdapter == null) {    
            // pre-condition    
            return;    
        }    
    
        int totalHeight = 0;    
        for (int i = 0; i < listAdapter.getCount(); i++) {    
            View listItem = listAdapter.getView(i, null, listView);    
            listItem.measure(View.MeasureSpec.UNSPECIFIED,
                    View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();    
        }    
    
        ViewGroup.LayoutParams params = listView.getLayoutParams();    
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
        listView.setLayoutParams(params);    
    }    
}    