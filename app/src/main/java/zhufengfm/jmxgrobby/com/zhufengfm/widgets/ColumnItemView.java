package zhufengfm.jmxgrobby.com.zhufengfm.widgets;


import android.content.Context;
import android.graphics.Color;
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

// TODO 发现新奇绘制
public class ColumnItemView extends RelativeLayout{

    private ImageView imgTitle;
    private TextView txtTitle;
    private TextView txtInfo;
    private ImageView imgLine;
    public ColumnItemView(Context context) {
        super(context);
        init(context,null);
    }

    public ColumnItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attributeSe) {
        imgTitle = new ImageView(context);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(DimensionUtil.dpTopx(context, 70),
                        DimensionUtil.dpTopx(context,70));
        //ImageView垂直居中
        params.addRule(CENTER_VERTICAL);
        int margin_5 = DimensionUtil.dpTopx(context, 5);
        params.setMargins(margin_5, margin_5, margin_5, margin_5);
        imgTitle.setLayoutParams(params);
        //设置imageview的id
        imgTitle.setId(R.id.column_item_icon);
        imgTitle.setImageResource(R.mipmap.title_logo);
        addView(imgTitle);

        txtTitle = new TextView(context);
        txtTitle = new TextView(context);
        txtTitle.setId(R.id.column_item_title);
        params= new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_TOP, R.id.column_item_icon);
        params.leftMargin = 20;//单位   像素  需要进行机型适配
        params.addRule(RIGHT_OF, R.id.column_item_icon);
        txtTitle.setText("社交必备其本说活利器");
        txtTitle.setLayoutParams(params);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setSingleLine();
        txtTitle.setTextSize(20);
        addView(txtTitle);

        txtInfo = new TextView(context);

        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置与图片底部对其，与标题左对齐
        params.addRule(ALIGN_BOTTOM ,R.id.column_item_icon);
        params.addRule(ALIGN_LEFT,R.id.column_item_title);
        txtInfo.setLayoutParams(params);
        txtInfo.setId(R.id.column_item_info);
        txtInfo.setText("10首声音");
        txtInfo.setTextSize(14);
        addView(txtInfo);


        //--------------imgLine-----------------
        imgLine = new ImageView(context);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,1);//一个像素高度的图片
        params.addRule(ALIGN_LEFT,R.id.column_item_title);
        params.addRule(BELOW,R.id.column_item_info);
        params.addRule(ALIGN_PARENT_RIGHT);
        //指定线与数量之间的间距
        params.setMargins(0, 5, 0, 0);
        imgLine.setLayoutParams(params);
        imgLine.setImageResource(R.drawable.line);

        addView(imgLine);
    }

    public  void showBottomLine(boolean show){
        if(show){
            imgLine.setVisibility(VISIBLE);
        }else
            imgLine.setVisibility(INVISIBLE);
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
    public void setInfo(String str){
        txtInfo.setText(str);
    }

    public void setImageBitmap(BitmapUtils bitmapUtils, String coverPath,BitmapDisplayConfig config) {
        bitmapUtils.display(imgTitle,coverPath,config);
    }
}
