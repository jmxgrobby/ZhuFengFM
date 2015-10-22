package zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 发现新奇
 */
public class DiscoverRecommenColumns extends DiscoverRecommenItem {
    private List<ColumnsRecommendItem> list;

    public List<ColumnsRecommendItem> getList() {
        return list;
    }

    @Override
    public void parseJSON(JSONObject jsonObject) {
        super.parseJSON(jsonObject);
        try {
            JSONArray array = jsonObject.getJSONArray("list");
            if(array!=null){
                list = new LinkedList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jo = array.getJSONObject(i);
                    ColumnsRecommendItem columns = new ColumnsRecommendItem();
                    columns.parseJSON(jo);
                    list.add(columns);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}