package cn.sniper.alice.Hear.HearResult;

import java.io.Serializable;

import cn.sniper.alice.Brain.Brain;


/**
 * Created by Administrator on 2017/1/1.
 */

/**
 * 结果模型类
 */
public class Result implements Serializable {

    /**
     * 结果的Key值
     */
    private String key;

    /**
     * 结果的Values值, 如果是说话, 则定义他的值.
     */
    private String values;

    /**
     * 结果的类型, 这个类型根据Brain提供的类型而定.
     */
    private Brain.MSG_STATE state;

    public Result(){

    }

    public Result(String key, String values, Brain.MSG_STATE state){
        this.key = key;
        this.values = values;
        this.state = state;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public Brain.MSG_STATE getState() {
        return state;
    }

    public void setState(Brain.MSG_STATE state) {
        this.state = state;
    }
}
