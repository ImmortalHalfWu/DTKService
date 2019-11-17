package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;


public class PageActionHome {
    private static final String TAG = "PageActionHome";


    private static boolean activityHidding(String deviceId, @NotNull String mainActivityPath) {
        String topActivity = ADBManager.getInstance().findTopActivityName(deviceId);
        return topActivity == null || topActivity.length() == 0 || !mainActivityPath.endsWith(topActivity);
    }


    @NotNull
    public static IAction newGoHomeAction(String packageName, @NotNull String mainActivityPath) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_MAIN, "")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            while (true) {

                                if (activityHidding(iDevice.getDeviceId(), mainActivityPath)) {
                                    LogUtil.i(TAG, "主页面未显示，启动主页面" + packageName);
                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
//                                    try {
//                                        Thread.sleep(100);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
                                    continue;
                                }

                                IAndroidPager androidPager = instance().getAndroidPager(iDevice, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
                                Point uiPoint = androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE);
                                if (uiPoint.x != 0 && uiPoint.y != 0) {
                                    LogUtil.i(TAG, "main已显示，移除updata");
                                    adbBuilder.addClick(uiPoint).send(iDevice.getDeviceId());
                                }

                                ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);

                                if (activityHidding(iDevice.getDeviceId(), mainActivityPath)) {
//                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
                                    continue;
                                }

                                LogUtil.i(TAG, "已成功启动主页面" + packageName);
                                return;
                            }

                        }
                );
    }

    @NotNull
    public static IAction newGoHomeMyAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_MAIN)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            IAndroidPager homeActivity = instance().getHomeActivity(iDevice);
                            adbBuilder.addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_MY)).send(iDevice.getDeviceId());
                        }
                );
    }


    @NotNull
    public static IAction newGoPostChoiceAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_MAIN)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            IAndroidPager homeActivity = instance().getHomeActivity(iDevice);
                            System.out.println("点击发布");
                            adbBuilder.addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_POST)).send(iDevice.getDeviceId());
                        }
                );
    }

}
