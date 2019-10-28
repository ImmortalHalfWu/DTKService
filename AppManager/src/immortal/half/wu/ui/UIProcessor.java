package immortal.half.wu.ui;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;
import org.dom4j.DocumentException;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UIProcessor {

    public static String getTopActivity(String devicesId) {
        String topActivity = ADBManager.getInstance().findTopActivity(devicesId);
        return topActivity == null ? "" : topActivity;
    }

    public static Map<String, Point> getPointByUIXML(
            String devicesId,
            String fileName,
            PointFilterBean pointFilterBean
    ) {
        return getPointByUIXML(pointFilterBean, androidUIXMLHaveCache(devicesId, fileName));
    }

    public static Map<String, Point> getPointByUIXMLNoCache(
            String devicesId,
            String fileName,
            PointFilterBean pointFilterBean
    ) {
        return getPointByUIXML(pointFilterBean, androidUIXMLNoCache(devicesId, fileName));
    }

    private static Map<String, Point> getPointByUIXML(
            PointFilterBean pointFilterBean,
            String xmlString) {

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


    private static String androidUIXMLHaveCache(String deviceId, String fileName) {
        return androidUIXMLHaveCache(deviceId, fileName, true);
    }

    public static String androidUIXMLNoCache(String deviceId, String fileName) {
        return androidUIXMLHaveCache(deviceId, fileName, false);
    }

    private static String androidUIXMLHaveCache(String deviceId, String fileName, boolean findCache) {

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
