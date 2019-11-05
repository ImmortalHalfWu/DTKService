package immortal.half.wu.apps.IdleFish.sender.actions;

import immortal.half.wu.adbs.IADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerName;
import immortal.half.wu.apps.IdleFish.sender.IAction;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.impls.PostedProductRefresh;
import immortal.half.wu.apps.impls.PostedProductRemove;
import immortal.half.wu.apps.interfaces.IDevice;

public class PageActionPosted {

    public static IAction newFindPostedProductNamesAction(PostedProductNames.CallBack callBack) {
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

    public static IAction newRefreshPostedProductAction() {
        return SimpleAction.newInstanceXML(AndroidIdleFishPagerName.PAGER_NAME_POSTED, "content-desc=\"标题栏\"")
                .setCheckSucAction(new ICheckSucAction() {
                    @Override
                    public void checkSucAction(IDevice iDevice, IADBBuilder adbBuilder, AndroidIdleFishPagerName pagerName) {
                        new PostedProductRefresh(iDevice).start();
                    }
                });
    }

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
