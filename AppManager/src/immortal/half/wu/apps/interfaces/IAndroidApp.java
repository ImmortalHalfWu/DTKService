package immortal.half.wu.apps.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IAndroidApp {

    String getDeviceId();

    @NotNull IDevice getDevice();

    String getPackageName();

    String getMainActivityPath();

    void refreshConnect(IActionCallBack<Boolean> callBack);

    boolean startApp();

    boolean toMainActivity();

    void isLogin(IActionCallBack<Boolean> callBack);

    <T> void postProduct(T productBean, IActionCallBack<T> callBack);

    void deleteProduct(String name, IActionCallBack<String> callBack);

    void getPostedProductsName(IActionCallBack<List<String>> callBack);

    <UserInfo> void getUserName(IActionCallBack<UserInfo> callBack);

    void refreshPostedProduct();


}
