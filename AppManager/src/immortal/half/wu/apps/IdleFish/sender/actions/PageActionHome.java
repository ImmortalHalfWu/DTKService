package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;

import java.awt.*;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;


public class PageActionHome {

    private static boolean activityHidding(String deviceId, String mainActivityPath) {
        String topActivity = ADBManager.getInstance().findTopActivity(deviceId);
        return topActivity == null || topActivity.length() == 0 || !mainActivityPath.endsWith(topActivity);
    }



    public static IAction newGoHomeAction(String packageName, String mainActivityPath) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_MAIN, "")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            System.out.println("============================启动App");
                            while (true) {

                                if (activityHidding(iDevice.getDeviceId(), mainActivityPath)) {
                                    System.out.println("============================main未显示，启动main");
                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
//                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
                                    continue;
                                }

                                System.out.println("============================main已显示，移除updata");

                                IAndroidPager androidPager = instance().getAndroidPager(iDevice, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
                                Point uiPoint = androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE);
                                adbBuilder.addClick(uiPoint).send(iDevice.getDeviceId());

                                System.out.println("============================再次开启main");
                                ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);

                                if (activityHidding(iDevice.getDeviceId(), mainActivityPath)) {
//                                    ADBManager.getInstance().startActivity(iDevice.getDeviceId(), packageName, mainActivityPath);
                                    System.out.println("============================再次开启main失败，从新启动app");
                                    continue;
                                }

                                System.out.println("============================开启main成功");
                                return;
                            }

                        }
                );
    }

    public static IAction newGoHomeMyAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_MAIN)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> {
                            IAndroidPager homeActivity = instance().getHomeActivity(iDevice);
                            adbBuilder.addClick(homeActivity.getUIPoint(PAGE_POINT_KEY_HOME_MY)).send(iDevice.getDeviceId());
                        }
                );
    }


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
