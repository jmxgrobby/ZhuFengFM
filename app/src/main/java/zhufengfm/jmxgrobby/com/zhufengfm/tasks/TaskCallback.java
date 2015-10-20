package zhufengfm.jmxgrobby.com.zhufengfm.tasks;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */

/**
 * 异步任务回调接口
 * @see TaskResult
 */
public interface TaskCallback {
    /**
     * 当异步任务执行完成的时候，会回调这个方法，将数据结果，传递给相应的实现类
     * @param result 返回结果
     */
    void onTaskFinished(TaskResult result);
}
