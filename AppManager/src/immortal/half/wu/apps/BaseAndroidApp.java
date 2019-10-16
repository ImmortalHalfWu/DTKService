package immortal.half.wu.apps;

import immortal.half.wu.apps.interfaces.IAndroidApp;

public class BaseAndroidApp implements IAndroidApp {

    private String deviceId;
    private String packageName;
    private String mainActivityPath;

    public BaseAndroidApp(String deviceId, String packageName, String mainActivityPath) {
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.mainActivityPath = mainActivityPath;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getMainActivityPath() {
        return mainActivityPath;
    }

    @Override
    public String getShowingActivity() {
        return null;
    }

    @Override
    public void startApp() {

    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void postProduct(Object product) {

    }

    @Override
    public String[] getPostedProductsName() {
        return new String[0];
    }

    @Override
    public String getUserName() {
        return null;
    }


}
