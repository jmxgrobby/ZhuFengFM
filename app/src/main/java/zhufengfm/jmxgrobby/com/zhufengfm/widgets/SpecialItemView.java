package zhufengfm.jmxgrobby.com.zhufengfm.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import zhufengfm.jmxgrobby.com.zhufengfm.R;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/22.
 */

/**
 * 用于发现模块中推荐栏目中精品听单的展现
 */
public class SpecialItemView extends RelativeLayout {

    //听单图片
    private ImageView imageIcon;
    //听单标题
    private TextView txtTitle;
    //听单子标题 实际上就是描述
    private TextView txtSubTitle;
    //代码中使用new SpecialItemView的时候 需要使用的构造方法
    public SpecialItemView(Context context) {
        super(context);
        init(context, null);
    }
    //布局xml中使用控件的时候，使用的构造
    public SpecialItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SpecialItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void  init(Context context,AttributeSet attributeSet){
        //初始化控件
        imageIcon = new ImageView(context);
        //手写属性
        //LayoutParams代表在布局中的 android：layout_xxx
        //控件要添加到哪一个容器中就用哪一个容器的layoutparams
        RelativeLayout.LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
        //ImageView垂直居中
        params.addRule(CENTER_VERTICAL);

        imageIcon.setLayoutParams(params);
        //设置imageview的id
        imageIcon.setId(R.id.special_item_icon);
        imageIcon.setImageResource(R.mipmap.title_logo);
        addView(imageIcon);

        //-----标题部分-----------
        txtTitle = new TextView(context);
        txtTitle.setId(R.id.special_item_title);
        params= new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_TOP,R.id.special_item_icon);
        params.leftMargin = 20;//单位   像素  需要进行机型适配
        params.addRule(RIGHT_OF, R.id.special_item_icon);
        txtTitle.setText("社交必备其本说活利器");
        txtTitle.setLayoutParams(params);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        addView(txtTitle);

        //------info部分------
        txtSubTitle = new TextView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_VERTICAL);
        params.addRule(RIGHT_OF, R.id.special_item_icon);
        params.addRule(BELOW,R.id.special_item_title);

        txtSubTitle.setId(R.id.special_item_subTitle);
        txtSubTitle.setLayoutParams(params);
        txtSubTitle.setText("让本来就讨人喜欢的你，更讨人喜欢");
        addView(txtSubTitle);
          }


}
