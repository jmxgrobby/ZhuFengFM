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
   // private ChangeProBroadCast changeProBroadCast;


    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.d("debug111", "开启mediaplayer");
        mediaPlayer = new MediaPlayer();
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        progressIntent = new Intent();
        progressIntent.setAction(Configs.SERVICETOMUSIC_BROADCAST);

    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return 代表进程意外种植的情况下，服务应该如何恢复
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String tag = intent.getStringExtra("tag");
        if (tag != null) {
            Intent testIntent = new Intent();
            testIntent.setAction(Configs.MUSICTOSERVICE_BROADCAST);
            MyLog.d("debug111", "接收到的意图的tag为：" + tag);
            if (Configs.MUSIC_TYPE_RECOMMEND.equals(tag)) {
                list = (ArrayList<TrackEntity>) intent.getSerializableExtra("list");
                currentPlayer = intent.getIntExtra("position", 0);
                // 设置新的数据源（新的音频文件），进入初始化状态
                MyLog.d("debug111", "obStartCommand " + currentPlayer+"listsize="+list.size());
                playMusic();
            }else if ("change".equals(tag)) {
                MyLog.d("debug11", " obStartCommand 调整进度的位置");
                int progress = intent.getIntExtra("progress", currentLength);
                float atterChange = (float) progress * sumLength / 100000;
                mediaPlayer.seekTo((int) atterChange);
            }else if ("pause".equals(tag)) {
                if (mediaPlayer.isPlaying()){
                    MyLog.d("debug11", " obStartCommand 暂停"+"listsize="+list.size());
                    mediaPlayer.pause();
                } else {
                    MyLog.d("debug11", " obStartCommand 播放");
                    mediaPlayer.start();
                }
            }else if ("before".equals(tag)) {
                if (currentPlayer != 0) {
                    currentPlayer--;
                    testIntent.putExtra("position",currentPlayer);
                    broadcastManager.sendBroadcast(testIntent);
                    playMusic();
                } else
                    Toast.makeText(getApplicationContext(), "没有上一首", Toast.LENGTH_SHORT).show();
            }else if ("next".equals(tag)) {
                if (currentPlayer != list.size() - 1) {
                    currentPlayer++;
                    testIntent.putExtra("position",currentPlayer);
                    broadcastManager.sendBroadcast(testIntent);
                    playMusic();
                } else
                    Toast.makeText(getApplicationContext(), "没有下一首", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onStartCommand(intent, flags, startId);
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
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //发送进度的广播-》给详情界面
    class ProgressThread extends Thread {
        @Override
        public void run() {

            while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                //当前播放的曲目对象
                sumLength = mediaPlayer.getDuration();
                currentLength = mediaPlayer.getCurrentPosition();
                progressIntent.putExtra("item", list.get(currentPlayer));
                currentPro = (float) currentLength * 100000 / sumLength;
                progressIntent.putExtra("currentPro", currentPro);
                LocalBroadcastManager.getInstance(
                        getApplicationContext()).
                        sendBroadcast(progressIntent);
            }
            // 下徐代码会引起bug，导致暂停时候出错
            //currentPlayer++;
            //playMusic();
        }
    }



}
