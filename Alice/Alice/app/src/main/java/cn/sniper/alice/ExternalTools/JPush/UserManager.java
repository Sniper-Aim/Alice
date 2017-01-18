package cn.sniper.alice.ExternalTools.JPush;

import android.content.Context;

import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.BrainInterFace.OnBrain;
import cn.sniper.alice.ExternalTools.Logs.Logger;

/**
 * Created by pei_song on 2017/1/3.
 */


/**
 * UserManager 操作类
 */
public class UserManager{

    private static UserManager jPush;

    private static Context context;

    private Alice alice;

    /**
     * 开启JPush 传入上下文参数
     * @param context
     */
    public static void startJPush(Context context, Boolean isDebug){
        cn.sniper.jpush.JPush.getInstance(context, isDebug);
    }

    /**
     * 登录
     * @param id
     * @param pwd
     * @param signOver
     */
    public void login(String id, String pwd, final OnBrain.SignOver signOver){
        JMessageClient.login(id, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (isSuccess(i)){
                    //获取用户信息
                    UserInfo userInfo = JMessageClient.getMyInfo();
                    //设置到当前Name
                    alice.setName(userInfo.getNickname());
                    //设置到当前Address
                    alice.setAddress(userInfo.getAddress());
                    //设置到当前Signature
                    alice.setSignature(userInfo.getSignature());
                    //获取好友列表
                    getFriendList();
                    //获取会话列表
                    signOver.over(true);

                    Logger.e("["+alice.getName()+"]["+alice.getAddress()+"]["+alice.getSignature()+"]");
                }else{
                    signOver.over(false);
                    Logger.e("登录出错");
                }
            }
        });
    }

    /**
     * 发送消息
     * @param msg
     */
    public void send(Message msg){
        JMessageClient.sendMessage(msg);
    }

    /**
     * 创建一条文本消息后可调用send方法将消息发送出去
     * @param id
     * @param tex
     */
    public Message createTextMessage(String id, String tex){
        return JMessageClient.createSingleTextMessage(id, tex);
    }

    /**
     * 申请添加好友
     * @param id   对方的id
     * @param appkey  appkey  默认为自己这个应用的
     * @param tex 申请理由
     */
    public void sendInvitationRequest(String id, String appkey, String tex){
        ContactManager.sendInvitationRequest(id, appkey, tex, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (isSuccess(i)){
                    Logger.e("获取成功");
                }else{
                    Logger.e("获取失败");
                }
            }
        });
    }

    /**
     * 同意好友申请
     * @param id  对方id
     * @param appkey appkey  默认为自己这个应用的
     */
    public void acceptInvitation(String id, String appkey){
        ContactManager.acceptInvitation(id, appkey, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (isSuccess(i)){
                    Logger.e("获取成功");
                }else{
                    Logger.e("获取失败");
                }
            }
        });
    }

    /**
     * 获取好友列表
     */
    public void getFriendList(){
        ContactManager.getFriendList(new GetUserInfoListCallback() {

            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (isSuccess(i)){
                    alice.setUserList(list);
                    //获取会话列表
                    getConversationList();
                    for (int a = 0; a < list.size(); a++ ){
                    }
                    Logger.e("获取好友成功");
                }else{
                    alice.setUserList(null);
                    Logger.e("获取好友失败");
                }
            }
        });
    }

    /**
     * 获取会话列表
     */
    public void getConversationList(){
        alice.setConversationList(JMessageClient.getConversationList());
    }

    public void bindThisConversation(String username){
        JMessageClient.enterSingleConversation(username);
    }

    public void exitBindConversation(){
        JMessageClient.exitConversation();
    }


    /**
     * 判断请求是否成功
     * @param i JMessage的回调数值
     * @return
     */
    private Boolean isSuccess(int i){
        // 0 代表成功
        if (i == 0){
            return true;
        }else{
            return false;
        }
    }



    /**
     * 单例
     * @param context
     * @return
     */
    public static UserManager getInstance(Context context){
        if (jPush == null){
            jPush = new UserManager(context);
        }
        return jPush;
    }


    /**
     * 构造
     * @param context
     */
    public UserManager(Context context){
        UserManager.context = context;
        alice = Alice.getInstance();
    }


}