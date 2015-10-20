package zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmxgrobby.utils.MyLog;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.BaseFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.DiscoverCategoryTask;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskCallback;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverCategoryFragment extends BaseFragment implements TaskCallback {


    public DiscoverCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscoverCategoryTask task = new DiscoverCategoryTask(this);
        task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_category, container, false);
    }


    @Override
    public String getFragmentTitle() {
        return "分类";
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int action = result.action;
            if(action== Configs.TASK_ACTION_DISCOVER_CATEGORIES){
                // TODO 结果从发现 - 分类 任务中返回的，获取的就是分类
                if(result.resultCode==Configs.TASK_RESULT_OK){
                    // TODO 加载成功
                    MyLog.d("测试","成功");
                }else {
                    // TODO 加载失败
                    MyLog.d("测试","失败");
                }
            }else{
                MyLog.d("测试","失败");
            }
        }
    }
}
