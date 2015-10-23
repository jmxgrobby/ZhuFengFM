package zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jmxgrobby.View.Utility;
import com.jmxgrobby.utils.MyLog;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.DiscoverCategory;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.BaseFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.DiscoverCategoryTask;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskCallback;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverCategoryFragment extends BaseFragment implements TaskCallback {


    private ListView listView;
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
        View view =inflater.inflate(R.layout.fragment_discover_category, container, false);
        listView = (ListView) view.findViewById(R.id.discover_categoryfragment_listview);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第"+i+"个");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        return view;
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
//                    List<DiscoverCategory> list = (List<DiscoverCategory>) result.data;
//                    int length = list.size();
//                    int sum = length/6;
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
