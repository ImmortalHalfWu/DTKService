package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory.*;

public class PageActionImgChoice {

    @NotNull
    public static IAction newGoImgProcessAction() {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE)
                .setCheckSucAction(
                        (iDevice, adbBuilder, pagerName) ->
                                adbBuilder.addClick(instance().getImgChoiceActivity(iDevice).getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK))
                                        .send(iDevice.getDeviceId())
                );
    }

    @NotNull
    public static IAction newChoiceImgToProcessAction(int imgCount) {
        return SimpleAction.newInstanceName(AndroidIdleFishPagerName.PAGER_NAME_IMAGE_CHOICE)
                .setCheckSucAction(new ICheckSucAction() {
                    @Override
                    public void checkSucAction(@NotNull IDevice iDevice, @NotNull IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                        int mImgCount = imgCount;
                        IAndroidPager imgChoiceActivity = instance().getImgChoiceActivity(iDevice);
                        if (PAGE_POINT_KEY_IMG_CHOICE.size() == 0) {
                            return;
                        }
                        for (int i = PAGE_POINT_KEY_IMG_CHOICE.size() - 1; i >= 0 && mImgCount > 0; i--, mImgCount--) {
                            Point uiPoint = imgChoiceActivity.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE.get(i));
                            adbBuilder.addClick(uiPoint);
                        }
                        adbBuilder.addClick(imgChoiceActivity.getUIPoint(PAGE_POINT_KEY_IMG_CHOICE_OK))
                                .send(iDevice.getDeviceId());
                    }
                });
    }

}
