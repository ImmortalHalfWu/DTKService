package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionImgProcess {

    public static IAction newGoImgTAGAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_PROCESS, "添加说明标签可被更多人看见")
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
                                .send(iDevice.getDeviceId())
                );
    }

}
