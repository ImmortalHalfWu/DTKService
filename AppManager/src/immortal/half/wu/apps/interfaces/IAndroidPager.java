package immortal.half.wu.apps.interfaces;

import immortal.half.wu.ui.PointFilterBean;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public interface IAndroidPager<T> {

    String getActivityName();

    PointFilterBean getPointFilter();

    Map<String, T> getUIPoint();

    @NotNull Point getUIPoint(String pointKey);

    boolean isResume();

}
