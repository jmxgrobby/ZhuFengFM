package zhufengfm.jmxgrobby.com.zhufengfm.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;
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
    private Intent progressIntent;
    private float currentPro;
    private ChangeProBroadCast changeProBroadCast;


    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.d("debug111", "开启mediaplayer");
        mediaPlayer = new MediaPlayer();
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        progressIntent = new Intent();
        progressIntent.setAction(Configs.SERVICETOMUSIC_BROADCAST);
        changeProBroadCast = new ChangeProBroadCast();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(changeProBroadCast,new IntentFilter(Configs.MUSICTOSERVICE_BROADCAST));
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
        mediaPlayer.stop();
        //LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(changeProBroadCast);
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }


    //发送进度的广播-》给详情界面
    class  ProgressThread extends  Thread{
        @Override
        public void run() {
           while(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                sumLength = mediaPlayer.getDuration();
               currentLength = mediaPlayer.getCurrentPosition();
               //当前播放的曲目对象
               progressIntent.putExtra("item",list.get(currentPlayer));
                currentPro =(float) currentLength*100000/sumLength;
               progressIntent.putExtra("currentPro", currentPro);
                 LocalBroadcastManager.getInstance(
                       getApplicationContext()).
                       sendBroadcast(progressIntent);
           }
        }
    }


    /**
     * 接收从播放界面传来的数据信息进行判断是上下一首暂停的判断还是进度的调整
     */
    class ChangeProBroadCast extends  BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String tag = intent.getStringExtra("tag");
                if("next".equals(tag)){
                    if(currentPlayer!=list.size()-1){
                        currentPlayer++;
                        playMusic();
                    }else
                        Toast.makeText(getApplicationContext(),"没有下一首",Toast.LENGTH_SHORT).show();
                }else if("before".equals(tag)){
                    if(currentPlayer!=0){
                        currentPlayer--;
                        playMusic();
                    }else
                        Toast.makeText(getApplicationContext(),"没有上一首",Toast.LENGTH_SHORT).show();
                }else if("pause".equals(tag)){
                    if(mediaPlayer.isPlaying())
                        mediaPlayer.pause();
                    else
                        mediaPlayer.start();
                }else if("change".equals(tag)){
                    MyLog.d("debug11","调整进度的位置");
                    int progress = intent.getIntExtra("progress",currentLength);
                    float atterChange =  (float)progress*sumLength/100000;
                    mediaPlayer.seekTo((int) atterChange);
                }
            }
        }
    }

}
