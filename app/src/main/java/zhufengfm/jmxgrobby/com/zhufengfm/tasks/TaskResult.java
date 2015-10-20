package zhufengfm.jmxgrobby.com.zhufengfm.tasks;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */

/**
 * 所有异步任务回调接口中定义的数据，其中包含的action用于代表处理请求的task
 * 让回调接口处理类能够 监测到这个数据从哪里来
 */
public class TaskResult {
    /**
     * 异步任务唯一标识，每一个异步任务的标识都不同
     */
    public int action;
    /**
     *  data为任意数据类型只要接口实现类支持即可
     */
    public Object data;
    /**
     * retsultCode 服务器返回的ret的值 0 代表成功
     */
    public int resultCode = -1;
}
