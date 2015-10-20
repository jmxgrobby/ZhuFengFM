package zhufengfm.jmxgrobby.com.zhufengfm.tasks;

import android.os.AsyncTask;
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

/**
 * 发现部分 分类的数据加载异步任务
 */
public class DiscoverCategoryTask extends BaseTask{

    public DiscoverCategoryTask(TaskCallback taskCallback) {
        super(taskCallback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.action  = Configs.TASK_ACTION_DISCOVER_CATEGORIES;
        JSONObject jsonObject = ClientAPI.getDiscoverCategories();
        if (jsonObject != null) {
            try {
                ret.resultCode = jsonObject.getInt("ret");
                if(ret.resultCode == 0){
                    ret.data = EntityParseUtils.parseDiscoverCategories(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


}
