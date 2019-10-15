package immortal.half.wu.apps.interfaces;

import com.sun.istack.internal.Nullable;

public interface IAndroidAppFactory {

    @Nullable IAndroidApp createAndroidApp(String deviceId, String appPackageName);

}
