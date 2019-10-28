package immortal.half.wu.apps.tasks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class AndroidPagerSender implements Runnable, AndroidPagerTask.AndroidPagerTaskCallBack {

    private final Set<AndroidPagerListener> pagerListeners;
    private final ExecutorService executor;

    private String deviceId;
    private String packageName;
    private String activityPath;
    private String activityName;

    private String lastDeviceId;
    private String lastPackageName;
    private String lastActivityPath;
    private String lastActivityName;

    AndroidPagerSender(ExecutorService executor) {
        pagerListeners = new HashSet<>();
        this.executor = executor;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (AndroidPagerListener pagerListener :
                pagerListeners) {

            try {
                if (!pagerListener.needProcess(
                        deviceId,
                        packageName,
                        activityPath,
                        activityName
                )) {
                    continue;
                }
            } catch (Exception ignored) {}

            if (!stringIsNull(lastActivityName) && !lastActivityName.equals(activityName)) {
                try {
                    pagerListener.onPagerStop(
                            lastDeviceId,
                            lastPackageName,
                            lastActivityPath,
                            lastActivityName);
                } catch (Exception ignored) {}
            }

            try {
                pagerListener.onPagerResume(
                        deviceId,
                        packageName,
                        activityPath,
                        activityName
                );
            } catch (Exception ignored) {}

        }
    }

    @Override
    public void onPagerResume(String deviceId, String packageName, String activityPath, String activityName) {

        if (activityName.equals(this.activityName)) {
            return;
        }

        System.out.print(this.activityName + "______" + lastActivityName);
        lastDeviceId = this.deviceId;
        lastPackageName = this.packageName;
        lastActivityName = this.activityName;
        lastActivityPath = this.activityPath;

        this.deviceId = deviceId;
        this.packageName = packageName;
        this.activityName = activityName;
        this.activityPath = activityPath;

        if (pagerListeners.size() == 0 || executor == null || executor.isShutdown()) {
            return;
        }

        executor.execute(this);
    }

    private boolean stringIsNull(String text) {
        return text == null || text.length() == 0;
    }

    public void addListener(AndroidPagerListener listener) {
        if (listener != null) {
            pagerListeners.add(listener);
        }
    }

    public void removeListener(AndroidPagerListener listener) {
        if (listener != null) {
            pagerListeners.remove(listener);
        }
    }

    int getListenerSize() {
        return packageName.length();
    }

}