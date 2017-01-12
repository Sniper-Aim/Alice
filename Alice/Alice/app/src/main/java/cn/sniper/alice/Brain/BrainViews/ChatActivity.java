package cn.sniper.alice.Brain.BrainViews;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/12.
 */

public class ChatActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_chat);
    }
}
