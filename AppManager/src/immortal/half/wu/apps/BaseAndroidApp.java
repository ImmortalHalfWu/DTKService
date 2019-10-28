package immortal.half.wu.apps;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.interfaces.IAndroidApp;
import immortal.half.wu.apps.interfaces.IAndroidPager;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.PAGE_POINT_KEY_HOME_UPDATE;

public abstract class BaseAndroidApp<T> implements IAndroidApp<T> {

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
    public boolean startApp() {

        if (mainActivityShowing()) {
            return true;
        }

        ADBManager.getInstance().startActivity(deviceId, packageName, mainActivityPath);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toMainActivity();

        IAndroidPager androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
        new ADBBuilder()
                .addClick(androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE))
                .send(deviceId);

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
                ADBManager.getInstance().startActivity(deviceId, packageName, mainActivityPath);
                continue;
            }
            return true;
        }

    }

    private boolean mainActivityShowing() {
        String topActivity = ADBManager.getInstance().findTopActivity(deviceId);
        return topActivity != null && topActivity.length() != 0 && mainActivityPath.endsWith(topActivity);
    }

}
