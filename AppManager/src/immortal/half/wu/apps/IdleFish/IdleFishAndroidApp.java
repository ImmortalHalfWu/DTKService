package immortal.half.wu.apps.IdleFish;

import immortal.half.wu.apps.BaseAndroidApp;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.beans.UserInfoBean;
import immortal.half.wu.apps.IdleFish.sender.IdleFishActionController;
import immortal.half.wu.apps.interfaces.IActionCallBack;
import immortal.half.wu.apps.interfaces.IDevice;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IdleFishAndroidApp extends BaseAndroidApp {

    public final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";

    @NotNull
    private final IdleFishActionController controller;

    public IdleFishAndroidApp(@NotNull IDevice deviceId) {
        super(deviceId, IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH);

        controller = new IdleFishActionController(deviceId, packageName, mainActivityPath);
//        startApp();
    }

    @Override
    public void refreshConnect(IActionCallBack<Boolean> callBack) {
        controller.init(callBack);
    }

    @Override
    public boolean startApp() {

//        super.startApp();
//
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        IAndroidPager androidPager = AndroidIdleFishPagerFactory.instance().getAndroidPager(deviceId, AndroidIdleFishPagerName.PAGER_NAME_MAIN);
//        new ADBBuilder()
//                .addClick(androidPager.getUIPoint(PAGE_POINT_KEY_HOME_UPDATE))
//                .send(deviceId.getDeviceId());
//
//        return toMainActivity();

//        controller.toMainActivity();
        return toMainActivity();
    }

    @Override
    public boolean toMainActivity() {
        controller.toMainActivity();
        return true;
    }

    @Override
    public void isLogin(IActionCallBack<Boolean> callBack) {
        controller.isLogin(callBack);
    }

    @Override
    public <T> void postProduct(T productBean, IActionCallBack<T> callBack) {
        if (productBean instanceof IdleFishProductBean) {
            controller.postProduct(
                    (IdleFishProductBean)productBean,
                    (IActionCallBack<IdleFishProductBean>)callBack);
        }
    }

    @Override
    public void deleteProduct(@NotNull String name, IActionCallBack<String> callBack) {
        controller.deleteProduct(name, callBack);
    }

    @Override
    public void getPostedProductsName(@NotNull IActionCallBack<List<String>> callBack) {
        controller.getPostedProductsName(callBack);
    }

    @Override
    public <UserInfo> void getUserName(IActionCallBack<UserInfo> callBack) {
        try {
            IActionCallBack<UserInfoBean> userInfoCallBack = (IActionCallBack<UserInfoBean>) callBack;
            controller.getUserName(userInfoCallBack);
        } catch (Exception e) {
            callBack.onError(e);
        }
    }

    @Override
    public void refreshPostedProduct() {
        controller.refreshPostedProduct(deviceId);
    }
}
