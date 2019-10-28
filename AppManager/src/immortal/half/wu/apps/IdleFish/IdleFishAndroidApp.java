package immortal.half.wu.apps.IdleFish;

import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.apps.BaseAndroidApp;
import immortal.half.wu.apps.IdleFish.beans.IdleFishProductBean;
import immortal.half.wu.apps.IdleFish.pagers.AndroidIdleFishPagerFactory;
import immortal.half.wu.apps.interfaces.IAndroidPager;

import java.awt.*;

public class IdleFishAndroidApp extends BaseAndroidApp<IdleFishProductBean> {

    public final static String IDLE_FISH_PACKAGE_NAME = "com.taobao.idlefish";
    public final static String IDLE_FISH_MAIN_PATH = "com.taobao.fleamarket.home.activity.MainActivity";

    public IdleFishAndroidApp(String deviceId) {
        super(deviceId, IDLE_FISH_PACKAGE_NAME, IDLE_FISH_MAIN_PATH);
    }

    @Override
    public boolean isLogin() {

        IAndroidPager homeActivity = AndroidIdleFishPagerFactory.instance().getHomeActivity(getDeviceId());

        if (homeActivity.isResume()) {
            Point uiPoint = homeActivity.getUIPoint(AndroidIdleFishPagerFactory.PAGE_POINT_KEY_HOME_MY);
            new ADBBuilder().addClick(uiPoint).send(getDeviceId());
            return AndroidIdleFishPagerFactory.instance().getLoginActivity(getDeviceId()).isResume();
        }

        return false;
    }

    @Override
    public void postProduct(IdleFishProductBean product) {

    }

    @Override
    public String[] getPostedProductsName() {
        return new String[0];
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void refreshPostedProduct() {

    }
}
