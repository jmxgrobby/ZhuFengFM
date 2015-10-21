package zhufengfm.jmxgrobby.com.zhufengfm.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommenItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */

/**
 * 一切实体的list解析生成累
 */
public class EntityParseUtils {
    public EntityParseUtils() {
    }

    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject jsonObject){
        List<DiscoverCategory> ret = null;

        if (jsonObject != null) {
            try {
                int code = jsonObject.getInt("ret");
                if(code== Configs.TASK_RESULT_OK){//获取json成功
                    ret = new LinkedList<>();
                    JSONArray list = jsonObject.getJSONArray("list");
                    if(list.length()>0){
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject object = list.getJSONObject(i);
                            DiscoverCategory category = new DiscoverCategory();
                            //利用实体类内部实现json解析，外部的代码，
                            //调用方便简洁
                            category.parseJSON(object);
                            ret.add(category);
                        }
                    }
                }else{

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    public static List<DiscoverRecommenItem> parseDiscovrRecommend(JSONObject jsonObject) {
        return null;
    }
}
