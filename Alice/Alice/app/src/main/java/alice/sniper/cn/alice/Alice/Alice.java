package alice.sniper.cn.alice.Alice;

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
    public static Boolean ISRUN = false;







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

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
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

}
