package robot.arthur.redpacketrobot;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener,IMonitorCallback{
    private static final String TAG = "MainActivity";
    private Button start_monitor,stop_monitor;
    private MonitorService monitorService=null;
    private TextView back_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        start_monitor=(Button)findViewById(R.id.start_monitor);
        stop_monitor=(Button)findViewById(R.id.stop_monitor);
        back_tip=(TextView)findViewById(R.id.back_tip);
        start_monitor.setOnClickListener(this);
        stop_monitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent monitorIntent = new Intent(MainActivity.this,MonitorService.class);
        switch (v.getId()){
            case R.id.start_monitor:
                Log.i(TAG, "start_monitor-click");
                startService(monitorIntent);
                bindService(monitorIntent,serviceConnection, Context.BIND_AUTO_CREATE);
                if (monitorService!=null){
                    monitorService.startMonitor(0,this);
                }


                break;
            case R.id.stop_monitor:
                Log.i(TAG, "stop_monitor-click");
                if (monitorService!=null){
                    monitorService.stopMonitor();
                }
                //stopService(monitorIntent);
                break;
            default:
                break;
        }

    }

    @Override
    public void monitorBack(int num) {
        back_tip.setText(String.valueOf(num));
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            monitorService=((MonitorService.MonitorBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            monitorService=null;
        }
    };
}
