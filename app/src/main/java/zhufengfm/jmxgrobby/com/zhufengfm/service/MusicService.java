package zhufengfm.jmxgrobby.com.zhufengfm.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum.TrackEntity;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    //服务创建成功
    public static final int STATE_CREATED  =0;
    //服务销毁
    public static final int STATE_DESTROY  =1;
    //服务准备中
    public static final int STATE_PREPARING = 2;
    //正在播放状态
    private static final int STATE_PLAYING = 3;
    //暂停状态
    private static final int STATE_PAUSE = 4;
    //播放完成状态
    private static final int STATE_STOP = 5;

    /**
     * 使用整形代表播放器的状态
     */
    private int playerState;

    private MediaPlayer mediaPlayer;
    private LocalBroadcastManager broadcastManager;
    private int sumLength;
    private int currentLength;
    private int currentPlayer;
    private ArrayList<TrackEntity> list;
    private Intent progressIntent;
    private float currentPro;

    private String play_tag;
    private Intent changePlayingIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.d("debug111", "开启mediaplayer");
        mediaPlayer = new MediaPlayer();
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        playerState = STATE_CREATED;


        mediaPlayer.setOnPreparedListener(this);

        mediaPlayer.setOnCompletionListener(this);

    }

    /**
     * @param intent
     * @param flags
     * @param startId
     * @return 代表进程意外种植的情况下，服务应该如何恢复
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String tag = intent.getStringExtra("tag");
        if (tag != null) {
            //
            changePlayingIntent = new Intent();
            changePlayingIntent.setAction(Configs.SERVICETOMUSIC);

            progressIntent = new Intent();
            progressIntent.setAction(Configs.SERVICETOMUSICINFO_BROADCAST);
            if (Configs.MUSIC_TYPE_RECOMMEND.equals(tag)) {
                //当音乐不在播放，或者点击的是不同的专辑的时候要更换list
                if (!(playerState==STATE_PLAYING )||
                        ( play_tag != null &&
                                play_tag.equals(intent.getStringExtra("play_tag"))==false
                        )) {
                    list = (ArrayList<TrackEntity>) intent.getSerializableExtra("list");
                    play_tag = intent.getStringExtra("play_tag");

                    MyLog.d("debug11","初次启动播放器");
                    currentPlayer = intent.getIntExtra("position", 0);
                    // 设置新的数据源（新的音频文件），进入初始化状态
                    MyLog.d("debug111", "obStartCommand " + currentPlayer + "listsize=" + list.size());
                    playMusic();
                }
            } else if ("change".equals(tag)) {
                MyLog.d("debug11", " obStartCommand 调整进度的位置");
                int progress = intent.getIntExtra("progress", currentLength);
                float atterChange = (float) progress * sumLength / 100000;
                mediaPlayer.seekTo((int) atterChange);
            } else if ("pause".equals(tag)) {
                if (playerState == STATE_PLAYING) {
                    mediaPlayer.pause();
                    playerState = STATE_PAUSE;
                } else {
                    mediaPlayer.start();
                    playerState = STATE_PLAYING;
                }
            } else if ("before".equals(tag)) {//上一首
                if (currentPlayer != 0) {
                    currentPlayer--;
                    changePlayingIntent.putExtra("position", currentPlayer);
                    broadcastManager.sendBroadcast(changePlayingIntent);
                    playMusic();
                } else
                    Toast.makeText(getApplicationContext(), "没有上一首", Toast.LENGTH_SHORT).show();
            } else if ("next".equals(tag)) {
                if (currentPlayer != list.size() - 1) {
                    currentPlayer++;
                    changePlayingIntent.putExtra("position", currentPlayer);
                    broadcastManager.sendBroadcast(changePlayingIntent);
                    playMusic();
                } else
                    Toast.makeText(getApplicationContext(), "没有下一首", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playMusic() {
        if(playerState==STATE_PLAYING){
            mediaPlayer.pause();
            playerState=STATE_PAUSE;
        }
        mediaPlayer.reset();
        String url = list.get(currentPlayer).getPlayUrl64();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();// 进入就绪状态
            playerState = STATE_PREPARING;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        playerState = STATE_DESTROY;
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        MyLog.d("debug111","prepatring");
        playerState = STATE_PLAYING;
        mediaPlayer.start();
        new ProgressThread().start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        MyLog.d("debug111","触发了onCompletion方法");
//        currentPlayer++;
//        playMusic();
//        Intent changePlayingIntent = new Intent();
//        changePlayingIntent.setAction(Configs.SERVICETOMUSIC);
//        changePlayingIntent.putExtra("position", currentPlayer);
//        broadcastManager.sendBroadcast(changePlayingIntent);
    }


    //发送进度的广播-》给详情界面
    class ProgressThread extends Thread {
        @Override
        public void run() {

            while (mediaPlayer != null && playerState == STATE_PLAYING) {
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
