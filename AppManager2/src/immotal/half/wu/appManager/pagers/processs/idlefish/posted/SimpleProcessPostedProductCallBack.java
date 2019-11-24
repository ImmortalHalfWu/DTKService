package immotal.half.wu.appManager.pagers.processs.idlefish.posted;

import com.sun.istack.internal.NotNull;
import immortal.half.wu.adbs.ADBBuilder;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import org.dom4j.Element;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleProcessPostedProductCallBack implements ProcessPostedProductCallBack {

    @org.jetbrains.annotations.NotNull
    @NotNull
    protected final Set<String> names;
    final DeviceInfoBean deviceId;
    private final String xml;

    private int maxCount = 0;

    SimpleProcessPostedProductCallBack(DeviceInfoBean deviceId, String xml) {
        this.deviceId = deviceId;
        this.xml = xml;
        names = new HashSet<>();
    }

    public void start() {
        AppManagerUtil.processPostedProduct(
                xml,
                deviceId,
                this
        );
    }


    @Override
    public boolean process(String productName) {
        names.add(productName);
        return false;
    }

    @Override
    public void over() {
        if (maxCount == names.size()) {
            over(true, names);
            return;
        }
        maxCount = names.size();
        over(false, names);
        swipeNext();
        start();
    }

    protected abstract void over(boolean isOver, Set<String> names);

    @Override
    public boolean process(String productName, int price) {
        return false;
    }

    @Override
    public boolean process(String productName, int price, Element refresh) {
        return false;
    }

    @Override
    public boolean process(String productName, int price, Element refresh, Element more) {
        return false;
    }

    @Override
    public void notFound() {

    }

    private void swipeNext() {
        Point dxSize = deviceId.getDeviceDx();
        new ADBBuilder().addSwipe(
                new Point(dxSize.x / 2, dxSize.y - dxSize.y / 3),
                new Point(dxSize.x / 2, dxSize.y / 3),
                300)
                .send(deviceId.getDeviceId());
    }

}