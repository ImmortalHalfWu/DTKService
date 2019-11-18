package immotal.half.wu.appManager.pagers.processs.idlefish.posted;

import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBBuilder;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.XMLUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class PostedPageProcess {

    private PostedPageProcess() {}

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addContentDesc("更多")
                    .next("更多")
                    .addContentDesc("编辑")
                    .next("编辑")
                    .create();

    public static BasePageProcess<ArrayList<String>> createGetPostedNamesPageProcess() {
        return new BasePageProcess<ArrayList<String>>() {
            @Override
            public ArrayList<String> doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                ArrayList<String> resultNames = new ArrayList<>();
                new SimpleProcessPostedProductCallBack(deviceInfo, xml) {
                    @Override
                    protected void over(boolean isOver, Set<String> names) {
                        if (isOver) {
                            names.remove("擦亮");
                            names.remove("更多");
                            LogUtil.i(AppManagerUtil.TAG, "获得已发布商品：" + names);
                            resultNames.addAll(names);
                        }
                    }
                }.start();
                return resultNames;
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };
    }


    public static BasePageProcess<Boolean> createDeleteProductPageProcess(String deleteName) {

        return new BasePageProcess<Boolean>() {
            @Override
            public Boolean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {

                final Boolean[] result = {false};

                new SimpleProcessPostedProductCallBack(deviceInfo, xml) {
                    @Override
                    public boolean process(String productName, int price, Element refresh, Element more) {
                        if (deleteName.startsWith(productName)) {
                            Point point = XMLUtil.getElementBoundsCenter(more);
                            new ADBBuilder().addClick(point).send(deviceId.getDeviceId());
                            result[0] = true;
                            return true;
                        }

                        return super.process(productName, price, refresh, more);
                    }

                    @Override
                    public void over() {
                    }

                    @Override
                    protected void over(boolean isOver, Set<String> names) {
                    }
                }.start();

                return result[0];
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };
    }

    public static BasePageProcess<Boolean> createRefreshProductPageProcess() {

        return new BasePageProcess<Boolean>() {
            Boolean result = false;
            @Override
            public Boolean doPageProcess(String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                new SimpleProcessPostedProductCallBack(deviceInfo, xml) {
                    @Override
                    public boolean process(String productName, int price, Element refresh) {
                        if (refresh != null) {
                            Point refreshCenter = XMLUtil.getElementBoundsCenter(refresh);
                            LogUtil.i(AppManagerUtil.TAG, "刷新商品：" + productName);
                            adb.createBuild().addClick(refreshCenter).send(deviceId.getDeviceId());
                            result = true;
                            return true;
                        }
                        return super.process(productName, price, null);
                    }

                    @Override
                    protected void over(boolean isOver, Set<String> names) {
                    }
                }.start();

                return result;
            }

            @Override
            public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
                return filter;
            }
        };

    }

}
