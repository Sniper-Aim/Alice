package alice.sniper.cn.alice.Hear;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import alice.sniper.cn.alice.ExternalTools.JsonAnalytic.JsonTools;
import alice.sniper.cn.alice.Hear.HearResult.HearResult;

/**
 * Created by pei_song on 2016/12/26.
 */

public class Hear {

    private static String TAG = "Hear";

    /**
     * 讯飞语音
     */
    private SpeechRecognizer mIat;

    /**
     * 上下文对象
     */
    private Context context;

    /**
     * 构造函数
     * @param context  调用者的上下文对象
     */
    public Hear(Context context){
        this.context = context;
        getSpeechRecognizer();
    }

    /**
     * 获得 SpeechRecognizer
     * @return
     */
    public SpeechRecognizer getSpeechRecognizer(){
        /**
         * 注册讯飞语音并且设置何种属性
         */
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=584a58fc");
        mIat = SpeechRecognizer.createRecognizer(context, null);
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        return mIat;
    }

    /**
     * 开始录音
     */
    public void start(){

        /**
         * 在开始录制之前将状态设置为false, 避免造成上一次得到结果后为True.
         * 而第二次就会冲突
         */
        HearResult.setIsResult(false);

        /**
         * 初始化结果,  将结果设置为空
         */
        HearResult.initResult();

        /**
         * 开始录制声音
         */
        mIat.startListening(mRecListener);
    }

    /**
     * 停止录音
     */
    public void stop(){
        mIat.stopListening();
    }

    /**
     * 听写监听器
     */
    private RecognizerListener mRecListener = new RecognizerListener() {

        /**
         * 结果回调函数, 会被多次回调 所以用StringBuffer拼接
         * @param results
         * @param isLast
         */
        public void onResult(RecognizerResult results, boolean isLast) {
            HearResult.setResult(JsonTools.getJsonStr(results.getResultString()));
            HearResult.setIsResult(isLast);
            Log.e(TAG, HearResult.getResult().toString());
        }

        /**
         * 会话发生错误回调接口
         * @param error
         */
        public void onError(SpeechError error) {
            error.getPlainDescription(true); //获取错误码描述}
        }

        /**
         * NULL
         * @param i
         * @param bytes
         */
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        /**
         * 开始录音
         */
        public void onBeginOfSpeech() {
        }

        /**
         * 音量值0~30
         * @param volume
         */
        public void onVolumeChanged(int volume) {
        }

        /**
         * 结束录音
         */
        public void onEndOfSpeech() {
        }

        /**
         * 扩展用接口
         * @param eventType
         * @param arg1
         * @param arg2
         * @param obj
         */
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
        }
    };
}
