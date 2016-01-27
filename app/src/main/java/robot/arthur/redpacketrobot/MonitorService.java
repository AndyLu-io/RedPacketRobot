package robot.arthur.redpacketrobot;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Arthur on 2016/1/27 14 : 41.
 */
public class MonitorService extends Service implements IMonitorService {
    private static final String TAG = "MonitorService";
    private IMonitorCallback iMonitorCallback;
    private boolean stop=false;
    private final MonitorBinder binder=new MonitorBinder();

    @Override
    public void onCreate() {
        Log.i(TAG, "MonitorService-onCreate");
        super.onCreate();
    }

    public class MonitorBinder extends Binder{
        public  MonitorService getService(){
            return MonitorService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MonitorService-onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MonitorService-onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "MonitorService-onBind");
        return binder;
    }

    @Override
    public void startMonitor(int initNum, final IMonitorCallback iMonitorCallback) {
        this.iMonitorCallback=iMonitorCallback;
        AsyncTask<Integer,Integer,Integer> task=new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... params) {
                Integer initMonitor=params[0];
                stop=false;
                while (!stop){
                    publishProgress(initMonitor);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    initMonitor++;
                }


                return initMonitor;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                int num=values[0];
                iMonitorCallback.monitorBack(num);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                iMonitorCallback.monitorBack(integer);
            }


        };
        task.execute(initNum);

    }

    @Override
    public void stopMonitor() {
        stop=true;
    }
}
