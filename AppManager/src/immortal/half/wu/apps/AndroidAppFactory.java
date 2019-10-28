package immortal.half.wu.apps;


import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.IdleFishAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidApp;


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


    public static @Nullable IAndroidApp createIdleFishAndroidApp(String deviceID) {
        return ADBManager.getInstance().isInstallApp(deviceID, IdleFishAndroidApp.IDLE_FISH_PACKAGE_NAME) ?
                new IdleFishAndroidApp(deviceID) :
                SimpleAndroidApp.getInstance();
    }



}
