package immotal.half.wu.appManager.pagers.processs;

import immortal.half.wu.adbs.ADBManager;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.beans.PagerInfoBean;
import immotal.half.wu.appManager.pagers.intefaces.IPageProcess;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BasePageProcess<DoResultType> implements IPageProcess<DoResultType> {

    @Override
    public boolean checkPager(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return false;
    }

    @Override
    public boolean isComplete(String xml, PagerInfoBean pagerInfo, DeviceInfoBean deviceInfo, ADBManager adb) {
        return true;
    }

    @Override
    public Class<DoResultType> getResultType() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        return  (Class<DoResultType>) trueType;
    }
}
