package cn.sniper.alice.Brain.BrainViews.User;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.liao.GifView;

import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.BrainInterFace.OnBrain;
import cn.sniper.alice.ExternalTools.JPush.UserManager;
import cn.sniper.alice.ExternalTools.LocalStorage.SharedPreference;
import cn.sniper.alice.ExternalTools.Logs.Logger;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/17.
 */

public class SignActivity extends Activity implements View.OnClickListener{

    /**
     * 用户名编剧框
     */
    private EditText sign_edit_username;

    /**
     * 密码编辑框
     */
    private EditText sign_edit_password;

    /**
     * 登录按钮
     */
    private TextView sign_btn_sign;

    /**
     * 登录按钮的loading动画图
     */
    private GifView sign_button_loading;

    /**
     * 保存密码按钮
     */
    private CheckBox sign_checkbox_save_pwd;

    /**
     * 忘记密码
     */
    private TextView sign_forget_tex;

    /**
     * 注册帐号
     */
    private TextView sign_new_user_tex;

    /**
     * 标识当前是否正在登录
     */
    private Boolean isSign = false;

    /**
     * 帐号密码是否符合条件
     */
    private Boolean isUsername = false;
    private Boolean isPassword = false;

    /**
     * 用户管理类
     */
    private UserManager userManager;

    private SharedPreference sharedPreference;



    private void initView(){
        sign_edit_username = (EditText) findViewById(R.id.sign_edit_username);

        sign_edit_password = (EditText) findViewById(R.id.sign_edit_password);

        sign_btn_sign = (TextView) findViewById(R.id.sign_btn_sign);

        sign_button_loading = (GifView) findViewById(R.id.sign_button_loading);

        sign_checkbox_save_pwd = (CheckBox) findViewById(R.id.sign_checkbox_save_pwd);

        sign_forget_tex = (TextView) findViewById(R.id.sign_forget_tex);

        sign_new_user_tex = (TextView) findViewById(R.id.sign_new_user_tex);

        /**
         * 初始化的时候登录按钮设置为不能点击
         */
        sign_btn_sign.setClickable(false);
    }

    private void initData(){
        /**
         * 获取本地存储单例
         */
        sharedPreference = SharedPreference.getInstance(this);

        /**
         * 获取user manager管理器
         */
        userManager = UserManager.getInstance(this);

        /**
         * 初始化登录按钮的Loading
         */
        sign_button_loading.setShowDimension(40,40);
        sign_button_loading.setGifImage(R.mipmap.sign_button_loading);

        /**
         * 初始化用户名密码和是否保存密码的操作
         */
        sign_checkbox_save_pwd.setChecked(getSaveCheckBox());
        sign_edit_username.setText(getSaveUserName());
        sign_edit_password.setText(getSavePassword(sign_edit_username.getText().toString()));

        /**
         * 初始化是否保存了帐号联动设置按钮状态
         */
        setSignButtonBack(checkUser(sign_edit_username.getText().toString()));


    }

    private void initEvent(){
        /*监听编辑框内容变化*/
        editUsernameEvent(sign_edit_username);
        sign_btn_sign.setOnClickListener(this);
        sign_checkbox_save_pwd.setOnClickListener(this);
        sign_forget_tex.setOnClickListener(this);
        sign_new_user_tex.setOnClickListener(this);
    }


