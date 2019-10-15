package immortal.half.wu;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.SimpleAndroidAppFactory;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidAppFactory;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.util.ArrayList;
import java.util.List;

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
    public List<IAndroidApp> getAllApps(@NotNull IAndroidAppFactory androidAppFactory) {

        if (androidAppFactory == null) {
            return new ArrayList<>();
        }

        String[] allAppPackage = ADBManager.getInstance().adbAllAppPackage(deviceId);
        ArrayList<IAndroidApp> androidApps = new ArrayList<>(allAppPackage.length);

        for (String packageName :
                allAppPackage) {
            IAndroidApp androidApp = androidAppFactory.createAndroidApp(deviceId, packageName.replace("package:", ""));
            if (androidApp != null) {
                androidApps.add(androidApp);
            }
        }

        return androidApps;
    }

    @Override
    public List<IAndroidApp> getAllApps() {
        return getAllApps(SimpleAndroidAppFactory.instance());
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
    public String toString() {
        return "BaseAndroidDevice{" +
                "deviceId='" + deviceId + '\'' +
                '}';
    }
}
