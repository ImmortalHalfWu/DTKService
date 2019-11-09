package immortal.half.wu;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceManager {

    private static final String TAG = "DeviceManager";
    
    private static DeviceManager instance;

    @NotNull
    private final Map<String, IAndroidDevice> androidDevices;

    private DeviceManager() {
        androidDevices = new HashMap<>();
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

    @NotNull
    public List<IAndroidDevice> getAllAndroidDevice() {

        String[] deviceIds = ADBManager.getInstance().adbFindAllDevice();

        for (String deviceId :
                deviceIds) {
            if (!androidDevices.containsKey(deviceId)) {
                androidDevices.put(deviceId, new BaseAndroidDevice(deviceId));
            }
        }

        ArrayList<IAndroidDevice> iAndroidDevices = new ArrayList<>(androidDevices.values());
        LogUtil.i(TAG,"获取所有Android设备：" + iAndroidDevices);
        return iAndroidDevices;
    }

}
