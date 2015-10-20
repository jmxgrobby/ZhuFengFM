package zhufengfm.jmxgrobby.com.zhufengfm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.CustomFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.DiscoverFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.DownLoadTingFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.PersonalFragment;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    /**
     * 主界面中第一层fragment 发现 定制听 下载听 我
     *
     * @param savedInstanceState
     */
    int current = 0;//记录当前fragment页
    private Fragment[] fragments;
    private FragmentTransaction tx;
    private FragmentManager manager;

    private RadioButton test;

    private static boolean isLoad = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);
        test = (RadioButton) findViewById(R.id.main_tab_item_discover);
        // TODO 将以下代码整合成一个方法
        Drawable[] compoundDrawables = test.getCompoundDrawables();
        compoundDrawables[1].setBounds(0,0,80,80);
        test.setCompoundDrawables(compoundDrawables[0],compoundDrawables[1],compoundDrawables[2],compoundDrawables[3]);

        if(savedInstanceState==null){
            fragments = new Fragment[4];
                fragments[0] = new DiscoverFragment();
                fragments[1] = new CustomFragment();
                fragments[2] = new DownLoadTingFragment();
                fragments[3] = new PersonalFragment();
        }else{
            fragments = (Fragment[]) savedInstanceState.getSerializable("fragments");
            current = savedInstanceState.getInt("cur");
            isLoad = true;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //采用hide和show的形式，进行处理
        Log.d("debug111","执行onResume方法");
        manager = getSupportFragmentManager();
        tx = manager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
                Log.d("debug111","onResume 添加碎片");
                tx.add(R.id.main_fragment_container, fragments[i], "tag" + i);
                tx.hide(fragments[i]);
        }
        if(isLoad){
            tx.show(fragments[current]);
        }else
            tx.show(fragments[0]);
        tx.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = 0;
        switch (checkedId) {
            case R.id.main_tab_item_discover:
                index = 0;
                break;
            case R.id.main_tab_item_customting:
                index = 1;
                break;
            case R.id.main_tab_item_downloadting:
                index = 2;
                break;
            case R.id.main_tab_item_personal:
                index = 3;
                break;
        }
        int length = fragments.length;
        manager = getSupportFragmentManager();
        tx = manager.beginTransaction();
        for (int i = 0; i < length; i++) {
            if (i == index)
                tx.show(fragments[i]);
            else
                tx.hide(fragments[i]);
        }

        tx.commit();
        current = index;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("fragments", fragments);
        outState.putInt("cur",current);
        tx = manager.beginTransaction();
        for(int i=0;i<4;i++){
            tx.remove(fragments[i]);
        }
        tx.commit();
        super.onSaveInstanceState(outState);
    }

}
