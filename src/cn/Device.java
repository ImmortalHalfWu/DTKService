package cn;

import immortal.half.wu.DeviceManager;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.util.List;

public class Device {

    public static void test() {
        List<IAndroidDevice> allAndroidDevice = DeviceManager.getInstance().getAllAndroidDevice();
        for (IAndroidDevice androidDevice :
                allAndroidDevice) {
            List<IAndroidApp> allApps = androidDevice.getAllApps();
            for (IAndroidApp app :
                    allApps) {
                System.out.println(app.getPackageName());
            }
        }
//        BaseAndroidDevice baseAndroidDevice = new BaseAndroidDevice("0123456789ABCDEF");
//
//        baseAndroidDevice.uninstallApp("com.android.adbkeyboard");
//
//        List<IAndroidApp> allApps = baseAndroidDevice.getAllApps(null);
//
//        baseAndroidDevice.installApp(
//                FileUtils.DIR_PATH_OTHER + FileUtils.FILE_NAME_KEY_BOARD_APK,
//                "com.android.adbkeyboard"
//        );
//
//        boolean installApp = baseAndroidDevice.isInstallApp("com.android.adbkeyboard");
//
//        System.out.println(installApp);
    }

}
