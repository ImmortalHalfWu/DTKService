package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.PAGE_POINT_KEY_IMG_CHOICE_OK;
import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.instance;

public class PageActionImgChoice {

    public static IAction newGoImgProcessAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder.addClick(instance().getImgChoiceActivity(iDevice).getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK))
                                .send(iDevice.getDeviceId())
                );
    }

}
