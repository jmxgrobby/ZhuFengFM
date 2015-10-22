package zhufengfm.jmxgrobby.com.zhufengfm.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/10/22.
 */
public interface Parsable  {
    void parseJson(JSONObject json) throws JSONException;
}
