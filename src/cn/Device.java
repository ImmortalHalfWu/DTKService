package cn;

import immortal.half.wu.DeviceManager;
import immortal.half.wu.devices.interfaces.IAndroidDevice;

import java.awt.*;
import java.util.List;

public class Device {

    public static void test() {
        List<IAndroidDevice> allAndroidDevice = DeviceManager.getInstance().getAllAndroidDevice();
        for (IAndroidDevice androidDevice :
                allAndroidDevice) {
            Point dxSize = androidDevice.getDxSize();
            System.out.println(dxSize);
        }
//        BaseAndroidDevice baseAndroidDevice = new BaseAndroidDevice("0123456789ABCDEF");
//
//        baseAndroidDevice.uninstallApp("com.android.adbkeyboard");
//
//        List<IAndroidApp> allApps = baseAndroidDevice.getAllAppsPackage(null);
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
