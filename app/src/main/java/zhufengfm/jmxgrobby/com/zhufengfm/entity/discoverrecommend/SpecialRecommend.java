package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 精品听单的推荐，内部包含多个听单信息
 */
public class SpecialRecommend extends DiscoverRecommenItem {
    List<DiscoverRecommendSpecialItem> datas ;

    public List<DiscoverRecommendSpecialItem> getDatas() {
        return datas;
    }

    @Override
    public void parseJSON(JSONObject json) {
        super.parseJSON(json);
        if (json != null) {
            JSONArray list = null;
            try {
                list = json.getJSONArray("list");
                int len = list.length() ;

                // TODO: 2015/10/22 解析List

                if (len>0){
                    datas = new ArrayList<>() ;

                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject = list.getJSONObject(i) ;

                        DiscoverRecommendSpecialItem specialtem = new DiscoverRecommendSpecialItem() ;

                        specialtem.parseJson(jsonObject);

                        datas.add(specialtem) ;
                    }



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}