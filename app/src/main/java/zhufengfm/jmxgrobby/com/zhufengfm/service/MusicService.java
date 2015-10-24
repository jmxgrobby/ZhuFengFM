package zhufengfm.jmxgrobby.com.zhufengfm.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.TrackEntity;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;
    private LocalBroadcastManager broadcastManager;
    private int sumLength;
    private int currentLength;
    private int currentPlayer;
    private ArrayList<TrackEntity> list;


    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.d("debug111","开启mediaplayer");
        mediaPlayer = new MediaPlayer();
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       if(intent.getStringExtra("tag").equals(Configs.MUSIC_TYPE_RECOMMEND)){
           list = (ArrayList<TrackEntity>) intent.getSerializableExtra("list");
           currentPlayer= intent.getIntExtra("position",0);
           // 设置新的数据源（新的音频文件），进入初始化状态
           MyLog.d("debug111", "" + currentPlayer);
           playMusic();

       }
        return  super.onStartCommand(intent,flags,startId);
    }

    private void playMusic() {
        mediaPlayer.reset();
        String url = list.get(currentPlayer).getPlayUrl64();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();// 进入就绪状态
            mediaPlayer.start();
            new ProgressThread().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }


    class  ProgressThread extends  Thread{
        @Override
        public void run() {
           while(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                sumLength = mediaPlayer.getDuration();
               currentLength = mediaPlayer.getCurrentPosition();
               if(currentLength>=sumLength){
                   currentPlayer++;
                   playMusic();
               }
           }


        }
    }
}
