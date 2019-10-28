package immortal.half.wu.apps.impls;

import immortal.half.wu.apps.SimpleProcessPostedProductCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import immortal.half.wu.ui.XMLUtil;
import org.dom4j.Element;

import java.util.Set;

public class PostedProductRefresh extends SimpleProcessPostedProductCallBack {

    public PostedProductRefresh(IDevice deviceId) {
        super(deviceId);
    }

    @Override
    protected void over(boolean isOver, Set<String> names) { }

    @Override
    public boolean process(String productName, int price, Element refresh) {
        if (refresh == null) {
            return super.process(productName, price, null);
        }

        System.out.println("点击擦亮商品：" + XMLUtil.getElementBoundsCenter(refresh));
//        new ADBBuilder().addClick(XMLUtil.getElementBoundsCenter(refresh));

        return super.process(productName, price, refresh);
    }


}
