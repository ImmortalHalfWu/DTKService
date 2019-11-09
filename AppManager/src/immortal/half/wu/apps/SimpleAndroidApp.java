package immortal.half.wu.apps;

import immortal.half.wu.apps.IdleFish.sender.actions.PageActionHomeMy;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashSet;

public class SimpleAndroidApp<T> extends BaseAndroidApp<T> {

    private static SimpleAndroidApp simpleAndroidApp;

    public static <T> SimpleAndroidApp<T> getInstance() {
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
    public void refreshConnect() {

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
    public void isLogin(@NotNull PageActionHomeMy.IsLoginCallBack callBack) {
        callBack.isLogin(false);
    }

    @Override
    public void postProduct(T product) {

    }

    @Override
    public void deleteProduct(String name) {

    }

    @Override
    public void getPostedProductsName(@NotNull PostedProductNames.CallBack callBack) {
        callBack.names(new HashSet<>(0));
    }

    @Override
    public void getUserName(@NotNull PageActionHomeMy.UserInfoCallBack callBack) {
        callBack.result("", "");
    }

    @Override
    public void refreshPostedProduct() {

    }
}
