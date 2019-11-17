package immotal.half.wu.appManager.pagers.beans;

public class PagerInfoBean {

    private final String activityNamePath;
    private final String activityDoc;

    public PagerInfoBean(String activityName, String activityDoc) {
        this.activityNamePath = activityName;
        this.activityDoc = activityDoc;
    }

    public String getActivityNamePath() {
        return activityNamePath;
    }

    public String getActivityDoc() {
        return activityDoc;
    }

    @Override
    public String toString() {
        return "PagerInfoBean{" +
                "activityNamePath='" + activityNamePath + '\'' +
                ", activityDoc='" + activityDoc + '\'' +
                '}';
    }
}
