package immortal.half.wu.apps;

import immortal.half.wu.adbs.ADBManager;

import java.awt.*;
import java.util.HashMap;

public class AndroidDeviceSize {

    private static AndroidDeviceSize androidDeviceSize;
    private HashMap<String, Point> deviceSize = new HashMap<>();

    public static AndroidDeviceSize getInstance() {
        if (androidDeviceSize == null) {
            synchronized (AndroidDeviceSize.class) {
                if (androidDeviceSize == null) {
                    androidDeviceSize = new AndroidDeviceSize();
                }
            }
        }
        return androidDeviceSize;
    }

    public Point getDeviceSize(String deviceId) {
        Point point = deviceSize.get(deviceId);
        if (point == null) {
            point = ADBManager.getInstance().getDxSize(deviceId);
            if (point.x > 0 && point.y > 0) {
                deviceSize.put(deviceId, point);
            }
        }
        return point;
    }

}
