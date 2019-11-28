package immortal.half.wu;

import immortal.half.wu.devices.DevicesStatueReader;
import immortal.half.wu.devices.interfaces.IAndroidDevice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceManager {

    private static DeviceManager instance;

    @NotNull
    private final Map<String, IAndroidDevice> androidDevices;

    @NotNull
    private final List<IDeviceConnectListener> deviceConnectListeners;

    private DeviceManager() {
        DevicesStatueReader.getInstance().setListeners(mStatueListener);
        androidDevices = new HashMap<>();
        deviceConnectListeners = new ArrayList<>();
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

    public void registerDeviceConnectListener(IDeviceConnectListener listener) {
        if (listener != null) {
            deviceConnectListeners.add(listener);
        }
    }

    @NotNull
    public List<IAndroidDevice> getAllAndroidDevice() {
        ArrayList<IAndroidDevice> iAndroidDevices = new ArrayList<>(androidDevices.values());
        LogUtil.i(DeviceManagerUtil.TAG,"获取所有Android设备：" + iAndroidDevices);
        return iAndroidDevices;
    }

    private DevicesStatueReader.StatueListener mStatueListener = new DevicesStatueReader.StatueListener() {
        @Override
        public void deviceConnect(String deviceId) throws Exception {
            if (!androidDevices.containsKey(deviceId)) {

                BaseAndroidDevice baseAndroidDevice = new BaseAndroidDevice(deviceId);
                androidDevices.put(deviceId, baseAndroidDevice);

                for (IDeviceConnectListener listener : deviceConnectListeners) {
                    listener.deviceConnect(deviceId, baseAndroidDevice);
                }

            }
        }

        @Override
        public void deviceDisconnect(String deviceId) throws Exception {
            if (androidDevices.containsKey(deviceId)) {

                IAndroidDevice iAndroidDevice = androidDevices.get(deviceId);
                androidDevices.remove(deviceId);

                for (IDeviceConnectListener listener : deviceConnectListeners) {
                    listener.deviceDisConnect(deviceId, iAndroidDevice);
                    iAndroidDevice.disconnect();
                }
            }
        }
    };

}
