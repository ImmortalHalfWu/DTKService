package immortal.half.wu;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.awt.*;

public class BaseAndroidDevice implements IAndroidDevice {

    private String deviceId;
    private Point dxSize;

    BaseAndroidDevice(@NotNull String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String[] getAllAppsPackage() {
        return ADBManager.getInstance().adbAllAppPackage(deviceId);
    }

    @Override
    public boolean isInstallApp(@org.jetbrains.annotations.NotNull String appPackageName) {
        boolean installApp = ADBManager.getInstance().isInstallApp(deviceId, appPackageName);
        LogUtil.i(DeviceManagerUtil.TAG, "设备" + deviceId + "是否安装" + appPackageName + "  " + installApp);
        return installApp;
    }

    @Override
    public void installApp(String apkPath, @org.jetbrains.annotations.NotNull String appPackageName) {
        LogUtil.i(DeviceManagerUtil.TAG, "设备" + deviceId +
                "安装" + appPackageName + "  " +
                ADBManager.getInstance().installApp(deviceId, apkPath, appPackageName));
    }

    @Override
    public void uninstallApp(@org.jetbrains.annotations.NotNull String appPackageName) {
        ADBManager.getInstance().uninstallApp(deviceId, appPackageName);
    }

    @Override
    public void choiceTextInputKeyBoard() {
        ADBManager.getInstance().choiceTextInputKeyBoard(deviceId);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Point getDxSize() {
        return dxSize == null ? dxSize = ADBManager.getInstance().getDxSize(deviceId) : dxSize;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String toString() {
        return "BaseAndroidDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", dxSize=" + getDxSize() +
                '}';
    }
}
