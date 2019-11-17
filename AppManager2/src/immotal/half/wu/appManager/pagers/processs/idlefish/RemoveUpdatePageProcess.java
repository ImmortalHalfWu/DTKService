package immotal.half.wu.appManager.pagers.processs.idlefish;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.PointFilterBuilder;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.processs.BasePageProcess;

import java.awt.*;
import java.util.Map;

public class RemoveUpdatePageProcess extends BasePageProcess<Boolean> {

    private final static String PAGE_POINT_KEY_HOME_UPDATE = "暂不升级";

    private final static Map<String, Map<String, String>> filter = new PointFilterBuilder()
            .addText(PAGE_POINT_KEY_HOME_UPDATE)
            .addResourceId("com.taobao.idlefish:id/left")
            .next(PAGE_POINT_KEY_HOME_UPDATE)
            .create();

    @Override
    public Map<String, Map<String, String>> getUiFilter(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return filter;
    }

    @Override
    public Boolean doPageProcess(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {

        Point point = AppManagerUtil.getUiPointWithSaveCache(
                xml, filter, pagerInfo, deviceInfo, PAGE_POINT_KEY_HOME_UPDATE
        ).get(PAGE_POINT_KEY_HOME_UPDATE);

        if (point != null) {
            adb.createBuild().addClick(point).send(deviceInfo.getDeviceId());
            return true;
        }

        return true;
    }
}