    /**
     * 此处的两个方法是帐号密码编辑框的输入监听
     * @param editText
     */
    private void editUsernameEvent(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setSignButtonBack(checkUser(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_edit_username:
                break;
            case R.id.sign_edit_password:
                break;

            /* 登录按钮 */
            case R.id.sign_btn_sign:
                if (checkUser(sign_edit_username.getText().toString()) && checkPwd(sign_edit_password.getText().toString())){
                    if (!isSign){
                        sign(sign_checkbox_save_pwd.isChecked());
                    }else{
                        Toast.makeText(this, "正在登录", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "请填写完整的帐号密码!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_checkbox_save_pwd:
                break;
            case R.id.sign_forget_tex:
                Toast.makeText(this, "忘记密码!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.sign_new_user_tex:
                Toast.makeText(this, "注册帐号!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 登录, 直接调用UserManager进行登录,
     * 重写Over回调执行登录完成后需要做的事情
     */
    private void sign(final Boolean isCheck){
        /**
         * isSign标注当前为正在登录状态
         * 随后设置btn为空且展示loading动画
         */
        isSign = true;
        sign_btn_sign.setText("");
        sign_button_loading.setVisibility(View.VISIBLE);

        /**
         * 调用执行登录操作
         */
        Logger.e("开始登录");
        userManager.login(sign_edit_username.getText().toString(), sign_edit_password.getText().toString(), new OnBrain.SignOver() {
            @Override
            public void over(Boolean isOver) {
                if (isOver){
                    //登录成功后初始化状态 隐藏键盘 并且关闭当前页面  还有获取当前已经保存的帐号数量并增加到后面
                    isSign = false;
                    String id = sign_edit_username.getText().toString();
                    String pwd = sign_edit_password.getText().toString();
                    //是否勾选了保存密码
                    if (isCheck){
                        saveUserInfo(id, pwd);
                    }else{
                        deleteUserInfo(id);
                    }
                    //设置当前登录状态
                    Alice.setISRUN(true);
                    Logger.e("SignActivity - 登录成功");
                    SignActivity.this.finish();
                    initLayout();
                }else{
                    isSign = false;
                    initLayout();
                    Toast.makeText(SignActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signOver(){

    }

    private void errSign(){

    }

    //获取保存的帐号
    private String getSaveUserName(){
        return sharedPreference.readStr(SharedPreference.FileName.SAVE_USERNAME.name(), 0 + "")
                == null ?
                "" :
                sharedPreference.readStr(SharedPreference.FileName.SAVE_USERNAME.name(), 0 + "");
    }

    //获取保存的密码
    private String getSavePassword(String id){
        return sharedPreference.readStr(SharedPreference.FileName.SAVE_PASSWORD.name(), id)
                == null ?
                "" :
                sharedPreference.readStr(SharedPreference.FileName.SAVE_PASSWORD.name(), id);
    }

    //获取保存的保存密码状态
    private Boolean getSaveCheckBox(){
        return sharedPreference.readBol(SharedPreference.FileName.SAVE_IS_USERNAME.name(),
                sharedPreference.readStr(SharedPreference.FileName.SAVE_USERNAME.name(), 0+""));
    }

    /**
     * 检查帐号是否通过
     * @return
     */
    private Boolean checkUser(String id){
        if (id.length() > 4){
            return true;
        }
        return false;
    }

    private Boolean checkPwd(String pwd){
        if (pwd.length() > 4){
            return true;
        }
        return false;
    }

    /**
     * 设置按钮的状态
     * @param is
     */
    private void setSignButtonBack(Boolean is){
        if (is){
            sign_btn_sign.setBackgroundResource(R.drawable.sign_rounded_sign_button_green);
            sign_btn_sign.setTextColor(ContextCompat.getColor(SignActivity.this, R.color.sign_rounded_sign_button_green_tex));
        }else{
            sign_btn_sign.setBackgroundResource(R.drawable.sign_rounded_sign_button_gray);
            sign_btn_sign.setTextColor(ContextCompat.getColor(SignActivity.this, R.color.sign_rounded_sign_button_gray_tex));
        }
    }

    /**
     * 键盘收起和弹出,  暂时为无效方法
     */
    private void showKeyBoard(){
        if(getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN){
            //显示软键盘
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private void displayKeyBoard(){
        if(getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE){
            //隐藏软键盘
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    /**
     * 保存密码
     * @param id
     * @param pwd
     */
    private void saveUserInfo(String id, String pwd){
        int cont = sharedPreference.getSize(SharedPreference.FileName.SAVE_USERNAME.name());
        sharedPreference.writeStr(SharedPreference.FileName.SAVE_USERNAME.name(), "" + 0, id);
        sharedPreference.writeStr(SharedPreference.FileName.SAVE_PASSWORD.name(), id, pwd);
        sharedPreference.writeBol(SharedPreference.FileName.SAVE_IS_USERNAME.name(), id, true);
    }

    private void deleteUserInfo(String id){
        sharedPreference.deleteStr(SharedPreference.FileName.SAVE_PASSWORD.name(), id);
        sharedPreference.writeBol(SharedPreference.FileName.SAVE_IS_USERNAME.name(), id, false);
    }

    /**
     * 将布局初始化, 但是保留帐号不清空 可用在登录完成后调用
     */
    private void initLayout(){
        sign_button_loading.setVisibility(View.GONE);
        sign_btn_sign.setText(R.string.sign_in_button);
        sign_edit_password.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initView();
        initData();
        initEvent();
    }
}
