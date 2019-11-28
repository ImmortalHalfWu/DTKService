package immortal.half.wu.deviceAppControl;

public class DeviceAppManager {

    private static DeviceAppManager deviceAppManager;

    public static DeviceAppManager getInstance() {
        if (deviceAppManager == null) {
            synchronized (DeviceAppManager.class) {
                if (deviceAppManager == null) {
                    deviceAppManager = new DeviceAppManager();
                }
            }
        }
        return deviceAppManager;
    }


}
