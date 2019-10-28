package immortal.half.wu.apps;

import immortal.half.wu.apps.interfaces.IDevice;

import java.awt.*;

public class SimpleAndroidApp extends BaseAndroidApp<Object> {

    private static SimpleAndroidApp simpleAndroidApp;

    public static SimpleAndroidApp getInstance() {
        if (simpleAndroidApp == null) {
            synchronized (SimpleAndroidApp.class) {
                if (simpleAndroidApp == null) {
                    simpleAndroidApp = new SimpleAndroidApp();
                }
            }
        }
        return simpleAndroidApp;
    }

    private SimpleAndroidApp() {
        super(new IDevice() {
            @Override
            public String getDeviceId() {
                return SimpleAndroidApp.class.getName();
            }

            @Override
            public Point getDxSize() {
                return new Point(0, 0);
            }
        }, SimpleAndroidApp.class.getName(), SimpleAndroidApp.class.getName());
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void postProduct(Object product) {

    }

    @Override
    public String[] getPostedProductsName() {
        return new String[0];
    }

    @Override
    public String getUserName() {
        return "";
    }

    @Override
    public void refreshPostedProduct() {

    }
}
