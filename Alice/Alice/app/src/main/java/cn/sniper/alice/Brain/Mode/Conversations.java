package cn.sniper.alice.Brain.Mode;

import java.io.Serializable;
import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * Created by peisong on 2017/1/18.
 */

public class Conversations implements Serializable {
    private String id;
    private String nickName;
    private List<Message> msgList;
}
