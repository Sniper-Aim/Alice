package cn.sniper.alice.Alice;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Alice模型类, 封装Alice的各种属性.
 */
public class Alice {

    private static Alice alice;

    /**
     * Alice的ID
     */
    private String id = "";

    /**
     * Alice的名字.
     */
    private String name = "Alice";

    /**
     * Alice的签名
     */
    private String signature = "";

    /**
     * Alice的地址
     */
    private String address = "上海";

    /**
     * Alice的性别.
     */
    private String sex = "Girl";

    /**
     * Alice的身高.
     */
    private Integer height = 175;

    /**
     * Alice的体重.
     */
    private Integer weight = 55;

    /**
     * Alice的生日.
     */
    private String age = "1995.08.08";

    /**
     * Alice的属性是否已设置.
     */
    private Boolean isSetting = false;

    /**
     * Alice是否已经启动
     */
    private static Boolean isRUN = false;

    /**
     * 枚举Alice当前正在做某些事情
     */
    public enum ALICE_TASK{

    }

    /**
     * 好友列表
     */
    private List<UserInfo> userList = new ArrayList<>();

    /**
     * 会话列表
     */
    private List<Conversation> conversationList = new ArrayList<>();



    /**=================== Get and Set =================*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String newSex) {
        this.sex = newSex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String newAge) {
        this.age = newAge;
    }

    public Boolean getSetting() {
        return isSetting;
    }

    public void setSetting(Boolean setting) {
        isSetting = setting;
    }

    public static Boolean getISRUN() {
        return isRUN;
    }

    public static void setISRUN(Boolean ISRUN) {
        Alice.isRUN = ISRUN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    public static Alice getInstance() {
        if (alice == null) {
            alice = new Alice();
        }
        return alice;
    }

    public List<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }
}
