package immortal.half.wu.devices.interfaces;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidAppFactory;

import java.util.List;

public interface IAndroidDevice {

    String getDeviceId();

    List<IAndroidApp> getAllApps(@NotNull IAndroidAppFactory androidAppFactory);

    List<IAndroidApp> getAllApps();

    boolean isInstallApp(String appPackageName);

    void installApp(String apkPath, String appPackageName);

    void uninstallApp(String appPackageName);

    void choiceTextInputKeyBoard();

}
