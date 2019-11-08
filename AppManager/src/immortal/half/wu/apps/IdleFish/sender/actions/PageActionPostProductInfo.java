package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IDevice;

import java.awt.*;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionPostProductInfo {

    public static IAction newPostProductInfoAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO, "index=\"1\" text=\"发布\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) -> instance().getPostProductInfoActivity(iDevice)
                );
    }

    public static IAction newPostProductInfoAction(String info) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO, "index=\"1\" text=\"发布\"")
                .setCheckSucAction(new ICheckSucAction() {
                                       @Override
                                       public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                                           Point uiPoint = instance().getPostProductInfoActivity(iDevice).getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_INFO);
                                           adbBuilder.addClick(uiPoint)
                                                   .addText(info)
                                                   .send(iDevice.getDeviceId());
                                       }
                                   }
                );
    }

    public static IAction newGoPostProductMoneyAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO, "index=\"1\" text=\"发布\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addClick(instance().getPostProductInfoActivity(iDevice).getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_MONEY))
                                        .send(iDevice.getDeviceId())
                );
    }


    public static IAction newGoPostProductOtherAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_INFO, "index=\"1\" text=\"发布\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addClick(instance().getPostProductInfoActivity(iDevice).getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_INFO_OTHER))
                                        .send(iDevice.getDeviceId())
                );
    }



}
