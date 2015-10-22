package zhufengfm.jmxgrobby.com.zhufengfm.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.*;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-21
 */
public class DiscoverRecommendAdapter extends BaseAdapter {

    private Context context;
    private List<DiscoverRecommenItem> list;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig config;

    public DiscoverRecommendAdapter(Context context, List<DiscoverRecommenItem> list) {
        this.context = context;
        this.list = list;
        bitmapUtils = new BitmapUtils(context);
        initBitmapUtils();
    }

    private void initBitmapUtils() {
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
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        //1 获取数据
        DiscoverRecommenItem item = list.get(position);

        if(item instanceof DiscoverRecommendAlbums){//小说推荐
            ret  = bindAlbumsView(position,convertView,parent);
        }else if(item instanceof DiscoverRecommenColumns){//发现新奇
            ret  = bindColumnView(position,convertView,parent);
        }else if(item instanceof DiscoverRecommendSpecial){//精品听单
            ret  = bindSpecialView(position,convertView,parent);
        }

        return ret;
    }

    /**
     * 加载 专辑推荐的Item 小编推荐 入门推荐爱你
     * @param position
     * @param contentView
     * @param parent
     * @return
     */
    private View bindAlbumsView(int position,View contentView,ViewGroup parent){
       View ret = null;
        if(contentView!=null){
            ret = contentView;
        }else
            ret = LayoutInflater.from(context).inflate(R.layout.discover_recommend_album_item,parent,false);
        //2 ViewHolder
        AlbumViewHolder holder= (AlbumViewHolder) ret.getTag();
        if(holder==null){
            holder = new AlbumViewHolder();
            holder.txtMore = (TextView) ret.findViewById(R.id.recommend_album_more);
            holder.txtTitle = (TextView) ret.findViewById(R.id.recommend_album_title);

            holder.albumIcons = new ImageView[3];
            holder.albumNames = new TextView[3];
            holder.trackNames = new TextView[3];
            for (int i = 0; i < 3; i++) {
                //给holder设置数组的内容
                holder.albumIcons[i] = (ImageView) findView(ret,"recommend_album_icon_"+i);
                holder.albumNames[i] = (TextView) findView(ret,"recommend_album_name_"+i);
                holder.trackNames[i] = (TextView) findView(ret,"recommend_album_track_"+i);

            }

            ret.setTag(holder);
        }
       //3 获取内容
        DiscoverRecommendAlbums albums = (DiscoverRecommendAlbums) list.get(position);
        holder.txtTitle.setText(albums.getTitle());


        holder.txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了更多",Toast.LENGTH_SHORT).show();
            }
        });

        if(!albums.isHasMore())
            holder.txtMore.setVisibility(View.INVISIBLE);
        List<AlbumRecommend> albumsList = albums.getList();
        for (int i = 0; i <3 ; i++) {
            final AlbumRecommend recommend = albumsList.get(i);
            holder.albumNames[i].setText(recommend.getTitle());
            holder.trackNames[i].setText(recommend.getTrackTitle());
            bitmapUtils.display(holder.albumIcons[i], recommend.getCoverLarge(), config);
            holder.albumIcons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点击了"+recommend.getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        return ret;
    }

    /**
     * 利用类的反射来获取指定的R.id.xxx代表的View
     * @param container
     * @param fieldName
     * @return
     */
    public static View findView(View container,String fieldName){
      View ret = null;
        if (container != null&&fieldName!=null) {
            Class<R.id> idClass = R.id.class;
            try {
                Field field = idClass.getDeclaredField(fieldName);
                int id = field.getInt(idClass);
                //通过静态常量获取整形int值来查找View
                ret = container.findViewById(id);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    /**
     * 小编推荐的ViewHolder
     */
    private static class AlbumViewHolder{
        public TextView txtTitle;
        public TextView txtMore;
        public ImageView[] albumIcons;//三张图片，在不同的RelativeLayout里面
        public TextView[] albumNames;//三个专辑标题
        public TextView[] trackNames;//三个曲目名称
    }

    /**
     *
     * @param position
     * @param contentView
     * @param viewGroup
     * @return
     */
    private View bindColumnView(int position,View contentView,ViewGroup viewGroup){
        View ret = null;
        return ret;
    }

    /**
     *  精品听单
     * @param position
     * @param contentView
     * @param viewGroup
     * @return
     */
    private View bindSpecialView(int position,View contentView,ViewGroup viewGroup){
        View ret = null;
        return ret;
    }


    @Override
    public int getViewTypeCount() {
        return 3;//当前listview显示可以显示三种类型，小编推荐/热门推荐，精品听单，发现新奇
    }

    @Override
    public int getItemViewType(int position) {
        int i = -1;
        DiscoverRecommenItem item = list.get(position);

        if(item instanceof DiscoverRecommendAlbums){//小说推荐
            i  = 0;
        }else if(item instanceof DiscoverRecommenColumns){//发现新奇
            i  = 1;
        }else if(item instanceof DiscoverRecommendSpecial) {//精品听单
            i = 2;
        }
        return i;
    }
}
