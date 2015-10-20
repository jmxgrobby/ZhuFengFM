package zhufengfm.jmxgrobby.com.zhufengfm.tasks;

import android.os.AsyncTask;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public abstract class BaseTask extends AsyncTask<String,Void,TaskResult> {

    private TaskCallback taskCallback;

    public BaseTask(TaskCallback taskCallback) {
        this.taskCallback = taskCallback;
    }


    @Override
    protected void onPostExecute(TaskResult result) {
        if(taskCallback!=null){
            taskCallback.onTaskFinished(result);
        }
    }
}
