package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionImgProcess {

    public static IAction newGoImgTAGAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS, "com.taobao.idlefish:id/ftv_lable")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder.addClick(instance().getImagProcessActivity(iDevice)
                                        .getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_TAG))
                                        .send(iDevice.getDeviceId())
                );
    }

    public static IAction newGoPostProductInfoAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder.addClick(instance().getImagProcessActivity(iDevice).getUIPoint(PAGE_POINT_KEY_IMG_PROCESS_OK))
                                        .delayTime(2 * 1000)
                                .send(iDevice.getDeviceId())
                );
    }

}
