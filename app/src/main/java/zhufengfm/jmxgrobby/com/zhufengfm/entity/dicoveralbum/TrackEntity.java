package zhufengfm.jmxgrobby.com.zhufengfm.entity.dicoveralbum;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.Parsable;

import java.io.Serializable;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/23.
 */
public class TrackEntity extends  AlbumItem implements  Serializable{




    /**
     *  "trackId": 9427338,
     "uid": 12495477,
     "playUrl64": "http://fdfs.xmcdn.com/group12/M02/89/A6/wKgDXFYoz1ezfzYrAPmmff-oc0Q305.mp3",
     "playUrl32": "http://fdfs.xmcdn.com/group16/M01/8A/48/wKgDbFYoz0ziO1BhAHzTY4lFcFQ989.mp3",
     "downloadUrl": "http://download.xmcdn.com/group11/M02/96/5A/wKgDbVYoz0KiDwRsAIGHpN82kYQ158.aac",
     "playPathAacv164": "http://audio.xmcdn.com/group12/M02/89/A6/wKgDXFYoz1OxjFj-APxck0CbZLA107.m4a",
     "playPathAacv224": "http://audio.xmcdn.com/group11/M02/7D/6C/wKgDa1Yoz0XCxr6eAGBUDG9paGI027.m4a",
     "downloadAacUrl": "http://audio.xmcdn.com/group11/M02/7D/6C/wKgDa1Yoz0XCxr6eAGBUDG9paGI027.m4a",
     "title": "吴晓波:\"跑男\"李嘉诚",
     "duration": 2045.09,
     "processState": 2,
     "createdAt": 1445515338000,
     "coverSmall": "http://fdfs.xmcdn.com/group9/M05/33/D5/wKgDZlWTeKfgdqYbAArO7X1ML0w087_web_meduim.jpg",
     "coverMiddle": "http://fdfs.xmcdn.com/group9/M05/33/D5/wKgDZlWTeKfgdqYbAArO7X1ML0w087_web_large.jpg",
     "coverLarge": "http://fdfs.xmcdn.com/group9/M05/33/D5/wKgDZlWTeKfgdqYbAArO7X1ML0w087_mobile_large.jpg",
     "nickname": "吴晓波频道",
     "smallLogo": "http://fdfs.xmcdn.com/group4/M04/D0/51/wKgDtFP2qljgWGrYAAR86TSbKQg782_mobile_small.jpg",
     "userSource": 1,
     "albumId": 269179,
     "albumTitle": "吴晓波频道",
     "albumImage": "http://fdfs.xmcdn.com/group5/M06/D5/8C/wKgDtlSJEZvxIRaKAArO7X1ML0w347_mobile_small.jpg",
     "orderNum": 99999999,
     "opType": 1,
     "isPublic": true,
     "likes": 107,
     "playtimes": 60388,
     "comments": 29,
     "shares": 0,
     "status": 1,
     "downloadSize": 8488868,
     "downloadAacSize": 6312972

     */
    public void TrackEntity(){
        this.tag = "tracks";
    }
    private String playUrl64;
    private String title;
    private long playtimes;
    private int comments;
    private double duration;

    public void parseJSON(JSONObject json){
        try {
            title = json.getString("title");
            playtimes = json.getLong("playtimes");
            comments = json.getInt("comments");
            duration = json.getDouble("duration");
            playUrl64 = json.getString("playUrl64");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getPlayUrl64() {
        return playUrl64;
    }

    public void setPlayUrl64(String playUrl64) {
        this.playUrl64 = playUrl64;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPlaytimes() {
        return playtimes;
    }

    public void setPlaytimes(long playtimes) {
        this.playtimes = playtimes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }



}
