package immotal.half.wu.appManager.pagers.processs.idlefish;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.XMLUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.beans.UserInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.awt.*;
import java.util.Map;

public class MyPagerProcess {

    private final static String PAGE_POINT_KEY_HOME_MY_POSTED = "我发布的";
    private final static String PAGE_POINT_KEY_HOME_MY_POSTED_NUM = "我发布的商品数量";
    private final static String PAGE_POINT_KEY_HOME_MY_USER_NAME = "用户名称";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addText(PAGE_POINT_KEY_HOME_MY_POSTED)
                    .next(PAGE_POINT_KEY_HOME_MY_POSTED)
                    .addResourceId("com.taobao.idlefish:id/entry_title")
                    .addIndex("0")
                    .next(PAGE_POINT_KEY_HOME_MY_USER_NAME)
                    .addIndex("2")
                    .addResourceId("com.taobao.idlefish:id/entry_sub_title")
                    .next(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                    .create();


    public static BasePageProcess<Boolean> createToPostedPageProcess() {
        return new BasePageProcess<Boolean>() {
            @Override
            public Boolean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_HOME_MY_POSTED);
                if (point != null) {
                    adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
                    return true;
                }
                return false;
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };
    }

    public static BasePageProcess<UserInfoBean> createGetUserInfoPageProcess() {

        return new BasePageProcess<UserInfoBean>() {
            @Override
            public UserInfoBean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {

                Map<String, String> textByUIXML = XMLUtil.getTextByUIXML(xml, filter);

                return new UserInfoBean(
                        textByUIXML.get(PAGE_POINT_KEY_HOME_MY_USER_NAME),
                        textByUIXML.get(PAGE_POINT_KEY_HOME_MY_POSTED_NUM)
                        );
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };

    }


}
