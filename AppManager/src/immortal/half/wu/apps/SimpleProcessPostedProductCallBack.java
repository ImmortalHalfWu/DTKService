package immortal.half.wu.apps;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.interfaces.IDevice;
import immortal.half.wu.apps.interfaces.ProcessPostedProductCallBack;
import org.dom4j.Element;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleProcessPostedProductCallBack implements ProcessPostedProductCallBack {

    protected final Set<String> names;
    protected final IDevice deviceId;

    private int maxCount = 0;

    public SimpleProcessPostedProductCallBack(IDevice deviceId) {
        this.deviceId = deviceId;
        names = new HashSet<>();
    }

    public void start() {
        AndroidIdleFishPagerFactory.instance().processPostedProduct(
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
        Point dxSize = deviceId.getDxSize();
        new ADBBuilder().addSwipe(
                new Point(dxSize.x / 2, dxSize.y - dxSize.y / 3),
                new Point(dxSize.x / 2, dxSize.y / 3),
                300)
                .send(deviceId.getDeviceId());
    }

}
