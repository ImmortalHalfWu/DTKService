package immortal.half.wu.apps;

import immortal.half.wu.apps.interfaces.IActionCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAndroidApp extends BaseAndroidApp {

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

            @NotNull
            @Override
            public Point getDxSize() {
                return new Point(0, 0);
            }
        }, SimpleAndroidApp.class.getName(), SimpleAndroidApp.class.getName());
    }


    @Override
    public void refreshConnect(IActionCallBack<Boolean> callBack) {
        callBack.onError(new IllegalStateException("SimpleAndroidApp refreshConnect"));
    }

    @Override
    public boolean startApp() {
        return false;
    }

    @Override
    public boolean toMainActivity() {
        return false;
    }

    @Override
    public void isLogin(IActionCallBack<Boolean> callBack) {
        callBack.onComplete(false);
    }

    @Override
    public <T> void postProduct(T productBean, IActionCallBack<T> callBack) {
        callBack.onError(new IllegalStateException("SimpleAndroidApp postProduct"));
    }

    @Override
    public void deleteProduct(String name, IActionCallBack<String> callBack) {
        callBack.onError(new IllegalStateException("SimpleAndroidApp deleteProduct"));
    }

    @Override
    public void getPostedProductsName(IActionCallBack<List<String>> callBack) {
        callBack.onComplete(new ArrayList<>(0));
    }

    @Override
    public <UserInfo> void getUserName(IActionCallBack<UserInfo> callBack) {
        callBack.onError(new IllegalStateException("SimpleAndroidApp getUserName"));
    }

    @Override
    public void refreshPostedProduct() {

    }
}
