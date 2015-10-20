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
}
