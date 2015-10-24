package zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.DimensionUtil;
import zhufengfm.jmxgrobby.com.zhufengfm.utils.MyLog;
import org.json.JSONException;
import org.json.JSONObject;
import zhufengfm.jmxgrobby.com.zhufengfm.AlbumDetailActivity;
import zhufengfm.jmxgrobby.com.zhufengfm.Configs;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.adapters.DiscoverRecommendAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.adapters.PicPagerAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.AlbumRecommend;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommenItem;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommendAlbums;
import zhufengfm.jmxgrobby.com.zhufengfm.entity.discoverrecommend.DiscoverRecommendHead;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.BaseFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.DiscoverRecommendTask;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskCallback;
import zhufengfm.jmxgrobby.com.zhufengfm.tasks.TaskResult;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiscoverRecommendFragment extends BaseFragment implements TaskCallback, View.OnClickListener {


    private DiscoverRecommendAdapter adapter;
    private ListView listview;
    private List<DiscoverRecommenItem> list;

    //轮播海报
    private ViewPager focusImageVpager;
    private PicPagerAdapter picPagerAdapter;
    private List<DiscoverRecommendHead> headList;

    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new LinkedList<>();
        adapter = new DiscoverRecommendAdapter(getActivity(), list);
        adapter.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_discover_recommend, container, false);
        listview = (ListView) inflate.findViewById(R.id.discover_recommend_listview);

        focusImageVpager = new ViewPager(getActivity());
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DimensionUtil.dpTopx(getActivity(),180)
        ) ;

        focusImageVpager.setLayoutParams(lp);
        headList = new LinkedList();
        picPagerAdapter = new PicPagerAdapter(headList,getActivity());

        focusImageVpager.setAdapter(picPagerAdapter);
        listview.addHeaderView(focusImageVpager);
        focusImageVpager.setOnClickListener(this);
        // TODO 广告栏响应事件

        View footView  = LayoutInflater.from(getActivity())
                .inflate(R.layout.discover_recommend_footview,null,false);
        listview.addFooterView(footView);
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
                    if(data!=null&& data instanceof HashMap){
                        HashMap<String,Object>  map = (HashMap<String, Object>) data;
                        if(map.get("list")!=null&&map.get("list") instanceof  List){
                            List dataList = (List) map.get("list");
                             //只要数据来了 就清除adapter
                             list.clear();
                            for (Object o :dataList) {
                                if(o instanceof  DiscoverRecommenItem){
                                    list.add((DiscoverRecommenItem) o);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        if(map.get("head")!=null&&map.get("head")instanceof List){
                            headList.clear();
                            headList.addAll( (List<DiscoverRecommendHead>)map.get("head"));
                            picPagerAdapter.notifyDataSetChanged();
                            MyLog.d("debug111","head长度为"+headList.size());
                        }

                    }else if(data instanceof JSONObject){
                        try {
                            //1 解析轮播海报
                            JSONObject jsonObject = ((JSONObject) data).getJSONObject("focusImages");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    MyLog.d("debug111","Recommends错误");
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if(tag instanceof  String){
            String[] strings = ((String) tag).split(":");
            DiscoverRecommendAlbums item = (DiscoverRecommendAlbums) list.get(Integer.parseInt(strings[0]));
            AlbumRecommend albumRecommend = item.getList().get(Integer.parseInt(strings[1]));
            //17 专辑详情
            Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
            intent.putExtra("albumId", ""+albumRecommend.getAlbumId());
            getActivity().startActivity(intent);
           // Toast.makeText(getActivity(), "点击了" + albumRecommend.getTitle(), Toast.LENGTH_SHORT).show();
        }else if(tag instanceof  Integer){
            DiscoverRecommendAlbums item = (DiscoverRecommendAlbums) list.get((int) tag);
            Toast.makeText(getActivity(), "点击了更多" + item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
