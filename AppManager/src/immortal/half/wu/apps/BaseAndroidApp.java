package immortal.half.wu.apps;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.PAGE_POINT_KEY_HOME_UPDATE;

public abstract class BaseAndroidApp<T> implements IAndroidApp<T> {

    private final IDevice deviceId;
    private final String packageName;
    private final String mainActivityPath;

    public BaseAndroidApp(IDevice deviceId, String packageName, String mainActivityPath) {
        this.deviceId = deviceId;
        this.packageName = packageName;
        this.mainActivityPath = mainActivityPath;
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

    @Override
    public boolean startApp() {

        if (mainActivityShowing()) {
            return true;
        }

        ADBManager.getInstance().startActivity(deviceId.getDeviceId(), packageName, mainActivityPath);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toMainActivity();

        IAndroidPager androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
        new ADBBuilder()
                .addClick(androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE))
                .send(deviceId.getDeviceId());

        return toMainActivity();
    }

    @Override
    public boolean toMainActivity() {

        while (true) {
            if (!mainActivityShowing()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ADBManager.getInstance().startActivity(deviceId.getDeviceId(), packageName, mainActivityPath);
                continue;
            }
            return true;
        }

    }

    private boolean mainActivityShowing() {
        String topActivity = ADBManager.getInstance().findTopActivity(deviceId.getDeviceId());
        return topActivity != null && topActivity.length() != 0 && mainActivityPath.endsWith(topActivity);
    }

}
