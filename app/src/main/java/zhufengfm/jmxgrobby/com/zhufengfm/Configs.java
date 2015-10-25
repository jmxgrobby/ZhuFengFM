package zhufengfm.jmxgrobby.com.zhufengfm;

/**
 * Created by Administrator on 15-10-19.
 *
 */

/**
 * 定义常量
 */
public class Configs {
    //欢迎页需要的共享参数
    public static final String SP_NAME = "app";
    //欢迎页显示的版本号，根据其判断是否需要显示欢迎页
    public static final String SP_KEY_WELCOME_SHOW_VER = "wsv";
    //json返回成功和失败判断的唯一标识
    public static final int TASK_RESULT_OK = 0;

    //分类异步任务的action定义
    public static final int TASK_ACTION_DISCOVER_CATEGORIES = 1;

    //发现推荐一步任务action定义
    public static final int TASK_ACTION_DISCOVER_RECOMMENDS = 2;

    //专辑详情异步任务定义
    public static final int TASK_ACTION_ALBUM = 3;

    //是否为开发版本
    public static final boolean  ISDEBUG = true;



    //专辑接口
    public static final String DISCOVER_ALBUM ="http://mobile.ximalaya.com/mobile/others/ca/album/track/%s/true/1/20";

    //音乐播放类型 点击推荐列表进入 传入一个List集合
    public static final String MUSIC_TYPE_RECOMMEND = "typ1";


    //音乐播放界面的广播，接受服务传来的广播
    public static final String SERVICETOMUSIC_BROADCAST  = "servicetomusic";
    //服务里面的广播，接受音乐播放界面调的进度广播
    public static final String MUSICTOSERVICE_BROADCAST = "musictoservice";
}
