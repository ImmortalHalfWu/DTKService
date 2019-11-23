package immotal.half.wu.appManager;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.cache.PagerPointCache;
import immotal.half.wu.appManager.pagers.processs.idlefish.posted.ProcessPostedProductCallBack;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AppManagerUtil {

    public static final String TAG = "AppManager";


    /**
     * @return 手机屏幕xml
     */
    public static String readUiInfo(
            @NotNull DeviceInfoBean deviceInfo,
            @NotNull ADBManager adb) {

        String fileName =
                deviceInfo.getDeviceId() +
                        deviceInfo.getDeviceDx();
        String filePath = FileUtils.DIR_PATH_XML + fileName + ".xml";

        String fileContent;
        return adb.androidUIXML(deviceInfo.getDeviceId(), fileName, filePath) ?
                (fileContent = FileUtils.readFile(filePath)) == null ? "" : fileContent.replaceAll("&#10;", "") : "";
    }


    /**
     * @return 获取xml中符合约束的point点，先取缓存
     */
    @org.jetbrains.annotations.NotNull
    public static @NotNull Map<String, Point> getUiPoint(
            @org.jetbrains.annotations.NotNull @NotNull String xmlString,
            @org.jetbrains.annotations.NotNull @NotNull Map<String, Map<String, String>> filterMap,
            @NotNull PagerInfoBean pagerInfoBean,
            @NotNull DeviceInfoBean deviceInfoBean,
            @NotNull String key) {

        Map<String, Point> point = PagerPointCache.instance().getPoint(pagerInfoBean, deviceInfoBean, key);
        try {
            return point.isEmpty() ? XMLUtil.findAllPointByAttrKeyValue(xmlString, filterMap) : point;
        } catch (DocumentException e) {
            return Collections.emptyMap();
        }
    }

    @org.jetbrains.annotations.NotNull
    public static @NotNull Map<String, Point> getUiPointWithSaveCache(
            @org.jetbrains.annotations.NotNull @NotNull String xmlString,
            @org.jetbrains.annotations.NotNull @NotNull Map<String, Map<String, String>> filterMap,
            @NotNull PagerInfoBean pagerInfoBean,
            @NotNull DeviceInfoBean deviceInfoBean,
            @NotNull String key) {

        Map<String, Point> uiPoint = getUiPoint(xmlString, filterMap, pagerInfoBean, deviceInfoBean, key);
        PagerPointCache.instance().putPoint(pagerInfoBean, deviceInfoBean, key, uiPoint);
        return uiPoint;
    }


    public static void processPostedProduct(
            @org.jetbrains.annotations.NotNull @NotNull String uiXmlString,
            @NotNull DeviceInfoBean deviceId,
            @org.jetbrains.annotations.Nullable @Nullable ProcessPostedProductCallBack callBack) {

        if (callBack == null) {
            return;
        }

        try {

            Element recyclerView = XMLUtil.findElementByNodeKeyValue(
                    uiXmlString,
                    "class",
                    "android.support.v7.widget.RecyclerView"
            );

            if (recyclerView == null) {
                callBack.notFound();
                return;
            }

            java.util.List<Element> items = XMLUtil.findAllElementByTagName(
                    recyclerView,
                    "node"
            );

            ArrayList<String> objects = new ArrayList<>();
            objects.add("曝光");
            objects.add("游戏");
            objects.add("我的问答");
            objects.add("宝贝图片");
            objects.add("¥");
            objects.add("留言");
            objects.add("浏览");
            objects.add("划重点");
            objects.add("+");
            objects.add("降价");
            objects.add("编辑");
            objects.add("已擦亮");
            objects.add("刚刚擦亮");
            objects.add("去发布");
            objects.add("再发个宝贝");


            List<Element> elements = XMLUtil.removeElementByAttrTextWithNull(
                    items,
                    objects
            );

            if (elements.size() <= 1) {
                callBack.notFound();
                return;
            }

            Element firstElement = elements.get(0);

            try {
                Integer.valueOf(firstElement.attributeValue(QName.get("content-desc")));
                elements.remove(0);
            } catch (Exception ignored) {
            }


            String nameElement = "";
            int priceElement = 0;
            Element refreshElement = null, moreElement;

            for (Element element : elements) {

                String value = element.attributeValue(QName.get("content-desc"));

                if (value.equals("更多") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    moreElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement, moreElement)) {
                        callBack.over();
                        return;
                    }
                    nameElement = "";
                    priceElement = 0;
                    continue;
                }

                if (value.equals("擦亮") && !FileUtils.isEmpty(nameElement) && priceElement != 0) {
                    refreshElement = element;
                    if (callBack.process(nameElement, priceElement, refreshElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                }

                try {
                    priceElement = Integer.valueOf(value);
                    if (!FileUtils.isEmpty(nameElement) && callBack.process(nameElement, priceElement)) {
                        callBack.over();
                        return;
                    }
                    continue;
                } catch (Exception ignored) {
                }

                nameElement = value;
                if (callBack.process(nameElement)) {
                    callBack.over();
                    return;
                }

            }

            callBack.over();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
