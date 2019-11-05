package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE;
import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.instance;

public class PageActionTagChoice {

    public static IAction newTagChoiceAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG, "class=\"android.widget.EditText\"")
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder
                                        .addClick(instance().getPostProductTagActivity(iDevice)
                                                .getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_CANCLE))
                                        .send(iDevice.getDeviceId())
                );
    }

}
