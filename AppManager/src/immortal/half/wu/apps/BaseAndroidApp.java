package immortal.half.wu.apps;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IDevice;



public abstract class BaseAndroidApp<T> implements IAndroidApp<T> {

    protected final IDevice deviceId;
    protected final String packageName;
    protected final String mainActivityPath;

    public BaseAndroidApp(IDevice deviceId, String packageName, String mainActivityPath) {
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.mainActivityPath = mainActivityPath;
        ADBManager.getInstance().startActivity(deviceId.getDeviceId(), packageName, mainActivityPath);
    }

    @Override
    public String getDeviceId() {
        return deviceId.getDeviceId();
    }

    @Override
    public IDevice getDevice() {
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

//    @Override
//    public boolean startApp() {
//
//        if (mainActivityShowing()) {
//            return true;
//        }
//
//        ADBManager.getInstance().startActivity(deviceId.getDeviceId(), packageName, mainActivityPath);
//
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return toMainActivity();
//    }
//
//    @Override
//    public boolean toMainActivity() {
//
//        while (true) {
//            if (!mainActivityShowing()) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ADBManager.getInstance().startActivity(deviceId.getDeviceId(), packageName, mainActivityPath);
//                continue;
//            }
//            return true;
//        }
//
//    }

    protected boolean mainActivityShowing() {
        String topActivity = ADBManager.getInstance().findTopActivity(deviceId.getDeviceId());
        return topActivity != null && topActivity.length() != 0 && mainActivityPath.endsWith(topActivity);
    }
}
