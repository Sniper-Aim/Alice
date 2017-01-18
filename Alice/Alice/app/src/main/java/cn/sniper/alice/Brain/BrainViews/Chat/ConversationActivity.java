package cn.sniper.alice.Brain.BrainViews.Chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.sniper.alice.ExternalTools.JPush.UserManager;
import cn.sniper.alice.R;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by peisong on 2017/1/16.
 */

public class ConversationActivity extends SwipeBackActivity implements View.OnClickListener{

    private String userId;
    private String userName;

    private UserManager userManager;

    private TextView conversation_username;

    private EditText conversation_edit_information;

    private Button conversation_send_out_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        initView();
        initData();
        initEvent();
    }

    private void initView(){
        conversation_username = (TextView) findViewById(R.id.conversation_username);

        conversation_edit_information = (EditText) findViewById(R.id.conversation_edit_information);

        conversation_send_out_button = (Button) findViewById(R.id.conversation_send_out_button);
    }

    private void initData(){
        userManager = UserManager.getInstance(this);

        userId = this.getIntent().getStringExtra("user_id");
        userName = this.getIntent().getStringExtra("user_name");
        conversation_username.setText(userName);
    }
    private void initEvent(){
        conversation_send_out_button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.conversation_send_out_button:
                userManager.send(userManager.createTextMessage(userId, conversation_edit_information.getText().toString()));
                conversation_edit_information.setText("");
                break;
        }
    }
}
