package zhufengfm.jmxgrobby.com.zhufengfm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.CustomFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.DiscoverFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.DownLoadTingFragment;
import zhufengfm.jmxgrobby.com.zhufengfm.fragment.PersonalFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    /**
     * 主界面中第一层fragment 发现 定制听 下载听 我
     *
     * @param savedInstanceState
     */
    int current = 0;//记录当前fragment页
    private Fragment[] fragments;
    private FragmentTransaction tx;
    private FragmentManager manager;

    private TextView  discover_text,download_text,custom_text,personal_text;
    private ImageView discover_image,download_image,custom_image,personal_image;

    private static boolean isLoad = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initTabBar();
        fragments = new Fragment[4];
        if(savedInstanceState==null){
            fragments = new Fragment[4];
                fragments[0] = new DiscoverFragment();
                fragments[1] = new CustomFragment();
                fragments[2] = new DownLoadTingFragment();
                fragments[3] = new PersonalFragment();

            //采用hide和show的形式，进行处理
            manager = getSupportFragmentManager();
            tx = manager.beginTransaction();
            for (int i = 0; i < fragments.length; i++) {
                tx.add(R.id.main_fragment_container, fragments[i], "tag" + i);
                tx.hide(fragments[i]);
            }
            if(isLoad){
                tx.show(fragments[current]);
            }else
                tx.show(fragments[0]);
            tx.commit();
        }else{
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = manager.findFragmentByTag("tag" + i);
            }

        }

    }

    private void initTabBar() {
        View discover_layout = findViewById(R.id.main_tab_item_discover_layout);
        View custom_layout = findViewById(R.id.main_tab_item_custom_layout);
        View download_layout = findViewById(R.id.main_tab_item_download_layout);
        View personal_layout = findViewById(R.id.main_tab_item_personal_layout);
        setEvent(discover_layout, custom_layout, download_layout, personal_layout);

        discover_image = (ImageView) findViewById(R.id.main_tab_item_discover_image);
        custom_image = (ImageView) findViewById(R.id.main_tab_item_custom_image);
        download_image = (ImageView) findViewById(R.id.main_tab_item_download_image);
        personal_image = (ImageView) findViewById(R.id.main_tab_item_personal_image);

        discover_text = (TextView) findViewById(R.id.main_tab_item_discover_text);
        custom_text = (TextView) findViewById(R.id.main_tab_item_custom_text);
        download_text = (TextView) findViewById(R.id.main_tab_item_download_text);
        personal_text = (TextView) findViewById(R.id.main_tab_item_personal_text);

    }

    private void setEvent(View... view) {
        for(int i = 0;i<view.length;i++)
            view[i].setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        resetAll();

        int index = 0;
        int length = fragments.length;
        manager = getSupportFragmentManager();
        tx = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.main_tab_item_discover_layout:
                discover_text.setTextColor(Color.rgb(0xf6,0x00,0x00));
                discover_image.setImageResource(R.mipmap.tab4_down);
                index = 0;
                break;
            case R.id.main_tab_item_custom_layout:
                custom_text.setTextColor(Color.rgb(0xf6,0x00,0x00));
                custom_image.setImageResource(R.mipmap.tab1_down);
                index=1;
                break;
            case R.id.main_tab_item_download_layout:
                download_text.setTextColor(Color.rgb(0xf6,0x00,0x00));
                download_image.setImageResource(R.mipmap.tab2_down);
                index=2;
                break;
            case R.id.main_tab_item_personal_layout:
                personal_text.setTextColor(Color.rgb(0xf6,0x00,0x00));
                personal_image.setImageResource(R.mipmap.tab5_down);
                index =3;
                break;
        }
        for (int i = 0; i < length; i++) {
            if (i == index)
                tx.show(fragments[i]);
            else
                tx.hide(fragments[i]);
        }

        tx.commit();
    }

    private void resetAll() {
        discover_text.setTextColor(Color.BLACK);
        discover_image.setImageResource(R.mipmap.tab4);

        custom_text.setTextColor(Color.BLACK);
        custom_image.setImageResource(R.mipmap.tab1);

        download_text.setTextColor(Color.BLACK);
        download_image.setImageResource(R.mipmap.tab2);

        personal_text.setTextColor(Color.BLACK);
        personal_image.setImageResource(R.mipmap.tab5);
    }
}
