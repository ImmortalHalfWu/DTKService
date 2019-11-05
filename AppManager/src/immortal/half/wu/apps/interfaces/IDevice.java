package immortal.half.wu.apps.interfaces;

import java.awt.*;

public interface IDevice {

    String getDeviceId();
    Point getDxSize();

    static IDevice create(String deviceId, Point dxSize) {
        return new IDevice() {
            @Override
            public String getDeviceId() {
                return deviceId;
            }

            @Override
            public Point getDxSize() {
                return dxSize;
            }
        };
    }

}
