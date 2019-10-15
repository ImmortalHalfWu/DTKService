package immortal.half.wu.apps;

import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidAppFactory;

public class SimpleAndroidAppFactory implements IAndroidAppFactory {

    private static SimpleAndroidAppFactory instance;

    private SimpleAndroidAppFactory() {}

    public static IAndroidAppFactory instance() {
        if (instance == null) {
            synchronized (SimpleAndroidAppFactory.class) {
                if (instance == null) {
                    instance = new SimpleAndroidAppFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public IAndroidApp createAndroidApp(String deviceId, String appPackageName) {
        return new IAndroidApp() {
            @Override
            public String getDeviceId() {
                return deviceId;
            }

            @Override
            public String getPackageName() {
                return appPackageName;
            }

            @Override
            public String getMainActivityPath() {
                return "dont know";
            }

        };
    }
}
