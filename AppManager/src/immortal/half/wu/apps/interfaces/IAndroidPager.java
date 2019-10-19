package immortal.half.wu.apps.interfaces;

import immortal.half.wu.ui.PointFilterBean;

import java.awt.*;
import java.util.Map;

public interface IAndroidPager {

    String getActivityName();
    PointFilterBean getPointFilter();
    Map<String, Point> getUIPoint();
    Point getUIPoint(String pointKey);
    boolean isResume();

}
