package zhufengfm.jmxgrobby.com.zhufengfm.tasks;


import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.client.ClientAPI;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.EntityParseUtils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public class DiscoverRecommendTask extends BaseTask{
    public DiscoverRecommendTask(TaskCallback taskCallback) {
        super(taskCallback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.action = Configs.TASK_ACTION_DISCOVER_RECOMMENDS;
        JSONObject jsonObject = ClientAPI.getDiscoverRecommend("and-f6", true, true);

        if (jsonObject != null) {
            try {
                ret.resultCode = jsonObject.getInt("ret");
                //解析数据
                ret.data = EntityParseUtils.parseDiscoverRecomments(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
