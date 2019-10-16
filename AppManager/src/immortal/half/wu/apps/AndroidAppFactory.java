package immortal.half.wu.apps;


import immortal.half.wu.apps.interfaces.IAndroidApp;

public class AndroidAppFactory {

    private final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.idlefish";


    public static IAndroidApp createAndroidApp(String deviceID, String appPackageName) {

        if (appPackageName.equals(IDLE_FISH_PACKAGE_NAME)) {
            return new BaseAndroidApp(deviceID, appPackageName, IDLE_FISH_MAIN_PATH);
        }

        return new BaseAndroidApp(deviceID, "", "");
    }

}
