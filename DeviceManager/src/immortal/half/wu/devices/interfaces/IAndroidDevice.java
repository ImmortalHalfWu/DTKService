package immortal.half.wu.devices.interfaces;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface IAndroidDevice {

    String getDeviceId();

//    String[] getAllAppsPackage(@NotNull IAndroidAppFactory androidAppFactory);

    @NotNull String[] getAllAppsPackage();

    boolean isInstallApp(String appPackageName);

    void installApp(String apkPath, String appPackageName);

    void uninstallApp(String appPackageName);

    void choiceTextInputKeyBoard();

    @NotNull Point getDxSize();

}
