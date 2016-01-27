package robot.arthur.redpacketrobot;

/**
 * Created by Arthur on 2016/1/27 15 : 35.
 */
public interface IMonitorService {
    public void startMonitor(int initNum,IMonitorCallback iMonitorCallback);
    public void stopMonitor();
}
