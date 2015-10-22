package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/22.
 */
public class DiscoverRecommendHead {

    /**
     * id : 4803
     * shortTitle : 后代太放浪，传世佳作被逼问世
     * longTitle : 后代太放浪，传世佳作被逼问世
     * pic : http://fdfs.xmcdn.com/group7/M06/89/B2/wKgDX1YnYCHzvag3AAIoDepSnPg420_android_large.jpg
     * type : 3
     * uid : 1389489
     * trackId : 9297041
     * isShare : false
     * is_External_url : false
     */

    private int id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private int uid;
    private int trackId;
    private boolean isShare;
    private boolean is_External_url;

    public void parseJSON(JSONObject json){
        try {
            id = json.getInt("id");
            shortTitle = json.optString("shortTitle");
            longTitle = json.optString("longTitle");
            pic = json.optString("pic");
            type  = json.optInt("type");
            uid = json.optInt("uid");
            trackId = json.optInt("trackId");
            isShare = json.getBoolean("isShare");
            is_External_url = json.optBoolean("is_External_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setIsShare(boolean isShare) {
        this.isShare = isShare;
    }

    public void setIs_External_url(boolean is_External_url) {
        this.is_External_url = is_External_url;
    }

    public int getId() {
        return id;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getPic() {
        return pic;
    }

    public int getType() {
        return type;
    }

    public int getUid() {
        return uid;
    }

    public int getTrackId() {
        return trackId;
    }

    public boolean isIsShare() {
        return isShare;
    }

    public boolean isIs_External_url() {
        return is_External_url;
    }
}
