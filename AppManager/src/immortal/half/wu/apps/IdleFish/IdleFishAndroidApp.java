package immortal.half.wu.apps.IdleFish;

import immortal.half.wu.apps.BaseAndroidApp;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.sender.IdleFishActionController;
import immortal.half.wu.apps.IdleFish.sender.actions.PageActionHomeMy;
import immortal.half.wu.apps.impls.PostedProductNames;
import immortal.half.wu.apps.interfaces.IDevice;

public class IdleFishAndroidApp extends BaseAndroidApp<IdleFishProductBean> {

    public final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    private final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";

    private final IdleFishActionController controller;

    public IdleFishAndroidApp(IDevice deviceId) {
        super(deviceId, IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH);

        controller = new IdleFishActionController(deviceId, packageName, mainActivityPath);
//        startApp();
    }

    @Override
    public void isLogin(PageActionHomeMy.IsLoginCallBack callBack) {
        controller.isLogin(callBack);
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
    public void postProduct(IdleFishProductBean product) {
        controller.postProduct(product);
    }

    @Override
    public void deleteProduct(String name) {
        controller.deleteProduct(name);
    }

    @Override
    public void getPostedProductsName(PostedProductNames.CallBack callBack) {
        controller.getPostedProductsName(callBack);
    }

    @Override
    public void getUserName(PageActionHomeMy.UserInfoCallBack callBack) {
        controller.getUserName(callBack);
    }

    @Override
    public void refreshPostedProduct() {
        controller.refreshPostedProduct(deviceId);
    }
}
