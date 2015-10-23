package zhufengfm.jmxgrobby.com.zhufengfm.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/23.
 */

/**
 * 自定义扩充尺寸的ListView 适用于ScrollView +ListView
 */
public class FullListView extends ListView {
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec
                (Integer.MAX_VALUE>>4,
                        MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
