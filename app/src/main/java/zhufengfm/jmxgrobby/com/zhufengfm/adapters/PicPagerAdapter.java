package zhufengfm.jmxgrobby.com.zhufengfm.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jmxgrobby.utils.DimensionUtil;
import com.jmxgrobby.utils.MyLog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommendHead;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover.DiscoverRecommendFragment;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/22.
 */
public class PicPagerAdapter extends PagerAdapter {
    private List<DiscoverRecommendHead> list;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig config;
    private Context context;

    private View.OnClickListener onClickListener;

    public PicPagerAdapter(List<DiscoverRecommendHead> list,Context context) {
        this.list = list;
        this.context =context;
        bitmapUtils = new BitmapUtils(context);
        //initBitmapConfig();
    }

    private void initBitmapConfig() {
        //设置加载时的图片
        bitmapUtils.configDefaultLoadingImage(R.drawable.a1);
        //设置默认的加载失败的图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.abc_ab_share_pack_mtrl_alpha);
        //设置默认的图片尺寸
        bitmapUtils.configDefaultBitmapMaxSize(new BitmapSize(100, 50));
        bitmapUtils.configDefaultConnectTimeout(5000);

        config = new BitmapDisplayConfig();
        //设置加载中的图片
        Resources resources = context.getResources();
        config.setLoadFailedDrawable(resources.getDrawable(R.drawable.a1));
        config.setLoadingDrawable(resources.getDrawable(android.R.drawable.btn_default));
        config.setBitmapConfig(Bitmap.Config.RGB_565);
    }

    @Override
    public int getCount() {
        int ret = -1;
        if(list.size()>=5)
            ret =  Integer.MAX_VALUE;
        else
            ret = list.size();
        return ret;

    }

    /**
     * 判断View是否是由object创建出来的
     * 通常对于只返回view的pageradapter可以简写成view==object
     * @param view ViewPager内部管理的一个View
     * @param object 由 instantiateItem（）返回的对象
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 根据指定的位置创建一个对象，这个对象可以就是VIew也可以说是管理View的对象
     * 例如fragment。最终在方法返回之前一定要将实际的View添加到container中
     * 并永远不要调用super
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       // TODO 因为getCount返回了整形最大值 实际的数据个数是有限的
        //利用 position %数据个数，从而映射成实际数据的索引
        int index = position%list.size();
        // TODO 根据index 获取点击的位置 以及数据
        ImageView imageView = new ImageView(context);
        MyLog.d("debug111",list.get(index).getPic());
        bitmapUtils.display(imageView,list.get(index).getPic());
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁对象，从容器删除视图
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
