package zhufengfm.jmxgrobby.com.zhufengfm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences sp = getSharedPreferences(Configs.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(Configs.SP_KEY_WELCOME_SHOW_VER,BuildConfig.VERSION_CODE);
        edit.apply();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    public void btnJump(View view) {
        onBackPressed();
    }
}
