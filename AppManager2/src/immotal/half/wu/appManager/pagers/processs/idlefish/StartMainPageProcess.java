package immotal.half.wu.appManager.pagers.processs.idlefish;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.util.HashMap;
import java.util.Map;

public class StartMainPageProcess extends BasePageProcess<Boolean> {

    private final String appPackage;
    private final String mainActivityNamePath;

    public StartMainPageProcess(String appPackage, String mainActivityName) {
        this.mainActivityNamePath = mainActivityName;
        this.appPackage = appPackage;
    }

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return new HashMap<>(0);
    }

    @Override
    public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return true;
    }

    @Override
    public Boolean doPageProcess(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {

        String deviceId = deviceInfo.getDeviceId();

        int delayTime;

        if (adb.findTopActivityPath(deviceId).contains(mainActivityNamePath)) {
            LogUtil.i(AppManagerUtil.TAG, "回到" + appPackage + "首页" + mainActivityNamePath);
            delayTime = 0;
        } else {
            LogUtil.i(AppManagerUtil.TAG, "启动" + appPackage + "首页" + mainActivityNamePath);
            delayTime =  1500;
        }

        do {

            adb.startActivity(deviceInfo.getDeviceId(), appPackage, mainActivityNamePath);

            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!checkTopActivity(deviceId, adb));

        return true;
    }

    private boolean checkTopActivity(String deviceId, ADBManager adb) {
        String topActivity = adb.findTopActivityPath(deviceId);
        return topActivity == null || topActivity.length() == 0 || topActivity.equals(mainActivityNamePath);
    }

}
