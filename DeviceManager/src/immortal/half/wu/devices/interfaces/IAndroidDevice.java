package immortal.half.wu.devices.interfaces;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.apps.interfaces.IAndroidAppFactory;

import java.awt.*;

public interface IAndroidDevice {

    String getDeviceId();

    String[] getAllAppsPackage(@NotNull IAndroidAppFactory androidAppFactory);

    String[] getAllAppsPackage();

    boolean isInstallApp(String appPackageName);

    void installApp(String apkPath, String appPackageName);

    void uninstallApp(String appPackageName);

    void choiceTextInputKeyBoard();

    Point getDxSize();

}
