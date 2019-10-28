package immortal.half.wu.apps.tasks;


import immortal.half.wu.adbs.ADBManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class AndroidPagerTask implements Runnable {

    private static AndroidPagerTask instance;
    private final Set<String> listenerDeviceIds;
    private final ExecutorService executor;

    private final AndroidPagerTaskCallBack callBack;

    private AndroidPagerTask(ExecutorService executor, AndroidPagerTaskCallBack callBack) {
        this.executor = executor;
        this.callBack = callBack;
        listenerDeviceIds = Collections.synchronizedSet(new HashSet<>());
        executor.execute(this);
    }

    public static void init(ExecutorService executor, AndroidPagerTaskCallBack callBack) {
        if (instance == null) {
            synchronized (AndroidPagerTask.class) {
                if (instance == null) {
                    instance = new AndroidPagerTask(executor, callBack);
                }
            }
        }
    }

    public static AndroidPagerTask getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AndroidPagerTask not init(Executor)");
        }
        return instance;
    }

    @Override
    public void run() {

        try {

            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (callBack == null) {
                    continue;
                }

                if (listenerDeviceIds.size() == 0) {
                    continue;
                }

                try {
                    for (String key : listenerDeviceIds) {

                        String topActivity = ADBManager.getInstance().findTopActivityPath(key);
                        String packageName, activityPath, activityName;
                        /*
                        mResumedActivity: ActivityRecord{69d26d4 u0 com.taobao.idlefish/.post.activity.publishEntry.PublishEntryActivity t844}
                          ResumedActivity: ActivityRecord{69d26d4 u0 com.taobao.idlefish/.post.activity.publishEntry.PublishEntryActivity t844}
                             */
                            String[] split = topActivity.split("/");

                            String[] s1 = split[0].split(" ");
                            packageName = s1[s1.length - 1];

                            s1 = split[1].split(" ");
                            activityPath = s1[0];

                            s1 = activityPath.split("\\.");
                            activityName = s1[s1.length - 1];

                            callBack.onPagerResume(key, packageName, activityPath, activityName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!executor.isShutdown()) {
                executor.execute(this);
            }
        }

    }

    void addListenDevice(String deviceId) {
        if (deviceId != null && deviceId.length() > 0) {
            listenerDeviceIds.add(deviceId);
        }
    }

    void removeListenDevice(String deviceId) {
        if (deviceId != null && deviceId.length() > 0) {
            listenerDeviceIds.add(deviceId);
        }
    }

    interface AndroidPagerTaskCallBack {
        void onPagerResume(String deviceId, String packageName, String activityPath, String activityName);
    }

}
