package immortal.half.wu.apps;


import com.sun.istack.internal.Nullable;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.IdleFishAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;


public class AndroidAppFactory {


    @NotNull
    public static @Nullable
    IAndroidApp createIdleFishAndroidApp(IDevice deviceID) {
        return ADBManager.getInstance().isInstallApp(deviceID.getDeviceId(), IdleFishAndroidApp.IDLE_FISH_PACKAGE_NAME) ?
                new IdleFishAndroidApp(deviceID) :
                SimpleAndroidApp.getInstance();
    }


}
