package cn.sniper.alice.Brain.BrainViews.Chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import cn.sniper.alice.Brain.BrainView.Adapter.FragmentAdapter.ChatFragmentAdapter;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/12.
 */

public class ChatActivity extends FragmentActivity {

    /**
     * 聊天页ViewPage, 包含切换的Fragment
     * ViewPageLimit : ViewPage预加载页数
     */
    private ChatViewPager viewPager;
    private int ViewPageLimit = 2;

    /**
     * Fragment适配器
     */
    private ChatFragmentAdapter fragmentAdapter;


    /**
     * 初始化布局
     */
    private void initView(){
        viewPager= (ChatViewPager) findViewById(R.id.chat_viewpager);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        /**
         * 初始化主页的Fragment的Adapter
         */
        fragmentAdapter = new ChatFragmentAdapter(getSupportFragmentManager());
    }

    /**
     * 初始化事件
     */
    private void initEvent(){
        /**
         * 设置ViewPager的适配器
         */
        viewPager.setOffscreenPageLimit(ViewPageLimit);
        viewPager.setAdapter(fragmentAdapter);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_chat);

        /*  初始化操作  */
        initView();
        initData();
        initEvent();
    }

}
