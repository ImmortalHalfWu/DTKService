package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionTagChoice {

    @NotNull
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

    @NotNull
    public static IAction newTagChoiceAction(String tag) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POST_PRODUCT_TAG, "class=\"android.widget.EditText\"")
                .setCheckSucAction(new ICheckSucAction() {
                                       @Override
                                       public void checkSucAction(@NotNull IDevice iDevice, @NotNull IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                                           IAndroidPager postProductTagActivity = instance().getPostProductTagActivity(iDevice);
                                           adbBuilder.addClick(postProductTagActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT))
                                                   .addText(tag)
                                                   .addClick(postProductTagActivity.getUIPoint(PAGE_POINT_KEY_POST_PRODUCT_TAG_FIRST_LINE))
                                                   .send(iDevice.getDeviceId());
                                       }
                                   }
                );
    }

}
