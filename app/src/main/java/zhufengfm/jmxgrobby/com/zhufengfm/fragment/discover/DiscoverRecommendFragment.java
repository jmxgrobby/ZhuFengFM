package zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.jmxgrobby.utils.MyLog;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.adapters.DiscoverRecommendAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommenItem;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.BaseFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.DiscoverRecommendTask;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskCallback;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskResult;
import java.util.LinkedList;
import java.util.List;

public class DiscoverRecommendFragment extends BaseFragment implements TaskCallback {


    private DiscoverRecommendAdapter adapter;
    private ListView listview;
    private List<DiscoverRecommenItem> list;

    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new LinkedList<>();
        adapter = new DiscoverRecommendAdapter(getActivity(), list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_discover_recommend, container, false);
        listview = (ListView) inflate.findViewById(R.id.discover_recommend_listview);
        listview.setEnabled(false);
        listview.setAdapter(adapter);
        return inflate;
    }

    /**
     * 因为每次进入推荐的时候，都会触发内容的刷新，因此网络加载，放到onResume中
     */
    @Override
    public void onResume() {
        super.onResume();
        MyLog.d("debug11", "OnResume");
        DiscoverRecommendTask discoverRecommendTask = new DiscoverRecommendTask(this);
        discoverRecommendTask.execute();
    }

    @Override
    public String getFragmentTitle() {
        return "推荐";
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            // TODO 处理推荐列表数据
            int action = result.action;
            MyLog.d("debug11","进入回调");
            if(action== Configs.TASK_ACTION_DISCOVER_RECOMMENDS){
                if(result.resultCode ==Configs.TASK_RESULT_OK){
                    MyLog.d("debug11","正确的回调");
                    Object data = result.data;
                    if(data!=null&& data instanceof List){
                        List dataList = (List) data;
                        //只要数据来了 就清除adapter
                        list.clear();
                        for (Object o :dataList) {
                            if(o instanceof  DiscoverRecommenItem){
                                list.add((DiscoverRecommenItem) o);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    MyLog.d("debug111","Recommends错误");
                }
            }
        }

    }
}
