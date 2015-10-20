package zhufengfm.jmxgrobby.com.zhufengfm.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import zhufengfm.jmxgrobby.com.zhufengfm.R;
import zhufengfm.jmxgrobby.com.zhufengfm.adapters.CommonFragmentPagerAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.discover.*;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements TabLayout.OnTabSelectedListener {


    private ViewPager viewPager;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false) ;

        TabLayout tabLayout =
                (TabLayout)view.findViewById(R.id.discover_top_tab_bar) ;


        //初始化ViPager
        viewPager = (ViewPager)view.findViewById(R.id.discover_view_pager);

        List<BaseFragment> list = new LinkedList<BaseFragment>();
        list.add(new DiscoverRecommendFragment());
        list.add(new DiscoverCategoryFragment());
        list.add(new DiscoverLiveFragment());
        list.add(new DiscoverRatingFragment());
        list.add(new DiscoverAnchorFragment());

        CommonFragmentPagerAdapter pagerAdapter =
                new CommonFragmentPagerAdapter(getChildFragmentManager(),list);

        viewPager.setAdapter(pagerAdapter);
        //ViewPager 和TabLayout的联动，封装了两者之间的联动
        //需要ViewPager内部指定的Adapter 必须要重写getPageTtitle方法
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        return view ;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
