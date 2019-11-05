package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.instance;

public class PageActionPostProductOther {

    public static IAction newPostProductOtherAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER, "index=\"3\" text=\"不讲价\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addCallBack(adbProcess -> instance().getPostProductOtherActivity(iDevice))
                                        .addBackClick()
                                        .send(iDevice.getDeviceId())
                );
    }

}
