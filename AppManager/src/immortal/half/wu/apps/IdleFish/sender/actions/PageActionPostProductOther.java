package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

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

    public static IAction newChoiceOtherAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_OTHER, "index=\"3\" text=\"不讲价\"")
                .setCheckSucAction(new ICheckSucAction() {
                                       @Override
                                       public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                                           /*

    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW = "全新";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY = "不讲价";
    public final static String PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK = "确认";
                                            */
                                           IAndroidPager postProductOtherActivity = instance().getPostProductOtherActivity(iDevice);
                                           adbBuilder.addClick(postProductOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_NEW))
                                                   .addClick(postProductOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_HOLD_MONEY))
                                                   .addClick(postProductOtherActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_OTHER_OK))
                                                   .send(iDevice.getDeviceId());

                                       }
                                   }
                );
    }

}
