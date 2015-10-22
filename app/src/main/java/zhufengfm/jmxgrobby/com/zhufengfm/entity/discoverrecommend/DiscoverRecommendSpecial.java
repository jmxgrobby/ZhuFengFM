package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 精品听单的推荐，内部包含多个听单信息
 */
public class DiscoverRecommendSpecial extends DiscoverRecommenItem {
    List<SpecialItem> datas ;

    public List<SpecialItem> getDatas() {
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

                        SpecialItem specialtem = new SpecialItem() ;

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