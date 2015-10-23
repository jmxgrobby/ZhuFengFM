package zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/23.
 */
public class AlbumEntity extends  AlbumItem{

/*
    "albumId": 269179,
            "categoryId": 8,
            "categoryName": "商业财经",
            "title": "吴晓波频道",
            "coverOrigin": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347.jpg",
            "coverSmall": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347_mobile_small.jpg",
            "coverMiddle": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347_mobile_meduim.jpg",
            "coverLarge": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347_mobile_large.jpg",
            "coverWebLarge": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347_web_large.jpg",
            "createdAt": 1406541586000,
            "updatedAt": 1436159148000,
            "uid": 12495477,
            "nickname": "吴晓波频道",
            "isVerified": true,
            "avatarPath": "http://fdfs.xmcdn.com/group4/M04/D0/51/wKgDtFP2qljgWGrYAAR86TSbKQg782_mobile_small.jpg",
            "intro": " 著名财经作家吴晓波，推出国内第一档财经脱口秀。只与最好玩的商业世界有关：细数那些企业家们犯过的错、解析经济发展的大小事件、讲述财经热点背后的故事、梳理与商业相关的八卦绯闻……或许你在商业世界遇到的种种谜团，都能在这里找到答案。 ",
            "introRich": " 著名财经作家吴晓波，推出国内第一档财经脱口秀。只与最好玩的商业世界有关：细数那些企业家们犯过的错、解析经济发展的大小事件、讲述财经热点背后的故事、梳理与商业相关的八卦绯闻……或许你在商业世界遇到的种种谜团，都能在这里找到答案。",
            "tags": "吴晓波,吴晓波频道,马云,中国经济,错误",
            "tracks": 60,
            "shares": 0,
            "hasNew": false,
            "isFavorite": false,
            "playTimes": 27152030,
            "status": 1,
            "serializeStatus": 0,
            "serialState": 0
            */

    private long albumId;//曲目id
    private long categoryId;
    private String categoryName;
    private String title;
    private String coverOrigin;
    private String coverSmall;
    private String coverLarge;
    private String coverWebLarge;
    private long createdAt;
    private long updatedAt;
    private long uid;
    private String nickname;
    private boolean isVerified;
    private String avatarPath;
    private String intro;
    private String introRich;
    private String tags;
    private int tracks;
    private int shares;
    private boolean hasNew;
    private boolean isFavorite;
    private long playTimes;
    private int status;
    private int serializeStatus;

    public void parseJSON(JSONObject json){
        try {
            title = json.getString("title");
            coverLarge = json.getString("coverLarge");
            coverOrigin = json.getString("coverOrigin");
            coverSmall = json.getString("coverSmall");
            coverWebLarge = json.getString("coverWebLarge");
            avatarPath = json.getString("avatarPath");
            intro = json.getString("intro");
            tags = json.getString("tags");
            playTimes = json.getLong("playTimes");
            tracks = json.getInt("tracks");
            nickname = json.getString("nickname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverOrigin() {
        return coverOrigin;
    }

    public void setCoverOrigin(String coverOrigin) {
        this.coverOrigin = coverOrigin;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public String getCoverWebLarge() {
        return coverWebLarge;
    }

    public void setCoverWebLarge(String coverWebLarge) {
        this.coverWebLarge = coverWebLarge;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public long getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(long playTimes) {
        this.playTimes = playTimes;
    }

    public AlbumEntity() {
        this.tag = "albums";
    }
}
