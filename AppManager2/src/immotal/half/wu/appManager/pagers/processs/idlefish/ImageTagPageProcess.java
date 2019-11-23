package immotal.half.wu.appManager.pagers.processs.idlefish;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.XMLUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class ImageTagPageProcess {

    private final static String PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT = "输入框";

    private final static Map<String, Map<String, String>> filter =
            new PointFilterBuilder()
                    .addClass("android.widget.EditText")
                    .next(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT)
                    .create();

    public static BasePageProcess<Boolean> createInputTag(String tagName) {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(String xml, @NotNull Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point point = pointMap.get(PAGE_POINT_KEY_POST_PRODUCT_TAG_EDIT);
                if (point != null) {
                    adb.createBuild().addClick(point).addText(tagName).send(deviceInfo.getDeviceId());
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

    public static BasePageProcess<Boolean> createChoiceTag() {
        return new BasePageProcess<Boolean>() {
            @NotNull
            @Override
            public Boolean doPageProcess(@NotNull String xml, Map<String, Point> pointMap, PagerInfoBean pagerInfo, @NotNull DeviceInfoBean deviceInfo, @NotNull ADBManager adb) {
                Point firstLinePoint = XMLUtil.findPointByAttrKeyValueEndWith(
                        xml,
                        "text",
                        "自定义标签");

                if (firstLinePoint != null) {
                    adb.createBuild().addClick(firstLinePoint).send(deviceInfo.getDeviceId());
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



}
