package immortal.half.wu.ui;

import immortal.half.wu.FileUtils;
import immortal.half.wu.adbs.ADBManager;
import org.dom4j.DocumentException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UIProcessor {

    public static String getTopActivity(String devicesId) {
        String topActivity = ADBManager.getInstance().findTopActivity(devicesId);
        return topActivity == null ? "" : topActivity;
    }

    public static Map<String, Point> getPointByUIXML(String devicesId, PointFilterBean pointFilterBean) {
        return getPointByUIXML(pointFilterBean, androidUIXML(devicesId));
    }

    public static Map<String, Point> getPointByUIXML(
            PointFilterBean pointFilterBean,
            String xmlString) {

        if (!FileUtils.isEmpty(xmlString)) {

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

    public static String androidUIXML(String devicesId) {

        String fileName = devicesId + ".xml";
        String saveFilePath = FileUtils.DIR_PATH_XML + fileName;

        return ADBManager
                .getInstance()
                .androidUIXML(devicesId, fileName, saveFilePath) ?
                FileUtils.readFile(saveFilePath).replaceAll("&#10;", "") :
                "";
    }
}
