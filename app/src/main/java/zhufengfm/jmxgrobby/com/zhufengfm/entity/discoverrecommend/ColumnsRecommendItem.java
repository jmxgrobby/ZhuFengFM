package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import com.jmxgrobby.utils.MyLog;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/22.
 */
public class ColumnsRecommendItem {

    @Override
    public String toString() {
        return "ColumnsRecommendItem{" +
                "id=" + id +
                ", orderNum=" + orderNum +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", contentType='" + contentType + '\'' +
                ", url='" + url + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", enableShare=" + enableShare +
                ", contentUpdatedAt=" + contentUpdatedAt +
                '}';
    }

    /**
     * id : 1
     * orderNum : 2
     * title : 听友圈子
     * subtitle : 玩个游戏呗 格式：回答楼上问题+想问楼下问题
     * coverPath : http://fdfs.xmcdn.com/group9/M07/1C/8C/wKgDYlV3rd2zGc9PAAAgRAu1VLU052.png
     * contentType : xzone
     * url :
     * sharePic :
     * enableShare : false
     * contentUpdatedAt : 0
     */

    private int id;
    private int orderNum;
    private String title;
    private String subtitle;
    private String coverPath;
    private String contentType;
    private String url;
    private String sharePic;
    private boolean enableShare;
    private int contentUpdatedAt;
    public void parseJSON(JSONObject jsonObject){
        if(jsonObject!=null){
            MyLog.d("debug111","ci json = "+jsonObject.toString());
            try {
                id = jsonObject.getInt("id");
                orderNum =jsonObject.optInt("orderNum");
                title = jsonObject.getString("title");
                MyLog.d("debug11","ci title = "+title );
                subtitle = jsonObject.getString("subtitle");
                coverPath = jsonObject.getString("coverPath");
                contentType = jsonObject.optString("contentType");
                url = jsonObject.optString("url");
                sharePic = jsonObject.optString("sharePic");
                enableShare = jsonObject.optBoolean("enableShare");
                contentUpdatedAt = jsonObject.optInt("contentUpdatedAt");
            } catch (JSONException e) {
                MyLog.d("debug111","json数据异常");
                e.printStackTrace();
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public void setEnableShare(boolean enableShare) {
        this.enableShare = enableShare;
    }

    public void setContentUpdatedAt(int contentUpdatedAt) {
        this.contentUpdatedAt = contentUpdatedAt;
    }

    public int getId() {
        return id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public String getContentType() {
        return contentType;
    }

    public String getUrl() {
        return url;
    }

    public String getSharePic() {
        return sharePic;
    }

    public boolean isEnableShare() {
        return enableShare;
    }

    public int getContentUpdatedAt() {
        return contentUpdatedAt;
    }
}
