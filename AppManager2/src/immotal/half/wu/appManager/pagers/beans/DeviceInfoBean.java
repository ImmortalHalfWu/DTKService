package immotal.half.wu.appManager.pagers.beans;

import java.awt.*;

public class DeviceInfoBean {

    private final String deviceId;
    private final Point deviceDx;

    public DeviceInfoBean(String deviceId, Point deviceDx) {
        this.deviceId = deviceId;
        this.deviceDx = deviceDx;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Point getDeviceDx() {
        return deviceDx;
    }

    @Override
    public String toString() {
        return "DeviceInfoBean{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceDx=" + deviceDx +
                '}';
    }
}
