package cn.sniper.alice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;
import com.zcw.togglebutton.ToggleButton;
import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.Brain;
import cn.sniper.alice.Setting.SettingActivity.SettingActivity;

/**
 * Created by pei_song on 2016/12/26.
 */
public class Hi extends Brain implements View.OnClickListener{
    private static String TAG = "Hi";
    private LayoutAnimationController in;
    private LayoutAnimationController out;
    /**
     * Alice的主要开关
     */
    private ToggleButton open_btn;
    private ViewPager viewPager;
    private Button speak_btn,write_btn;

    /**
     * 初始化操作
     */
    public void initView() {
        viewPager= (ViewPager) findViewById(R.id.my_viewpager);
        viewPager.setOffscreenPageLimit(2);
        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        /* 开关 */
        open_btn = (ToggleButton) findViewById(R.id.open_btn);
        speak_btn= (Button) findViewById(R.id.speak_btn);
        write_btn= (Button) findViewById(R.id.write_btn);
        speak_btn.setOnClickListener(this);
        write_btn.setOnClickListener(this);
    }

   public void initAnimation(){
        in= AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.alpha_0_5s);
       out=AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.alpha_back_0_5s);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_btn:
                viewPager.setCurrentItem(0);
                viewPager.setLayoutAnimation(in);
                break;

            case R.id.write_btn:
                Toast.makeText(this, "写字", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1);
                viewPager.setLayoutAnimation(out);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_alice);

        /*  设置Alice已经启动  */
        Alice.setISRUN(true);
//        initAnimation();
        /*  初始化操作  */
        initView();
        test();
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* 初始化按钮状态 */
        open_btn.toggle(Alice.getISRUN());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void test(){
        Intent intent = new Intent(alice, SettingActivity.class);
        startActivity(intent);
    }
}
