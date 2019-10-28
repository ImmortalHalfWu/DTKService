package immortal.half.wu.apps.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndroidPagerManager implements AndroidPagerTask.AndroidPagerTaskCallBack {

    private static AndroidPagerManager instance;
    private final ExecutorService executor;
    private final Map<String, AndroidPagerSender> pagerListenerHashMap;

    private AndroidPagerManager() {

        executor = Executors.newCachedThreadPool();
        pagerListenerHashMap = Collections.synchronizedMap(new HashMap<>());

        AndroidPagerTask.init(executor, this);

    }

    public static AndroidPagerManager getInstance() {
        if (instance == null) {
            synchronized (AndroidPagerManager.class) {
                if (instance == null) {
                    instance = new AndroidPagerManager();
                }
            }
        }
        return instance;
    }

    public void registerPagerListener(String deviceId, AndroidPagerListener listener) {

        AndroidPagerSender androidPagerSender = pagerListenerHashMap.get(deviceId);

        if (androidPagerSender == null) {
            androidPagerSender = new AndroidPagerSender(executor);
            pagerListenerHashMap.put(deviceId, androidPagerSender);
            AndroidPagerTask.getInstance().addListenDevice(deviceId);
        }
        androidPagerSender.addListener(listener);

    }

    public void unRegisterPagerListener(String deviceId, AndroidPagerListener listener) {

        AndroidPagerSender androidPagerSender = pagerListenerHashMap.get(deviceId);

        if (androidPagerSender == null) {
            return;
        }

        androidPagerSender.removeListener(listener);
        if (androidPagerSender.getListenerSize() == 0) {
            AndroidPagerTask.getInstance().removeListenDevice(deviceId);
            pagerListenerHashMap.remove(deviceId);
        }
    }

    @Override
    public void onPagerResume(String deviceId, String packageName, String activityPath, String activityName) {
        AndroidPagerTask.AndroidPagerTaskCallBack pagerTaskCallBack = pagerListenerHashMap.get(deviceId);
        if (pagerTaskCallBack != null) {
            pagerTaskCallBack.onPagerResume(
                    deviceId, packageName, activityPath, activityName
            );
        }
    }
}
