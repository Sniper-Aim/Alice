package cn.sniper.alice.Say;

import android.widget.Toast;

import cn.sniper.alice.Brain.Brain;


/**
 *  Say类  说话类, 用来处理一切Alice的说话能力.
 *
 * Created by "pei song" on 2016/12/8.
 */

public class Say extends Brain {

    /**
     * Say (说)  让Alice显示一句话.
     */
    public void Say(String str){
        Toast.makeText(alice, str, Toast.LENGTH_SHORT).show();
    }

    public static Say getInstance(){
        return new Say();
    }
}