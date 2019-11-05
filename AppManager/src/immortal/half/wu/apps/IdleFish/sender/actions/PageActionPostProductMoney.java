package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.instance;

public class PageActionPostProductMoney {

    public static IAction newPostProductMoneyAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_MONEY, "index=\"1\" text=\"包邮\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addCallBack(adbProcess -> instance().getPostProductMoneyActivity(iDevice))
                                        .addBackClick()
                                        .send(iDevice.getDeviceId())
                );
    }



}
