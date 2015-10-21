package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.AlbumBasic;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-21
 */
public class AlbumRecommend extends AlbumBasic{
    /*
    {

        "tags": "翻唱|原创,文艺|小众,清新|催眠",
        "serialState": 0,
        "playsCounts": 133347,
        "isFinished": 0,

        "trackId": 9333769,
        "trackTitle": "3D:睡不着就听听星夜的声音"
        }
     */

    private long playCounts;
    private int isFinished;
    private long trackId;
    private String trackTitle;

    public void parseJSON(JSONObject jsonObject){
        super.parseJSON(jsonObject);
        if (jsonObject != null) {
            playCounts = jsonObject.optLong("playCounts", 0);
            isFinished = jsonObject.optInt("isFinished", 0);
            try {
                trackId = jsonObject.getLong("trackId");
                trackTitle = jsonObject.getString("trackTitle");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
