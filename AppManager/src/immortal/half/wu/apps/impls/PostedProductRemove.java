package immortal.half.wu.apps.impls;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.SimpleProcessPostedProductCallBack;
import immortal.half.wu.apps.interfaces.IAndroidPager;
import immortal.half.wu.ui.XMLUtil;
import org.dom4j.Element;

import java.awt.*;
import java.util.Set;

public class PostedProductRemove extends SimpleProcessPostedProductCallBack {

    private final String removeProduct;

    public PostedProductRemove(String deviceId, String removeProductName) {
        super(deviceId);
        this.removeProduct = removeProductName;
    }

    @Override
    public boolean process(String productName, int price, Element refresh, Element more) {

        if (removeProduct.startsWith(productName)) {
            Point point = XMLUtil.getElementBoundsCenter(more);
            new ADBBuilder().addClick(point).send(deviceId);

            IAndroidPager postedMoreActivity = AndroidIdleFishPagerFactory.instance().getPostedMoreActivity(deviceId);
            if (postedMoreActivity.isResume()) {
                Point uiPoint = postedMoreActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POSTED_DELETE);
                new ADBBuilder().addClick(uiPoint).delayTime(100).send(deviceId);
            }

            IAndroidPager postedDeleteOkActivity = AndroidIdleFishPagerFactory.instance().getPostedDeleteOkActivity(deviceId);
            if (postedDeleteOkActivity.isResume()) {
                Point uiPoint = postedDeleteOkActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_POSTED_DELETE_OK);
                new ADBBuilder().addClick(uiPoint).send(deviceId);
            }

            return true;
        }

        return super.process(productName, price, refresh, more);
    }

    @Override
    protected void over(boolean isOver, Set<String> names) {

    }


}
