package immortal.half.wu.apps.impls;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.SimpleProcessPostedProductCallBack;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.apps.interfaces.IDevice;
import immortal.half.wu.ui.XMLUtil;
import org.dom4j.Element;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Set;

public class PostedProductRemove extends SimpleProcessPostedProductCallBack {

    private final String removeProduct;

    public PostedProductRemove(IDevice deviceId, String removeProductName) {
        super(deviceId);
        this.removeProduct = removeProductName;
    }

    @Override
    public boolean process(@NotNull String productName, int price, Element refresh, @NotNull Element more) {

        if (removeProduct.startsWith(productName)) {
            Point point = XMLUtil.getElementBoundsCenter(more);
            new ADBBuilder().addClick(point).send(deviceId.getDeviceId());

            IAndroidPager postedMoreActivity = AndroidIdleFishPagerFactory.instance().getPostedMoreActivity(deviceId);
            if (postedMoreActivity.isResume()) {
                Point uiPoint = postedMoreActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POSTED_DELETE);
                new ADBBuilder().addClick(uiPoint).delayTime(100).send(deviceId.getDeviceId());
            }

            IAndroidPager postedDeleteOkActivity = AndroidIdleFishPagerFactory.instance().getPostedDeleteOkActivity(deviceId);
            if (postedDeleteOkActivity.isResume()) {
                Point uiPoint = postedDeleteOkActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POSTED_DELETE_OK);
                new ADBBuilder().addClick(uiPoint).send(deviceId.getDeviceId());
            }

            return true;
        }

        return super.process(productName, price, refresh, more);
    }

    @Override
    protected void over(boolean isOver, Set<String> names) {

    }


}
