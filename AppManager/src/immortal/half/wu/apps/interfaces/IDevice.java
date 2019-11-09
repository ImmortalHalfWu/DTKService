package immortal.half.wu.apps.interfaces;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface IDevice {

    String getDeviceId();

    Point getDxSize();

    static IDevice create(@NotNull String deviceId, @NotNull Point dxSize) {
        return new IDevice() {
            @NotNull
            @Override
            public String getDeviceId() {
                return deviceId;
            }

            @NotNull
            @Override
            public Point getDxSize() {
                return dxSize;
            }

            @NotNull
            @Override
            public String toString() {
                return deviceId;
            }
        };
    }

}
