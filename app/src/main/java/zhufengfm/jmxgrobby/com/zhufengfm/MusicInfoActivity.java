package zhufengfm.jmxgrobby.com.zhufengfm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.TrackEntity;
import zhufengfm.jmxgrobby.com.zhufengfm.service.MusicService;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;

import java.io.Serializable;
import java.math.BigDecimal;

public class MusicInfoActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    @ViewInject(R.id.activity_music_info_back)
    private ImageView info_back;
    @ViewInject(R.id.activity_music_info_music_title)
    private TextView music_title;
    @ViewInject(R.id.activity_music_info_music_number)
    private TextView music_number;
    @ViewInject(R.id.activity_music_info_alarm)
    private ImageView alarm;
    @ViewInject(R.id.activity_music_info_center_img)
    private ImageView centerImg;
    @ViewInject(R.id.activity_music_info_music_list)
    private Button playList;
    @ViewInject(R.id.activity_music_info_music_before)
    private Button playBefore;
    @ViewInject(R.id.activity_music_info_music_pause)
    private Button playPause;
    @ViewInject(R.id.activity_music_info_music_next)
    private Button playNext;
    @ViewInject(R.id.activity_music_info_music_history)
    private Button playHistory;
    @ViewInject(R.id.activity_music_info_bg)
    private ImageView bgImg;
    private SeekBar pro;

    //判断目前是否正在播放
    private boolean isPlaying = true ;

    private boolean isForst = true;
    private int currentPro;

    private BitmapUtils bitmapUtils;
    //广播接收器 用来接收播放信息的广播
    private ProgressBroadReceiver progressBroadReceiver;
    private LocalBroadcastManager instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_info);
        ViewUtils.inject(this);
        progressBroadReceiver = new ProgressBroadReceiver();
        bitmapUtils = new BitmapUtils(this);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(progressBroadReceiver, new IntentFilter(Configs.SERVICETOMUSIC_BROADCAST));
        pro = (SeekBar) findViewById(R.id.activity_music_info_music_progress);
        pro.setMax(100000);

        instance = LocalBroadcastManager.getInstance(this);
        //点击事件声明
        initEvent(info_back, alarm, playList, playBefore, playNext, playPause, playHistory);
        pro.setOnSeekBarChangeListener(this);
    }

    private void initEvent(View... view) {
        if (view != null) {
            for (int i = 0; i < view.length; i++) {
                view[i].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent changeProIntent = new Intent(this,MusicService.class);
        switch (v.getId()) {
            case R.id.activity_music_info_back:
                finish();
                break;
            case R.id.activity_music_info_alarm:
                Toast.makeText(this, "点击了闹钟", Toast.LENGTH_SHORT).show();
                break;
            //下一首
            case R.id.activity_music_info_music_next:
                changeProIntent.putExtra("tag", "next");
                isForst = true;
                startService(changeProIntent);
                break;
            //上一首
            case R.id.activity_music_info_music_before:
                changeProIntent.putExtra("tag", "before");
                isForst = true;
                startService(changeProIntent);
                break;
            //暂停
            case R.id.activity_music_info_music_pause:
                if(isPlaying){
                    playPause.setBackgroundResource(R.mipmap.play_icon_transparent_normal);
                    isPlaying = false;
                }else{
                    playPause.setBackgroundResource(R.mipmap.pause_icon_transparent_normal);
                    isPlaying = true;
                }
                changeProIntent.putExtra("tag", "pause");
                startService(changeProIntent);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    // TODO 滑动调整有点bug 待修复
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Intent changeProIntent;
        changeProIntent = new Intent(this, MusicService.class);
        int progress = seekBar.getProgress();
        changeProIntent.putExtra("tag","change");
        changeProIntent.putExtra("progress", progress);
        startService(changeProIntent);
    }


    /**
     * 进度广播接收器
     */
    class ProgressBroadReceiver extends BroadcastReceiver {

        private TrackEntity trackEntity;

        @Override
        public void onReceive(Context context, Intent intent) {
            currentPro = (int) intent.getFloatExtra("currentPro", 0);
            pro.setProgress(currentPro);
            if(isForst) {
                trackEntity = (TrackEntity) intent.getSerializableExtra("item");
                music_title.setText(trackEntity.getTitle());
                long playtimes = trackEntity.getPlaytimes();
                if (playtimes >= 10000) {
                    float f = (float) playtimes / 10000;
                    BigDecimal bd = new BigDecimal(f);
                    bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                    music_number.setText(bd + "万");
                } else
                    music_number.setText("" + playtimes);
                isForst = false;

                bitmapUtils.display(centerImg, trackEntity.getCoverSmall());
                //bitmapUtils.display(bgImg,trackEntity.getCoverSmall());
            }
        }
    }

}
