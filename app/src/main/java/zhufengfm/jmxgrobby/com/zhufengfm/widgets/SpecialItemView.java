package zhufengfm.jmxgrobby.com.zhufengfm.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.DimensionUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
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
    //听单中的专辑或者曲目的数量
    private TextView txtNumber;
    //箭头
    private ImageView imgArrow;
    //底部线
    private ImageView imgLine;

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
                new LayoutParams(DimensionUtil.dpTopx(context,90),
                        DimensionUtil.dpTopx(context,90));
        //ImageView垂直居中
        params.addRule(CENTER_VERTICAL);
        int margin_5 = DimensionUtil.dpTopx(context, 5);
        params.setMargins(margin_5, margin_5, margin_5, margin_5);
        imageIcon.setLayoutParams(params);
        //设置imageview的id
        imageIcon.setId(R.id.special_item_icon);
        imageIcon.setImageResource(R.mipmap.title_logo);
        addView(imageIcon);

        //-----标题部分-----------
        txtTitle = new TextView(context);
        txtTitle.setId(R.id.special_item_title);
        params= new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_TOP, R.id.special_item_icon);
        params.leftMargin = 20;//单位   像素  需要进行机型适配
        params.addRule(RIGHT_OF, R.id.special_item_icon);
        txtTitle.setText("社交必备其本说活利器");
        txtTitle.setLayoutParams(params);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setSingleLine();
        txtTitle.setTextSize(20);
        addView(txtTitle);

        //------info部分------
        txtSubTitle = new TextView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_LEFT, R.id.special_item_title);
        params.addRule(CENTER_IN_PARENT);

        //单行
        txtSubTitle.setSingleLine();
        txtSubTitle.setId(R.id.special_item_subTitle);
        txtSubTitle.setLayoutParams(params);
        txtSubTitle.setText("让本来就讨人喜欢的你，更讨人喜欢");
        txtSubTitle.setTextSize(16);
        addView(txtSubTitle);

        //--------txtNumber--------
        txtNumber = new TextView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置与图片底部对其，与标题左对齐
        params.addRule(ALIGN_BOTTOM ,R.id.special_item_icon);
        params.addRule(ALIGN_LEFT,R.id.special_item_title);
        txtNumber.setLayoutParams(params);
        txtNumber.setId(R.id.special_item_number);
        txtNumber.setText("10首声音");
        txtNumber.setTextSize(14);
        //给TextView设置左侧图标
        //setCompoundDrawables同时设置四个方向的图标
        //使用v4包获取drawable方式
        Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.finding_album_img);
        drawable.setBounds(0, 0, 20, 20);
        txtNumber.setCompoundDrawables(drawable
                , null, null, null);
        addView(txtNumber);

        //-------------------
        imgArrow = new ImageView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_RIGHT);
        params.addRule(CENTER_VERTICAL);
        imgArrow.setLayoutParams(params);
        imgArrow.setImageResource(R.mipmap.arrow_right);
        addView(imgArrow);

        //--------------imgLine-----------------
        imgLine = new ImageView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,1);//一个像素高度的图片
        params.addRule(ALIGN_LEFT,R.id.special_item_title);
        params.addRule(BELOW,R.id.special_item_number);
        params.addRule(ALIGN_PARENT_RIGHT);
        //指定线与数量之间的间距
        params.setMargins(0, 5, 0, 0);
        imgLine.setLayoutParams(params);
        imgLine.setImageResource(R.drawable.line);

        addView(imgLine);
    }

    /**
     * 设置标题
     * @param str
     */
    public void setTitle(String str){
        txtTitle.setText(str);
    }

    /**
     * 设置描述
     * @param str
     */
    public void setSubTitle(String str){
        txtSubTitle.setText(str);
    }

    /**
     * 数量描述
     * @param str
     */
    public void setNumber(String str){
        txtNumber.setText(str);
    }

    /**
     * 设置底部的线是否显示
     * @param show
     */
    public  void showBottomLine(boolean show){
        if(show){
            imgLine.setVisibility(VISIBLE);
        }else
            imgLine.setVisibility(INVISIBLE);
    }


    public  void setRightArrowResource(int resId){
        imgArrow.setImageResource(resId);
    }

    public void setImageBitmap(BitmapUtils bitmapUtils,String url,BitmapDisplayConfig config) {
        bitmapUtils.display(imageIcon,url,config);
    }
}
