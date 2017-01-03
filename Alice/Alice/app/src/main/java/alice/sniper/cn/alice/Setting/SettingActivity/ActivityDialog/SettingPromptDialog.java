package alice.sniper.cn.alice.Setting.SettingActivity.ActivityDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import alice.sniper.cn.alice.R;

/**
 * Created by peisong on 2016/12/16.
 */

public class SettingPromptDialog extends Dialog{

    Context context;

    TextView setting_dialog_prompt_title;

    TextView setting_dialog_prompt_text;

    Button setting_dialog_prompt_ok_button;

    public SettingPromptDialog(Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.setting_prompt_dialog);
        initView();
    }

    public SettingPromptDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        setContentView(R.layout.setting_prompt_dialog);
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initView(){
        setting_dialog_prompt_title = (TextView) findViewById(R.id.setting_dialog_prompt_title);

        setting_dialog_prompt_text = (TextView) findViewById(R.id.setting_dialog_prompt_text);

        setting_dialog_prompt_ok_button = (Button) findViewById(R.id.setting_dialog_prompt_ok_button);

    }

    @Override
    public void setTitle(CharSequence title) {
        this.setting_dialog_prompt_title.setText(title);
    }

    public void setText(CharSequence text){
        this.setting_dialog_prompt_text.setText(text);
    }

    public void setOKButtonListener(View.OnClickListener onClickListener){
        setting_dialog_prompt_ok_button.setOnClickListener(onClickListener);
    }
}
