package alice.sniper.cn.alice.Setting.SettingActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import alice.sniper.cn.alice.R;
import alice.sniper.cn.alice.Setting.SettingActivity.ActivityDialog.SettingPromptDialog;

/**
 * Created by pei_song on 2016/12/28.
 */

/**
 * 设置界面Activity  用于Alice的设置, Alice的部分属性只能设置一次.
 */
public class SettingActivity extends Activity {

    /**
     * 申明 Prompt Dialog
     */
    private SettingPromptDialog settingPromptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        /**
         * 由于这个Activity不会被经常调用, 而Alice的属性也不能重复设置, 所以
         * 这里调用Dialog提示用户是否要对Alice进行设置, 一旦设置完成将不能更改
         */
        initDialog();
    }


    /**
     * Dialog提示Alice只能设置一次
     */
    public void initDialog(){
        settingPromptDialog = new SettingPromptDialog(this, R.style.MyDialog);
        settingPromptDialog.setTitle("");
        settingPromptDialog.setText("");
        settingPromptDialog.setOKButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //TODO 点击确定后要做的事情
                settingPromptDialog.dismiss();
            }
        });
        settingPromptDialog.show();
    }
}
