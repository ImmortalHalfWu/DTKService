package immortal.half.wu.apps.tasks;

public interface AndroidPagerListener {

    boolean needProcess(String deviceId, String packageName, String activityPath, String activityName);
    void onPagerResume(String deviceId, String packageName, String activityPath, String activityName);
    void onPagerStop(String deviceId, String packageName, String activityPath, String activityName);

}
