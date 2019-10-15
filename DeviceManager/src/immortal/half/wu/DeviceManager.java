package immortal.half.wu;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    private static DeviceManager instance;

    private DeviceManager() {
        ADBManager.getInstance();
    }

    public static DeviceManager getInstance() {
        if (instance == null) {
            synchronized (DeviceManager.class) {
                if (instance == null) {
                    instance = new DeviceManager();
                }
            }
        }
        return instance;
    }

    public List<IAndroidDevice> getAllAndroidDevice() {

        String[] deviceIds = ADBManager.getInstance().adbFindAllDevice();

        ArrayList<IAndroidDevice> androidDevices = new ArrayList<>(deviceIds.length);

        for (String deviceId :
                deviceIds) {
            androidDevices.add(new BaseAndroidDevice(deviceId));
        }
        androidDevices.trimToSize();

        return androidDevices;
    }

}
