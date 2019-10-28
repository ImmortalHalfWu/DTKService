package immortal.half.wu;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.awt.*;

public class BaseAndroidDevice implements IAndroidDevice {

    private String deviceId;

    BaseAndroidDevice(@NotNull String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String[] getAllAppsPackage() {
        return ADBManager.getInstance().adbAllAppPackage(deviceId);
    }

    @Override
    public boolean isInstallApp(String appPackageName) {
        return ADBManager.getInstance().isInstallApp(deviceId, appPackageName);
    }

    @Override
    public void installApp(String apkPath, String appPackageName) {
        ADBManager.getInstance().installApp(deviceId, apkPath, appPackageName);
    }

    @Override
    public void uninstallApp(String appPackageName) {
        ADBManager.getInstance().uninstallApp(deviceId, appPackageName);
    }

    @Override
    public void choiceTextInputKeyBoard() {
        ADBManager.getInstance().choiceTextInputKeyBoard(deviceId);
    }

    @Override
    public Point getDxSize() {
        return ADBManager.getInstance().getDxSize(deviceId);
    }

    @Override
    public String toString() {
        return "BaseAndroidDevice{" +
                "deviceId='" + deviceId + '\'' +
                '}';
    }
}
