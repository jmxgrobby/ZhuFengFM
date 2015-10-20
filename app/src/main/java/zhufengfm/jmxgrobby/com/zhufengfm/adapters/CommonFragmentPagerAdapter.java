package zhufengfm.jmxgrobby.com.zhufengfm.adapters;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.BaseFragment;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date: 15-10-20
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public CommonFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(fragments!=null)
            ret = fragments.size();
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       String ret = null;
        BaseFragment fragment = (BaseFragment) fragments.get(position);
        notifyDataSetChanged();
        ret = fragment.getFragmentTitle();
        return  ret;
    }
}
