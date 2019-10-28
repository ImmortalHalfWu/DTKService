package immortal.half.wu.apps;


import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.IdleFishAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IDevice;



public class AndroidAppFactory {

    private static AndroidAppFactory instance;

    public static AndroidAppFactory getInstance() {
        if (instance == null) {
            synchronized (AndroidAppFactory.class) {
                if (instance == null) {
                    instance = new AndroidAppFactory();
                }
            }
        }
        return instance;
    }


    public static @Nullable IAndroidApp createIdleFishAndroidApp(IDevice deviceID) {
        return ADBManager.getInstance().isInstallApp(deviceID.getDeviceId(), IdleFishAndroidApp.IDLE_FISH_PACKAGE_NAME) ?
                new IdleFishAndroidApp(deviceID) :
                SimpleAndroidApp.getInstance();
    }



}
