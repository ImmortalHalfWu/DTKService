package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import org.jetbrains.annotations.NotNull;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POST_CHOICE_POST;

public class PageActionPostChoice {

    @NotNull
    public static IAction newGoPostAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_POST_CHOICE)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> adbBuilder.addClick(
                                AndroidIdleFishPagerFactory.instance()
                                        .getPostChoiceActivity(iDevice)
                                        .getUIPoint(PAGE_POINT_KEY_POST_CHOICE_POST))
                                .send(iDevice.getDeviceId())
                );
    }

}
