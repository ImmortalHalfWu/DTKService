package immortal.half.wu.ui;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UIProcessor {

    @NotNull
    public static String getTopActivity(String devicesId) {
        String topActivity = ADBManager.getInstance().findTopActivityName(devicesId);
        return topActivity == null ? "" : topActivity;
    }

    @NotNull
    public static Map<String, Point> getPointByUIXML(
            String devicesId,
            @NotNull String fileName,
            @NotNull PointFilterBean pointFilterBean
    ) {
        return getPointByUIXML(pointFilterBean, androidUIXMLHaveCache(devicesId, fileName));
    }

    @NotNull
    public static Map<String, Point> getPointByUIXMLNoCache(
            String devicesId,
            @NotNull String fileName,
            @NotNull PointFilterBean pointFilterBean
    ) {
        return getPointByUIXML(pointFilterBean, androidUIXMLNoCache(devicesId, fileName));
    }

    @NotNull
    private static Map<String, Point> getPointByUIXML(
            @NotNull PointFilterBean pointFilterBean,
            @NotNull String xmlString) {

        if (!FileUtils.isEmpty(xmlString) && pointFilterBean.getPointFilter().size() > 0) {

            try {

                return XMLUtil.findAllPointByAttrKeyValue(
                        XMLUtil.findRootElement(xmlString),
                        new HashMap<>(pointFilterBean.getPointFilter().size()),
                        pointFilterBean.getPointFilter()
                );

            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }

        return new HashMap<>(0);
    }

    @NotNull
    public static Map<String, String> getTextByUIXML(
            @NotNull PointFilterBean pointFilterBean,
            @NotNull String xmlString) {

        if (!FileUtils.isEmpty(xmlString) && pointFilterBean.getPointFilter().size() > 0) {

            try {

                return XMLUtil.findTextByAttrKeyValues(
                        XMLUtil.findRootElement(xmlString),
                        new HashMap<>(pointFilterBean.getPointFilter().size()),
                        pointFilterBean.getPointFilter()
                );

            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }

        return new HashMap<>(0);
    }


    public static String androidUIXMLHaveCache(String deviceId, @NotNull String fileName) {
        return androidUIXMLHaveCache(deviceId, fileName, true);
    }

    public static String androidUIXMLNoCache(String deviceId, @NotNull String fileName) {
        return androidUIXMLHaveCache(deviceId, fileName, false);
    }

    private static String androidUIXMLHaveCache(String deviceId, @NotNull String fileName, boolean findCache) {

        String filePath = FileUtils.DIR_PATH_XML + fileName + ".xml";

        if (findCache && new File(filePath).exists()) {
            return FileUtils.readFile(filePath).replaceAll("&#10;", "");
        }

        return ADBManager
                .getInstance()
                .androidUIXML(deviceId, fileName, filePath) ?
                FileUtils.readFile(filePath).replaceAll("&#10;", "") :
                "";
    }

}
