package zhufengfm.jmxgrobby.com.zhufengfm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.DimensionUtil;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import zhufengfm.jmxgrobby.com.zhufengfm.adapters.AbsAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.AlbumEntity;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.AlbumItem;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.TrackEntity;
import zhufengfm.jmxgrobby.com.zhufengfm.service.MusicService;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.AlbumTask;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskCallback;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskResult;
import zhufengfm.jmxgrobby.com.zhufengfm.widgets.MyScrollView;
import zhufengfm.jmxgrobby.com.zhufengfm.widgets.ScrollViewListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AlbumDetailActivity extends Activity implements View.OnClickListener, TaskCallback, AdapterView.OnItemClickListener, ScrollViewListener {

    private ListView listView;
    private ImageView album_back,album_setting,album_icon,album_small_icon;
    private TextView  album_title,album_info,album_tag,album_person_name;
    //当前播放的是第几首
    private  int currentPosition;

    @ViewInject(R.id.album_detail_collect0)
    private Button collect_0;
    @ViewInject(R.id.album_detail_collect1)
    private Button collect_1;
    @ViewInject(R.id.album_detail_downs0)
    private Button downs_0;
    @ViewInject(R.id.album_detail_downs1)
    private Button downs_1;
    @ViewInject(R.id.album_detail_like0)
    private Button likes_0;
    @ViewInject(R.id.album_detail_like1)
    private Button likes_1;
    @ViewInject(R.id.album_detail_sum_0)
    private TextView sum_0;
    @ViewInject(R.id.album_detail_sum_1)
    private TextView sum1_1;
    @ViewInject(R.id.album_detail_select_0)
    private TextView selector_0;
    @ViewInject(R.id.album_detail_select_1)
    private TextView selector_1;
    @ViewInject(R.id.album_detail_scollview)
    private MyScrollView scrollView;
    @ViewInject(R.id.album_detail_hide_layout)
    private RelativeLayout hide_layout;
    @ViewInject(R.id.album_detail_play)
    private ImageButton play;
    private BitmapUtils bitmapUtils;


    private AbsAdapter<TrackEntity> adapter;
    private ArrayList<TrackEntity> list;
    private Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_album_detail);
        com.lidroid.xutils.ViewUtils.inject(this);
        serviceIntent = new Intent(getApplicationContext(), MusicService.class);
        serviceIntent.putExtra("tag",Configs.MUSIC_TYPE_RECOMMEND);
        String url = String.format(Configs.DISCOVER_ALBUM,getIntent().getStringExtra("albumId"));
        bitmapUtils = new BitmapUtils(this);
        init();
        AlbumTask task = new AlbumTask(this);
        task.execute(url);
    }

    private void init() {
        album_back = (ImageView) findViewById(R.id.album_detail_back);
        album_setting= (ImageView) findViewById(R.id.album_detail_setting);
        album_icon= (ImageView) findViewById(R.id.album_detail_icon);
        album_small_icon= (ImageView) findViewById(R.id.album_detail_person_icon);
        album_title  = (TextView) findViewById(R.id.album_detail_title);
        album_info= (TextView) findViewById(R.id.album_detail_info);
        album_tag= (TextView) findViewById(R.id.album_detail_tag);
        album_person_name= (TextView) findViewById(R.id.album_detail_person_name);
        listView = (ListView) findViewById(R.id.album_detail_listview);
        // 这是测试数据
        list = new ArrayList<>();
        adapter = new AbsAdapter<TrackEntity>(R.layout.discover_album_listview_item,
              this,list  ) {
            @Override
            public void bindView(ViewHolder vHolder, TrackEntity data) {
                TextView title,time,playcount,playtime,takecount;
                ImageView icon,down;
                title = (TextView) vHolder.getView(R.id.album_detail_listview_item_title);
                time = (TextView) vHolder.getView(R.id.album_detail_listview_item_time);
                playcount = (TextView) vHolder.getView(R.id.album_detail_listview_item_playcounts);
                playtime = (TextView) vHolder.getView(R.id.album_detail_listview_item_playtime);
                takecount = (TextView) vHolder.getView(R.id.album_detail_listview_item_takecounts);
                icon = (ImageView) vHolder.getView(R.id.album_detail_listview_item_isplay);

                if(data == list.get(currentPosition)){
                    icon.setVisibility(View.VISIBLE);
                    AnimationDrawable drawable = (AnimationDrawable) icon.getBackground();
                    drawable.start();
                }else
                    icon.setVisibility(View.GONE);
                down = (ImageView) vHolder.getView(R.id.album_detail_listview_item_download);
                down.setTag(data);
                down.setOnClickListener(AlbumDetailActivity.this);
                //标题
                title.setText(data.getTitle());
                //播放次数
                long playtimes = data.getPlaytimes();
                if(playtimes>10000){
                    float f =(float) playtimes/10000 ;
                    BigDecimal bd = new BigDecimal(f);
                    bd = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
                    playcount.setText(bd+"万");
                }
               else{
                    playcount.setText(playtimes +"");
                }
                //评论数
                int comments = data.getComments();
                takecount.setText(comments +"");

                //播放时长
                double duration = data.getDuration();
                int minute = (int) (duration)/60;
                String minuteS = minute>10?minute+"":"0"+minute;
                int secont = (int) duration %60;
                String secoud = secont>10?secont+"":"0"+secont;
                playtime.setText(String.format("%s:%s",minuteS,secoud));
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        // TODO 给ScroView设置滑动监听
        scrollView.setScrollViewListener(this);
        play.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getTag() instanceof TrackEntity){
            TrackEntity tag = (TrackEntity) v.getTag();
            Toast.makeText(this,"点击了下载"+tag.getTitle(),Toast.LENGTH_SHORT).show();
        }else if(v.getId() == R.id.album_detail_play){
            Intent  infoIntent = new Intent(AlbumDetailActivity.this,MusicInfoActivity.class);
            startActivity(infoIntent);
        }
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if(result!=null){
            if(result.action==Configs.TASK_ACTION_ALBUM
                    &&result.resultCode==Configs.TASK_RESULT_OK){
                if(result.data instanceof List){
                    List<AlbumItem >  albumItemList = (List<AlbumItem>) result.data;
                    list.clear();
                    for (int i = 0; i < albumItemList.size(); i++) {
                        if(albumItemList.get(i) instanceof AlbumEntity ){
                            MyLog.d("debug111", "进入if");
                            AlbumEntity albumEntity = (AlbumEntity) albumItemList.get(i);
                            album_title.setText(albumEntity.getTitle());
                            album_info.setText(albumEntity.getIntro());
                            album_person_name.setText(albumEntity.getNickname());
                            album_tag.setText(albumEntity.getTags());
                            bitmapUtils.display(album_icon, albumEntity.getCoverSmall());
                            bitmapUtils.display(album_small_icon,albumEntity.getCoverOrigin());

                            //选集 设置
                            sum1_1.setText("共"+albumEntity.getTracks()+"集");
                            sum_0.setText("共"+albumEntity.getTracks()+"集");
                        }else{
                            TrackEntity track = (TrackEntity) albumItemList.get(i);
                            list.add(track);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    serviceIntent.putExtra("list",list);
                    MyLog.d("debug111", "开启服务");
                    getApplicationContext().startService(serviceIntent);

                }
            }
        }
    }

    //listview点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        serviceIntent.putExtra("position",position);
        currentPosition = position;
        adapter.notifyDataSetChanged();
        startService(serviceIntent);
    }

    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
       if(y>= DimensionUtil.dpTopx(this,160)){
           hide_layout.setVisibility(View.VISIBLE);
          // hide_llayout.setVisibility(View.VISIBLE);
       }else {
           hide_layout.setVisibility(View.INVISIBLE);
           //hide_llayout.setVisibility(View.GONE);
       }

    }
}
