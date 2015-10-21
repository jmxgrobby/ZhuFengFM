package zhufengfm.jmxgrobby.com.zhufengfm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


/**
 * 启动扉页
 */
public class SplashActivity extends FragmentActivity implements  Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        // TODO  如果没有显示欢迎页 那么启动欢迎页，否则直接启动首页
        SharedPreferences sharedPreferences =
                getSharedPreferences(Configs.SP_NAME, MODE_PRIVATE);
        //利用SP保存字段，判断是否显示欢迎页，保存数值一定是程序版本号
        //利用当前程序版本号以及sp中的版本号进行比较，从而判断，这样更精确，兼容性好
        int wsv = sharedPreferences.getInt(Configs.SP_KEY_WELCOME_SHOW_VER, -1);

        if(wsv!=BuildConfig.VERSION_CODE){
            // TODO 显示欢迎页
            intent.setClass(this,WelcomeActivity.class);
        }else{
            //TODO 显示主界面
            intent.setClass(this,MainActivity.class);
        }



        startActivity(intent);
        finish();
    }
}
