package robot.arthur.redpacketrobot;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Arthur on 2016/1/27 14 : 41.
 */
public class MonitorService extends Service {
    private static final String TAG = "MonitorService";
    @Override
    public void onCreate() {
        Log.i(TAG, "MonitorService-onCreate");
        super.onCreate();
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
        return null;
    }
}
