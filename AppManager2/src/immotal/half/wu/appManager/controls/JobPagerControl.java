package immotal.half.wu.appManager.controls;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import immortal.half.wu.LogUtil;
import immortal.half.wu.adbs.ADBManager;
import immortal.half.wu.executor.interfaces.IJobWithTimeOut;
import immotal.half.wu.appManager.AppManagerUtil;
import immotal.half.wu.appManager.pagers.beans.DeviceInfoBean;
import immotal.half.wu.appManager.pagers.intefaces.IPage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JobPagerControl<DoResultType> implements IJobWithTimeOut<DoResultType> {

    private final static int tryAgainCount = 3;

    private final @NotNull List<IPage<?>> iPages;
    private final @NotNull ADBManager adbManager;
    private final @NotNull DeviceInfoBean deviceInfoBean;
    private final Class<DoResultType> resultTypeClass;

    public static <T> IJobWithTimeOut<T> create(
            @NotNull ADBManager adbManager,
            @NotNull DeviceInfoBean deviceInfoBean) {

        return new JobPagerControl<T>(adbManager, deviceInfoBean){};
    }

    private JobPagerControl(
            @NotNull ADBManager adbManager,
            @NotNull DeviceInfoBean deviceInfoBean) {

        this.adbManager = adbManager;
        this.deviceInfoBean = deviceInfoBean;
        this.iPages = new ArrayList<>();

        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.resultTypeClass = (Class<DoResultType>) trueType;
    }

    public void addPager(@Nullable IPage<?> page) {
        if (page != null) {
            iPages.add(page);
        }
    }

    @Override
    public DoResultType run() throws Exception {

        LogUtil.d(AppManagerUtil.TAG, "开始轮询IPager任务，共" + tryAgainCount + "次，" + iPages);

        for (int i = 0; i < tryAgainCount; i++) {

            try {

                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务第" + (i + 1) + "次");
                Object o = looperIPager();
                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务结束，结果：" + o);
//                return resultTypeClass.cast(o);
                return (DoResultType)"123";

            } catch (Exception e) {
                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务第" + (i + 1) + "次时捕获异常：" + e.getMessage() + "并重试");
            }

        }

        throw new IllegalStateException("轮询IPager任务失败，未得到正确结果：" + iPages);

    }

    private @Nullable Object looperIPager() {

        String xml;
        IPage page;
        Object result = null;

        for (IPage iPage : iPages) {

            do {

                xml = AppManagerUtil.readUiInfo(deviceInfoBean, adbManager);
                page = iPage;

                if (!page.check(xml, deviceInfoBean, adbManager)) {
                    LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，check失败：" + iPage);
                    break;
                }

                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，check成功：" + iPage);
                result = page.doPageProcess(xml, deviceInfoBean, adbManager);
                LogUtil.d(AppManagerUtil.TAG, "轮询IPager任务，process成功：" + iPage);

//                if (result == null || (result instanceof Boolean && !((boolean) (result)))) {
//                    break;
//                }

            } while (!page.isComplete(xml, deviceInfoBean, adbManager));

        }

        return result;

    }
}
