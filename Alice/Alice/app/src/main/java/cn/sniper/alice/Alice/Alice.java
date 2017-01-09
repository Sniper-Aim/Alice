package cn.sniper.alice.Alice;

/**
 * Alice模型类, 封装Alice的各种属性.
 */
public abstract class Alice {

    /**
     * Alice的名字.
     */
    private String name = "Alice";

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






    /**=================== Get and Set =================*/

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
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
}
