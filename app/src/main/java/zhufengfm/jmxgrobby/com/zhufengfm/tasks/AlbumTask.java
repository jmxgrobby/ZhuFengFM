package zhufengfm.jmxgrobby.com.zhufengfm.tasks;


import zhufengfm.jmxgrobby.com.zhufengfm.utils.HttpTools;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.EntityParseUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/10/23.
 */
public class AlbumTask extends BaseTask {
    public AlbumTask(TaskCallback taskCallback) {
        super(taskCallback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret= null;
        MyLog.d("debug111","进入专辑异步任务");
        if(params!=null) {
            ret = new TaskResult();
            ret.action = Configs.TASK_ACTION_ALBUM;
            byte[] datas= HttpTools.doGet(params[0]);
            MyLog.d("debug111","专辑异步任务，网络获取完毕");
            if(datas!=null){
                try {
                    MyLog.d("debug111","专辑异步任务，daas不为空");
                    JSONObject json  = new JSONObject(new String(datas,"utf-8"));
                    if(json!=null){
                        MyLog.d("debug111","进入专辑异步任务，json不为空");
                        ret.resultCode = json.getInt("ret");
                        if(ret.resultCode==0){
                            ret.data = EntityParseUtils.parseAlbumItem(json);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        }
        MyLog.d("debug111","准备返回ret");
        return ret;
    }
}
