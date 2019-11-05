package immortal.half.wu.apps;


import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.IdleFishAndroidApp;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IDevice;



public class AndroidAppFactory {


    public static @Nullable IAndroidApp<IdleFishProductBean> createIdleFishAndroidApp(IDevice deviceID) {
        return ADBManager.getInstance().isInstallApp(deviceID.getDeviceId(), IdleFishAndroidApp.IDLE_FISH_PACKAGE_NAME) ?
                new IdleFishAndroidApp(deviceID) :
                SimpleAndroidApp.getInstance();
    }


}
