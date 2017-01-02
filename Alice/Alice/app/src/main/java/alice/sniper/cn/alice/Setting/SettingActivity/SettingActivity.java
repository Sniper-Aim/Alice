package alice.sniper.cn.alice.Setting.SettingActivity;

import android.app.Activity;
import android.os.Bundle;

import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.R;

/**
 * Created by pei_song on 2016/12/28.
 */

/**
 * 设置界面Activity  用于Alice的设置, Alice的部分属性只能设置一次.
 */
public class SettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
    }
}
