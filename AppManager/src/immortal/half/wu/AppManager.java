package immortal.half.wu;

import immortal.half.wu.apps.AndroidAppFactory;
import immortal.half.wu.apps.interfaces.IAndroidApp;

public class AppManager {

    private static AppManager appManager;

    private AppManager() {}

    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    public IAndroidApp createAndroidApp(String deviceID, String appPackageName) {
        return AndroidAppFactory.createAndroidApp(deviceID, appPackageName);
    }

}
