package zhufengfm.jmxgrobby.com.zhufengfm.client;

import com.jmxgrobby.utils.HttpTools;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public final class ClientAPI {
    /**
     * 单独提取服务器地址的 部分，避免以后更换服务器地址
     */
    public static final String API_POINT = "http://mobile.ximalaya.com";

    private ClientAPI(){

    }

    //-----------------
    // 接口12
    /**
     * 获取发现分类<br/>
     * API地址 ：/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2
     * @return  JSONOBJECT
     */
    public static JSONObject getDiscoverCategories(){
        JSONObject ret = null;
        byte[] data = HttpTools.doGet(API_POINT + "/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2");
        if (data != null) {
            try {
                ret =new JSONObject(new String(data,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    //接口11
    /**
     * 获取发现部分推荐列表的内容，请求网址为/mobile/discovery/v1/recommends?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
     * @param channel 软件发布渠道
     * @param includeActivity 是否包含活动
     * @param includeSpecial 是否包含精品听单
     * @return
     */
    public static JSONObject getDiscoverRecommend(String channel,
                                                  boolean includeActivity,
                                                  boolean includeSpecial){
        JSONObject ret = null;

        if (channel == null) {
            channel  = "and-f6";
        }
        String url = API_POINT + "/mobile/discovery/v1/recommends?" +
                "channel=" + channel +
                "&device=android&" +
                "includeActivity=" +includeActivity+
                "&includeSpecial=" +includeSpecial+
                "&scale=2&version=4.1.7.1";
        byte[] data = HttpTools.doGet(url);
        if (data != null) {
            try {
                ret = new JSONObject(new String(data,"utf-8"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
