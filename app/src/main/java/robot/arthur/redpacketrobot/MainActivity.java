package robot.arthur.redpacketrobot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button start_monitor,stop_monitor;

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
                break;
            case R.id.stop_monitor:
                Log.i(TAG, "stop_monitor-click");
                stopService(monitorIntent);
                break;
            default:
                break;
        }

    }
}
