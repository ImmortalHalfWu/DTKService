package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.impls.PostedProductRefresh;
import immortal.half.wu.apps.impls.PostedProductRemove;
import immortal.half.wu.apps.interfaces.IActionCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PageActionPosted {

    @NotNull
    public static IAction newFindPostedProductNamesAction(@Nullable IActionCallBack<List<String>> callBack) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POSTED, "content-desc=\"标题栏\"")
                .setCheckSucAction(new ICheckSucAction() {
                    @Override
                    public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                        if (callBack == null) {
                            return;
                        }
                        new PostedProductNames(iDevice, callBack).start();
                    }
                });
    }

    @NotNull
    public static IAction newRefreshPostedProductAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POSTED, "content-desc=\"标题栏\"")
                .setCheckSucAction(new ICheckSucAction() {
                    @Override
                    public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                        new PostedProductRefresh(iDevice).start();
                    }
                });
    }

    @NotNull
    public static IAction newRemovePostedProductAction(String productName) {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POSTED, "content-desc=\"标题栏\"")
                .setCheckSucAction(new ICheckSucAction() {
                    @Override
                    public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                        new PostedProductRemove(iDevice, productName).start();
                    }
                });
    }

}
