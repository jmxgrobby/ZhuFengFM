package zhufengfm.jmxgrobby.com.zhufengfm.entity;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-21
 */

import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.AlbumRecommend;

/**
 * 专辑基本信息
 * @see AlbumRecommend
 */
public class AlbumBasic {
    /**
     * albumId : 3005249
     * coverLarge : http://fdfs.xmcdn.com/group16/M01/7A/D3/wKgDbFYKqqHCamtQAAIv621_oBE247_mobile_large.jpg
     * title : 3D环绕音乐
     * tracks : 75
     */

    private long albumId;
    private String coverLarge;
    private String title;
    private long tracks;
    private String tags;

    public void parseJSON(JSONObject json){
        if (json != null) {
            try {
                albumId = json.getLong("albumId");
                coverLarge = json.getString("coverLarge");
                title = json.getString("title");
                tracks = json.getLong("tracks");
                tags = json.getString("tags");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTracks(long tracks) {
        this.tracks = tracks;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public String getTitle() {
        return title;
    }

    public long getTracks() {
        return tracks;
    }
}
