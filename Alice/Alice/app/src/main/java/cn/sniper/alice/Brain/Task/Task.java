package cn.sniper.alice.Brain.Task;

import android.os.AsyncTask;

/**
 * Created by peisong on 2017/1/18.
 */

public abstract class Task extends AsyncTask{

    /**
     * 耗时操作
     * @param objects
     * @return
     */
    @Override
    protected Object doInBackground(Object[] objects) {
        start();
        return null;
    }

    /**
     * 执行结束时调用
     * @param o
     */
    @Override
    protected void onPostExecute(Object o) {
        exit();
        super.onPostExecute(o);
    }

    /**
     * 在doInBackground之前执行,  一般用作初始化
     */
    @Override
    protected void onPreExecute() {
        init();
        super.onPreExecute();
    }

    /**
     * 在doInBackground中调用ProgressUpdate方法就会调用此方法进行UI更新
     * @param values
     */
    @Override
    protected void onProgressUpdate(Object[] values) {
        update();
        super.onProgressUpdate(values);
    }

    public Task(){
        this.execute();
    }

    public void aupdate(){
        publishProgress();
    }
    public abstract void init();
    public abstract void start();
    public abstract void update();
    public abstract void exit();
}
